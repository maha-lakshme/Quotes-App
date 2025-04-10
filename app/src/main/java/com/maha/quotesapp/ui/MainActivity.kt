// File: app/src/main/java/com/maha/quotesapp/ui/MainActivity.kt
package com.maha.quotesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maha.quotesapp.R
import com.maha.quotesapp.data.repository.ContentRepository
import com.maha.quotesapp.databinding.ActivityMainBinding
import com.maha.quotesapp.network.QuotesService
import com.maha.quotesapp.network.RetrofitInstance
import com.maha.quotesapp.ui.adapter.QuotesAdapter
import com.maha.quotesapp.ui.viewmodel.ContentViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ContentViewModel
    private lateinit var quotesAdapter: QuotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using Data Binding.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Setup toolbar (already defined in XML with centered title)
        setSupportActionBar(binding.topAppBar)

        // Set status bar color to match primary/background color of ToolBar
        window.statusBarColor = ContextCompat.getColor(this, R.color.pastel_primary)

        // Setup RecyclerView.
        quotesAdapter = QuotesAdapter()
        binding.rvQuotes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = quotesAdapter
        }

        // Use the real QuotesService instance from RetrofitInstance.
        val quotesService: QuotesService = RetrofitInstance.quotesService
        val repository = ContentRepository(quotesService)

        // Initialize the ViewModel.
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return ContentViewModel(repository) as T
            }
        }).get(ContentViewModel::class.java)

        // Observe LiveData and update UI.
        viewModel.quotes.observe(this) { quotes ->
            quotesAdapter.updateData(quotes)
        }

        // Trigger loading of quotes.
        viewModel.loadQuotes()
    }
}

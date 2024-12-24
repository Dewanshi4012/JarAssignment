package com.myjar.jarassignment

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myjar.jarassignment.data.model.ComputerItem
import com.myjar.jarassignment.databinding.ActivityMainBinding
import com.myjar.jarassignment.ui.adapter.ItemAdapter
import com.myjar.jarassignment.ui.vm.JarViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<JarViewModel>()
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi(binding)
        observeFlows()
    }

    private fun observeFlows() {
        lifecycleScope.launchWhenResumed {
            viewModel.listStringData.collectLatest {
                adapter.submitList(it)
            }
        }

        lifecycleScope.launchWhenResumed {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.navigateToItem.filterNotNull().collectLatest {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("itemId", it)
                    startActivity(intent)
                }
            }
        }
    }

    private fun setupUi(binding: ActivityMainBinding) {
        val recyclerView: RecyclerView = binding.itemList
        adapter = ItemAdapter { selectedItem ->
            viewModel.navigateToItemDetail(selectedItem.id)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }
}
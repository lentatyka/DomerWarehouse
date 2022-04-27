package com.lentatyka.domerwarehouse.presentation.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.lentatyka.domerwarehouse.DomerApp
import com.lentatyka.domerwarehouse.R
import com.lentatyka.domerwarehouse.data.main.background.worker.FirebaseWorker
import com.lentatyka.domerwarehouse.data.main.background.worker.SampleWorkerFactory
import com.lentatyka.domerwarehouse.databinding.ActivityMainBinding
import com.lentatyka.domerwarehouse.di.main.MainComponent
import com.lentatyka.domerwarehouse.presentation.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainComponent: MainComponent

    lateinit var mainAdapter: MainAdapter

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModeFactory)[MainViewModel::class.java]
    }

    @Inject
    lateinit var sampleWorkerFactory: SampleWorkerFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        mainComponent = (application as DomerApp).appComponent.mainComponent().create()
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        setAdapter()
        setViewModel()
    }

    private fun setAdapter() {
        mainAdapter = MainAdapter()
        val decoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        binding.databaseRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
            addItemDecoration(decoration)
        }
    }

    private fun setViewModel() {
        viewModel.productList.observe(this){
            mainAdapter.submitList(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isIconifiedByDefault = false
            isFocusable = true
            requestFocusFromTouch()


            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.search(newText)
                    return true
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
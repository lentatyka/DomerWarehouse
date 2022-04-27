package com.lentatyka.domerwarehouse.presentation.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.auth.FirebaseAuth
import com.lentatyka.domerwarehouse.DomerApp
import com.lentatyka.domerwarehouse.R
import com.lentatyka.domerwarehouse.data.main.background.worker.FirebaseWorker
import com.lentatyka.domerwarehouse.data.main.background.worker.SampleWorkerFactory
import com.lentatyka.domerwarehouse.databinding.ActivityMainBinding
import com.lentatyka.domerwarehouse.di.main.MainComponent
import com.lentatyka.domerwarehouse.presentation.ViewModelFactory
import com.lentatyka.domerwarehouse.presentation.login.LoginActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainComponent: MainComponent

    lateinit var mainAdapter: MainAdapter

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var fbAuth: FirebaseAuth

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
        if (fbAuth.currentUser == null){
            //user are not authorized. GOTO LoginActivity
            startLoginActivity()
        }
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        setAdapter()
        setViewModel()
    }

    private fun startLoginActivity() {
        Intent(this, LoginActivity::class.java).also {
            startActivity(it)
            finish()
        }
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
            binding.numbers = it.size
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout ->{
                fbAuth.signOut()
                startLoginActivity()
            }
            R.id.update ->{
                loadDatabase()
            }
        }
        return true
    }

    private fun loadDatabase() {
        WorkManager.getInstance(this).enqueue(
            OneTimeWorkRequestBuilder<FirebaseWorker>().build()
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
package com.lentatyka.domerwarehouse.presentation.main.product

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lentatyka.domerwarehouse.R
import com.lentatyka.domerwarehouse.databinding.FragmentProductBinding
import com.lentatyka.domerwarehouse.presentation.ViewModelFactory
import com.lentatyka.domerwarehouse.presentation.main.MainActivity
import javax.inject.Inject

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    private val viewModel: ProductViewModel by lazy {
        ViewModelProvider(this, viewModeFactory)[ProductViewModel::class.java]
    }

    lateinit var productAdapter: ProductAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setViewModel()
    }

    private fun setViewModel() {
        viewModel.productList.observe(viewLifecycleOwner) {
            productAdapter.submitList(it)
        }
    }

    private fun setAdapter() {
        productAdapter = ProductAdapter()
        binding.databaseRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            isIconifiedByDefault = false
            isFocusable = true
            requestFocusFromTouch()


            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.search(newText)
                    return true
                }
            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }


}
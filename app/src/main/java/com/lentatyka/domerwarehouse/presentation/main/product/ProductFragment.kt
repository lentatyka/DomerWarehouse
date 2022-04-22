package com.lentatyka.domerwarehouse.presentation.main.product

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lentatyka.domerwarehouse.databinding.FragmentProductBinding
import com.lentatyka.domerwarehouse.presentation.ViewModelFactory
import com.lentatyka.domerwarehouse.presentation.main.MainActivity
import javax.inject.Inject

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModeFactory:ViewModelFactory
    private val viewModel: ProductViewModel by lazy {
        ViewModelProvider(this, viewModeFactory)[ProductViewModel::class.java]
    }

    lateinit var productAdapter: ProductAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
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
        viewModel.productList.observe(viewLifecycleOwner){
            Log.d("TAG", "--> ${it.size}")
            productAdapter.submitList(it)
        }
        viewModel.test()
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
}
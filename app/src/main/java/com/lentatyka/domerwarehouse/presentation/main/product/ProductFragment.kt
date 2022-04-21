package com.lentatyka.domerwarehouse.presentation.main.product

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.lentatyka.domerwarehouse.R
import com.lentatyka.domerwarehouse.presentation.ViewModelFactory
import com.lentatyka.domerwarehouse.presentation.main.MainActivity
import javax.inject.Inject

class ProductFragment : Fragment() {

    @Inject
    lateinit var viewModeFactory:ViewModelFactory
    private val viewModel: ProductViewModel by lazy {
        ViewModelProvider(this, viewModeFactory)[ProductViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.test()

    }
}
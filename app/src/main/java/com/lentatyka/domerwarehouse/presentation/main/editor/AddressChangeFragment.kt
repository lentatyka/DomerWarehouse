package com.lentatyka.domerwarehouse.presentation.main.editor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lentatyka.domerwarehouse.R
import com.lentatyka.domerwarehouse.presentation.main.MainActivity
import javax.inject.Inject


class AddressChangeFragment : Fragment() {

    private var id: String? = null

    @Inject
    lateinit var viewModeFactory: AddressChangeVMFactory.Factory
    private val viewModel: AddressChangeViewModel by viewModels{
        viewModeFactory.create(id!!)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            id = it.getString(ID)
        }
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_address_change, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        const val ID = "id"

        @JvmStatic
        fun newInstance(id: String) =
            AddressChangeFragment().apply {
                arguments = Bundle().apply {
                    putString(ID, id)
                }
            }
    }
}
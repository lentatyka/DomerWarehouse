package com.lentatyka.domerwarehouse.presentation.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.lentatyka.domerwarehouse.R
import com.lentatyka.domerwarehouse.common.Response
import com.lentatyka.domerwarehouse.databinding.FragmentSignUpBinding
import com.lentatyka.domerwarehouse.presentation.login.viewmodel.LoginViewModel
import com.lentatyka.domerwarehouse.presentation.login.viewmodel.SignUpViewModel
import javax.inject.Inject


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SignUpViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as LoginActivity).loginComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)

        binding.viewmodel = viewModel as SignUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Loading -> {
                    //showLoading
                }
                is Response.Error -> {
                    showToast(response.message)
                }
                is Response.Success -> {
                    showSnackBar()
                }
            }
        }

        binding.btnLogIn.setOnClickListener {
            showSnackBar()
        }
    }

    private fun showSnackBar() {
        Snackbar.make(
            requireView(),
            getString(R.string.account_success_creation),
            Snackbar.LENGTH_INDEFINITE,
        ).setAction(R.string.ok){
            requireActivity().supportFragmentManager.popBackStack()
        }.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RegistrationFragment.
         */
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }
}
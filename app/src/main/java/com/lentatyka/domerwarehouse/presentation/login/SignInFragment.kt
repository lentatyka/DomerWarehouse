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
import com.lentatyka.domerwarehouse.R
import com.lentatyka.domerwarehouse.common.Response
import com.lentatyka.domerwarehouse.databinding.FragmentSignInBinding
import com.lentatyka.domerwarehouse.presentation.login.viewmodel.LoginViewModel
import com.lentatyka.domerwarehouse.presentation.login.viewmodel.SignInViewModel
import javax.inject.Inject

private const val ARG_PARAM_EMAIL = "email"

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private var email: String? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SignInViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as LoginActivity).loginComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_PARAM_EMAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(
            layoutInflater, container, false
        )
        binding.viewmodel = viewModel as SignInViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        binding.btnRegistration.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.login_fragment_container, SignUpFragment.newInstance()
                )
                .addToBackStack(null)
                .commit()
        }
    }

    private fun observeViewModel() {
        viewModel.response.observe(viewLifecycleOwner) { responce ->
            when (responce) {
                is Response.Loading -> {
                    //showLoading
                }
                is Response.Error -> {
                    showToast(responce.message)
                }
                is Response.Success -> {
                    Log.d("TAG", "answer-> ${responce.data}")
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(email: String) =
            SignInFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_EMAIL, email)
                }
            }
    }
}
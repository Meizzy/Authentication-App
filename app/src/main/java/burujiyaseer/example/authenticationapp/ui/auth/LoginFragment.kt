package burujiyaseer.example.authenticationapp.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import burujiyaseer.example.authenticationapp.R
import burujiyaseer.example.authenticationapp.data.network.Resource
import burujiyaseer.example.authenticationapp.databinding.FragmentLoginBinding
import burujiyaseer.example.authenticationapp.ui.base.BaseFragment
import burujiyaseer.example.authenticationapp.ui.enable
import burujiyaseer.example.authenticationapp.ui.handleApiError
import burujiyaseer.example.authenticationapp.ui.home.HomeActivity
import burujiyaseer.example.authenticationapp.ui.startNewActivity
import burujiyaseer.example.authenticationapp.ui.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "LoginFragment"

@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner) {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAccessTokens(
                            it.value.user.access_token!!,
                            it.value.user.refresh_token!!
                        )
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }
                Resource.Loading -> binding.progressbar.visible(true)
            }
        }

        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            login()
        }

        binding.textViewRegisterNow.setOnClickListener {
            Log.d(TAG, "registerNow clicked")
//            requireActivity().startNewActivity(HomeActivity::class.java)
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun login() {
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        viewModel.login(email, password)
    }
}
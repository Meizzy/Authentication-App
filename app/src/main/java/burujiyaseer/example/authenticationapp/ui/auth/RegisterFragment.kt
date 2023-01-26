package burujiyaseer.example.authenticationapp.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import burujiyaseer.example.authenticationapp.R
import burujiyaseer.example.authenticationapp.data.network.Resource
import burujiyaseer.example.authenticationapp.databinding.FragmentRegisterBinding
import burujiyaseer.example.authenticationapp.ui.base.BaseFragment
import burujiyaseer.example.authenticationapp.ui.enable
import burujiyaseer.example.authenticationapp.ui.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "RegisterFragment"
@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    FragmentRegisterBinding::inflate
) {
    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewLoginNow.setOnClickListener {
            Log.d(TAG,"loginNow clicked")
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
        binding.progressbar.visible(false)
        binding.buttonRegister.enable(false)

        lifecycleScope.launch {
            viewModel.signupFlow.collect{
                binding.progressbar.visible(it is Resource.Loading)
                when (it) {
                    is Resource.Success -> {
                        findNavController().apply {
                            navigate(R.id.action_registrationFragment_to_homeFragment)
                            popBackStack()
                        }
                    }
                    is Resource.Failure -> {
                        Log.d(TAG, "failed to register with ${it.exception.printStackTrace()}")
                        Toast.makeText(context,it.exception.message, Toast.LENGTH_LONG).show()
                    }
                    Resource.Loading -> binding.progressbar.visible(true)
                    else -> {}
                }
            }
        }

        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonRegister.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonRegister.setOnClickListener {
            viewModel.signup(binding.editTextTextPersonName.text.toString().trim(), binding.editTextTextEmailAddress.text.toString().trim(), binding.editTextTextEmailAddress.text.toString().trim())
        }
    }
}
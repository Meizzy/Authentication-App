package burujiyaseer.example.authenticationapp.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import burujiyaseer.example.authenticationapp.R
import burujiyaseer.example.authenticationapp.databinding.FragmentRegisterBinding
import burujiyaseer.example.authenticationapp.ui.base.BaseFragment

private const val TAG = "RegisterFragment"
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    FragmentRegisterBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewLoginNow.setOnClickListener {
            Log.d(TAG,"loginNow clicked")
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
    }
}
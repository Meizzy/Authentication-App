package burujiyaseer.example.authenticationapp.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import burujiyaseer.example.authenticationapp.R
import burujiyaseer.example.authenticationapp.databinding.FragmentHomeBinding
import burujiyaseer.example.authenticationapp.ui.base.BaseFragment
import burujiyaseer.example.authenticationapp.ui.visible
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressbar.visible(false)

        updateUI(viewModel.currentUser)

        binding.buttonLogout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }
    }




    private fun updateUI(user: FirebaseUser?) {
        with(binding) {
            textViewId.text = user?.uid
            textViewName.text = user?.displayName ?: ""
            textViewEmail.text = user?.email ?: ""
        }
    }
}
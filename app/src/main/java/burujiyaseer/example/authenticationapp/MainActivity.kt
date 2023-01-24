package burujiyaseer.example.authenticationapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import burujiyaseer.example.authenticationapp.data.UserPreferences
import burujiyaseer.example.authenticationapp.ui.auth.AuthActivity
import burujiyaseer.example.authenticationapp.ui.home.HomeActivity
import burujiyaseer.example.authenticationapp.ui.startNewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userPreferences = UserPreferences(this)

        userPreferences.accessToken.asLiveData().observe(this) {
            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        }
    }

}
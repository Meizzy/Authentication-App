package burujiyaseer.example.authenticationapp.ui.base

import androidx.lifecycle.ViewModel
import burujiyaseer.example.authenticationapp.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {

    suspend fun logout() = withContext(Dispatchers.IO) { repository.logout() }

}
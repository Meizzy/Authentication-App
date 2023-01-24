package burujiyaseer.example.authenticationapp.data.repository

import burujiyaseer.example.authenticationapp.data.network.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi
) : BaseRepository(api) {

    suspend fun getUser() = safeApiCall { api.getUser() }

}
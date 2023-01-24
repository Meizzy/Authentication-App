package burujiyaseer.example.authenticationapp.data.repository

import burujiyaseer.example.authenticationapp.data.network.BaseApi
import burujiyaseer.example.authenticationapp.data.network.SafeApiCall

abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() = safeApiCall {
        api.logout()
    }
}
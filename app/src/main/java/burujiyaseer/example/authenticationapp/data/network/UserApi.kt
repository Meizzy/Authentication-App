package burujiyaseer.example.authenticationapp.data.network

import burujiyaseer.example.authenticationapp.data.responses.LoginResponse
import retrofit2.http.GET

interface UserApi : BaseApi{
    @GET("user")
    suspend fun getUser(): LoginResponse
}
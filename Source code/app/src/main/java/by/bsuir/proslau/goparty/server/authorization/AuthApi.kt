package by.bsuir.proslau.goparty.server.authorization

import by.bsuir.proslau.goparty.entity.authorization.User
import by.bsuir.proslau.goparty.logic.authorization.AuthInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import okhttp3.ResponseBody
import retrofit2.http.Header
import retrofit2.http.GET



interface AuthApi {
    @POST("/signin")
    fun checkLogin(@Body info: AuthInfo): Call<ServerAnswer>

    @POST("/signup")
    fun SignUp(@Body user: User): Call<ResponseBody>

    @POST("/verification")
    fun checkVerification(@Header("Authorization") token: String): Call<ServerAnswer>

    @GET("/user")
    fun getUser(@Header("Authorization") token: String): Call<User>

}
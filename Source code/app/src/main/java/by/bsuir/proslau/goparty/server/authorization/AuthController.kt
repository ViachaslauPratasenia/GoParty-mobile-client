package by.bsuir.proslau.goparty.server.authorization

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthController {
    internal val BASE_URL = "http://localhost:8008/"
    private var service: AuthApi? = null

    val api: AuthApi
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }
            val builder = Retrofit.Builder()
            builder.client(httpClient.build())


            val retrofit = builder
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            service = retrofit.create(AuthApi::class.java)
            return service as AuthApi
        }
}

package by.bsuir.proslau.goparty.server.events

import com.google.gson.GsonBuilder

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object EventController {
    val BASE_URL = "http://localhost:8008/"
    private var eventApi: EventApi? = null

    fun getApi(token: String): EventApi {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Authorization", token)
                    .addHeader("Content-Type", "application/json")
                    .build()
                return chain.proceed(request)
            }
        })
        val builder = Retrofit.Builder()
        builder.client(httpClient.build())


        val retrofit = builder
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        eventApi = retrofit.create(EventApi::class.java)
        return eventApi as EventApi
    }
}
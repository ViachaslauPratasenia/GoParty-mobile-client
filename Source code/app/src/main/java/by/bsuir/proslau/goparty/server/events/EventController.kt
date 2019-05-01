package by.bsuir.proslau.goparty.server.events

import com.google.gson.GsonBuilder

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object EventController {
    val BASE_URL = "http://10.0.2.2:8080/api/"
    private var eventApi: EventApi? = null

    fun getApi(token: String): EventApi {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val httpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Authorization", token)
                    //.header("Authorization", "1234567890")
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
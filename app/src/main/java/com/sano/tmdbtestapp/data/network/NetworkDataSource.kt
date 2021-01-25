package com.sano.tmdbtestapp.data.network

import com.sano.tmdbtestapp.BuildConfig
import com.sano.tmdbtestapp.data.network.pojo.MovieDetailsModel
import com.sano.tmdbtestapp.data.network.pojo.PagedResponse
import com.sano.tmdbtestapp.data.network.service.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO remove from git
private const val API_KEY = ""

class NetworkDataSource : INetworkDataSource {

    private val movieService: MovieService

    init {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", API_KEY).build()
                request.url(url)
                return@addInterceptor chain.proceed(request.build())
            }.build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .apply {
                movieService = create(MovieService::class.java)
            }
    }

    override suspend fun discoverMovies(page: Int): PagedResponse? {
        return movieService.discoverMovies(page)
    }

    override suspend fun searchMovies(query: String): PagedResponse? {
        return movieService.searchMovies(query)
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsModel? {
        return movieService.getMovieDetails(movieId)
    }

}
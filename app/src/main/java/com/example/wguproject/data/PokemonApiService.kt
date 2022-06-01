package com.example.wguproject.data
import kotlinx.coroutines.Deferred
import com.example.wguproject.data.response.pokemonlist.PokemonListResponse
import com.example.wguproject.data.response.pokemon.PokemonResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

//https://pokeapi.co/api/v2/pokemon/

interface PokemonApiService {
    //Allows for changing the limit to the api call
    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: String = "100000"
    ): Deferred<PokemonListResponse>

    companion object {
        operator fun invoke() : PokemonApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()
            return Retrofit.Builder().client(okHttpClient)
                .baseUrl("https://pokeapi.co/api/v2/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokemonApiService::class.java)
        }
    }

    @GET
    fun getPokemon(
        @Url url:String
    ): Deferred<PokemonResponse>

}
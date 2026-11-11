package com.example.conjuquizspike

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = Retrofit.Builder()
            .baseUrl("https://conjugaison.my-french.com/")
            .addConverterFactory(
                Json { ignoreUnknownKeys = true}.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(ConjugationApi::class.java)

        lifecycleScope.launch {
            for (verb in listOf("parler", "être", "aller", "préférer", "farterblotch")){
                try {
                    val r = api.conjugate(verb)
                    Log.d("SPIKE", "$verb présent: ${r.moods["indicatif"]?.get("présent")}")
                } catch (e: Exception) {
                    Log.e("SPIKE", "$verb failed: ${e.message}")
                }
            }
        }
    }
}
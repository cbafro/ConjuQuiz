package com.example.conjuquizspike

import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Query

@Serializable
data class ConjugationResponse (
    val verb: String,
    val group: String? = null,
    val auxiliary: String? = null,
    val moods: Map<String, Map<String, List<String>>>
)
interface ConjugationApi {
    @GET("api-public.php")
    suspend fun conjugate(@Query("v") verb: String): ConjugationResponse
}
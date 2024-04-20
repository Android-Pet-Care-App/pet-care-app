package com.example.petcareapp.data


import org.json.JSONObject
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiService {

    private val client = OkHttpClient()
    private val TAG = "ApiService"

    suspend fun fetchAnimalTypes(apiKey: String, onComplete: (List<String>) -> Unit, onError: (Exception) -> Unit) = withContext(Dispatchers.IO) {

        try {
            val request = Request.Builder()
                .url("https://pet-data.p.rapidapi.com/animalType?orderBy=asc&limit=500")
                .get()
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", "pet-data.p.rapidapi.com")
                .build()

            Log.d(TAG, "Sending request to ${request.url}")

            val response = client.newCall(request).execute()

            Log.d(TAG, "Response received with code: ${response.code}")

            if (response.isSuccessful) {
                response.body?.let { responseBody ->
                    val jsonData = responseBody.string()
                    Log.d(TAG, "Response JSON: $jsonData")

                    val jsonObject = JSONObject(jsonData)
                    val data = jsonObject.getJSONArray("data")
                    val animalTypes = mutableListOf<String>()
                    for (i in 0 until data.length()) {
                        animalTypes.add(data.getString(i))
                    }
                    withContext(Dispatchers.Main) {
                        onComplete(animalTypes)
                    }
                } ?: run {
                    val errMsg = "Response body is null"
                    Log.e(TAG, errMsg)
                    throw Exception(errMsg)
                }
            } else {
                val errMsg = "Failed to fetch data: ${response.message}"
                Log.e(TAG, errMsg)
                throw Exception(errMsg)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error during API call", e)
            withContext(Dispatchers.Main) {
                onError(e)
            }
        }
    }
}

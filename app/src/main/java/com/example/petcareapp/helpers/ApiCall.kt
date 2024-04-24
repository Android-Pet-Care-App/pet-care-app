package com.example.petcareapp.helpers

import org.json.JSONObject
import okhttp3.OkHttpClient
import okhttp3.Request
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray

class ApiService {
    private val client = OkHttpClient()
    private val TAG = "ApiService"

    suspend fun fetchDogBreeds(
        onComplete: (List<String>) -> Unit,
        onError: (Exception) -> Unit
    ) = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url("https://dog.ceo/api/breeds/list/all")
                .get()
                .build()

            Log.d(TAG, "Sending request to ${request.url}")

            val response = client.newCall(request).execute()

            Log.d(TAG, "Response received with code: ${response.code}")

            if (response.isSuccessful) {
                response.body?.let { responseBody ->
                    val jsonData = responseBody.string()
                    Log.d(TAG, "Response JSON: $jsonData")


                    val jsonObject = JSONObject(jsonData)
                    val data = jsonObject.getJSONObject("message")
                    val keysList = mutableListOf<String>()
                    val keysIterator = data.keys()

                    while (keysIterator.hasNext()) {
                        val key = keysIterator.next()
                        keysList.add(key)
                    }

                    withContext(Dispatchers.Main) {
                        onComplete(keysList)
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

    suspend fun fetchCatBreeds(
        onComplete: (List<String>) -> Unit,
        onError: (Exception) -> Unit
    ) = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url("https://api.thecatapi.com/v1/breeds")
                .get()
                .build()

            Log.d(TAG, "Sending request to ${request.url}")

            val response = client.newCall(request).execute()

            Log.d(TAG, "Response received with code: ${response.code}")

            if (response.isSuccessful) {
                response.body?.let { responseBody ->
                    val jsonData = responseBody.string()
                    Log.d(TAG, "Response JSON: $jsonData")

                    val list = JSONArray(jsonData)
                    val catsBreeds = mutableListOf<String>()

                    for (i in 0 until list.length()) {
                        val name = list.getJSONObject(i).getString("name")
                        catsBreeds.add(name)
                    }

                    withContext(Dispatchers.Main) {
                        onComplete(catsBreeds)
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

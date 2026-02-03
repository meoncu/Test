package com.example.fuelprices

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

class FuelPriceRepository(
    private val client: OkHttpClient = OkHttpClient()
) {
    suspend fun fetchPrices(context: Context): List<FuelPriceItem> = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url(BuildConfig.FUEL_API_URL)
            .build()

        val responseBody = runCatching {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    error("HTTP ${response.code}")
                }
                response.body?.string().orEmpty()
            }
        }.getOrElse {
            context.assets.open("sample_prices.json").bufferedReader().use { it.readText() }
        }

        parsePayload(responseBody)
    }

    private fun parsePayload(payload: String): List<FuelPriceItem> {
        val trimmed = payload.trim()
        if (trimmed.isEmpty()) return emptyList()

        val root = if (trimmed.startsWith("{")) {
            JSONObject(trimmed).optJSONArray("data")
        } else {
            JSONArray(trimmed)
        }

        val array = root ?: JSONArray()
        return (0 until array.length()).mapNotNull { index ->
            val item = array.optJSONObject(index) ?: return@mapNotNull null
            FuelPriceItem(
                city = item.optString("city", "Bilinmiyor"),
                fuelType = item.optString("fuelType", "Bilinmiyor"),
                price = item.optString("price", "-"),
                updatedAt = item.optString("updatedAt", "-")
            )
        }
    }
}

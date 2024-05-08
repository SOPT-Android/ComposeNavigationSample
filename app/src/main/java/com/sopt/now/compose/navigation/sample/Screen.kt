package com.sopt.now.compose.navigation.sample

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.Serializable

// Define a home destination that doesn't take any arguments
sealed class Screen {
    @Serializable
    object Home

    // Define a profile destination that takes an ID
    @Serializable
    data class Profile(val id: String)

    @Serializable
    data class SearchParameters(
        val searchQuery: String,
        val filters: List<String>
    ) : java.io.Serializable

    @Serializable
    data class Search(
        val parameters: SearchParameters
    ) : java.io.Serializable
}

val searchParametersType = object : NavType<Screen.SearchParameters>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): Screen.SearchParameters? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getSerializable(key, Screen.SearchParameters::class.java)
        } else {
            bundle.getSerializable(key) as? Screen.SearchParameters
        }
    }

    override fun parseValue(value: String): Screen.SearchParameters {
        return Screen.SearchParameters(value, emptyList())
    }

    override fun put(bundle: Bundle, key: String, value: Screen.SearchParameters) {
        bundle.putSerializable(key, value)
    }

}
package com.raven.news

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions

@SuppressLint("RestrictedApi")
fun NavController.navigateWithAnimation(route: String, args: Bundle?, navOptions: NavOptions? = null) {
    findDestination(route)?.id?.let { destinationId ->
        val navigationOptions = navOptions ?: NavOptions.Builder()
            .build()
        navigate(resId = destinationId, args = args, navOptions = navigationOptions)
    }
}
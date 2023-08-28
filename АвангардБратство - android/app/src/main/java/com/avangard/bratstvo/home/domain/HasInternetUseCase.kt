package com.avangard.bratstvo.home.domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log

class HasInternetUseCase(private val context: Context) {

    operator fun invoke(): Boolean {
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            Log.i("myLog", "cm = $cm")
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                val netInfo = cm.activeNetworkInfo
                Log.i("myLog", "netInfo = $netInfo")
                Log.i("myLog", "netInfo = ${netInfo?.isConnected}")
                //should check null because in airplane mode it will be null
                netInfo != null && netInfo.isConnected
            } else {
                val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
                return capabilities != null && (
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                    )
            }
        } catch (e: NullPointerException) {
        }

        return false
    }
}
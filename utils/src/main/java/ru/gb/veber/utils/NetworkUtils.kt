package ru.gb.veber.utils

import android.net.ConnectivityManager
import android.net.NetworkInfo

fun isOnline(connectivityManager: ConnectivityManager): Boolean {
    val netInfo: NetworkInfo?
    netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}

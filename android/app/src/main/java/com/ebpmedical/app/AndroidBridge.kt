package com.ebpmedical.app

import android.app.Activity
import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

class AndroidBridge(
    private val context: Context,
    private val apiEndpoint: String
) {
    @JavascriptInterface
    fun getApiEndpoint(): String = apiEndpoint

    @JavascriptInterface
    fun showToast(message: String) {
        (context as? Activity)?.runOnUiThread {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    @JavascriptInterface
    fun getPlatform(): String = "android"

    @JavascriptInterface
    fun getAppVersion(): String = "1.0.0"
}

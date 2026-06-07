package eu.kanade.tachiyomi

import okhttp3.Request
import okhttp3.Headers

fun GET(url: String, headers: Headers = Headers.Builder().build()): Request {
    return Request.Builder().url(url).headers(headers).build()
}

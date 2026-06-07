package eu.kanade.tachiyomi.network

import okhttp3.Request
import okhttp3.Headers

// Genera una petición HTTP estándar tipo GET usando la librería OkHttp3
fun GET(url: String, headers: Headers = Headers.Builder().build()): Request {
    return Request.Builder().url(url).headers(headers).build()
}

package eu.kanade.tachiyomi.source

import okhttp3.Headers
import okhttp3.Request
import okhttp3.Response

// --- 1. CLASES BASE DE FUENTES ---
abstract class Source {
    abstract val id: Long
    abstract val name: String
    abstract val lang: String
}

abstract class HttpSource : Source() {
    abstract val baseUrl: String
    open val supportsLatest: Boolean = true
    open val headers: Headers = Headers.Builder().build()

    abstract fun popularMangaRequest(page: Int): Request
    abstract fun popularMangaParse(response: Response): eu.kanade.tachiyomi.source.model.MangasPage
    abstract fun latestUpdatesRequest(page: Int): Request
    abstract fun latestUpdatesParse(response: Response): eu.kanade.tachiyomi.source.model.MangasPage
    abstract fun searchMangaRequest(page: Int, query: String, filters: eu.kanade.tachiyomi.source.model.FilterList): Request
    abstract fun searchMangaParse(response: Response): eu.kanade.tachiyomi.source.model.MangasPage
    abstract fun mangaDetailsParse(response: Response): eu.kanade.tachiyomi.source.model.SManga
    abstract fun chapterListParse(response: Response): List<eu.kanade.tachiyomi.source.model.SChapter>
    abstract fun pageListRequest(chapter: eu.kanade.tachiyomi.source.model.SChapter): Request
    abstract fun pageListParse(response: Response): List<eu.kanade.tachiyomi.source.model.Page>
    abstract fun imageUrlParse(response: Response): String
}

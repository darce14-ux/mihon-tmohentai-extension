package eu.kanade.tachiyomi

import okhttp3.Headers
import okhttp3.Request
import okhttp3.Response

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
    abstract fun popularMangaParse(response: Response): MangasPage
    abstract fun latestUpdatesRequest(page: Int): Request
    abstract fun latestUpdatesParse(response: Response): MangasPage
    abstract fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request
    abstract fun searchMangaParse(response: Response): MangasPage
    abstract fun mangaDetailsParse(response: Response): SManga
    abstract fun chapterListParse(response: Response): List<SChapter>
    abstract fun pageListRequest(chapter: SChapter): Request
    abstract fun pageListParse(response: Response): List<Page>
    abstract fun imageUrlParse(response: Response): String
}

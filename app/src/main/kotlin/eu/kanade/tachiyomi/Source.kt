package eu.kanade.tachiyomi.source.online

import okhttp3.Headers
import okhttp3.Request
import okhttp3.Response
// Importamos los modelos que están en el otro paquete para que HttpSource los conozca
import eu.kanade.tachiyomi.source.model.Page
import eu.kanade.tachiyomi.source.model.SChapter
import eu.kanade.tachiyomi.source.model.SManga
import eu.kanade.tachiyomi.source.model.MangasPage
import eu.kanade.tachiyomi.source.model.FilterList

// Clase base para cualquier fuente de mangas
abstract class Source {
    abstract val id: Long
    abstract val name: String
    abstract val lang: String
}

// Clase especializada para fuentes que obtienen datos de internet (como TMOHentai)
abstract class HttpSource : Source() {
    abstract val baseUrl: String
    open val supportsLatest: Boolean = true
    open val headers: Headers = Headers.Builder().build()

    // Todos estos métodos son los que tu TMOHentai.kt sobreescribe (override)
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

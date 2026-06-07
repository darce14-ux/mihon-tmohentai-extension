package eu.kanade.tachiyomi.extension.es.tmohentai

import eu.kanade.tachiyomi.network.GET
import eu.kanade.tachiyomi.source.model.FilterList
import eu.kanade.tachiyomi.source.model.MangasPage
import eu.kanade.tachiyomi.source.model.SManga
import eu.kanade.tachiyomi.source.model.SChapter
import eu.kanade.tachiyomi.source.model.Page
import eu.kanade.tachiyomi.source.online.HttpSource
import okhttp3.Request
import okhttp3.Response
import kotlinx.serialization.json.*
import org.jsoup.Jsoup

class TMOHentai : HttpSource() {
    override val name = "TMOHentai"
    override val baseUrl = "https://tmohentai.app"
    override val lang = "es"
    override val supportsLatest = true

    // Búsqueda y Catálogo
    override fun popularMangaRequest(page: Int): Request = GET("$baseUrl/library?page=$page", headers)
    override fun latestUpdatesRequest(page: Int): Request = GET("$baseUrl/library?order=latest&page=$page", headers)
    
    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        // Formato básico de búsqueda por palabra clave
        return GET("$baseUrl/library?search=$query&page=$page", headers)
    }

    override fun popularMangaParse(response: Response): MangasPage = parseMangaList(response)
    override fun latestUpdatesParse(response: Response): MangasPage = parseMangaList(response)
    override fun searchMangaParse(response: Response): MangasPage = parseMangaList(response)

    private fun parseMangaList(response: Response): MangasPage {
        val document = Jsoup.parse(response.body.string())
        val mangas = document.select("div.manga-card").map { element ->
            SManga.create().apply {
                // Extrae el ID y la ruta base desde el enlace de la tarjeta
                url = element.select("a").attr("href") 
                title = element.select("h5.manga-title").text()
                thumbnail_url = element.select("img").attr("src")
            }
        }
        val hasNextPage = document.select("a[rel=next]").isNotEmpty()
        return MangasPage(mangas, hasNextPage)
    }

    // Detalles del Manga
    override fun mangaDetailsParse(response: Response): SManga {
        val document = Jsoup.parse(response.body.string())
        return SManga.create().apply {
            title = document.select("h1.manga-title").text()
            description = document.select("p.manga-description").text()
            genre = document.select("span.badge-info").joinToString { it.text() }
            status = SManga.COMPLETED
        }
    }

    // Capítulos (En este tipo de sitios, la obra completa suele ser un único capítulo)
    override fun chapterListParse(response: Response): List<SChapter> {
        val document = Jsoup.parse(response.body.string())
        val chapter = SChapter.create().apply {
            // Se usa la misma URL de la obra o el ID del lector asociado
            url = response.request.url.toString().replace(baseUrl, "")
            name = "Capítulo Único"
            date_upload = 0L
        }
        return listOf(chapter)
    }

    // Extracción de Páginas (Utilizando el endpoint JSON que descubrimos)
    override fun pageListRequest(chapter: SChapter): Request {
        val id = chapter.url.substringAfter("/library/").substringBefore("/")
        return GET("$baseUrl/api/manga/$id/preview-pages", headers)
    }

    override fun pageListParse(response: Response): List<Page> {
        val json = Json.parseToJsonElement(response.body.string()).jsonObject
        val pagesArray = json["pages"]?.jsonArray ?: throw Exception("Formato inválido")
        
        return pagesArray.mapIndexed { index, element ->
            val obj = element.jsonObject
            val imageUrl = obj["proxied_url"]?.jsonPrimitive?.content ?: ""
            Page(index, "", imageUrl)
        }
    }

    override fun imageUrlParse(response: Response): String = throw UnsupportedOperationException()
}
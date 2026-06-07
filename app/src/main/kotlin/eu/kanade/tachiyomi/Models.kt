package eu.kanade.tachiyomi.source.model

// El molde para los datos de un Manga
interface SManga {
    var url: String
    var title: String
    var artist: String?
    var author: String?
    var description: String?
    var genre: String?
    var status: Int
    var thumbnail_url: String?
    var initialized: Boolean
    companion object {
        const val UNKNOWN = 0
        const val ONGOING = 1
        const val COMPLETED = 2
        const val LICENSED = 3
        // Método constructor que usa TMOHentai.kt para crear un objeto vacío y luego llenarlo
        fun create(): SManga = object : SManga {
            override var url: String = ""
            override var title: String = ""
            override var artist: String? = null
            override var author: String? = null
            override var description: String? = null
            override var genre: String? = null
            override var status: Int = 0
            override var thumbnail_url: String? = null
            override var initialized: Boolean = false
        }
    }
}

// El molde para los capítulos
interface SChapter {
    var url: String
    var name: String
    var date_upload: Long
    var chapter_number: Float
    var scanlator: String?
    companion object {
        fun create(): SChapter = object : SChapter {
            override var url: String = ""
            override var name: String = ""
            override var date_upload: Long = 0L
            override var chapter_number: Float = -1f
            override var scanlator: String? = null
        }
    }
}

open class Page(
    val index: Int,
    val url: String = "",
    var imageUrl: String? = null
)

// Clases de soporte para la paginación y filtros del buscador
data class MangasPage(val mangas: List<SManga>, val hasNextPage: Boolean)
open class Filter<T>(val name: String, val state: T)
class FilterList(val list: List<Filter<*>>) : List<Filter<*>> by list

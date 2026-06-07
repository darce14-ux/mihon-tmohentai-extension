package eu.kanade.tachiyomi

// Clases base que simulan la estructura oficial de Tachiyomi/Mihon
open class Source {
    open val id: Long = 0L
    open val name: String = ""
    open val lang: String = ""
    open val supportsLatest: Boolean = true
}

open class HttpSource : Source() {
    open val baseUrl: String = ""
}

// Modelos de datos mínimos que exige tu archivo TMOHentai.kt
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
}

interface SChapter {
    var url: String
    var name: String
    var date_upload: Long
    var chapter_number: Float
    var scanlator: String?
}

interface SPage {
    var index: Int
    var url: String
    var imageUrl: String?
}

package eu.kanade.tachiyomi

import rx.Observable
import org.jsoup.nodes.Document

interface Source {
    val id: Long
    val name: String
    val lang: String
    
    fun fetchMangaDetails(manga: Any): Observable<Any> = Observable.just(manga)
    fun fetchChapterList(manga: Any): Observable<List<Any>> = Observable.just(emptyList())
    fun fetchPageList(chapter: Any): Observable<List<Any>> = Observable.just(emptyList())
}

package sanchez.sergio.kmp_test.domain.models

/**
 * Page Data
 * @param page
 * @param data
 * @param isLast
 */
data class PageData<T> (val page: Long, val data: List<T>, val isLast: Boolean)

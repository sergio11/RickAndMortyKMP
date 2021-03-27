package sanchez.sergio.kmp_test

import kotlinx.coroutines.CoroutineScope

expect abstract class BaseTest() {
    fun <T> runTest(block: suspend CoroutineScope.() -> T)
}

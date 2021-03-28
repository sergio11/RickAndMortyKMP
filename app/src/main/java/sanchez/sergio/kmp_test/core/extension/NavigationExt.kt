package sanchez.sergio.kmp_test.core.extension

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

/**
 * nav Controller
 * @param navHostId
 */
fun AppCompatActivity.navController(@IdRes navHostId: Int): NavController? =
    supportFragmentManager.findFragmentById(navHostId)?.let {
        return if(it is NavHostFragment) {
            it.navController
        } else {
            null
        }
    }
package sanchez.sergio.kmp_test.ui.locations

import androidx.lifecycle.ViewModelProvider
import dev.icerock.moko.mvvm.MvvmFragment
import dev.icerock.moko.mvvm.createViewModelFactory
import sanchez.sergio.kmp_test.R
import sanchez.sergio.kmp_test.databinding.LocationListFragmentBinding

/**
 * Location List Fragment
 */
class LocationListFragment: MvvmFragment<LocationListFragmentBinding, LocationsViewModel>() {

    override val layoutId: Int = R.layout.location_list_fragment
    override val viewModelClass: Class<LocationsViewModel> =
        LocationsViewModel::class.java
    override val viewModelVariableId: Int = 432

    override fun viewModelFactory(): ViewModelProvider.Factory =
        createViewModelFactory { LocationsViewModel() }

}
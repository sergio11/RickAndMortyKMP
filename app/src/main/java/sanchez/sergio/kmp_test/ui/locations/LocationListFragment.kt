package sanchez.sergio.kmp_test.ui.locations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import sanchez.sergio.kmp_test.databinding.LocationListFragmentBinding
import sanchez.sergio.kmp_test.domain.models.Location

/**
 * Location List Fragment
 */
class LocationListFragment: Fragment(), KoinComponent {

    private val locationsViewModel: LocationsViewModel by viewModel()
    private val binding by lazy { LocationListFragmentBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            locationsViewModel.state.addObserver {
                when(it) {
                    is LocationsState.OnLoading -> onLoading()
                    is LocationsState.OnError -> onError(it.error)
                    is LocationsState.OnSuccess -> onLoaded(it.locationList)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationsViewModel.load()
    }

    /**
     * Private Methods
     */

    private fun onLoading() {
        Log.d("LOC", "onLoading CALLED")
    }

    private fun onLoaded(locations: List<Location>) {
        Log.d("LOC", "onLoaded ${locations.size} CALLED")
    }

    private fun onError(ex: Exception) {
        ex.printStackTrace()
        Log.d("LOC", "onError ${ex.message} CALLED")
    }

}
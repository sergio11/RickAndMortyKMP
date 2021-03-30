package sanchez.sergio.kmp_test.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import sanchez.sergio.kmp_test.core.extension.configure
import sanchez.sergio.kmp_test.databinding.LocationListFragmentBinding
import sanchez.sergio.kmp_test.domain.models.Location

/**
 * Location List Fragment
 */
class LocationListFragment: Fragment(), KoinComponent, LocationListAdapter.OnLocationClickListener {

    private val locationsViewModel: LocationsViewModel by viewModel()
    private val binding by lazy { LocationListFragmentBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            locationsViewModel.state.addObserver { state ->
                with(binding) {
                    uiState = state
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.apply {
                configure(isLoading = { true }, onLoadMore = {}, isLast = {true})
                adapter = onBuildAdapter()
            }
            swipeRefreshLayout.configure { locationsViewModel.load() }
        }
        locationsViewModel.load()
    }

    override fun onLocationClicked(location: Location) {

    }

    /**
     * Private Methods
     */

    /**
     * On Build Adapter
     */
    private fun onBuildAdapter(): LocationListAdapter =
        LocationListAdapter(context = requireContext(), data = mutableListOf(), locationItemListener = this)


}
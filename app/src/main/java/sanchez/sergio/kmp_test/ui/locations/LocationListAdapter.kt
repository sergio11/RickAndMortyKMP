package sanchez.sergio.kmp_test.ui.locations

import android.content.Context
import android.view.ViewGroup
import sanchez.sergio.kmp_test.databinding.LocationItemLayoutBinding
import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.ui.core.SupportAdapter

/**
* Location List Adapter
* @param context
* @param data
* @param locationItemListener
*/
class LocationListAdapter(
    context: Context,
    data: MutableList<Location>,
    private val locationItemListener: OnLocationClickListener,
): SupportAdapter<LocationListAdapter.LocationViewHolder, Location>(context, data) {

    /**
     * Create View Holder
     * @param parent
     * @param viewType
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder =
        LocationViewHolder(LocationItemLayoutBinding.inflate(inflater, parent, false))


    interface OnLocationClickListener {

        /**
         * on Location Clicked
         * @param location
         */
        fun onLocationClicked(location: Location)
    }

    /**
     * Location View Holder
     * @param binding
     */
    inner class LocationViewHolder(private val binding: LocationItemLayoutBinding):
        SupportAdapter.SupportViewHolder<Location>(binding.root) {
        override fun bind(model: Location) {
            with(binding) {
                location = model
            }
        }
    }


}
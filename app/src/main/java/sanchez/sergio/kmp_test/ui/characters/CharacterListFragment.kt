package sanchez.sergio.kmp_test.ui.characters

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import sanchez.sergio.kmp_test.R
import sanchez.sergio.kmp_test.databinding.CharacterListFragmentBinding
import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.ui.core.SupportRecyclerViewPagination

/**
 * Character List Fragment
 */
class CharacterListFragment: Fragment(), CharacterListAdapter.OnCharacterClickListener {

    /**
     * Dependencies
     */
    private val characterViewModel: CharactersViewModel by viewModel()

    private val binding by lazy { CharacterListFragmentBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            characterViewModel.state.addObserver { state ->
                with(binding) {
                    Log.d("CHAR", "ui state -> $state CALLED")
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
                addItemDecoration(object: RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                        val commonPadding = requireContext().resources.getDimension(R.dimen.common_small_padding).toInt()
                        outRect.apply {
                            left = commonPadding
                            right = commonPadding
                            top = commonPadding
                            bottom = commonPadding
                        }
                    }
                })

                SupportRecyclerViewPagination(
                    recyclerView = this,
                    isLoading = { characterViewModel.isLoading() },
                    loadMore = {  },
                    onLast = { true }
                )

                adapter = onBuildAdapter()
            }

            swipeRefreshLayout.apply {
                setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
                setProgressBackgroundColorSchemeColor(ContextCompat.getColor(requireContext(), android.R.color.black))
                setOnRefreshListener {
                    characterViewModel.load()
                }
            }
        }

        characterViewModel.load()
    }

    /**
     * On Character Clicked
     * @param character
     */
    override fun onCharacterClicked(character: Character) {


    }

    /**
     * Private Methods
     */

    /**
     * On Build Adapter
     */
    private fun onBuildAdapter(): CharacterListAdapter =
        CharacterListAdapter(context = requireContext(), data = mutableListOf(), characterItemListener = this)

}
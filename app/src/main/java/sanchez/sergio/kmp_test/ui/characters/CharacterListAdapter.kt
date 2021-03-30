package sanchez.sergio.kmp_test.ui.characters

import android.content.Context
import android.view.ViewGroup
import sanchez.sergio.kmp_test.databinding.CharacterItemLayoutBinding
import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.ui.core.SupportAdapter

/**
* Character List Adapter
* @param context
* @param data
* @param characterItemListener
*/
class CharacterListAdapter(
    context: Context,
    data: MutableList<Character>,
    private val characterItemListener: OnCharacterClickListener,
): SupportAdapter<CharacterListAdapter.CharacterViewHolder, Character>(context, data) {

    /**
     * Create View Holder
     * @param parent
     * @param viewType
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(CharacterItemLayoutBinding.inflate(inflater, parent, false))


    interface OnCharacterClickListener {
        /**
         * on Character Clicked
         * @param character
         */
        fun onCharacterClicked(character: Character)
    }

    /**
     * Character View Holder
     * @param binding
     */
    inner class CharacterViewHolder(private val binding: CharacterItemLayoutBinding):
        SupportAdapter.SupportViewHolder<Character>(binding.root) {
        override fun bind(model: Character) {
            with(binding) {
                character = model
            }
        }
    }


}
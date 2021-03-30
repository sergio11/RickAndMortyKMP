package sanchez.sergio.kmp_test.ui.characters

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import sanchez.sergio.kmp_test.R
import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.domain.models.CharacterStatusEnum

/**
 * Character List Bindings
 */
object CharacterListBindings {

    /**
     * Bind Swipe Refresh State
     * @param view
     * @param state
     */
    @JvmStatic
    @BindingAdapter("bindSwipeRefreshState")
    fun bindSwipeRefreshState(view: SwipeRefreshLayout, state: CharactersState) {
        view.isRefreshing = state is CharactersState.OnLoading
    }

    /**
     * Bind Visibility By State
     * @param view
     * @param state
     */
    @JvmStatic
    @BindingAdapter("bindVisibilityByState")
    fun bindVisibilityByState(view: RecyclerView, state: CharactersState) {
        view.visibility = if (state is CharactersState.OnSuccess)
            View.VISIBLE else View.GONE
    }

    /**
     * Bind Adapter Data
     */
    @JvmStatic
    @BindingAdapter("bindAdapterData")
    fun bindAdapterData(view: RecyclerView, state: CharactersState) {
        val adapter = view.adapter
        if(state is CharactersState.OnSuccess &&  adapter is CharacterListAdapter)
            adapter.addData(state.characterList)

    }

    /**
     * Bind Character State
     * @param view
     * @param character
     */
    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("bindCharacterState")
    fun bindCharacterState(view: TextView, character: Character) {
        view.text = if(character.type.isNotBlank())
            "${character.status.name} - ${character.type}"
        else
            character.status.name

        ContextCompat.getDrawable(view.context, R.drawable.ic_character_status_indicator)?.let {
            DrawableCompat.setTint(it, view.context.getColor(when(character.status) {
                CharacterStatusEnum.Alive -> R.color.colorCharacterStatusAlive
                CharacterStatusEnum.Dead -> R.color.colorCharacterStatusDead
                CharacterStatusEnum.unknown -> R.color.colorCharacterStatusUnknown
            }))
            view.setCompoundDrawablesWithIntrinsicBounds( it, null, null, null)
        }
    }
}
package sanchez.sergio.kmp_test.ui.core

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Support Adapter
 * @param context
 * @param data
 */
abstract class SupportAdapter<T: SupportAdapter.SupportViewHolder<E>, E>(
    private val context: Context,
    private val data: MutableList<E>
): RecyclerView.Adapter<T>() {

    protected val inflater by lazy {
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    /**
     * On Bind Model to View Holder
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: T, position: Int) {
        data.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = data.size

    /**
     * Update Adapter Data
     * @param newData
     */
    fun addData(newData: List<E>) {
        data.apply {
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    /**
     * Clear Data
     */
    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    /**
     * Replace Data
     * @param newData
     */
    fun replaceData(newData: List<E>) {
        data.clear()
        addData(newData)
    }

    /**
     * Inflate Layout
     */
    protected fun inflateLayout(@LayoutRes layoutRest: Int, parent: ViewGroup): View {
        return inflater.inflate(layoutRest, parent, false)
    }

    /**
     * Support View Holder
     * @param itemView
     */
    abstract class SupportViewHolder<E>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(model: E)
    }
}
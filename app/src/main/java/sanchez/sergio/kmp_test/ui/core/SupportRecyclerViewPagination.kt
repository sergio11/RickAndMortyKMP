package sanchez.sergio.kmp_test.ui.core

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Support Recycler View Pagination
 */
class SupportRecyclerViewPagination(
    recyclerView: RecyclerView,
    private val isLoading: () -> Boolean,
    private val loadMore: () -> Unit,
    private val onLast: () -> Boolean = { true }
): RecyclerView.OnScrollListener() {

    init {
        recyclerView.addOnScrollListener(this)
    }

    /**
     * On Scrolled
     * @param recyclerView
     * @param dx
     * @param dy
     */
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        recyclerView.layoutManager?.let {

            if (!onLast() && !isLoading()) {

                val totalItemCount = it.itemCount

                val lastCompletelyVisibleItemPosition = when (it) {
                    is LinearLayoutManager -> it.findLastCompletelyVisibleItemPosition()
                    is GridLayoutManager -> it.findLastCompletelyVisibleItemPosition()
                    else -> return
                }

                if(lastCompletelyVisibleItemPosition + 1 >= totalItemCount)
                    loadMore()
            }
        }
    }
}
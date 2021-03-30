package sanchez.sergio.kmp_test.core.extension

import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import sanchez.sergio.kmp_test.R
import sanchez.sergio.kmp_test.ui.core.SupportRecyclerViewPagination

/**
 * Common configuration for Swipe refresh layout
 * @param onRefresh
 */
fun SwipeRefreshLayout.configure(onRefresh: () -> Unit) {
    apply {
        setColorSchemeColors(ContextCompat.getColor(context, R.color.colorPrimaryDark))
        setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                context,
                R.color.colorAccent
            )
        )
        setOnRefreshListener { onRefresh() }
    }
}

/**
 * Common configuration for Recycler View
 * @param isLoading
 * @param onLoadMore
 * @param isLast
 */
fun RecyclerView.configure(
    isLoading: () -> Boolean,
    onLoadMore: () -> Unit,
    isLast: () -> Boolean
) {
    apply {
        addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                val commonPadding = context.resources.getDimension(
                    R.dimen.common_small_padding).toInt()
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
            isLoading = isLoading,
            loadMore = onLoadMore,
            onLast = isLast
        )
    }
}
package ___PACKAGE_NAME___.presentation.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.htecgroup.core.presentation.adapter.CoreAdapter
import com.htecgroup.core.presentation.adapter.CoreDiffAdapter
import java.util.*

@BindingAdapter(value = ["visibility", "setInvisible"], requireAll = false)
fun bindVisibility(view: View, visibility: Boolean?, setInvisible: Boolean?) {
    if (visibility != null) {
        view.visibility =
            if (visibility) View.VISIBLE else if (setInvisible != null && setInvisible) View.INVISIBLE else View.GONE
    }
}

@BindingAdapter(value = ["visibility", "setInvisible"], requireAll = false)
fun bindVisibility(view: View, visibility: String?, setInvisible: Boolean?) {
    view.visibility =
        if (visibility != null && !visibility.isEmpty()) {
            View.VISIBLE
        } else if (setInvisible != null && setInvisible) {
            View.INVISIBLE
        } else {
            View.GONE
        }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["items"])
fun <ItemT> bindItems(recyclerView: RecyclerView, items: List<ItemT>?) {
    val adapter = recyclerView.adapter
    if (adapter is CoreDiffAdapter<*>) {
        (adapter as CoreDiffAdapter<ItemT>).submitList(items ?: ArrayList())
    } else if (adapter is CoreAdapter<*>) {
        if (items != null && items.isNotEmpty()) {
            (adapter as CoreAdapter<ItemT>).setItems(items)
        } else {
            (adapter as CoreAdapter<ItemT>).clear()
        }
    }
}

@BindingAdapter(value = ["onRefresh"])
fun SwipeRefreshLayout.onRefresh(block: () -> Unit) {
    setOnRefreshListener { block() }
}

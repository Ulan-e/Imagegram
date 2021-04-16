package ulanapp.imagegram.ui.main

import androidx.databinding.BindingAdapter
import com.google.android.material.chip.ChipGroup

class ChipsAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter(
            value = ["android:onCheckedChanged"],
            requireAll = false
        )
        fun setChipListeners(
            view: ChipGroup?,
            listener: ChipGroup.OnCheckedChangeListener?
        ) {
            view?.setOnCheckedChangeListener { group, checkedId ->
                listener?.onCheckedChanged(group, checkedId)
            }
        }
    }
}
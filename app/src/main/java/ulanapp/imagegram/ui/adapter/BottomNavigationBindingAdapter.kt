package ulanapp.imagegram.ui.adapter

import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lessons.img.R

class BottomNavigationBindingAdapter {

    companion object {
        @BindingAdapter("onNavigationItemSelected")
        @JvmStatic
        fun setNavigationItemClickListener(
            view: BottomNavigationView,
            listener: BottomNavigationView.OnNavigationItemSelectedListener
        ) {
            view.selectedItemId = R.id.home
            view.setOnNavigationItemSelectedListener(listener)
        }
    }
}
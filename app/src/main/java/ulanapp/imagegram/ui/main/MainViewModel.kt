package ulanapp.imagegram.ui.main

import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lessons.img.R
import ulanapp.imagegram.helpers.DISCOVER_FRAGMENT
import ulanapp.imagegram.helpers.HOME_FRAGMENT
import ulanapp.imagegram.helpers.LIKED_FRAGMENT
import ulanapp.imagegram.listeners.CallFragmentListener

class MainViewModel : ViewModel {

    private var toolbarTitles = MutableLiveData<String>()
    private var listener: CallFragmentListener

    constructor(listener: CallFragmentListener) : super(){
        this.listener = listener
    }

    fun getToolbarTitle(): String? {
        return toolbarTitles.value
    }

    private fun setToolbarTitle(title: String) {
        toolbarTitles.value = title
    }

    fun onNavigationClick(@NonNull menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.home -> {
                setToolbarTitle(HOME_FRAGMENT)
                this.listener.callFragment(HOME_FRAGMENT)
                true
            }
            R.id.discover -> {
                setToolbarTitle(DISCOVER_FRAGMENT)
                this.listener.callFragment(DISCOVER_FRAGMENT)
                true
            }
            R.id.liked -> {
                setToolbarTitle(LIKED_FRAGMENT)
                this.listener.callFragment(LIKED_FRAGMENT)
                true
            }
            else -> false
        }
    }
}
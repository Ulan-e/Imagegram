package ulanapp.imagegram.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel  : ViewModel() {

    protected val loadingProgress = MutableLiveData<Boolean>()

    fun getLoadingResult(): MutableLiveData<Boolean> {
        return loadingProgress
    }
}
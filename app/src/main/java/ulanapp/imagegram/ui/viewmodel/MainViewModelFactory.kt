package ulanapp.imagegram.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ulanapp.imagegram.listeners.CallFragmentListener

class MainViewModelFactory: ViewModelProvider.Factory{

    private var listener: CallFragmentListener

    constructor(listener: CallFragmentListener) {
        this.listener = listener
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(listener) as T
    }

}
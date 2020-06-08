package ulanapp.imagegram.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ulanapp.imagegram.data.repository.Repository
import ulanapp.imagegram.listeners.OnChangePhotoResponseListener

class HomeViewModelFactory : ViewModelProvider.Factory {

    private var repository: Repository
    private var listener: OnChangePhotoResponseListener

    constructor(repository: Repository, listener: OnChangePhotoResponseListener) {
        this.repository = repository
        this.listener = listener
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository, listener) as T
    }

}
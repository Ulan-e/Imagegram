package ulanapp.imagegram.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ulanapp.imagegram.data.repository.Repository
import ulanapp.imagegram.listeners.OnChangePhotoResponseListener

class HomeViewModelFactory(
    private var repository: Repository,
    private var listener: OnChangePhotoResponseListener
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository, listener) as T
    }
}
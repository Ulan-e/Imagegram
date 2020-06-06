package ulanapp.imagegram.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ulanapp.imagegram.data.model.PhotosResponse
import ulanapp.imagegram.data.repository.Repository
import ulanapp.imagegram.data.repository.PixabayRepositoryImpl

class PhotosViewModel : ViewModel() {

    private var mutableLiveData: MutableLiveData<PhotosResponse>
    private var repository: Repository = PixabayRepositoryImpl()

    init {
        mutableLiveData = repository.getPhotos("Nature")
    }

    public fun loadPhotos(): MutableLiveData<PhotosResponse> {
        return mutableLiveData
    }

    override fun onCleared() {
        super.onCleared()
    }
}
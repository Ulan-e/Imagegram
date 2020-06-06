package ulanapp.imagegram.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ulanapp.imagegram.data.model.PhotosResponse
import ulanapp.imagegram.data.repository.Repository
import ulanapp.imagegram.data.repository.PixabayRepositoryImpl

class HomeViewModel : ViewModel() {

    private var photosLiveData: MutableLiveData<PhotosResponse>
    private var repository: Repository = PixabayRepositoryImpl()

    init {
        photosLiveData = repository.getPhotos("Nature")
    }

    fun loadPhotos(): MutableLiveData<PhotosResponse> {
        return photosLiveData
    }

    override fun onCleared() {
        super.onCleared()
    }
}
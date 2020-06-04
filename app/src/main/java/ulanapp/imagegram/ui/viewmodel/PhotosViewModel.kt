package ulanapp.imagegram.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ulanapp.imagegram.data.model.Result
import ulanapp.imagegram.data.repository.PexelsRepository
import ulanapp.imagegram.data.repository.PexelsRepositoryImpl

class PhotosViewModel : ViewModel() {

    private var mutableLiveData: MutableLiveData<Result>
    private var mPexelsRepository: PexelsRepository

    init {
        mPexelsRepository =
            PexelsRepositoryImpl()
        mutableLiveData = mPexelsRepository.getPhotos("Nature")
    }

    public fun loadPhotos(): MutableLiveData<Result> {
        return mutableLiveData
    }

    override fun onCleared() {
        super.onCleared()
    }
}
package ulanapp.imagegram.listeners

import androidx.lifecycle.LiveData
import ulanapp.imagegram.data.model.PhotosResponse

interface OnChangePhotoResponseListener {

    fun onChange(photos: LiveData<PhotosResponse>)

}
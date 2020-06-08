package ulanapp.imagegram.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.chip.ChipGroup
import com.lessons.img.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ulanapp.imagegram.data.model.PhotosResponse
import ulanapp.imagegram.data.repository.Repository
import ulanapp.imagegram.helpers.TAG
import ulanapp.imagegram.listeners.OnChangePhotoResponseListener

class HomeViewModel : ViewModel {

    private var repository: Repository
    private var changeListener: OnChangePhotoResponseListener
    private var disposable: CompositeDisposable

    private var photosLiveData = MutableLiveData<PhotosResponse>()

    constructor(repository: Repository, listener: OnChangePhotoResponseListener) : super() {
        this.repository = repository
        this.changeListener = listener
        this.disposable = CompositeDisposable()
        this.changeListener.onChange(getPhotos(true, ""))
    }

    private fun getPhotos(isPopular: Boolean, query: String): LiveData<PhotosResponse> {
        loadPhotos(isPopular, query)
        return photosLiveData
    }

    private fun loadPhotos(isPopular: Boolean, query: String) {
        disposable.add(
            repository.getPhotos(isPopular, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { p -> photosLiveData.value = p },
                    { e -> Log.d(TAG, "onError() $e") })
        )
    }

    //Chip click
    fun onChipClick(group: ChipGroup, id: Int) {
        when (id) {
            R.id.first_chip -> {
                changeListener.onChange(getPhotos(true,""))
                Log.d("ulanbek", " First check $id")
            }
            R.id.second_chip -> {
                changeListener.onChange(getPhotos(false, ""))
                Log.d("ulanbek", " Second check $id")
            }
            R.id.third_chip -> {
                changeListener.onChange(getPhotos(true, "beautiful"))
                Log.d("ulanbek", " Third check $id")
            }
            R.id.fourth_chip -> {
                changeListener.onChange(getPhotos(true, "summer"))
                Log.d("ulanbek", " Fourth check $id")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
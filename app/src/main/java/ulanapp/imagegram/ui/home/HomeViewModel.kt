package ulanapp.imagegram.ui.home

import android.util.Log
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

    private var photos = MutableLiveData<PhotosResponse>()

    constructor(repository: Repository, listener: OnChangePhotoResponseListener) : super() {
        this.repository = repository
        this.changeListener = listener
        this.disposable = CompositeDisposable()
        generatePhotos(true, "")
        this.changeListener.onChange(photos)
    }

    private fun generatePhotos(isPopular: Boolean, query: String){
        loadPhotos(isPopular, query)
    }

    private fun loadPhotos(isPopular: Boolean, query: String) {
        disposable.add(
            repository.getPhotos(isPopular, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { p -> photos.value = p },
                    { e -> Log.d(TAG, "$e") })
        )
    }

    //Chip click
    fun onChipClick(group: ChipGroup, id: Int) {
        when (id) {
            R.id.first_chip -> {
                generatePhotos(true, "")
                changeListener.onChange(photos)
            }
            R.id.second_chip -> {
                generatePhotos(false, "")
                changeListener.onChange(photos)
            }
            R.id.third_chip -> {
                generatePhotos(true, "beautiful")
                changeListener.onChange(photos)
            }
            R.id.fourth_chip -> {
                generatePhotos(true, "summer")
                changeListener.onChange(photos)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
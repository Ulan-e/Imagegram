package ulanapp.imagegram.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.material.chip.ChipGroup
import com.lessons.img.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ulanapp.imagegram.data.model.PhotosResponse
import ulanapp.imagegram.data.repository.Repository
import ulanapp.imagegram.helpers.TAG
import ulanapp.imagegram.listeners.OnChangePhotoResponseListener
import ulanapp.imagegram.ui.base.BaseViewModel

class HomeViewModel(
    var repository: Repository,
    listener: OnChangePhotoResponseListener
) : BaseViewModel() {

    private var changeListener: OnChangePhotoResponseListener = listener
    private var disposable: CompositeDisposable = CompositeDisposable()

    private var photos = MutableLiveData<PhotosResponse>()

    init {
        loadPhotos(true, "")
        this.changeListener.onChange(photos)
    }

    private fun loadPhotos(isPopular: Boolean, query: String) {
        disposable.add(
            repository.getPhotos(isPopular, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingProgress.value = true }
                .doAfterTerminate { loadingProgress.value = false }
                .subscribe(
                    { p -> photos.value = p },
                    { e -> Log.d(TAG, "$e") })
        )
    }

    fun onChipClick(group: ChipGroup, id: Int) {
        when (id) {
            R.id.first_chip -> {
                loadPhotos(true, "")
                changeListener.onChange(photos)
            }
            R.id.second_chip -> {
                loadPhotos(false, "")
                changeListener.onChange(photos)
            }
            R.id.third_chip -> {
                loadPhotos(true, "beautiful")
                changeListener.onChange(photos)
            }
            R.id.fourth_chip -> {
                loadPhotos(true, "summer")
                changeListener.onChange(photos)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
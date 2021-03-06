package ulanapp.imagegram.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.lessons.img.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.search_layout.*
import ulanapp.imagegram.data.model.Photo
import ulanapp.imagegram.data.model.PhotosResponse
import ulanapp.imagegram.data.repository.PixabayRepositoryImpl
import ulanapp.imagegram.data.repository.Repository
import ulanapp.imagegram.helpers.TAG
import ulanapp.imagegram.ui.home.PhotoAdapter
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment() {

    private lateinit var repository: Repository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_view.setIconifiedByDefault(false)
        search_view.requestFocus()

        press_back.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }
        repository = PixabayRepositoryImpl()
        setUpObservable()
    }

    private fun setUpObservable() {
        RxSearchObservable.fromView(search_view)
            .debounce(500, TimeUnit.MILLISECONDS)
            .filter { text -> text.isNotEmpty() }
            .distinctUntilChanged()
            .switchMap { t ->
                dataFromNetwork(t)
                    .doOnError { throwable ->
                        Log.d(
                            TAG,
                            "Throwable $throwable"
                        )
                    }
                    .onErrorReturn { throwable -> PhotosResponse() }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t ->
                val photos = mutableListOf<Photo>()
                t?.hits?.let { result ->
                    photos.addAll(result)
                    setupAdapter(photos)
                }
            }
    }

    private fun setupAdapter(photos: List<Photo>) {
        val adapter = PhotoAdapter(photos)
        val layoutManager = GridLayoutManager(activity!!, 2)
        search_results.layoutManager = layoutManager
        search_results.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun dataFromNetwork(query: String): Observable<PhotosResponse> {
        return repository.getPhotos(true, query)
    }
}
package ulanapp.imagegram.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.lessons.img.databinding.HomeLayoutBinding
import kotlinx.android.synthetic.main.home_layout.*
import ulanapp.imagegram.data.model.Photo
import ulanapp.imagegram.data.model.PhotosResponse
import ulanapp.imagegram.data.repository.PixabayRepositoryImpl
import ulanapp.imagegram.data.repository.Repository
import ulanapp.imagegram.listeners.OnChangePhotoResponseListener
import ulanapp.imagegram.ui.adapter.PhotoAdapter

class HomeFragment : Fragment(), OnChangePhotoResponseListener {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var repository: Repository
    private lateinit var homeLayoutBinding: HomeLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeLayoutBinding = HomeLayoutBinding.inflate(inflater, container, false)
        return homeLayoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = PixabayRepositoryImpl()

        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(repository, this))
            .get(HomeViewModel::class.java)
        homeLayoutBinding.home = homeViewModel

    }

    private fun setupAdapter(photos: List<Photo>) {
        val adapter = PhotoAdapter(activity!!, photos)
        val layoutManager = GridLayoutManager(activity!!, 2)
        home_recycler_view.layoutManager = layoutManager
        home_recycler_view.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onChange(photoResponse: LiveData<PhotosResponse>) {
        photoResponse.observe(activity!!, Observer<PhotosResponse> {
            val photos = mutableListOf<Photo>()
            it.hits?.let { result ->
                photos.addAll(result)
                setupAdapter(photos)
            }
        })
    }

}
package ulanapp.imagegram.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.lessons.img.R
import com.lessons.img.databinding.HomeLayoutBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.home_layout.*
import ulanapp.imagegram.data.model.Photo
import ulanapp.imagegram.data.model.PhotosResponse
import ulanapp.imagegram.data.repository.PixabayRepositoryImpl
import ulanapp.imagegram.data.repository.Repository
import ulanapp.imagegram.listeners.OnChangePhotoResponseListener
import ulanapp.imagegram.ui.search.SearchFragment


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

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        repository = PixabayRepositoryImpl()

        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(repository, this))
            .get(HomeViewModel::class.java)
        homeLayoutBinding.home = homeViewModel
        homeLayoutBinding.homeSearch.setOnClickListener{
            val fragment = SearchFragment()
            activity!!.supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_container, fragment)
                .commit()
        }

        handleLoadingProgress()
    }

    private fun handleLoadingProgress() {
        homeViewModel.getLoadingResult().observe(activity!!,
            Observer<Boolean> { t ->
                if(t == true){
                    homeLayoutBinding.homeProgress.visibility = View.VISIBLE
                }else{
                    homeLayoutBinding.homeProgress.visibility = View.GONE
                }
            })
    }

    private fun setupAdapter(photos: List<Photo>)   {
        val adapter = PhotoAdapter(photos)
        val layoutManager = GridLayoutManager(activity!!, 2)
        home_recycler_view.layoutManager = layoutManager
        home_recycler_view.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onChange(photos: LiveData<PhotosResponse>) {
        photos.observe(activity!!, Observer<PhotosResponse> {
            val resultPhotos = mutableListOf<Photo>()
            it.hits?.let { result ->
                resultPhotos.addAll(result)
                setupAdapter(resultPhotos)
            }
        })
    }

}
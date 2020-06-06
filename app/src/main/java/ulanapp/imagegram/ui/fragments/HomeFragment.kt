package ulanapp.imagegram.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.lessons.img.R
import kotlinx.android.synthetic.main.home_layout.*
import ulanapp.imagegram.data.model.Photo
import ulanapp.imagegram.data.model.PhotosResponse
import ulanapp.imagegram.ui.adapter.PhotoAdapter
import ulanapp.imagegram.ui.viewmodel.HomeViewModel

class HomeFragment: Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var photos = mutableListOf<Photo>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.loadPhotos().observe(activity!!, Observer<PhotosResponse> {
            it.hits?.let {
                result -> photos.addAll(result)
                setupAdapter()
            }
        })


    }

    private fun setupAdapter() {
        val adapter = PhotoAdapter(activity!!, photos)
        val layoutManager = GridLayoutManager(activity!!, 2)
        home_recycler_view.layoutManager = layoutManager
        home_recycler_view.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
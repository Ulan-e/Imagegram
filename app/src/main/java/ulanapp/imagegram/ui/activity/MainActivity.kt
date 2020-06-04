package ulanapp.imagegram.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.lessons.img.R
import ulanapp.imagegram.data.model.Photo
import ulanapp.imagegram.data.model.Result
import kotlinx.android.synthetic.main.activity_main.*
import ulanapp.imagegram.ui.adapter.PhotoAdapter
import ulanapp.imagegram.ui.viewmodel.PhotosViewModel

class MainActivity : AppCompatActivity() {

    private var photos: ArrayList<Photo> = ArrayList<Photo>()
    private lateinit var adapter: PhotoAdapter
    private lateinit var viewModel: PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        val data = viewModel.loadPhotos()
        data.observe(this, Observer<Result> {
            val list  = it.photos
            photos.addAll(list)
            Log.d("ulanbek", "Photo List is " + photos.size)
            setUpAdapter()
        })

    }

    private fun setUpAdapter(){
        adapter = PhotoAdapter(this, photos)
        recycler_photos.layoutManager = GridLayoutManager(this, 2)
        recycler_photos.adapter = adapter
        Log.d("ulanbek", " set adapter ")
        adapter.notifyDataSetChanged()
        foo(x = 1, y = 1, width = 5)
    }

    private fun foo(x: Int, y: Int, width: Int){

    }


}

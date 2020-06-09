package ulanapp.imagegram.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lessons.img.databinding.PhotoItemBinding
import ulanapp.imagegram.data.model.Photo
import ulanapp.imagegram.listeners.OnItemClickListener

class PhotoAdapter(mPhotos: List<Photo>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private var photos: List<Photo> = mPhotos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        holder.binding?.photo = photo
        holder.binding?.itemClick = object :
            OnItemClickListener {
            override fun onItemClick(view: View) {
                Log.d("ulanbek", "Click in adapter " + photo.imageSize)
            }
        }
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding: PhotoItemBinding? = androidx.databinding.DataBindingUtil.bind<PhotoItemBinding>(itemView)

    }

}
package ulanapp.imagegram.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lessons.img.databinding.PhotoItemBinding
import ulanapp.imagegram.data.model.Photo
import ulanapp.imagegram.listeners.OnItemClickListener

class PhotoAdapter : RecyclerView.Adapter<PhotoViewHolder> {

    private var context: Context
    private var photos: List<Photo>

    constructor(mContext: Context, mPhotos: List<Photo>) : super() {
        this.context = mContext
        this.photos = mPhotos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = PhotoItemBinding.inflate(inflater, parent, false)
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
                Toast.makeText(context, "Image " + photo.toString(), Toast.LENGTH_LONG).show()


            }

        }
    }

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun ImageView.loadPhoto(url: String) {
            Glide.with(context).load(url).into(this)
        }
    }

}
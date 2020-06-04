package ulanapp.imagegram.ui.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lessons.img.databinding.PhotoItemBinding

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding: PhotoItemBinding? = DataBindingUtil.bind<PhotoItemBinding>(itemView)

}
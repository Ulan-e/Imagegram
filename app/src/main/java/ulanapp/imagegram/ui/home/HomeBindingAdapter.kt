package ulanapp.imagegram.ui.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class HomeBindingAdapter {

    companion object {

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun ImageView.setPhoto(url: String) {
            Glide.with(context).load(url).into(this)
        }

    }

}
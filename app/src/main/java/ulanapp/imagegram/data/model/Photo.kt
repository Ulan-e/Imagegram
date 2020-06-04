package ulanapp.imagegram.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("width")
    @Expose
    val width: Int,
    @SerializedName("height")
    @Expose
    val height: Int,
    @SerializedName("url")
    @Expose
    val url: String,
    @SerializedName("photographer")
    @Expose
    val photographer: String,
    @SerializedName("photographer_url")
    @Expose
    val photographerUrl: String,
    @SerializedName("photographer_id")
    @Expose
    val photographerId: Int,
    @SerializedName("src")
    @Expose
    val src: Src,
    @SerializedName("liked")
    @Expose
    val liked: Boolean
    )

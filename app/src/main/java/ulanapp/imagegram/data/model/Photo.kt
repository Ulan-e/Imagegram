package ulanapp.imagegram.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photo(

    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("pageURL")
    @Expose
    var pageURL: String? = null,
    @SerializedName("type")
    @Expose
    var type: String? = null,
    @SerializedName("tags")
    @Expose
    var tags: String? = null,
    @SerializedName("previewURL")
    @Expose
    var previewURL: Any? = null,
    @SerializedName("previewWidth")
    @Expose
    var previewWidth: Int? = null,
    @SerializedName("previewHeight")
    @Expose
    var previewHeight: Int? = null,
    @SerializedName("webformatURL")
    @Expose
    var webformatURL: String? = null,
    @SerializedName("webformatWidth")
    @Expose
    var webformatWidth: Int? = null,
    @SerializedName("webformatHeight")
    @Expose
    var webformatHeight: Int? = null,
    @SerializedName("largeImageURL")
    @Expose
    var largeImageURL: String? = null,
    @SerializedName("fullHDURL")
    @Expose
    var fullHDURL: String? = null,
    @SerializedName("imageURL")
    @Expose
    var imageURL: String? = null,
    @SerializedName("imageWidth")
    @Expose
    var imageWidth: Int? = null,
    @SerializedName("imageHeight")
    @Expose
    var imageHeight: Int? = null,
    @SerializedName("imageSize")
    @Expose
    var imageSize: Int? = null,
    @SerializedName("views")
    @Expose
    var views: Int? = null,
    @SerializedName("downloads")
    @Expose
    var downloads: Int? = null,
    @SerializedName("favorites")
    @Expose
    var favorites: Int? = null,
    @SerializedName("likes")
    @Expose
    var likes: Int? = null,
    @SerializedName("comments")
    @Expose
    var comments: Int? = null,
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null,
    @SerializedName("user")
    @Expose
    var user: String? = null,
    @SerializedName("userImageURL")
    @Expose
    var userImageURL: Any? = null
)
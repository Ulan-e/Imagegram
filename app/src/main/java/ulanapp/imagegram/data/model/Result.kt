package ulanapp.imagegram.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result(
    @SerializedName("page")
    @Expose
    var page: Int = -1,
    @SerializedName("per_page")
    @Expose
    val per_page: Int = -1,
    @SerializedName("total_results")
    @Expose
    val total_results: Int = -1,
    @SerializedName("photos")
    @Expose
    val photos: List<Photo>,
    @SerializedName("next_page")
    @Expose
    val nextPage: String
)
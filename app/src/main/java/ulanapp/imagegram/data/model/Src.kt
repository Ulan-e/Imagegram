package ulanapp.imagegram.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



class Src (
    @SerializedName("original")
    @Expose
    var original: String? = null,
    @SerializedName("large2x")
    @Expose
    var large2x: String? = null,
    @SerializedName("large")
    @Expose
    var large: String? = null,
    @SerializedName("medium")
    @Expose
    var medium: String? = null,
    @SerializedName("small")
    @Expose
    var small: String? = null,
    @SerializedName("portrait")
    @Expose
    var portrait: String? = null,
    @SerializedName("landscape")
    @Expose
    var landscape: String? = null,
    @SerializedName("tiny")
    @Expose
    var tiny: String? = null
)
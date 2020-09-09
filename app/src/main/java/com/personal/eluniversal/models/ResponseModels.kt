package com.personal.eluniversal.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class DataModel(
    @SerializedName("site")
    var site: String? = null,
    @SerializedName("sitio_dominio")
    var sitio_dominio: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("main_section")
    var main_section: String? = null,
    @SerializedName("id_seccion")
    var id_seccion: String? = null,
    @SerializedName("id_subseccion")
    var id_subseccion: String? = null,
    @SerializedName("section")
    var section: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("subtitle")
    var subtitle: String? = null,
    @SerializedName("summary")
    var summary: String? = null,
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("pubdate")
    var pupdate: String? = null,
    @SerializedName("pubtime")
    var pubtime: String? = null,
    @SerializedName("timestamp")
    var timestamp: Int? = null,
    @SerializedName("place")
    var place: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("image_small_size")
    var image_small_size: String? = null,
    @SerializedName("caption")
    var caption: String? = null,
    @SerializedName("audio")
    var audio: String? = null,
    @SerializedName("gallery")
    var gallery: String? = null,
    @SerializedName("video")
    var video: String? = null,
    @SerializedName("rank")
    var rank: Int? = null,
    @SerializedName("link")
    var link: String? = null,
    @SerializedName("link_title")
    var link_title: String? = null,
    @SerializedName("comments")
    var comments: Int? = null,
    @SerializedName("premium")
    var premium: Int? = null,
    @SerializedName("body_html")
    var body_html: String? = null,
    @SerializedName("body")
    var body: String? = null,
    @SerializedName("seccion_nombre_api")
    var seccion_nombre_api: String? = null,
    @SerializedName("opta-widget")
    var opta_widget: Boolean? = null,
    @SerializedName("iframe_body_html")
    var iframe_body_html: String? = null,
    @SerializedName("tags")
    var tags: String? = null
): Serializable
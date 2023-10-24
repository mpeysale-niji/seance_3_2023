package com.example.seance3

import com.google.gson.annotations.SerializedName

class Category {
    @SerializedName("idCategory")
    var id: String? = null
    @SerializedName("strCategory")
    var title: String? = null
    @SerializedName("strCategoryDescription")
    var description: String? = null
    @SerializedName("strCategoryThumb")
    var imageURL: String? = null
}

class CategoriesResponse {
    var categories: List<Category> = emptyList()
}
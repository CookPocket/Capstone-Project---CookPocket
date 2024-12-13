package com.capstone.cookpocket.Network.Response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CookPocketResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: List<Product> = emptyList()
): Parcelable

@Parcelize
data class Product(

    @field:SerializedName("id_product")
    val idProduct: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("ingredient")
    val ingredient: String? = null,

    @field:SerializedName("steps")
    val steps: String? = null,

    @field:SerializedName("cooking_time")
    val cookingTime: String? = null,

    @field:SerializedName("servings")
    val servings: Int? = null,

    @field:SerializedName("id_category")
    val idCategory: Int? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null
) :Parcelable

data class ProductsData(
    val products: List<Product> // List of Product objects
)
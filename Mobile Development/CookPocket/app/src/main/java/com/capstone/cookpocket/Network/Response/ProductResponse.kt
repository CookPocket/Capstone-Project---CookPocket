package com.capstone.cookpocket.Network.Response

import com.google.gson.annotations.SerializedName

data class ProductResponse(

	@field:SerializedName("servings")
	val servings: Int? = null,

	@field:SerializedName("id_product")
	val idProduct: Int? = null,

	@field:SerializedName("ingredient")
	val ingredient: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("id_category")
	val idCategory: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("cooking_time")
	val cookingTime: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("steps")
	val steps: String? = null
)

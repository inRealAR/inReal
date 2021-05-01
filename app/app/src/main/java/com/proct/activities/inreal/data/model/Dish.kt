package com.proct.activities.inreal.data.model

data class Dish(
    var name: String,
    var description: String,
    var price: String,
    var imageId : Int,
    var calories: String,
    var ingredients: String,
    var type: DishType,
    var weight: String,
    var rawForObject: Int
)


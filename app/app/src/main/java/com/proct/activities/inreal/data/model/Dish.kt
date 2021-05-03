package com.proct.activities.inreal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.proct.activities.inreal.R

@Entity(tableName = "dishes")
data class Dish(
    @PrimaryKey
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "price")
    var price: String,

    @ColumnInfo(name = "imageId")
    var imageId : Int,

    @ColumnInfo(name = "calories")
    var calories: String,

    @ColumnInfo(name = "ingredients")
    var ingredients: String,

    @ColumnInfo(name = "type")
    var type: DishType,

    @ColumnInfo(name = "weight")
    var weight: String,

    @ColumnInfo(name = "rawForObject")
    var rawForObject: Int
)
{
    object ListOfDishesLoader {
        val listOfDishes : MutableList<Dish> = mutableListOf(
            Dish(
                "Салат",
                "Листья салата в\nсоке лимона и специях",
                "250",
                R.drawable.standart_salad,
                "17; 2; 1; 2.7",
                "Листья салата",
                DishType.SALADS,
                "100 Г",
                R.raw.standart_salad),
            Dish(
                "Картофельный салат",
                "Летний салат с\nкартофелем, морковкой и ветчиной",
                "350",
                R.drawable.potato_salad,
                "117.9; 1.8; 6; 14",
                "Картофель, морковь, ветчина, майонез",
                DishType.SALADS,
                "350 Г",
                R.raw.potato_salad),
            Dish(
                "Кремовый суп",
                "Сырный кремовый\nсуп",
                "150",
                R.drawable.krem_soup,
                "136.7; 8; 8; 8.5",
                "Крем, сухарики",
                DishType.SOUPS,
                "300 Г",
                R.raw.krem_soup),
            Dish(
                "Том Ям",
                "Острый пан-\nазиатский суп",
                "450",
                R.drawable.tom_yam,
                "83.1; 5.6; 4; 4",
                "Лапша, яйцо, грибы",
                DishType.SOUPS,
                "560 Г",
                R.raw.tom_yam),
            Dish(
                "Каппучино",
                "Cливочный латте\nc сиропом на выбор",
                "130",
                R.drawable.cappuccino,
                "104; 5; 5.6; 9",
                "Кофеин, сливки, молоко",
                DishType.DRINKS,
                "310 Г",
                R.raw.capuccino),
            Dish(
                "Кофе",
                "Kофе, \npauling president",
                "100",
                R.drawable.coffee,
                "7; 0.2; 0.5; 0.2",
                "Черный кофе",
                DishType.DRINKS,
                "300 Г",
                R.raw.coffee),
            Dish(
                "Черный чай",
                "Чай \nс добавлением цебреца",
                "70",
                R.drawable.tea,
                "151.8; 20; 5.1; 7",
                "Лапсанх Сушонг",
                DishType.DRINKS,
                "300 Г",
                R.raw.tea),
            Dish(
                "Воздушный торт",
                "Нежное безе и шоколад\nс вишней и черникой",
                "1000",
                R.drawable.bisquit_cake,
                "400; 5; 17; 56",
                "Шоколад, крем, черника, вишня, сливки",
                DishType.DESSERTS,
                "1000 Г",
                R.raw.bisquit_cake),
            Dish(
                "Шоколадный торт",
                "Вкусный многослойный\nторт",
                "400",
                R.drawable.piece_of_the_cake,
                "320; 5.8; 14; 35",
                "Шоколад, крем, вишня",
                DishType.DESSERTS,
                "500 Г",
                R.raw.piece_of_the_cake),
            Dish(
                "Шоколадный пудинг",
                "Шоколадный пудинг\nсо сладкими вишенками",
                "300",
                R.drawable.pudding,
                "131; 5; 3.8; 13",
                "Шоколад, вишня",
                DishType.DESSERTS,
                "600 Г",
                R.raw.pudding),
            Dish(
                "Блины",
                "Блины на молоке",
                "300",
                R.drawable.pancakes,
                "170; 4.8; 7; 22",
                "Мука, сахар, молоко",
                DishType.DESSERTS,
                "400 Г",
                R.raw.pancakes),
            Dish(
                "Чизбургер",
                "Американский чизбургер\nс тремя видами сыра и говядиной",
                "200",
                R.drawable.cheeseburger,
                "305; 16; 13; 30",
                "Котлета, сыры, салат, помидор",
                DishType.HOT,
                "700 Г",
                R.raw.cheeseburger),
            Dish(
                "Пицца",
                "Пицца на красном соусе\nс острым чоризо",
                "400",
                R.drawable.pizza,
                "271; 11.3; 14; 23",
                "Колбаса, сыр, кетчуп",
                DishType.HOT,
                "1000 Г",
                R.raw.pizza),
            Dish(
                "Курица с картошкой",
                "Целая курица\nс картофельными дольками",
                "800",
                R.drawable.chicken_mit_potato,
                "170; 9.4; 11; 7.6",
                "Курица, картошка",
                DishType.HOT,
                "1500 Г",
                R.raw.chicken_mit_potato),
            Dish(
                "Крылышки",
                "Куриные крылышки\nс укропом и соком лимона",
                "400",
                R.drawable.chicken_wings,
                "195; 19.3; 13; 1",
                "Курица, укроп",
                DishType.HOT,
                "500 Г",
                R.raw.chicken_wings),
            Dish(
                "Мясо с картошкой",
                "Мясо с картохой\nпо домашнему",
                "300",
                R.drawable.meat_whith_potato,
                "132; 9; 5.6; 9.7",
                "Курица, картошка",
                DishType.HOT,
                "500 Г",
                R.raw.meat_whith_potato)
        )
    }
}



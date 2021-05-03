package com.proct.activities.inreal.di

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.proct.activities.inreal.data.database.*
import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.sources.InRealDataLocalSource
import com.proct.activities.inreal.utils.adapters.CategoryViewModelAdapter
import com.proct.activities.inreal.utils.adapters.DetailedDishViewModelAdapter
import com.proct.activities.inreal.utils.adapters.DishesViewModelAdapter
import com.proct.activities.inreal.utils.adapters.OrderViewModelAdapter
import com.proct.activities.inreal.utils.providers.CategoryAndDishesViewModelProvider
import com.proct.activities.inreal.utils.providers.DetailedDishAndOrderProvider
import com.proct.activities.inreal.utils.providers.DishesAndDetailedDishViewModelProvider
import com.proct.activities.inreal.viewmodels.category.CategoryViewModel
import com.proct.activities.inreal.viewmodels.dishes.DetailedDishViewModel
import com.proct.activities.inreal.viewmodels.dishes.DishesViewModel
import com.proct.activities.inreal.viewmodels.order.OrderViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): InRealDatabase {
        var result: InRealDatabase? = null
        result = Room.databaseBuilder(
            appContext,
            InRealDatabase::class.java,
            "InrRealApp.db"
        ).addCreateCallback {
            for (category in Category.ListOfCategoriesListener.listOfCategories) {
                Log.e("DatabaseModule", "insert ${category.name}")
                DataStoreScope.launch(Dispatchers.IO) {
                    result!!.categoryDao().insert(category)
                }
            }
            for (dish in Dish.ListOfDishesLoader.listOfDishes) {
                DataStoreScope.launch(Dispatchers.IO) {
                    result!!.dishDao().insert(dish)
                }
            }
            Log.e("DatabaseModule", "AddCreateCallback")
        }.build()
        return result
    }

    @Provides
    fun provideDishDao(database: InRealDatabase): DishDAO {
        return database.dishDao()
    }

    @Provides
    fun provideCategoryDao(database: InRealDatabase): CategoryDAO {
        return database.categoryDao()
    }

    @Provides
    fun provideOrderDao(database: InRealDatabase): OrderItemDAO {
        return database.orderDao()
    }

    @Provides
    @Singleton
    fun provideInRealDataLocalSource(dishDAO: DishDAO, categoryDAO: CategoryDAO, orderItemDAO: OrderItemDAO): InRealDataLocalSource =
        InRealDataLocalSource(dishDAO, categoryDAO, orderItemDAO)

    @Provides
    @Singleton
    fun provideCategoryViewModelAdapter(inRealDataLocalSource: InRealDataLocalSource): CategoryViewModelAdapter =
        CategoryViewModelAdapter(inRealDataLocalSource)

    @Provides
    @Singleton
    fun provideDishesViewModelAdapter(inRealDataLocalSource: InRealDataLocalSource): DishesViewModelAdapter =
        DishesViewModelAdapter(inRealDataLocalSource)

    @Provides
    @Singleton
    fun provideOrderViewModelAdapter(inRealDataLocalSource: InRealDataLocalSource): OrderViewModelAdapter =
        OrderViewModelAdapter(inRealDataLocalSource)


    @Provides
    @Singleton
    fun provideDetailedDishViewModelAdapter(inRealDataLocalSource: InRealDataLocalSource): DetailedDishViewModelAdapter =
        DetailedDishViewModelAdapter(inRealDataLocalSource)

    @Provides
    @Singleton
    fun provideCategoryAdnDishesViewModelProvider(dishesViewModelAdapter: DishesViewModelAdapter): CategoryAndDishesViewModelProvider =
        CategoryAndDishesViewModelProvider(dishesViewModelAdapter)

    @Provides
    @Singleton
    fun provideDishesAndDetailedDishViewModelProvider(detailedDishViewModelAdapter: DetailedDishViewModelAdapter): DishesAndDetailedDishViewModelProvider =
        DishesAndDetailedDishViewModelProvider(detailedDishViewModelAdapter)

    @Provides
    @Singleton
    fun provideDetailedDishAndOrderProvider(orderViewModelAdapter: OrderViewModelAdapter): DetailedDishAndOrderProvider =
        DetailedDishAndOrderProvider(orderViewModelAdapter)

    @Provides
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    fun provideCategoryViewModel(
        provider: CategoryAndDishesViewModelProvider,
        adapter: CategoryViewModelAdapter
    ): ViewModel =
        CategoryViewModel(provider, adapter)

    @Provides
    @IntoMap
    @ViewModelKey(DishesViewModel::class)
    fun provideDishesViewModel(
        provider: DishesAndDetailedDishViewModelProvider,
        adapter: DishesViewModelAdapter
    ): ViewModel =
        DishesViewModel(provider, adapter)

    @Provides
    @IntoMap
    @ViewModelKey(DetailedDishViewModel::class)
    fun provideDetailedDishViewModel(
        provider: DetailedDishAndOrderProvider,
        adapter: DetailedDishViewModelAdapter
    ): ViewModel =
        DetailedDishViewModel(provider, adapter)



    @Provides
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    fun provideOrderViewModel(
        adapter: OrderViewModelAdapter
    ): ViewModel =
        OrderViewModel(adapter)

}

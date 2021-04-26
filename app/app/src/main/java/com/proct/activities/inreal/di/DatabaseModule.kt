package com.proct.activities.inreal.di

import androidx.lifecycle.ViewModel
import com.proct.activities.inreal.data.sources.InRealDataLocalSource
import com.proct.activities.inreal.utils.adapters.CategoryViewModelAdapter
import com.proct.activities.inreal.utils.adapters.DishesViewModelAdapter
import com.proct.activities.inreal.utils.providers.CategoryAndDishesViewModelProvider
import com.proct.activities.inreal.viewmodels.category.CategoryViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideInRealDataLocalSource():InRealDataLocalSource =
        InRealDataLocalSource()

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
    fun provideCategoryAdnDishesViewModelProvider(dishesViewModelAdapter: DishesViewModelAdapter): CategoryAndDishesViewModelProvider =
        CategoryAndDishesViewModelProvider(dishesViewModelAdapter)

    @Provides
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    fun provideConfigureViewModel(
        provider: CategoryAndDishesViewModelProvider,
        adapter: CategoryViewModelAdapter
    ): ViewModel =
        CategoryViewModel(provider, adapter)


//    @Provides
//    @IntoMap
//    @ViewModelKey(DishesViewModel::class)
//    fun provideDishesViewModel(
//        provider: CategoryAndDishesViewModelProvider,
//        adapter: DishesViewModelAdapter
//    ): ViewModel =
//        DishesViewModel(provider, adapter)
}
package com.example.commercialpower

import android.content.Context
import androidx.room.Room
import com.example.data.room.ProductDao
import com.example.data.room.ProductDatabase
import com.example.network.service.ProductInterface
import com.example.network.service.SHOPPING_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(SHOPPING_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
    @Provides
    @Singleton
    fun provideAuthRepository(retrofit: Retrofit): ProductInterface {
        return retrofit.create(ProductInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(authInterface: ProductInterface,productDao: ProductDao): com.example.network.service.ProductRepository {
        return com.example.network.service.ProductRepoImpl(authInterface,productDao)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ProductDatabase{
        return  Room.databaseBuilder(context,ProductDatabase::class.java,"Shopping database").build()
    }

    @Singleton
    @Provides
    fun provideProductDao(productDatabase: ProductDatabase): ProductDao{
        return productDatabase.productDao
    }

}
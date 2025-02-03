package com.example.techmarket.di




import com.example.techmarket.core.data.datasource.UserPreferencesRepositoryImpl
import com.example.techmarket.core.data.local.AppPreferencesDataSource
import com.example.techmarket.core.domain.datasource.UserPreferencesRepository
import com.example.techmarket.latech.data.dataSource.account.ProfileRepositoryImpl
import com.example.techmarket.latech.data.dataSource.admin.AdminRepositoryImpl
import com.example.techmarket.latech.data.dataSource.auth.AuthRepositoryImpl
import com.example.techmarket.latech.data.dataSource.cart.CartRepositoryImpl
import com.example.techmarket.latech.data.dataSource.home.HomeRepositoryImpl
import com.example.techmarket.latech.data.dataSource.product.ProductRepositoryImpl
import com.example.techmarket.latech.data.network.Supabase
import com.example.techmarket.latech.domain.dataSource.auth.AuthRepository
import com.example.techmarket.latech.domain.dataSource.product.ProductRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module{
    single { Supabase }
    single { AppPreferencesDataSource(androidContext()) }
    single { UserPreferencesRepositoryImpl(androidContext())}
    singleOf(::UserPreferencesRepositoryImpl){bind<UserPreferencesRepository>()}
    singleOf(::AuthRepositoryImpl){bind<AuthRepository>()}
    singleOf(::ProductRepositoryImpl){bind<ProductRepository>()}
    single { AdminRepositoryImpl(get(),get())}
    single { HomeRepositoryImpl(get()) }
    single { CartRepositoryImpl(get()) }
    single { ProfileRepositoryImpl(get(),get(),get()) }

}
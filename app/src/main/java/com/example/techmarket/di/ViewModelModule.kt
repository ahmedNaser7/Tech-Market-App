package com.example.techmarket.di

import com.example.techmarket.core.data.local.AppPreferencesDataSource
import com.example.techmarket.latech.data.dataSource.account.ProfileRepositoryImpl
import com.example.techmarket.latech.data.dataSource.admin.AdminRepositoryImpl
import com.example.techmarket.latech.data.dataSource.cart.CartRepositoryImpl
import com.example.techmarket.latech.data.dataSource.home.HomeRepositoryImpl
import com.example.techmarket.latech.data.dataSource.product.ProductRepositoryImpl
import com.example.techmarket.latech.data.network.Supabase
import com.example.techmarket.latech.presentation.account.ProfileViewModel
import com.example.techmarket.latech.presentation.admin.AdminViewModel
import com.example.techmarket.latech.presentation.auth.components.viewModel.LoginViewModel
import com.example.techmarket.latech.presentation.auth.components.viewModel.RegisterViewModel
import com.example.techmarket.latech.presentation.cart.CartViewModel
import com.example.techmarket.latech.presentation.connection.components.ConnectionViewModel
import com.example.techmarket.latech.presentation.home.HomeViewModel
import com.example.techmarket.latech.presentation.onBoarding.components.viewModel.OnBoardingViewModel
import com.example.techmarket.latech.presentation.product.ProductViewModel
import com.example.techmarket.latech.presentation.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { HomeViewModel(HomeRepositoryImpl()) }
    viewModel { ProductViewModel(ProductRepositoryImpl(get())) }
    viewModel{ OnBoardingViewModel(androidContext(), AppPreferencesDataSource(androidContext())) }
    viewModel{ AdminViewModel(Supabase, AdminRepositoryImpl(get(),get())) }
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::ConnectionViewModel)
    viewModel{ CartViewModel(CartRepositoryImpl(get())) }
    viewModel{ ProfileViewModel(ProfileRepositoryImpl(get(),get(),get())) }
    viewModel { SearchViewModel(HomeRepositoryImpl()) }
}
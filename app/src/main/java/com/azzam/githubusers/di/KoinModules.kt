package com.azzam.githubusers.di


import com.azzam.githubusers.data.api.RetrofitFactory
import com.azzam.githubusers.data.repository.UsersRepositoryImpl
import com.azzam.githubusers.domain.repository.UsersRepository
import com.azzam.githubusers.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single { UsersRepositoryImpl(get()) as UsersRepository }
}

val viewModelModule = module {
    viewModel { UsersViewModel(get()) }
}

val networkModule = module {
    single { RetrofitFactory.create(get()) }
}
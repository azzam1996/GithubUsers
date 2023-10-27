package com.azzam.githubusers

import android.app.Application
import com.azzam.githubusers.di.networkModule
import com.azzam.githubusers.di.repositoryModule
import com.azzam.githubusers.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import timber.log.Timber

class GithubUsersApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        when (BuildConfig.DEBUG) {
            true -> Timber.plant(Timber.DebugTree())
            else -> {
                //TODO to be handled in release
            }
        }
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@GithubUsersApp)
            val modulesList = arrayListOf<Module>()
            modulesList.addAll(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    networkModule
                )
            )
            modules(modulesList)
        }
    }
}
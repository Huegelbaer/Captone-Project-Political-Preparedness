package com.example.android.politicalpreparedness

import android.app.Application
import com.example.android.politicalpreparedness.data.ElectionDataSource
import com.example.android.politicalpreparedness.data.local.ElectionDao
import com.example.android.politicalpreparedness.data.local.ElectionDatabase
import com.example.android.politicalpreparedness.data.remote.CivicsApi
import com.example.android.politicalpreparedness.data.repository.ElectionRepository
import com.example.android.politicalpreparedness.presentation.election.ElectionsViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            viewModel {
                ElectionsViewModel(get())
            }
            single { CivicsApi(applicationContext).retrofitService }
            single { Dispatchers.IO }
            single { ElectionDatabase.getInstance(this@MainApplication).electionDao as ElectionDao }
            single {
                ElectionRepository(get(), get()) as ElectionDataSource
            }
        }

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
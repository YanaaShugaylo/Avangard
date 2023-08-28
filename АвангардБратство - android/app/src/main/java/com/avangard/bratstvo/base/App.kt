package com.avangard.bratstvo.base

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.avangard.bratstvo.authorization.authorizationModule
import com.avangard.bratstvo.education.educationModules
import com.avangard.bratstvo.home.homeModule
import com.avangard.bratstvo.intro.introModules
import com.avangard.bratstvo.points_exchange.pointsExchangeModules
import com.avangard.bratstvo.profile.profileModules
import com.avangard.bratstvo.start.startAppModule
import com.avangard.bratstvo.tasks.tasksModules
import com.avangard.bratstvo.user.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setKoin()
    }

    private fun setKoin() {
        startKoin {
            androidContext(this@App)

            modules(baseModule())
            modules(baseNetworkModule())
            modules(startAppModule())
            modules(authorizationModule())
            modules(educationModules())
            modules(homeModule())
            modules(introModules())
            modules(pointsExchangeModules())
            modules(profileModules())
            modules(tasksModules())
            modules(userModule())
        }
    }
}
package com.melorriaga.kokemon.injection.component

import com.melorriaga.kokemon.injection.module.ApplicationModule
import com.melorriaga.kokemon.injection.module.DataModule
import com.melorriaga.kokemon.injection.module.DetailsModule
import com.melorriaga.kokemon.injection.module.MainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        DataModule::class
))
interface ApplicationComponent {

    fun plus(module: MainModule): MainComponent
    fun plus(module: DetailsModule): DetailsComponent

}

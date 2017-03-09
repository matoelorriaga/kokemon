package com.melorriaga.kokemon.injection.component

import com.melorriaga.kokemon.injection.module.MainModule
import com.melorriaga.kokemon.injection.scope.ActivityScope
import com.melorriaga.kokemon.view.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(
        MainModule::class
))
interface MainComponent {

    fun injectTo(activity: MainActivity)

}

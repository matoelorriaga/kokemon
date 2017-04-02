package com.melorriaga.kokemon.injection.component

import com.melorriaga.kokemon.injection.module.MainModule
import com.melorriaga.kokemon.injection.scope.FragmentScope
import com.melorriaga.kokemon.view.main.MainFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = arrayOf(
        MainModule::class
))
interface MainComponent {

    fun injectTo(fragment: MainFragment)

}

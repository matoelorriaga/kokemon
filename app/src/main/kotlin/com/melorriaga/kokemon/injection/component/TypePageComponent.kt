package com.melorriaga.kokemon.injection.component

import com.melorriaga.kokemon.injection.module.TypePageModule
import com.melorriaga.kokemon.injection.scope.FragmentScope
import com.melorriaga.kokemon.view.types.TypePageFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = arrayOf(
        TypePageModule::class
))
interface TypePageComponent {

    fun injectTo(fragment: TypePageFragment)

}

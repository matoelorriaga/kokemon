package com.melorriaga.kokemon.injection.component

import com.melorriaga.kokemon.injection.module.DetailsModule
import com.melorriaga.kokemon.injection.scope.FragmentScope
import com.melorriaga.kokemon.view.details.DetailsActivity
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = arrayOf(
        DetailsModule::class
))
interface DetailsComponent {

    fun injectTo(activity: DetailsActivity)

}

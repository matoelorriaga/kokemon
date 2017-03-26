package com.melorriaga.kokemon.injection.component

import com.melorriaga.kokemon.injection.module.TypesModule
import com.melorriaga.kokemon.injection.scope.ActivityScope
import com.melorriaga.kokemon.view.types.TypesActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(
        TypesModule::class
))
interface TypesComponent {

    fun injectTo(activity: TypesActivity)

}

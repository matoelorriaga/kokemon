package com.melorriaga.kokemon

import com.melorriaga.kokemon.injection.component.ApplicationComponent
import com.melorriaga.kokemon.injection.component.DaggerApplicationComponent
import com.melorriaga.kokemon.injection.module.ApplicationModule
import com.melorriaga.kokemon.injection.module.DataModule
import io.appflate.restmock.RESTMockServer

class TestKokemonApp : KokemonApp() {

    override val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .dataModule(DataModule(RESTMockServer.getUrl()))
                .build()
    }

}

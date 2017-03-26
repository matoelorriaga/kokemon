package com.melorriaga.kokemon

import android.app.Application
import android.content.Context

import io.appflate.restmock.android.RESTMockTestRunner

class TestRunner : RESTMockTestRunner() {

    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestKokemonApp::class.java.name, context)
    }

}

package com.melorriaga.kokemon.activity

import io.appflate.restmock.RESTMockServer.reset
import org.junit.After
import org.junit.Before

abstract class BaseActivityTest {

    @Before
    fun setUp() {
        reset()
    }

    @After
    fun tearDown() {
        Thread.sleep(1000)
    }

}

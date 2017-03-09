package com.melorriaga.kokemon

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.junit.MatcherAssert.assertThat
import org.junit.Test
import kotlin.test.assertTrue

class CollectionsUnitTest {

    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    @Test
    fun testAny() {
        assertTrue { list.any { it > 5 } }
    }

    @Test
    fun testAll() {
        assertTrue { list.all { it > 0 } }
    }

    @Test
    fun testCount() {
        assertThat(list.count { it > 5 }, `is`(4))
    }

    @Test
    fun testFold() {
        assertThat(list.fold(10) { total, next -> total + next }, `is`(55))
    }

    @Test
    fun testFoldRight() {
        assertThat(list.foldRight(5) { total, next -> total + next }, `is`(50))
    }

}

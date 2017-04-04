package com.melorriaga.kokemon.activity

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import android.support.test.rule.ActivityTestRule
import com.melorriaga.kokemon.R
import com.melorriaga.kokemon.assertion.RecyclerViewItemCountAssertion
import com.melorriaga.kokemon.assertion.ViewPagerItemCountAssertion
import com.melorriaga.kokemon.view.types.TypesActivity
import io.appflate.restmock.RESTMockServer.whenGET
import io.appflate.restmock.RequestsVerifier.verifyGET
import io.appflate.restmock.utils.RequestMatchers.pathEndsWith
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class TypesActivityTest : BaseActivityTest() {

    @Rule
    @JvmField
    var typesActivityTestRule = ActivityTestRule(
            TypesActivity::class.java,  // activityClass
            true,                       // initialTouchMode
            false                       // launchActivity
    )

    @Test
    fun testShowPokemonTypes_success() {
        whenGET(pathEndsWith("type"))
                .delay(TimeUnit.SECONDS, 1)
                .thenReturnFile(200, "getPokemonTypes_200.json")
        whenGET(pathEndsWith("type/1"))
                .delay(TimeUnit.SECONDS, 1)
                .thenReturnFile(200, "getPokemonType_1_200.json")
        whenGET(pathEndsWith("type/2"))
                .delay(TimeUnit.SECONDS, 1)
                .thenReturnFile(200, "getPokemonType_2_200.json")

        val intent = Intent()
        typesActivityTestRule.launchActivity(intent)

        onView(withId(R.id.view_pager)).check(ViewPagerItemCountAssertion(20))
        onView(allOf(withId(R.id.recycler_view), isDisplayed())).check(RecyclerViewItemCountAssertion(102))
        onView(withText(R.string.done)).check(matches(withEffectiveVisibility(VISIBLE)))

        verifyGET(pathEndsWith("type")).invoked()
        verifyGET(pathEndsWith("type/1")).invoked()
        verifyGET(pathEndsWith("type/2")).invoked()
    }

}

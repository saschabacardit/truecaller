package com.tbc.truecallersimple

import com.tbc.truecallersimple.model.remote.GetPageImpl
import kotlinx.coroutines.test.runTest
import org.jsoup.Jsoup
import org.junit.Test

import org.junit.Assert.*

class GetPageTest {

    /**
     * This is a basic test, normally you'd like to have a 'default' response for an API call,
     * however because of the extreme length (and changing nature) of a webpage, we can settle
     * for just 'isNotEmpty' so we know we have data at all, in an even more perfect world,
     * you can run up your own server for this test to test multiple responses w/o mockito usage
     * in case you want a finer and more granular control or similar, albeit it is often overkill
     * */
    @Test
    fun addition_isCorrect() = runTest {
        val getPage = GetPageImpl()
        assert(getPage.getPage().isNotEmpty())
    }
}
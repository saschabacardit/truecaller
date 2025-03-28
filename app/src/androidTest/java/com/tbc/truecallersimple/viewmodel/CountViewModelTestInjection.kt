package com.tbc.truecallersimple.viewmodel

import androidx.test.filters.SmallTest
import com.tbc.truecallersimple.HiltTestRunner
import com.tbc.truecallersimple.model.remote.GetPageService
import com.tbc.truecallersimple.model.remote.RemoteModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
@UninstallModules(RemoteModule::class)
class CountViewModelTestInjection {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @field:[Inject Named("mock_interface")]
    lateinit var fakeImpl: GetPageService

    lateinit var viewModel: CountViewModel
    private val fakePage = "123456789 123456789 123456789\n1234X6789"


    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = CountViewModel(fakeImpl)
    }

    /**
     * Simply mocked test, we return a fake (and easier to work with) result
     * We know the possible result and all we want to ensure is that the viewmodel
     * updates properly
     * */
    @Test
    fun get_state() = runTest {
        assert(viewModel.state.value.page==fakePage)
        assert(viewModel.state.value.webPageLivaDataWords==fakePage.split("\\s".toRegex()))
        assert(viewModel.state.value.webPageLiveDataCharacters[0]=='5')
        assert(viewModel.state.value.webPageLiveDataCharacters== listOf('5', '\n'))
    }

}
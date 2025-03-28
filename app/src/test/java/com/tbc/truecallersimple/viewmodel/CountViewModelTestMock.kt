package com.tbc.truecallersimple.viewmodel

import com.tbc.truecallersimple.MainDispatcherRule
import com.tbc.truecallersimple.model.remote.GetPageImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Example of a unit test w/o using dagger+hilt instead mocking through framework
 * */
@RunWith(JUnit4::class)
class CountViewModelTestMock {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private val getPage = mockk<GetPageImpl>()
    private lateinit var viewModel: CountViewModel
    val fakePage = "123456789 123456789 123456789\n1234X6789"


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        coEvery{ getPage.getPage()} returns fakePage
        viewModel = CountViewModel(getPage)
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
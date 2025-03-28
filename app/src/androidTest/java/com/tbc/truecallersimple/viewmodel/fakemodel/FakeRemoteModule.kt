package com.tbc.truecallersimple.viewmodel.fakemodel

import com.tbc.truecallersimple.model.remote.GetPageService
import com.tbc.truecallersimple.model.remote.RemoteModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named

@Module
@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [RemoteModule::class]
) object FakeRemoteModule {

    @Provides
    @Named("mock_interface")
    fun bindGetFakePageService(): GetPageService = GetPageFakeImpl()
}
package com.tbc.truecallersimple.model.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RemoteModule {

    @Provides
    fun bindGetPageService(): GetPageService = GetPageImpl()
}
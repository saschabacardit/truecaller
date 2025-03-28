package com.tbc.truecallersimple.model.remote

import dagger.Binds

interface GetPageService {
    suspend fun getPage(): String
}
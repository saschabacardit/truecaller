package com.tbc.truecallersimple.viewmodel.fakemodel

import com.tbc.truecallersimple.model.remote.GetPageService
import javax.inject.Inject

class GetPageFakeImpl  @Inject constructor() : GetPageService {
    override suspend fun getPage(): String {
        return "123456789 123456789 123456789\n1234X6789"
    }
}
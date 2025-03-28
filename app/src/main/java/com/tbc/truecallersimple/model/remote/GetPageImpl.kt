package com.tbc.truecallersimple.model.remote

import org.jsoup.Jsoup
import javax.inject.Inject

class GetPageImpl @Inject constructor() : GetPageService {
    override suspend fun getPage(): String {
        return Jsoup.connect("https://www.truecaller.com/blog/life-at-truecaller/life-as-an-android-engineer").get().html()
    }
}
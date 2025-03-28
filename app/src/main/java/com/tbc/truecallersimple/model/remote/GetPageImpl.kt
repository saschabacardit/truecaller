package com.tbc.truecallersimple.model.remote

import org.jsoup.Jsoup
import javax.inject.Inject

class GetPageImpl @Inject constructor() : GetPageService {
    /**
     * While a much better solution would be to manually have all possible
     * java exceptions mapped via using a raw http call and having full control
     * doing so would sky-rocket the complexity of the project, and we can just use
     * the empty string to signal we are waiting for the page to update instead saving us dev time
     * */
    override suspend fun getPage(): String {
        var string = ""
        try{
            string = Jsoup.connect("https://www.truecaller.com/blog/life-at-truecaller/life-as-an-android-engineer").get().html()
        } catch (_: Exception){

        }
        return string
    }
}
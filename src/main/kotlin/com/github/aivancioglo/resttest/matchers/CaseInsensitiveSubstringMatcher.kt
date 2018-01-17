package com.github.aivancioglo.resttest.matchers

import org.hamcrest.Description
import org.hamcrest.Factory
import org.hamcrest.TypeSafeMatcher

class CaseInsensitiveSubstringMatcher(private val subString: String) : TypeSafeMatcher<String>() {
    override fun describeTo(description: Description) {
        description.appendText("containing substring \"${this.subString}\"")
    }

    override fun matchesSafely(actualString: String) = actualString.toLowerCase().contains(this.subString.toLowerCase())

    companion object {
        @Factory
        @JvmStatic
        fun containsIgnoringCase(subString: String) = CaseInsensitiveSubstringMatcher(subString)
    }
}

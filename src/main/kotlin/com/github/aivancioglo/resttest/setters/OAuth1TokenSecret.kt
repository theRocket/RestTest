package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

class OAuth1TokenSecret<in T : HTTPRequest<*>>(private val tokenSecret: String) : Setter<T> {
    override fun update(request: T) {
        request.oAuth1.tokenSecret = tokenSecret
    }
}
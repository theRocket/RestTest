package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.verifiers.*
import io.restassured.http.Header
import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.response.Response
import org.hamcrest.Matcher

/**
 * This class is using for HTTP/HTTPS response validation and processing.
 *
 * @constructor is setting response of requestSpecification.
 * @param response of your requestSpecification.
 */
class HTTPResponse(private val response: Response) {

    /**
     * Making response validation.
     *
     * @param verifiers for response validation.
     */
    fun assertThat(vararg verifiers: Verifier) {
        verifiers.forEach { it.verify(response) }
    }

    /**
     * Making response validation.
     *
     * @param code of response.
     * @param verifiers for response validation.
     */
    fun assertThat(code: Int, vararg verifiers: Verifier) {
        response.then().statusCode(code)
        verifiers.forEach { it.verify(response) }
    }

    /**
     * Making response validation.
     *
     * @param statusCode of response.
     * @param verifiers for response validation.
     */
    fun assertThat(statusCode: StatusCode, vararg verifiers: Verifier) {
        response.then().statusCode(statusCode.code)
        verifiers.forEach { it.verify(response) }
    }

    /**
     * Making response validation.
     *
     * @param code of response.
     * @param jsonSchema for response validation.
     * @param verifiers for response validation.
     */
    fun assertThat(code: Int, jsonSchema: String, vararg verifiers: Verifier) {
        response.then().statusCode(code).body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        verifiers.forEach { it.verify(response) }
    }

    /**
     * Making response validation.
     *
     * @param statusCode of response.
     * @param jsonSchema for response validation.
     * @param verifiers for response validation.
     */
    fun assertThat(statusCode: StatusCode, jsonSchema: String, vararg verifiers: Verifier) {
        response.then().statusCode(statusCode.code).body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        verifiers.forEach { it.verify(response) }
    }

    /**
     * Response logging.
     *
     * @return this class instance.
     */
    fun log(): HTTPResponse {
        response.then().log().all()
        return this
    }

    /**
     * Response logging.
     *
     * @param isPretty is setting if log is pretty.
     * @return this class instance.
     */
    fun log(isPretty: Boolean): HTTPResponse {
        response.then().log().all(isPretty)
        return this
    }

    /**
     * For getting response code of last response.
     *
     * @return response code.
     */
    fun getStatusCode() = response.statusCode

    /**
     * For getting body of last response as string.
     *
     * @return body as string.
     */
    fun getBody() = response.body.asString()

    /**
     * Deserialize response body as your model class.
     *
     * @param cls that of your module.
     * @param T is response model.
     * @return deserialized body as your model class.
     */
    fun <T> `as`(cls: Class<T>) = response.`as`(cls)

    /**
     * Extract value by JSON path.
     *
     * @param path1 JSON path1.
     * @param path2 JSON path2.
     * @param T is returning type.
     * @return path value.
     */
    fun <T> path(path1: String, vararg path2: String): T = response.path(path1, *path2)

    /**
     * Get response header by name.
     *
     * @param name of header.
     * @return header value.
     */
    fun getHeader(name: String) = response.getHeader(name)

    /**
     * Returns list of headers.
     *
     * @return list.
     */
    fun getHeaders(): List<Header> = response.headers.asList()

    /**
     * Check if header exist.
     *
     * @param name of header.
     * @return boolean value.
     */
    fun isHeaderExist(name: String) = response.headers.hasHeaderWithName(name)
}
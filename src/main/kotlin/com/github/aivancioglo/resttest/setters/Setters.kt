package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest
import io.restassured.http.ContentType
import java.io.InputStream
import java.net.URLDecoder
import java.util.regex.Pattern

/**
 * Abstract class for using static functions to call setters.
 */
abstract class Setters {
    companion object {

        /**
         * Getting content type setter.
         *
         * @param type of content.
         * @return ContentType setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> contentType(type: ContentType) = object : Setter<T> {
            override fun update(request: T) {
                request.requestSpecification.contentType(type)
            }
        }

        /**
         * Getting content type setter.
         *
         * @param type of content.
         * @return ContentType setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> contentType(type: String) = object : Setter<T> {
            override fun update(request: T) {
                request.requestSpecification.contentType(type)
            }
        }

        /**
         * Getting param setter.
         *
         * @param key of param.
         * @param value of param.
         * @return Param setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> param(key: String, value: Any) = object : Setter<T> {
            override fun update(request: T) {
                request.requestSpecification.param(key, value)
            }
        }

        /**
         * Getting query param setter.
         *
         * @param key of query param.
         * @param value of query param.
         * @return QueryParam setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> queryParam(key: String, value: Any) = object : Setter<T> {
            override fun update(request: T) {
                request.requestSpecification.queryParam(key, value)
            }
        }

        /**
         * Getting form param setter.
         *
         * @param key of form param.
         * @param value of form param.
         * @return FormParam setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> formParam(key: String, value: Any) = object : Setter<T> {
            override fun update(request: T) {
                request.requestSpecification.formParam(key, value)
            }
        }

        /**
         * Getting header setter.
         *
         * @param name of header.
         * @param value of header.
         * @return Header setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> header(name: String, value: String) = object : Setter<T> {
            override fun update(request: T) {
                request.requestSpecification.header(name, value)
            }
        }

        /**
         * Getting requestSpecification body setter.
         *
         * @param body of requestSpecification.
         * @return Body setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> body(body: Any) = object : Setter<T> {
            override fun update(request: T) {
                request.requestSpecification.body(body)
            }
        }

        /**
         * Getting requestSpecification protocol setter.
         *
         * @param protocol of requestSpecification.
         * @return Protocol setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> protocol(protocol: String) = object : Setter<T> {
            override fun update(request: T) {
                request.protocol = protocol
            }
        }

        /**
         * Getting requestSpecification host setter.
         *
         * @param host of requestSpecification.
         * @return Host setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> host(host: String) = object : Setter<T> {
            override fun update(request: T) {
                request.host = host
            }
        }

        /**
         * Getting requestSpecification path setter.
         *
         * @param path of requestSpecification.
         * @return Path setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> path(path: String) = object : Setter<T> {
            override fun update(request: T) {
                val m = Pattern.compile("[?&]([^&=]+)=([^&=]+)").matcher(path)

                while (m.find())
                    request.requestSpecification.queryParam(m.group(1), URLDecoder.decode(m.group(2), "UTF-8"))

                request.requestSpecification.basePath(path)
            }
        }

        /**
         * Getting requestSpecification port setter.
         *
         * @param port of requestSpecification.
         * @return Port setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> port(port: Int) = object : Setter<T> {
            override fun update(request: T) {
                request.requestSpecification.port(port)
            }
        }

        /**
         * Getting path param setter.
         *
         * @param key of path param.
         * @param value of path param.
         * @return PathParam setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> pathParam(key: String, value: String) = object : Setter<T> {
            override fun update(request: T) {
                request.requestSpecification.pathParam(key, value)
            }
        }

        /**
         * Getting multi part setter.
         *
         * @param controlName of the body part. In HTML this is the attribute name of the input tag.
         * @param fileName of the content you're uploading.
         * @param stream you want to requestSpecification.
         * @return MultiPart setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> multiPart(controlName: String, fileName: String, stream: InputStream) = object : Setter<T> {
            override fun update(request: T) {
                request.requestSpecification.multiPart(controlName, fileName, stream)
            }
        }

    }
}
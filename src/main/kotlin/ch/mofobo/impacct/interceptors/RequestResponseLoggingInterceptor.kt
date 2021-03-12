package ch.mofobo.impacct.interceptors

import org.slf4j.LoggerFactory
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.util.StreamUtils
import java.io.IOException
import java.nio.charset.Charset
import kotlin.jvm.Throws

/*
Copyright 2018 Okta, Inc.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

class RequestResponseLoggingInterceptor : ClientHttpRequestInterceptor {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Throws(IOException::class)
    override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
        logRequest(request, body)
        val response = execution.execute(request, body)
        logResponse(response)
        return response
    }

    @Throws(IOException::class)
    private fun logRequest(request: HttpRequest, body: ByteArray) {
        if (log.isDebugEnabled) {
            log.debug("===========================request begin================================================")
            log.debug("URI         : {}", request.uri)
            log.debug("Method      : {}", request.method)
            log.debug("Headers     : {}", request.headers)
            log.debug("Request body: {}", String(body, Charset.forName("UTF-8")))
            log.debug("==========================request end================================================")
        }
    }

    @Throws(IOException::class)
    private fun logResponse(response: ClientHttpResponse) {
        if (log.isDebugEnabled) {
            log.debug("============================response begin==========================================")
            log.debug("Status code  : {}", response.statusCode)
            log.debug("Status text  : {}", response.statusText)
            log.debug("Headers      : {}", response.headers)
            log.debug("Response body: {}", StreamUtils.copyToString(response.body, Charset.defaultCharset()))
            log.debug("=======================response end=================================================")
        }
    }
}
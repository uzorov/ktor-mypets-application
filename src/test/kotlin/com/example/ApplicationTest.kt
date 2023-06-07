package com.example

import com.example.features.applications.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import com.example.plugins.*
import io.ktor.util.*
import kotlinx.serialization.json.Json


class ApplicationTest {
    @OptIn(InternalAPI::class)
    @Test
    fun testFetchAppsByUserId() = testApplication {
        application {
            configureApplicationRouting()
        }
        client.post("/fetch_apps_by_user_id") {
            // Set the content type and body of the request
            contentType(ContentType.Application.Json)
            body = ApplicationIdReceive(1)
        }.apply {
            // Assert the status and body of the response
            assertEquals(HttpStatusCode.OK, status)
            val applications = Json.decodeFromString<List<ApplicationResponse>>(bodyAsText())
            assertEquals(2, applications.size)
            assertEquals(1, applications[0].client.id)
            assertEquals(2, applications[1].client.id)
        }
    }

    @Test
    fun testAddApplication() = testApplication {
        application {
            configureApplicationRouting()
        }
        client.post("/add_application") {
            // Set the content type and body of the request
            contentType(ContentType.Application.Json)
            body = ApplicationReceive(
                client = 1,
                animal = 2,
                hosingConditions = "Apartment",
                aboutTheClient = "I love cats",
                usersCity = "Moscow"
            )
        }.apply {
            // Assert the status and body of the response
            assertEquals(HttpStatusCode.OK, status)
            val application = Json.decodeFromString<ApplicationGetResponse>(bodyAsText())
            assertEquals(3, application.code)
        }
    }

    @Test
    fun testDeleteApplication() = testApplication {
        application {
            configureApplicationRouting()
        }
        client.post("/delete_application") {
            // Set the content type and body of the request
            contentType(ContentType.Application.Json)
            body = ApplicationIdReceive(2)
        }.apply {
            // Assert the status and body of the response
            assertEquals(HttpStatusCode.OK, status)
            val code = Json.decodeFromString<Number>(bodyAsText())
            assertEquals(1, code.toInt())
        }
    }

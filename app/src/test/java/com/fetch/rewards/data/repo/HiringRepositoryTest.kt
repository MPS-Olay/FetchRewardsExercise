package com.fetch.rewards.data.repo

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class HiringRepositoryTest {

    private lateinit var repository: HiringRepository
    private lateinit var client: HttpClient

    @Test
    @DisplayName("✅ Returns a list of items when the response is valid")
    fun `getItems returns list of items when response is valid`() = runTest {
        // Given
        mockHttpResponse(
            HttpStatusCode.OK,
            """
                [
                    {"id": 1, "listId": 1, "name": "Item 1"},
                    {"id": 2, "listId": 2, "name": "Item 2"}
                ]
            """
        )

        // When
        val items = repository.getItems()

        // Then
        Assertions.assertEquals(2, items.size)
        Assertions.assertEquals("Item 1", items[0].name)
    }

    @Test
    @DisplayName("🚫 Returns an empty list when the response is empty")
    fun `getItems returns empty list when response is empty`() = runTest {
        // Given
        mockHttpResponse(HttpStatusCode.OK, "[]")

        // When
        val items = repository.getItems()

        // Then
        Assertions.assertTrue(items.isEmpty())
    }

    // Helper: Mock the HTTP response based on provided status and content
    private fun mockHttpResponse(
        status: HttpStatusCode,
        responseContent: String = ""
    ) {
        val mockEngine = MockEngine { request ->
            respond(
                content = responseContent,
                status = status,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        client = HttpClient(mockEngine) {
            install(JsonFeature) {
                KotlinxSerializer()
            }
        }
        repository = HiringRepoImpl(client) // Reinitialize with new client
    }
}

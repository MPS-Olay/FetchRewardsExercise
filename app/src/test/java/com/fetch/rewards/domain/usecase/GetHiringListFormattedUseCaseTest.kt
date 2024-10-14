package com.fetch.rewards.domain.usecase

import com.fetch.rewards.data.entity.Hiring
import com.fetch.rewards.data.repo.HiringRepository
import io.mockk.coEvery
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GetHiringListFormattedUseCaseTest {

    private lateinit var repository: HiringRepository
    private lateinit var useCase: GetHiringListFormattedUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk() // Mock the repository
        useCase = GetHiringListFormattedUseCase(repository) // Initialize the use case
    }

    @Test
    @DisplayName("✅ Returns a grouped and sorted map when items are valid")
    fun `invoke returns grouped and sorted map when items are valid`() = runTest {
        // Given
        val mockItems = listOf(
            Hiring(1, 1, "Apple"),
            Hiring(2, 1, "Banana"),
            Hiring(3, 2, "Carrot"),
            Hiring(4, 2, null) // This item will be filtered out
        )
        coEvery { repository.getItems() } returns mockItems

        // When
        val result = useCase()

        // Then
        Assertions.assertEquals(2, result.size)
        Assertions.assertEquals(listOf(Hiring(1, 1, "Apple"), Hiring(2, 1, "Banana")), result["1"])
        Assertions.assertEquals(listOf(Hiring(3, 2, "Carrot")), result["2"])
    }

    @Test
    @DisplayName("🚫 Returns empty map when repository returns no items")
    fun `invoke returns empty map when repository returns no items`() = runTest {
        // Given
        coEvery { repository.getItems() } returns emptyList()

        // When
        val result = useCase()

        // Then
        Assertions.assertTrue(result.isEmpty())
    }

    @Test
    @DisplayName("🚫 Returns empty map when all items have blank or null names")
    fun `invoke returns empty map when all items have blank or null names`() = runTest {
        // Given
        val mockItems = listOf(
            Hiring(1, 1, ""),
            Hiring(2, 2, null)
        )
        coEvery { repository.getItems() } returns mockItems

        // When
        val result = useCase()

        // Then
        Assertions.assertTrue(result.isEmpty())
    }
}

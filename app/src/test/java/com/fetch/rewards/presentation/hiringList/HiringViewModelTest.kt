package com.fetch.rewards.presentation.hiringList

import app.cash.turbine.test
import com.fetch.rewards.data.entity.Hiring
import com.fetch.rewards.domain.usecase.GetHiringListFormattedUseCase
import com.fetch.rewards.testUtil.MainDispatcherExtension
import io.mockk.coEvery
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class, MainDispatcherExtension::class)
class HiringViewModelTest {
    private lateinit var getHiringListFormattedUseCase: GetHiringListFormattedUseCase
    private lateinit var viewModel: HiringViewModel

    @BeforeEach
    fun setUp() {
        getHiringListFormattedUseCase = mockk()
    }

    @Test
    @DisplayName("✅ ViewModel updates state with hiring map when use case returns valid data")
    fun `state updates with hiring map when use case returns valid data`() = runTest {
        // Given
        val hiringMap = mapOf("1" to listOf(Hiring(1, 1, "John")))
        coEvery { getHiringListFormattedUseCase() } returns hiringMap

        // When
        viewModel = HiringViewModel(getHiringListFormattedUseCase)

        // Then
        viewModel.state.test {
            val successState = awaitItem()
            Assertions.assertFalse(successState.isLoading)
            Assertions.assertEquals(hiringMap, successState.hiringMap)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    @DisplayName("🚫 ViewModel updates state with empty map when use case returns no data")
    fun `state updates with empty map when use case returns no data`() = runTest {
        // Given
        coEvery { getHiringListFormattedUseCase() } returns emptyMap()

        // When
        viewModel = HiringViewModel(getHiringListFormattedUseCase)

        // Then
        viewModel.state.test {
            val successState = awaitItem()
            Assertions.assertFalse(successState.isLoading)
            Assertions.assertTrue(successState.hiringMap.isEmpty())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    @DisplayName("🚫 ViewModel handles exception and updates state with error message")
    fun `state updates with error when use case throws exception`() = runTest {
        // Given
        val exception = RuntimeException("Test exception")
        coEvery { getHiringListFormattedUseCase() } throws exception

        // When
        viewModel = HiringViewModel(getHiringListFormattedUseCase)

        // Then
        viewModel.state.test {
            val errorState = awaitItem()
            Assertions.assertFalse(errorState.isLoading)
            Assertions.assertEquals("Something went wrong!", errorState.error)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
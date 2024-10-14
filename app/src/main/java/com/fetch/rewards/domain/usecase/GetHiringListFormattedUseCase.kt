package com.fetch.rewards.domain.usecase

import com.fetch.rewards.data.repo.HiringRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetHiringListFormattedUseCase(private val repo: HiringRepository) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repo.getItems()
            .filter { !it.name.isNullOrBlank() } // Filter out items with blank or null names
            .sortedWith(compareBy({ it.listId }, { it.name })) // Sort by listId and name
            .groupBy { it.listId.toString() }
    }
}

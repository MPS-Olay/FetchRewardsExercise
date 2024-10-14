package com.fetch.rewards.presentation.hiringList

import com.fetch.rewards.data.entity.Hiring

data class HiringListState(
    val isLoading: Boolean = false,
    val hiringMap: Map<String, List<Hiring>> = emptyMap(),
    val error: String? = null
)

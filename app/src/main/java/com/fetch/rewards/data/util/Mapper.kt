package com.fetch.rewards.data.util

import com.fetch.rewards.data.dto.HiringDTO
import com.fetch.rewards.data.entity.Hiring

val HiringDTO.toEntity
    get() = Hiring(
        id = id,
        listId = listId,
        name = name,
    )
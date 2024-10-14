package com.fetch.rewards.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class HiringDTO(val id: Int, val listId: Int, val name: String?)

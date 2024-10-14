package com.fetch.rewards.data.repo

import com.fetch.rewards.data.dto.HiringDTO
import com.fetch.rewards.data.entity.Hiring
import com.fetch.rewards.data.util.toEntity
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface HiringRepository {
    suspend fun getItems(): List<Hiring>
}

class HiringRepoImpl(private val client: HttpClient) : HiringRepository {
    override suspend fun getItems(): List<Hiring> {
        return client.get<List<HiringDTO>>(urlString = "hiring.json").map { it.toEntity }
    }
}
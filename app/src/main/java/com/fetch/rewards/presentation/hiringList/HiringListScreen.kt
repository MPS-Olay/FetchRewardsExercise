package com.fetch.rewards.presentation.hiringList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fetch.rewards.data.entity.Hiring
import org.koin.androidx.compose.koinViewModel

@Composable
fun HiringListScreen(
    modifier: Modifier = Modifier,
    viewModel: HiringViewModel = koinViewModel<HiringViewModel>(),
) {
    val state by viewModel.state.collectAsState()
    Box(modifier, contentAlignment = Alignment.Center) {
        HiringList(state.hiringMap, Modifier.fillMaxSize())
        if (state.isLoading) CircularProgressIndicator()
    }
}

@Composable
private fun HiringList(hiringMap: Map<String, List<Hiring>>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        hiringMap.forEach { (headerId, hiringList) ->
            item { HiringItemHeader(headerId, Modifier.fillMaxWidth().padding(8.dp)) }
            items(hiringList) { hire ->
                HiringItem(hire, Modifier.fillMaxWidth().padding(start = 16.dp, bottom = 4.dp))
            }
        }
    }
}

@Composable
private fun HiringItemHeader(headerText: String, modifier: Modifier = Modifier) {
    Text(
        text = "List ID: $headerText",
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun HiringItem(hiring: Hiring, modifier: Modifier = Modifier) = with(hiring) {
    Text(
        text = "ID: $id, Name: $name",
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

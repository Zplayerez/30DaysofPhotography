package com.example.a30daysofphotography.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.a30daysofphotography.R
import com.example.a30daysofphotography.data.DataSource
import com.example.a30daysofphotography.ui.components.ChallengeCard

enum class ViewType {
    LIST, GRID
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var viewType by remember { mutableStateOf(ViewType.LIST) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    IconButton(onClick = {
                        viewType = if (viewType == ViewType.LIST) ViewType.GRID else ViewType.LIST
                    }) {
                        Icon(
                            painter = painterResource(id = if (viewType == ViewType.LIST)
                                R.drawable.ic_grid else R.drawable.ic_list),
                            contentDescription = "切换视图"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        when (viewType) {
            ViewType.LIST -> {
                LazyColumn(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(DataSource.challenges) { challenge ->
                        ChallengeCard(photoChallenge = challenge)
                    }
                }
            }
            ViewType.GRID -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(DataSource.challenges) { challenge ->
                        ChallengeCard(photoChallenge = challenge)
                    }
                }
            }
        }
    }
}
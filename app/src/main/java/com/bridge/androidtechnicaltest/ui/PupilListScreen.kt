package com.bridge.androidtechnicaltest.ui

import ErrorItem
import LoadingIndicator
import LoadingIndicatorSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bridge.androidtechnicaltest.R
import com.bridge.androidtechnicaltest.domain.Pupil
import com.bridge.androidtechnicaltest.ui.components.PupilItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PupilListScreen(
    viewModel: PupilViewModel,
    onPupilClick: (Pupil) -> Unit,
    onAddPupilClick: () -> Unit
) {

    val pupils = viewModel.pupils.collectAsLazyPagingItems()
    val state = rememberPullToRefreshState()
    val isRefreshing = pupils.loadState.refresh is LoadState.Loading

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = { pupils.refresh() },
            state = state,
            contentAlignment = Alignment.Center,
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = isRefreshing,
                    containerColor = Color.White,
                    color = Color.Black,
                    state = state
                )
            }
        ) {
            LazyColumn {
                items(pupils.itemCount) { index ->
                    val pupil = pupils[index]
                    if (pupil != null) {
                        PupilItem(pupil, onPupilClicked = onPupilClick)
                    }
                }

                if (pupils.loadState.append is LoadState.Loading) {
                    item { LoadingIndicator(indicatorSize = LoadingIndicatorSize.SMALL) }
                }

                if (pupils.loadState.append is LoadState.Error || pupils.loadState.refresh is LoadState.Error) {
                    item {
                        ErrorItem {
                            pupils.retry()
                        }
                    }
                }

            }
        }

        FloatingActionButton(
            onClick = onAddPupilClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = colorResource(R.color.colorPrimary),
            contentColor = Color.White
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Pupil")
        }
    }
}
package io.github.antwhale.compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.antwhale.compose.models.TweetListItem
import io.github.antwhale.compose.repository.TweetRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: TweetRepository) : ViewModel() {
    val tweets: StateFlow<List<TweetListItem>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            repository.getTweets("motivation")
        }
    }
}
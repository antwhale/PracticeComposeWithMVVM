package io.github.antwhale.compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.antwhale.compose.repository.TweetRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository : TweetRepository
) : ViewModel() {
    val categories: StateFlow<List<String>>
        get() = repository.categories

    init {
        viewModelScope.launch {
            repository.getCategories()
        }
    }
}
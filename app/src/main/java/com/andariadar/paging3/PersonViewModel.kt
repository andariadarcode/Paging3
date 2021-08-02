package com.andariadar.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.andariadar.paging3.data.PersonPagingSource

private const val PAGE_SIZE = 10

class PersonViewModel: ViewModel() {
    val flow = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        )
    ) {
        PersonPagingSource()
    }.flow
        .cachedIn(viewModelScope)
}





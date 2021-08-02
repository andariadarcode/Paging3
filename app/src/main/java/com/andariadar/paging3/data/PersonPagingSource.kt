package com.andariadar.paging3.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

private const val TAG = "PagingSource"
private const val PAGE_SIZE = 10
private const val INITIAL_LOAD_SIZE = 1
const val ITEMS_COUNT = 99

class PersonPagingSource: PagingSource<Int, Person>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        try {
            val position = params.key ?: INITIAL_LOAD_SIZE
            val offset = if (params.key != null) ((position - 1) * PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE
            val response = generatePersonSubList(offset, params.loadSize)

            Log.i(TAG, "position: $position, load size: ${params.loadSize}")

            val nextKey = if (response.isEmpty()) {
                null
            } else {
                position + (params.loadSize / PAGE_SIZE)
            }
            return LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        fun generatePersonSubList(fromIndex: Int, toIndex: Int): List<Person> {
            return (0..ITEMS_COUNT).map {
                Person(it, "Person $it", "Text $it")
            }.subList(fromIndex-1, toIndex+fromIndex-1)
        }
    }
}
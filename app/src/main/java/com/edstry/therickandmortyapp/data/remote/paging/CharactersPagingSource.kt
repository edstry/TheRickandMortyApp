package com.edstry.therickandmortyapp.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.edstry.therickandmortyapp.data.mapper.toDomain
import com.edstry.therickandmortyapp.data.remote.api.RickMortyApi
import com.edstry.therickandmortyapp.domain.model.Character
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharactersPagingSource @Inject constructor(
    private val api: RickMortyApi
): PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchor = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchor)
        Log.d("getRefreshKey", "Anchor: $anchor | Page: $page")
        return page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1
        Log.d("loadPage", "Page: $page")
        return try {
            delay(500)
            val response = api.getCharacters(page)
            val data = response.results.map { it.toDomain() }

            val nextKey = if (response.info.next == null) null else page + 1
            val prevKey = if (page == 1) null else page - 1

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
package com.edstry.therickandmortyapp.data.remote.paging

import android.net.http.HttpException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.edstry.therickandmortyapp.data.mapper.toDomain
import com.edstry.therickandmortyapp.data.remote.api.RickMortyApi
import com.edstry.therickandmortyapp.domain.model.Character
import java.io.IOException
import javax.inject.Inject

class CharactersPagingSource @Inject constructor(
    private val api: RickMortyApi
): PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchor = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchor)
        return page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1
        return try {
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
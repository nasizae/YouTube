import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.youtube.core.network.RemoteDataSource
import com.example.youtube.data.model.PlayListModel

class PagingSource(
     var remoteDataSource: RemoteDataSource,
     var query: String,
     var utils: String
) : PagingSource<String,PlayListModel.Item>() {
    override fun getRefreshKey(state: PagingState<String, PlayListModel.Item>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PlayListModel.Item> {
        try {
            val pageToken = params.key ?: ""
            var response: Result<PlayListModel>? = null
            var nextKey = ""
            when (query) {
                "key1" -> response =
                    remoteDataSource.getPlaylists(pageToken)

//                Constants.GET_PLAYLISTS_ITEM -> response =
//                    remoteDataSource.getPlaylistItems(utils, pageToken)
            }
            val items = mutableListOf<PlayListModel.Item>()

            if (response != null) {
                when {
                    response.isSuccess -> response.onSuccess {
                        items.addAll(it.items)
                    }

                    response.isFailure -> response.onFailure {

                    }

                }

                if (response.isSuccess) response.onSuccess { nextKey = it.nextPageToken }
            }

            return LoadResult.Page(data = items, prevKey = null, nextKey = nextKey)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
package com.hitanshudhawan.popcorn2

import androidx.lifecycle.liveData
import com.hitanshudhawan.popcorn2.database.cache.*
import com.hitanshudhawan.popcorn2.network.MoviesService

class MoviesRepositoryImpl(
    private val moviesService: MoviesService,
    private val moviesDao: MoviesDao
) : MoviesRepository {

    override fun getNowPlayingMovies() = liveData<Resource<List<MovieBrief>>> {
        emit(Resource.Loading())
        val response = safe { moviesService.getNowPlayingMovies() }
        if (response != null && response.isSuccessful) {
            moviesDao.insertNowPlayingMovies(response.body()!!.results.mapIndexed { index, it -> NowPlayingMovieBriefEntity(index, it.id, it.title, it.poster_path, it.backdrop_path, it.vote_average, it.genre_ids) })
            emit(Resource.Success(response.body()!!.results.map { MovieBrief(it.id, it.title, it.poster_path, it.backdrop_path, it.vote_average, it.genre_ids) }))
        } else {
            val movieBriefEntities = moviesDao.getNowPlayingMovies()
            if (movieBriefEntities.isNotEmpty()) {
                emit(Resource.Success(movieBriefEntities.map { MovieBrief(it.id, it.title, it.poster, it.backdrop, it.rating, it.genreIds) }))
            } else {
                emit(Resource.Error())
            }
        }
    }

    override fun getPopularMovies() = liveData<Resource<List<MovieBrief>>> {
        emit(Resource.Loading())
        val response = safe { moviesService.getPopularMovies() }
        if (response != null && response.isSuccessful) {
            moviesDao.insertPopularMovies(response.body()!!.results.mapIndexed { index, it -> PopularMovieBriefEntity(index, it.id, it.title, it.poster_path, it.backdrop_path, it.vote_average, it.genre_ids) })
            emit(Resource.Success(response.body()!!.results.map { MovieBrief(it.id, it.title, it.poster_path, it.backdrop_path, it.vote_average, it.genre_ids) }))
        } else {
            val movieBriefEntities = moviesDao.getPopularMovies()
            if (movieBriefEntities.isNotEmpty()) {
                emit(Resource.Success(movieBriefEntities.map { MovieBrief(it.id, it.title, it.poster, it.backdrop, it.rating, it.genreIds) }))
            } else {
                emit(Resource.Error())
            }
        }
    }

    override fun getUpcomingMovies() = liveData<Resource<List<MovieBrief>>> {
        emit(Resource.Loading())
        val response = safe { moviesService.getUpcomingMovies() }
        if (response != null && response.isSuccessful) {
            moviesDao.insertUpcomingMovies(response.body()!!.results.mapIndexed { index, it -> UpcomingMovieBriefEntity(index, it.id, it.title, it.poster_path, it.backdrop_path, it.vote_average, it.genre_ids) })
            emit(Resource.Success(response.body()!!.results.map { MovieBrief(it.id, it.title, it.poster_path, it.backdrop_path, it.vote_average, it.genre_ids) }))
        } else {
            val movieBriefEntities = moviesDao.getUpcomingMovies()
            if (movieBriefEntities.isNotEmpty()) {
                emit(Resource.Success(movieBriefEntities.map { MovieBrief(it.id, it.title, it.poster, it.backdrop, it.rating, it.genreIds) }))
            } else {
                emit(Resource.Error())
            }
        }
    }

    override fun getTopRatedMovies() = liveData<Resource<List<MovieBrief>>> {
        emit(Resource.Loading())
        val response = safe { moviesService.getTopRatedMovies() }
        if (response != null && response.isSuccessful) {
            moviesDao.insertTopRatedMovies(response.body()!!.results.mapIndexed { index, it -> TopRatedMovieBriefEntity(index, it.id, it.title, it.poster_path, it.backdrop_path, it.vote_average, it.genre_ids) })
            emit(Resource.Success(response.body()!!.results.map { MovieBrief(it.id, it.title, it.poster_path, it.backdrop_path, it.vote_average, it.genre_ids) }))
        } else {
            val movieBriefEntities = moviesDao.getTopRatedMovies()
            if (movieBriefEntities.isNotEmpty()) {
                emit(Resource.Success(movieBriefEntities.map { MovieBrief(it.id, it.title, it.poster, it.backdrop, it.rating, it.genreIds) }))
            } else {
                emit(Resource.Error())
            }
        }
    }

}
package com.emanuel.pulsusnorris.ui

import android.util.Log
import androidx.lifecycle.*
import com.emanuel.pulsusnorris.data.model.local.Joke
import com.emanuel.pulsusnorris.data.model.remote.toJokeModel
import com.emanuel.pulsusnorris.data.repository.ChuckNorrisRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val chuckRepository: ChuckNorrisRepository
) : ViewModel() {

    private val _jokesRandom = MutableLiveData<Joke>()
    val jokesRandom: LiveData<Joke>
        get() = _jokesRandom

    private val _jokesSearch = MutableLiveData<List<Joke>>()
    val jokesSearch: LiveData<List<Joke>>
        get() = _jokesSearch

    private val _jokeCategories = MutableLiveData<List<String>>()
    val jokeCategories: LiveData<List<String>>
        get() = _jokeCategories

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    private val _snackBar = MutableLiveData<String?>()
    val snackBar: LiveData<String?>
        get() = _snackBar

    fun getJokesRandom(category: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _progressBar.postValue(true)
                val res = chuckRepository.getRandomJokes(category)
                res.body()?.toJokeModel()
            }.onSuccess { joke ->
                _progressBar.postValue(false)
                _jokesRandom.postValue(joke)
            }.onFailure {
                Log.e(TAG, "getJokesRandom: ${it.message}")
                _progressBar.postValue(false)
                _snackBar.postValue("Problemas ao carregar...")
            }
        }
    }

    fun searchJokes(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _progressBar.postValue(true)
                val res = chuckRepository.searchJoke(text = text)
                res.body()?.result?.map { it.toJokeModel() }
            }.onSuccess { jokes ->
                _progressBar.postValue(false)
                _jokesSearch.postValue(jokes)
            }.onFailure {
                Log.e(TAG, "getJokesRandom: ${it.message}")
                _progressBar.postValue(false)
                _snackBar.postValue("Problemas ao carregar...")
            }
        }
    }

    fun getJokeCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _progressBar.postValue(true)
                chuckRepository.getCategoriesJokes()
            }.onSuccess {
                _progressBar.postValue(false)
                _jokeCategories.postValue(it.body())
            }.onFailure {
                _progressBar.postValue(false)
                Log.e(TAG, "getCategories: ${it.message}")
                _jokeCategories.postValue(listOf("falha ao carregar"))
            }
        }
    }

    fun snackBarShown() {
        _snackBar.value = null
    }

    class MainViewModelFactory(private val repository: ChuckNorrisRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        const val TAG = "chuck_api"
    }
}
package com.example.android.bintest.search_bin

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.bintest.R
import com.example.android.bintest.database.BinDatabaseDao
import com.example.android.bintest.database.CardInfoEntity
import com.example.android.bintest.network.BinAPI
import com.example.android.bintest.network.CardInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchBinViewModel
@Inject constructor(private val dataSource: BinDatabaseDao,
                                             application: Application): ViewModel() {
    private var _cardInfo = MutableLiveData<CardInfo>()
    val cardInfo : LiveData<CardInfo>
        get() = _cardInfo

    private var _visibilityError = MutableLiveData(View.GONE)
    val visibilityError: LiveData<Int>
        get() = _visibilityError

    private var _errorText = MutableLiveData("unexpected value")
    val errorText: LiveData<String>
            get() = _errorText

    private var _navigateToHistory = MutableLiveData<Boolean>()
    val navigateToHistory: LiveData<Boolean>
        get() = _navigateToHistory

    init{
        _cardInfo.value = CardInfo()
    }

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    //var urlFormatted: String = "Url:" + (cardInfo.value?.bank?.url ?: "")
    var cityFormatted: String = application.getString(R.string.city_field, (cardInfo.value?.bank?.url ?: ""))
    var phoneFormatted: String = application.getString(R.string.number_field, (cardInfo.value?.bank?.url ?: ""))
    var urlFormatted = application.getString(R.string.url_field, (cardInfo.value?.bank?.url ?: ""))


    fun getCardInfo(number: String){
        coroutineScope.launch {
            _errorText.value = "unexpected error"
            _visibilityError.value = View.GONE
            try{
                val a = BinAPI.retrofitService.getInfo(number)
                saveRequestInfo(convertCardInfo(a, number))
                _cardInfo.value = a
            }
            catch (e: Exception){
                Log.e("network", e.message!!)
                _visibilityError.value = View.VISIBLE
            }
        }
    }

    private suspend fun saveRequestInfo(cardInfoObject: CardInfoEntity){
        withContext(Dispatchers.IO){
            dataSource.insert(cardInfoObject)
        }
    }

    private fun convertCardInfo(cardInfoObject: CardInfo, number: String): CardInfoEntity{
        val entity = CardInfoEntity()
        entity.number = number
        entity.bankCity = cardInfoObject.bank.city
        entity.bankPhone = cardInfoObject.bank.phone
        entity.bankUrl = cardInfoObject.bank.url
        entity.coordinates = "0.0.0.0"
        entity.country = cardInfoObject.country.name
        entity.type = cardInfoObject.type
        //val cardInfoItem = entity
        return entity
    }

    fun doneNavigationHistory() {
        _navigateToHistory.value = false
    }
    fun startNavigationHistory(){
        _navigateToHistory.value = true
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
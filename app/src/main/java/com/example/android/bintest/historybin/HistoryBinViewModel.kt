package com.example.android.bintest.historybin

import androidx.lifecycle.ViewModel
import com.example.android.bintest.database.BinDatabaseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryBinViewModel @Inject constructor(dataSourse: BinDatabaseDao): ViewModel() {
    var historyList = dataSourse.getAllRequests()

    init {
        historyList = dataSourse.getAllRequests()
    }
}
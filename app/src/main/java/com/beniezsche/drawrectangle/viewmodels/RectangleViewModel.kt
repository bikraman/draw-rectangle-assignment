package com.beniezsche.drawrectangle.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beniezsche.drawrectangle.db.AppDatabase
import com.beniezsche.drawrectangle.models.RectItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RectangleViewModel : ViewModel() {

    var db: AppDatabase? = null

    fun addRect(rectItem: RectItem) {
        viewModelScope.launch(Dispatchers.IO) {
            db?.rectItemDao()?.insertRectItem(rectItem)
        }
    }

    fun getAllRects() : LiveData<List<RectItem>> {

        val _data = MutableLiveData<List<RectItem>>()

        viewModelScope.launch(Dispatchers.IO) {
            val list = db?.rectItemDao()?.getAllRectItems()
            _data.postValue(list)
        }

        return _data
    }

    fun clearAllRects() : LiveData<Boolean> {

        val _data = MutableLiveData(false)

        viewModelScope.launch(Dispatchers.IO) {
            db?.rectItemDao()?.deleteAllRectItems()
            _data.postValue(true)
        }

        return _data

    }
}
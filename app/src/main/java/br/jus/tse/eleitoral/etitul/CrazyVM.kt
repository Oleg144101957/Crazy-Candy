package br.jus.tse.eleitoral.etitul

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CrazyVM : ViewModel(){

    val mutableLiveScores = MutableLiveData<Int>(0)

    fun increaseScores(){
        mutableLiveScores.value = mutableLiveScores.value?.plus(1)
    }

}
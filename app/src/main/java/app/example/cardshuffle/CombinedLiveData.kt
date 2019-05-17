package app.example.cardshuffle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class CombinedLiveData<T, K,L, S>(source1: LiveData<T>, source2: LiveData<K>, source3: LiveData<L>,
                                  private val combine: (data1: T?, data2: K?,data3: L?) -> S) :
        MediatorLiveData<S>() {

    private var data1: T? = null
    private var data2: K? = null
    private var data3: L? = null

    init {
        super.addSource(source1) {
            data1 = it
            value = combine(data1, data2, data3)
        }
        super.addSource(source2) {
            data2 = it
            value = combine(data1, data2,data3)
        }
        super.addSource(source3) {
            data3 = it
            value = combine(data1, data2,data3)
        }
    }

}
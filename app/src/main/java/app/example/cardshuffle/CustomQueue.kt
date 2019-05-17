package app.example.cardshuffle

class CustomQueue<T> : ArrayList<T>(){


    /**
    * Moves an item from position index to 0 position.
    **/
    fun moveToFront(index: Int){
        val temp = get(index)
        for (i in index downTo 1)
        {
            set(i , get(i-1))
        }
        set(0, temp)
    }
}
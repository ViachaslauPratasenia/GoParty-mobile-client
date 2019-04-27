package by.bsuir.proslau.goparty.server

abstract class RunnableWithObject<T> : Runnable {
    var `object`: T? = null
        private set

    fun init(`object`: T) {
        this.`object` = `object`
    }
}
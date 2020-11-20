package utils.extensions

fun <T, R> Map<T,R>.reversed(): Map<R,T> = this.toList().map{it.second to it.first}.toMap()
package com.zaidzakir.mediumexample.model

/**
 *Created by Zaid Zakir
 */
sealed class Resource<T>(val data:T? , val message:String?) {
    class Error<T>(message: String?):Resource<T>(null, message){}
    class Success<T>(data: T):Resource<T>(data,null){}
}
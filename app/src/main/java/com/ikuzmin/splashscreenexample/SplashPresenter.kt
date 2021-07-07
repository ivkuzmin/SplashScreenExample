package com.ikuzmin.splashscreenexample

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashPresenter {

  private val job = Job()
  private val scope = CoroutineScope(job+Dispatchers.IO)
  private lateinit var view:View

  fun attach(view:View){
    this.view = view
  }

  fun startJob(){
    scope.launch {
      delay(2000)
      withContext(Dispatchers.Main){
        view.setIsReady(true)
      }
    }
  }
}
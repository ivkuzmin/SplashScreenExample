package com.ikuzmin.splashscreenexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity(),com.ikuzmin.splashscreenexample.View {
  private var isReady = false
  private val presenter = SplashPresenter()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    installSplashScreen()
    setContentView(R.layout.activity_main)
    val content: View = findViewById(android.R.id.content)

    content.viewTreeObserver.addOnPreDrawListener(
      object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
          // Check if the initial data is ready.
          return if (isReady) {
            // The content is ready; start drawing.
            content.viewTreeObserver.removeOnPreDrawListener(this)
            true
          } else {
            // The content is not ready; suspend.
            false
          }
        }
      }
    )
    presenter.attach(this)
    presenter.startJob()
  }

  override fun setIsReady(isReady: Boolean) {
    this.isReady = isReady
  }
}
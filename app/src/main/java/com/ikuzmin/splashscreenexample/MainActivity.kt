package com.ikuzmin.splashscreenexample

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity(), com.ikuzmin.splashscreenexample.View {
  private var isReady = false
  private val presenter = SplashPresenter()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val splashScreen = installSplashScreen()
    setContentView(R.layout.activity_main)
    splashScreen.setKeepVisibleCondition {
      return@setKeepVisibleCondition !isReady
    }

    splashScreen.setOnExitAnimationListener { splashScreenView ->
      val slideUp = ObjectAnimator.ofFloat(
        splashScreenView.view,
        View.TRANSLATION_Y,
        0f,
        -splashScreenView.view.height.toFloat()
      )
      slideUp.interpolator = AnticipateInterpolator()
      slideUp.duration = 200L

      // Call SplashScreenView.remove at the end of your custom animation.
      slideUp.doOnEnd { splashScreenView.remove() }

      // Run your animation.
      slideUp.start()
    }
    presenter.attach(this)
    presenter.startJob()
  }

  override fun setIsReady(isReady: Boolean) {
    this.isReady = isReady
  }
}
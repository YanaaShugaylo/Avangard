package com.avangard.bratstvo.base.root.presentation


import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.avangard.bratstvo.R
import com.avangard.bratstvo.base.root.domain.MainActivityInteractionsHelper
import com.avangard.bratstvo.base.root.domain.MainActivityStateHelper
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.base.root.presentation.model.ToolbarStateModel
import com.avangard.bratstvo.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(com.avangard.bratstvo.R.style.Theme_BratstvoAvangard)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(com.avangard.bratstvo.R.id.fragment_container_view) as NavHostFragment

        binding.mainBottomNavigation.run {
            setupWithNavController(navHostFragment.navController)

            val tintList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                resources.getColorStateList(com.avangard.bratstvo.R.color.nav_bar_icons_tint_list, theme)
            } else {
                resources.getColorStateList(com.avangard.bratstvo.R.color.nav_bar_icons_tint_list)
            }

            itemIconTintList = tintList
            itemTextColor = tintList
        }

        binding.toolbarLayout.toolbarProfileButtonIv.setOnClickListener {
            if (viewModel.isOnline.value == true) {
                findNavController(com.avangard.bratstvo.R.id.fragment_container_view).navigate(com.avangard.bratstvo.R.id.profileFragment)
            }
        }

        binding.toolbarLayout.toolbarBackArrowButtonIv.setOnClickListener { onBackPressed() }

        setObservers()
    }

    private fun setObservers() {
        MainActivityStateHelper.toolbarState.observe(this) {
            setToolbarState(it)
        }

        MainActivityStateHelper.bottomNavigationVisibility.observe(this) {
            binding.mainBottomNavigation.visibility = it
        }

        MainActivityInteractionsHelper.message.observe(this) {
            if (it != null) {
                showMessage(it)
                MainActivityInteractionsHelper.setMessageShowed()
            }
        }

        MainActivityInteractionsHelper.loaderVisibility.observe(this) {
            Log.i("myLog", "loaderVisibility = $it")
            binding.loaderBackgroundView.post {
                binding.loaderBackgroundView.isVisible = it
            }
            binding.loaderPb.post {
                binding.loaderPb.isVisible = it
            }
        }

        viewModel
    }

    private fun setToolbarState(stateModel: ToolbarStateModel) {
        setBackArrowState(stateModel)
        setTimerState(stateModel)
        setTitleState(stateModel.titleText)
        setProfileButtonState(stateModel)
        setMenuExtraState(stateModel)
    }

    private fun setBackArrowState(stateModel: ToolbarStateModel) {
        binding.toolbarLayout.toolbarBackArrowButtonIv.isInvisible = when (stateModel.state) {
            ToolbarButtonsStates.MAIN_STATE, ToolbarButtonsStates.NOTHING -> true
            else -> false
        }
    }
    private fun setTimerState(stateModel: ToolbarStateModel) {
        var mTimer = binding.toolbarLayout.tvTimer
       var myTimer = object : CountDownTimer(10000, 1000) {
            //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
            override fun onTick(millisUntilFinished: Long) {
                var i = millisUntilFinished/1000
                mTimer.setText("$i")
            }
            override fun onFinish() {
                mTimer.setText("Бабах!")
            }
        }
            myTimer.start()
        binding.toolbarLayout.vTimer.isInvisible = when (stateModel.state) {
          ToolbarButtonsStates.MY_STATE -> false
            else -> true
        }
        binding.toolbarLayout.tvTimer.isInvisible = when (stateModel.state) {
            ToolbarButtonsStates.MY_STATE -> false
            else -> true
        }
    }

    private fun setTitleState(titleText: String) {
        binding.toolbarLayout.toolbarTitleTv.text = titleText
    }

    private fun setProfileButtonState(stateModel: ToolbarStateModel) {
        val buttonVisibility = when (stateModel.state) {
            ToolbarButtonsStates.MAIN_STATE -> View.VISIBLE
            else -> View.INVISIBLE
        }

        val profileButton = binding.toolbarLayout.toolbarProfileButtonIv
        profileButton.visibility = buttonVisibility

        if (buttonVisibility == View.VISIBLE) {
            viewModel.profileImage.observe(this) {
                if (it.isNullOrEmpty()) {
                    profileButton.load(com.avangard.bratstvo.R.drawable.profile_placeholder_icon)
                } else {
                    profileButton.load(it)
                }
            }
        }
    }

    private fun setMenuExtraState(stateModel: ToolbarStateModel) {
//        binding.toolbarLayout.menuExtraButtonIv.isInvisible = when (stateModel.state) {
//            ToolbarStates.MAIN_STATE -> false
//            else -> true
//        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
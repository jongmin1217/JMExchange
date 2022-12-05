package com.bellminp.jmexchange.ui.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.widget.NumberPicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bellminp.jmexchange.R
import com.bellminp.jmexchange.databinding.ActivityMainBinding
import com.bellminp.jmexchange.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()

    private var time: Long = 0

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val builder = NetworkRequest.Builder()
        cm.registerNetworkCallback(builder.build(), object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                viewModel.networkConnected = true
            }

            override fun onLost(network: Network) {
                viewModel.networkConnected = false
            }
        })

        initLayout()
        viewModel.getExchange("KRW")
    }

    private fun initLayout() {
        binding.numberPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        binding.numberPicker.maxValue = 2
        binding.numberPicker.displayedValues =
            resources.getStringArray(R.array.recipient_country_list)
    }

    override fun onBackPressed() {
        doubleTabBack()
    }

    private fun doubleTabBack() {
        if (System.currentTimeMillis() - time >= 2000) {
            time = System.currentTimeMillis()
            showToast(resources.getString(R.string.double_tab))
        } else if (System.currentTimeMillis() - time < 2000) {
            super.onBackPressed()
            finish()
            exitProcess(0)
        }
    }
}
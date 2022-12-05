package com.bellminp.jmexchange.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bellminp.jmexchange.utils.ext.shortToast

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes
    private val layoutResId: Int
) : AppCompatActivity() {

    protected val binding by lazy { DataBindingUtil.setContentView<VDB>(this, layoutResId) }
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply { lifecycleOwner = this@BaseActivity }

        setupBinding()
        setupObserver()
    }

    abstract fun setupBinding()

    open fun setupObserver(){
        with(viewModel){
            toast.observe(this@BaseActivity){
                it.getContentIfNotHandled()?.let { msg ->
                    showToast(msg)
                }
            }
        }
    }


    protected fun showToast(msg : String){
        this@BaseActivity.shortToast(msg)
    }

}
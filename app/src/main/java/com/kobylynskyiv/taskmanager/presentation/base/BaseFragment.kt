package com.kobylynskyiv.taskmanager.presentation.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.kobylynskyiv.taskmanager.presentation.utils.hideSoftInputKeyboard
import com.kobylynskyiv.taskmanager.presentation.utils.lazyNonSafetyMode
import com.kobylynskyiv.taskmanager.presentation.utils.lazySynchronizedMode


/**
 * Base fragment
 * @param VB - ViewBinding
 * @param VM - ViewModel
 * @constructor Create empty Base fragment
 */
abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>() : Fragment() {

    abstract val viewModel: VM

    open val binding by lazyNonSafetyMode { inflateViewBinding(inflater = layoutInflater) }

    @MainThread
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return binding.root
    }

    @MainThread
    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
    }

    /**
     * Refresh fragment
     * Function to refresh the screen
     */
    @MainThread
    private fun refreshFragment() = requireActivity().supportFragmentManager
        .beginTransaction()
        .detach(this)
        .attach(this)
        .commit();

    /**
     * On back pressed
     * @param updateScreen - flag update screen
     */
    @MainThread
    fun onBackPressed(updateScreen: Boolean = false) {
        if(updateScreen) {
            activity?.onBackPressedDispatcher?.onBackPressed()
            refreshFragment()
        }
        else activity?.onBackPressedDispatcher?.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        activity?.hideSoftInputKeyboard()
    }
    @MainThread
    fun navigateToFragment(@IdRes id: Int, bundle: Bundle? = null) = findNavController().navigate(id, bundle)

    @MainThread
    fun navigateToActivity(clazz: Class<*>) =
        startActivity(Intent(requireActivity(), clazz)).apply {
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            requireActivity().finish()
        }

    abstract fun inflateViewBinding(inflater: LayoutInflater): VB
}
package com.kobylynskyiv.taskmanager.presentation.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.kobylynskyiv.taskmanager.presentation.utils.alert.Alerter
import com.kobylynskyiv.taskmanager.presentation.utils.extentions.hideSoftInputKeyboard


/**
 * Base fragment
 * @param VB - ViewBinding
 * @param VM - ViewModel
 * @constructor Create empty Base fragment
 */
abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>() : Fragment() {

    abstract val viewModel: VM
    val alerter: Alerter by lazy { Alerter(requireActivity()) }
    open var binding: VB? = null

    @MainThread
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = inflateViewBinding(inflater = layoutInflater)
        return binding?.root
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
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        viewModelStore.clear()
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
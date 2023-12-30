package com.kobylynskyiv.taskmanager.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.kobylynskyiv.taskmanager.R
import com.kobylynskyiv.taskmanager.databinding.FragmentMainBinding
import com.kobylynskyiv.taskmanager.presentation.base.AdapterModel
import com.kobylynskyiv.taskmanager.presentation.base.BaseAdapter
import com.kobylynskyiv.taskmanager.presentation.base.BaseFragment
import com.kobylynskyiv.taskmanager.presentation.ui.main.viewmodel.MainViewModel
import com.kobylynskyiv.taskmanager.presentation.utils.alert.Alerter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment: BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()
    val alerter: Alerter by lazy { Alerter(requireActivity()) }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BaseAdapter<AdapterModel>().apply {
            binding.recyclerView.adapter = this
        }

        with(viewModel){
            load()
            observer.observe(viewLifecycleOwner) {
                if (it.error != null) {
                    alerter.showError(getString(R.string.error), it.toString())
                }
            }
        }
    }
}
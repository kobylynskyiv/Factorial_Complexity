package com.kobylynskyiv.taskmanager.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import com.kobylynskyiv.taskmanager.R
import com.kobylynskyiv.taskmanager.databinding.FragmentMainBinding
import com.kobylynskyiv.taskmanager.presentation.base.AdapterModel
import com.kobylynskyiv.taskmanager.presentation.base.BaseAdapter
import com.kobylynskyiv.taskmanager.presentation.base.BaseFragment
import com.kobylynskyiv.taskmanager.presentation.entity.UIStatus
import com.kobylynskyiv.taskmanager.presentation.ui.main.viewmodel.MainViewModel
import com.kobylynskyiv.taskmanager.presentation.utils.alert.Alerter
import com.kobylynskyiv.taskmanager.presentation.utils.extentions.visibleOrInvisible
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator

@AndroidEntryPoint
class MainFragment: BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()
    private val alerter: Alerter by lazy { Alerter(requireActivity()) }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BaseAdapter<AdapterModel>(){

        }
        binding.recyclerView.apply {
            this.adapter = adapter
            this.itemAnimator = SlideInDownAnimator()
        }

        with(viewModel){
            fetch()

            status.observe(viewLifecycleOwner){
                when(it){
                    UIStatus.LOADING -> binding.includeLoading.root.visibleOrInvisible(true)
                    UIStatus.COMPLETE -> {
                        binding.includeLoading.root.visibleOrInvisible(false)
                        alerter.showSuccess(getString(R.string.complete), it.toString())
                    }
                    UIStatus.ERROR -> {
                        binding.includeLoading.root.visibleOrInvisible(false)
                        alerter.showError(getString(R.string.error), it.toString())
                    }
                }
            }

            observer.observe(viewLifecycleOwner) {
                requireActivity().findViewById<Toolbar>(R.id.toolbar).title = it.title
                if (it.fruits != null) adapter.submitList(it.fruits)
            }
        }
    }
}
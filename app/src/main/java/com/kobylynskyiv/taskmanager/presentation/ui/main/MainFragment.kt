package com.kobylynskyiv.taskmanager.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import com.kobylynskyiv.data.model.Fruit
import com.kobylynskyiv.taskmanager.R
import com.kobylynskyiv.taskmanager.databinding.FragmentMainBinding
import com.kobylynskyiv.taskmanager.presentation.base.AdapterModel
import com.kobylynskyiv.taskmanager.presentation.base.BaseAdapter
import com.kobylynskyiv.taskmanager.presentation.base.BaseFragment
import com.kobylynskyiv.taskmanager.presentation.entity.UIStatus
import com.kobylynskyiv.taskmanager.presentation.ui.main.viewmodel.MainViewModel
import com.kobylynskyiv.taskmanager.presentation.utils.extentions.visibleOrInvisible
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator

@AndroidEntryPoint
class MainFragment: BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.menu_main, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.refresh -> viewModel.fetch().let { true }
            else -> { true }
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val adapter = BaseAdapter<AdapterModel> {
            val bundle = Bundle()
            bundle.putSerializable(CONTENT, it as Fruit)
            navigateToFragment(R.id.FruitFragment, bundle)
        }

        with(binding) {
            if(this == null) return

            recyclerView.apply {
                this.adapter = adapter
                this.itemAnimator = SlideInDownAnimator()
            }

            with(viewModel){
                fetch()

                status.observe(viewLifecycleOwner){
                    when(it){
                        UIStatus.LOADING -> {
                            recyclerView.visibleOrInvisible(false)
                            includeLoading.root.visibleOrInvisible(true)
                        }
                        UIStatus.COMPLETE -> {
                            recyclerView.visibleOrInvisible(true)
                            includeLoading.root.visibleOrInvisible(false)
                            alerter.showSuccess(getString(R.string.complete), it.toString())
                        }
                        UIStatus.ERROR -> {
                            recyclerView.visibleOrInvisible(true)
                            includeLoading.root.visibleOrInvisible(false)
                            alerter.showError(getString(R.string.error), it.toString())
                        }

                        else -> {}
                    }
                }

                observer.observe(viewLifecycleOwner) {
                    requireActivity().findViewById<Toolbar>(R.id.toolbar).title = it?.title
                    if (it?.data != null) adapter.submitList(it.data as List<AdapterModel.FruitModel>)
                }
            }
        }
    }

    companion object {
        const val CONTENT = "content"
    }
}
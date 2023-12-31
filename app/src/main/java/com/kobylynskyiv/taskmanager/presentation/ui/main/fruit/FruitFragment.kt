package com.kobylynskyiv.taskmanager.presentation.ui.main.fruit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.kobylynskyiv.data.BuildConfig
import com.kobylynskyiv.data.model.Fruit
import com.kobylynskyiv.taskmanager.R
import com.kobylynskyiv.taskmanager.databinding.FragmentFruitBinding
import com.kobylynskyiv.taskmanager.presentation.base.BaseFragment
import com.kobylynskyiv.taskmanager.presentation.entity.UIStatus
import com.kobylynskyiv.taskmanager.presentation.ui.main.MainFragment.Companion.CONTENT
import com.kobylynskyiv.taskmanager.presentation.ui.main.fruit.viewmodel.FruitViewModel
import com.kobylynskyiv.taskmanager.presentation.utils.extentions.replaceAllVariable
import com.kobylynskyiv.taskmanager.presentation.utils.extentions.visibleOrInvisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FruitFragment: BaseFragment<FragmentFruitBinding, FruitViewModel>() {

    override val viewModel: FruitViewModel by viewModels()

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentFruitBinding {
        return FragmentFruitBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fruit = arguments?.getSerializable(CONTENT) as Fruit

        with(viewModel){
            loadFruit(fruit.id.toString())
            requireActivity().findViewById<Toolbar>(R.id.toolbar).title = fruit.name

            Glide.with(binding.root.context)
                .load("${BuildConfig.API_URL}${fruit.image}")
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.image)

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

                    else -> {}
                }
            }

            observer.observe(viewLifecycleOwner) {
                if(it.data != null) {
                    binding.content.visibleOrInvisible(true)
                    binding.title.text = it.data.toString().replaceAllVariable(mapOf("{itemId}" to fruit.name))
                }
            }
        }
    }
}
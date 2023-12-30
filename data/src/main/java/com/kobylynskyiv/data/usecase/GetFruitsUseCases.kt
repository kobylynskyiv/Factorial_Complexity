package com.kobylynskyiv.data.usecase

import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.switchMap
import com.kobylynskyiv.data.repository.FruitRepository
import javax.inject.Inject

class GetFruitsUseCases @Inject constructor(
   private val mFruitRepository: FruitRepository
) {

   private val value = mFruitRepository._observable.let {
      /*flatMapIndexed {
         dbLiveData.search(name) //a list with the names
      }*/
   }
   suspend operator fun invoke() = mFruitRepository.getAllFruits()
}
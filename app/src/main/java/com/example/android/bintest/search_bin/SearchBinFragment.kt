package com.example.android.bintest.search_bin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android.bintest.R
import com.example.android.bintest.databinding.FragmentSearchBinBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchBinFragment : Fragment() {

    @get:Inject
    val searchViewModel: SearchBinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentSearchBinBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search_bin, container, false)

        binding.searchButton.setOnClickListener{
            searchViewModel.getCardInfo(binding.editText.text.toString())
        }

        searchViewModel.navigateToHistory.observe(viewLifecycleOwner, Observer {
            if(it){
                startNavigationHistory()
            }
        })

        binding.viewModel = searchViewModel
        binding.lifecycleOwner = this

        return binding.root
   }
    private fun startNavigationHistory() {
        findNavController().navigate(SearchBinFragmentDirections.actionSearchBinFragmentToHistoryBinFragment())
        searchViewModel.doneNavigationHistory()
    }
}

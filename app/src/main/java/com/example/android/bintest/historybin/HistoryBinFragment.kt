package com.example.android.bintest.historybin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.android.bintest.R
import com.example.android.bintest.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HistoryBinFragment : Fragment() {

    @get:Inject
    val historyViewModel: HistoryBinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHistoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = HistoryBinAdapter()
        binding.recyclerView.adapter = adapter
        historyViewModel.historyList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })


        return binding.root
    }
}

// что с кнопкой истории
//доделай строки
    //что отобразить если ничего не пришло
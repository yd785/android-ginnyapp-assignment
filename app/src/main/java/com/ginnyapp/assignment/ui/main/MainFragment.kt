package com.ginnyapp.assignment.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.ginnyapp.assignment.R
import com.ginnyapp.assignment.databinding.FragmentMainBinding
import com.ginnyapp.assignment.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainFragment"

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var binding: FragmentMainBinding by autoCleared()
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: RvMainAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        Log.d(TAG, "setupObservers: ")
        // show the spinner when [MainViewModel.spinner] is true
        viewModel.spinner.observe(viewLifecycleOwner) { value ->
            value.let { show ->
                binding.progressBar.visibility = if (show) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        }

        viewModel.toastMsg.observe(viewLifecycleOwner) { text ->
            text?.let {
                Log.d(TAG, "setupObservers: $text")
                Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.numbers.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }

    private fun setupRecyclerView() {
        adapter = RvMainAdapter()
        binding.numbersRv.hasFixedSize()
        binding.numbersRv.adapter = adapter
    }
}
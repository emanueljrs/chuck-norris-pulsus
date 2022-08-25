package com.emanuel.pulsusnorris.ui.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.emanuel.pulsusnorris.R
import com.emanuel.pulsusnorris.data.datasource.ChuckNorrisDataSource
import com.emanuel.pulsusnorris.databinding.FragmentSearchBinding
import com.emanuel.pulsusnorris.ui.MainViewModel
import com.google.android.material.snackbar.Snackbar

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory(ChuckNorrisDataSource())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        seListeners()
        setObservers()
    }

    private fun seListeners() {
        binding.apply {
            searchBtn.setOnClickListener {
                val text = searchEdt.text.toString()
                if (text.isNotEmpty()) {
                    mainViewModel.searchJokes(text = text)
                    searchTil.error = null
                } else {
                    searchTil.error = getString(R.string.error_message)
                }
            }
        }
    }

    private fun setObservers() {
        mainViewModel.jokesSearch.observe(viewLifecycleOwner) { searchJokes ->
            binding.apply {
                val adapterJokes = SearchAdapter(searchJokes)
                searchRv.apply {
                    adapter = adapterJokes
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }

        mainViewModel.progressBar.observe(viewLifecycleOwner) { showProgress ->
            binding.apply {
                if (showProgress) {
                    searchPb.visibility = View.VISIBLE
                    searchRv.visibility = View.GONE
                    searchBtn.isEnabled = !showProgress
                } else {
                    searchPb.visibility = View.GONE
                    searchRv.visibility = View.VISIBLE
                    searchBtn.isEnabled = !showProgress
                }
            }
        }

        mainViewModel.snackBar.observe(viewLifecycleOwner) { message ->
            message?.let {
                Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
                mainViewModel.snackBarShown()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
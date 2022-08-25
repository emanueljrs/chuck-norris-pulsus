package com.emanuel.pulsusnorris.ui.fragments.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emanuel.pulsusnorris.R
import com.emanuel.pulsusnorris.data.datasource.ChuckNorrisDataSource
import com.emanuel.pulsusnorris.databinding.FragmentRandomBinding
import com.emanuel.pulsusnorris.ui.MainViewModel
import com.google.android.material.snackbar.Snackbar

class RandomFragment : Fragment() {

    private var _binding: FragmentRandomBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory(ChuckNorrisDataSource())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        setListeners()
        mainViewModel.getJokeCategories()
        mainViewModel.getJokesRandom(null)
    }

    private fun setListeners() {
        binding.run {
            newRandomBtn.setOnClickListener {
                val category = randomCategoryMatv.text.toString()
                if (category == "-") {
                    mainViewModel.getJokesRandom(null)
                } else {
                    mainViewModel.getJokesRandom(category = category)
                }
            }
        }
    }

    private fun setObservers() {
        mainViewModel.jokesRandom.observe(viewLifecycleOwner) { joke ->
            binding.randomValueTv.text = getString(R.string.text_joke, joke.value)
        }

        mainViewModel.progressBar.observe(viewLifecycleOwner) { showProgress ->
            binding.apply {
                if (showProgress) {
                    randomPb.visibility = View.VISIBLE
                    randomValueTv.visibility = View.GONE
                    randomCategoryTil.visibility = View.GONE
                    newRandomBtn.isEnabled = false
                } else {
                    randomPb.visibility = View.GONE
                    randomValueTv.visibility = View.VISIBLE
                    randomCategoryTil.visibility = View.VISIBLE
                    newRandomBtn.isEnabled = true
                }
            }
        }

        mainViewModel.jokeCategories.observe(viewLifecycleOwner) { categories ->
            binding.apply {
                randomCategoryMatv.setText("-")
                val categoriesList = buildList {
                    add("-")
                    addAll(categories)
                }
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_expandable_list_item_1,
                    categoriesList
                )
                randomCategoryMatv.setAdapter(adapter)
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
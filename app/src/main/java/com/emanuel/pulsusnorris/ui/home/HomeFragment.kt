package com.emanuel.pulsusnorris.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.emanuel.pulsusnorris.R
import com.emanuel.pulsusnorris.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainAdapter: HomeMainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    private fun setupRecycler() {
        mainAdapter = HomeMainAdapter(HomeMainItem.getItemsMain(), object : HomeItemClickListener {
            override fun onClick(item: HomeMainItem) {
                when (item.id) {
                    0 -> openRandom()
                    1 -> openSearch()
                }
            }
        })
        binding.mainRv.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mainAdapter
        }
    }

    private fun openRandom() {
        findNavController().navigate(R.id.toRandomFragment)
    }

    private fun openSearch() {
        findNavController().navigate(R.id.toSearchFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
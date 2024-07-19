package com.example.vimos.presentation.fragments

import android.os.Build
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vimos.app.App
import com.example.vimos.databinding.FragmentSecondBinding
import com.example.vimos.domain.Categories
import com.example.vimos.presentation.Adapter
import com.example.vimos.presentation.viewmodels.SecondViewModel
import com.example.vimos.utils.Factory
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val category by lazy { requireArguments().get }
    private val viewModel by viewModels<SecondViewModel> {
        Factory {
            App.appComponent.secondComponent().create(requireNotNull(category)).viewModel()
        }
    }
    private lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observeUi().collect { state ->
                    when (state.isLoading) {
                        true -> binding.progressBar.visibility = View.VISIBLE
                        false -> binding.progressBar.visibility = View.GONE
                    }
                    adapter.submitList(state.items)
                    state.error?.let {
                        Toast.makeText(
                            requireContext(),
                            getString(it),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycler() {
        adapter = Adapter { categoryLevel: Categories->
            /*findNavController().navigate(
                R.id.action_firstFragment_to_secondFragment,
                bundleOf("categoryLevel" to categoryLevel, "title" to title)
            )*/
        }
        binding.hotelsRecycler.adapter = adapter
        binding.hotelsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.hotelsRecycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
    }
}
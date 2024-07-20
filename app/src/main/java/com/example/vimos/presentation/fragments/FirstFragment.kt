package com.example.vimos.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vimos.R
import com.example.vimos.app.App
import com.example.vimos.databinding.FragmentFirstBinding
import com.example.vimos.presentation.Adapter
import com.example.vimos.presentation.MainActivity
import com.example.vimos.presentation.viewmodels.FirstViewModel
import com.example.vimos.utils.Factory
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FirstViewModel> {
        Factory {
            App.appComponent.firstComponent().viewModel()
        }
    }
    private lateinit var adapter: Adapter
    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
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
                    state.firstCategory?.let { activity.setData(it) }
                    adapter.submitList(state.items)
                    binding.specificToolbars.title = state.title
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
        adapter = Adapter {
            findNavController().navigate(
                R.id.action_firstFragment_to_secondFragment,
                bundleOf("title" to it)
            )
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
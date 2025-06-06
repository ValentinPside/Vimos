package com.example.vimos.presentation.fragments

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vimos.R
import com.example.vimos.app.App
import com.example.vimos.databinding.FragmentSecondBinding
import com.example.vimos.presentation.Adapter
import com.example.vimos.presentation.MainActivity
import com.example.vimos.presentation.viewmodels.SecondViewModel
import com.example.vimos.utils.Factory
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val title by lazy { requireArguments().getString("title") }
    private val viewModel by viewModels<SecondViewModel> {
        Factory {
            App.appComponent.secondComponent().create(activity.getData()!!, requireNotNull(title)).viewModel()
        }
    }
    private lateinit var adapter: Adapter
    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        binding.specificToolbars.title = title
        binding.specificToolbars.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observeUi().collect { state ->
                    when (state.isLoading) {
                        true -> binding.progressBar.visibility = View.VISIBLE
                        false -> binding.progressBar.visibility = View.GONE
                    }
                    adapter.submitList(state.items)
                    activity.setSecondFragmentIndex(state.index)
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
        adapter = Adapter {title, _ ->
            findNavController().navigate(
                R.id.action_secondFragment_to_thirdFragment,
                bundleOf("title" to title)
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
package com.example.vimos.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.vimos.R
import com.example.vimos.app.App
import com.example.vimos.databinding.FragmentProductBinding
import com.example.vimos.domain.models.Product
import com.example.vimos.presentation.viewmodels.ProductViewModel
import com.example.vimos.utils.APIConstants
import com.example.vimos.utils.Factory
import kotlinx.coroutines.launch

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val slug by lazy { requireArguments().getString("slug") }
    private val viewModel by viewModels<ProductViewModel> {
        Factory {
            App.appComponent.productComponent().create(requireNotNull(slug)).viewModel()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observeUi().collect { state ->
                    when (state.isLoading) {
                        true -> binding.progressBar.visibility = View.VISIBLE
                        false -> binding.progressBar.visibility = View.GONE
                    }
                    state.product?.let { updateDetails(it) }
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

        binding.toolbars.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateDetails(product: Product) {
        binding.article.text = product.sku.toString()
        binding.price.text = product.purchase.price.toString()
        binding.article.text = product.sku.toString()
        setImage(product.images[0].original)
    }

    private fun setImage(imgUrl: String) {
        Glide.with(this)
            .load(APIConstants.BASE_IMAGE_URL + imgUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.imageView)
    }
}
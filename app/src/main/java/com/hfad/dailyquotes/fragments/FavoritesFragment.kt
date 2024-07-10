package com.hfad.dailyquotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.hfad.dailyquotes.R
import com.hfad.dailyquotes.adapter.FavoriteAdapter
import com.hfad.dailyquotes.database.QuoteDataBase
import com.hfad.dailyquotes.databinding.FragmentFavoritesBinding
import com.hfad.dailyquotes.repository.QuoteRepository
import com.hfad.dailyquotes.viewModels.QuoteViewModel
import com.hfad.dailyquotes.viewModels.UserViewModelFactory

class FavoritesFragment : Fragment() {
    private  var _binding:FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteAdapter
    private lateinit var viewModel: QuoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        val repository = QuoteRepository(QuoteDataBase.getInstance(requireContext()))
        viewModel = ViewModelProvider(this, UserViewModelFactory(repository))[QuoteViewModel::class.java]

        adapter = FavoriteAdapter(requireContext(), onClickListener = {
            viewModel.removeQuote(it)
        })

        binding.recyclerAdd.adapter = adapter

        viewModel.getAllQuote.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
 }

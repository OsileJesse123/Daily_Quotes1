package com.hfad.dailyquotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.hfad.dailyquotes.R
import com.hfad.dailyquotes.adapter.CategoriesAdapter
import com.hfad.dailyquotes.dataClass.CategoriesClass
import com.hfad.dailyquotes.databinding.FragmentCategoriesBinding


class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var list: ArrayList<CategoriesClass>
    private lateinit var adapter: CategoriesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val view = binding.root





        list = ArrayList()

        val loveCategory = CategoriesClass("Love", R.drawable.love2)
        list.add(loveCategory)

        val lifeCategory = CategoriesClass("Life", R.drawable.life)
        list.add(lifeCategory)

        val successCategory = CategoriesClass("Success", R.drawable.success)
        list.add(successCategory)

       val englishCategory = CategoriesClass("English", R.drawable.bean2)
        list.add(englishCategory)

        val motivationCategory = CategoriesClass("Motivation", R.drawable.steve)
        list.add(motivationCategory)

        val angryCategory = CategoriesClass("Angry", R.drawable.angry)
        list.add(angryCategory)

        val tiredCategory = CategoriesClass("Tired", R.drawable.tired)
        list.add(tiredCategory)

        val happyCategory = CategoriesClass("Happy", R.drawable.happy)
        list.add(happyCategory)

        val sadCategory = CategoriesClass("Sad", R.drawable.sad)
        list.add(sadCategory)

        val funnyCategory = CategoriesClass("Funny", R.drawable.fuuny)
        list.add(funnyCategory)


        adapter = CategoriesAdapter(list, onClickListener = {
            name ->
            findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToQuotesFragment(name = name))
        })
        binding.recyclerCategories.adapter = adapter

        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
 }

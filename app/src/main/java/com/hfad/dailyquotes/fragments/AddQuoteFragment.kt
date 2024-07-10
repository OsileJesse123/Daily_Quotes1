package com.hfad.dailyquotes.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hfad.dailyquotes.mainActivity.MainActivity
import com.hfad.dailyquotes.R
import com.hfad.dailyquotes.adapter.AddAdapter
import com.hfad.dailyquotes.dataClass.Quotedataclass
import com.hfad.dailyquotes.database.QuoteDataBase
import com.hfad.dailyquotes.databinding.FragmentAddQuoteBinding
import com.hfad.dailyquotes.repository.QuoteRepository
import com.hfad.dailyquotes.viewModels.QuoteViewModel
import com.hfad.dailyquotes.viewModels.UserViewModelFactory

class AddQuoteFragment : Fragment() {
    private var _binding: FragmentAddQuoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var userAdapter : AddAdapter
    private lateinit var viewModel: QuoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddQuoteBinding.inflate(inflater, container, false)

        val repository = QuoteRepository(QuoteDataBase.getInstance(requireContext()))
        viewModel = ViewModelProvider(this, UserViewModelFactory(repository))[QuoteViewModel::class.java]
 
         //set Adapter
        userAdapter = AddAdapter(requireContext(), onClickListener = {
            quote ->
            viewModel.removeQuote(quote)
        }, viewModel)
        binding.recyclerAdd.adapter = userAdapter

        viewModel.userQuotes.observe(viewLifecycleOwner, Observer {
                viewAll ->
            userAdapter.submitList(viewAll)
        })

        binding.addingBtnFrag.setOnClickListener { addInfo()

        }

        binding.recyclerAdd.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                binding.apply {
                    if (dy > 0 && addingBtnFrag.isShown){
                        addingBtnFrag.hide()
                    } else if(dy < 0 && !addingBtnFrag.isShown){
                        addingBtnFrag.show()
                    }
                }

            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }


    private fun addInfo() {
        val inflater = LayoutInflater.from(requireContext())
        val v = inflater.inflate(R.layout.add_dialog, null)
        val userAuthor = v.findViewById<EditText>(R.id.user)
        val userQuote = v.findViewById<EditText>(R.id.userInput)
        val addDialog = AlertDialog.Builder(requireContext())
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
            dialog, _ ->
            val author = userAuthor.text.toString()
            val quote = userQuote.text.toString()
            if (author.isNotEmpty() && quote.isNotEmpty()){
                val quotes = Quotedataclass(0, "Author: $author", "Quote: $quote",false, "name")
                viewModel.addQuote(quotes)
                Toast.makeText(requireContext(), "Quote added successful", Toast.LENGTH_SHORT).show()

            } else{
                Toast.makeText(requireContext(), "Fill Margin", Toast.LENGTH_SHORT).show()

            }
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
            dialog, _ ->
            dialog.dismiss()
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).hideBottomNav()
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as MainActivity).showBottomNav()
    }
 }

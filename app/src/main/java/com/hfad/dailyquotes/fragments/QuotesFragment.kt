package com.hfad.dailyquotes.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hfad.dailyquotes.R
import com.hfad.dailyquotes.adapter.FavoriteAdapter
import com.hfad.dailyquotes.dataClass.QuoteCategory
import com.hfad.dailyquotes.dataClass.Quotedataclass
import com.hfad.dailyquotes.database.QuoteDataBase
import com.hfad.dailyquotes.database.QuoteDataBase_Impl
import com.hfad.dailyquotes.databinding.FragmentQuotesBinding
import com.hfad.dailyquotes.mainActivity.MainActivity
import com.hfad.dailyquotes.repository.QuoteRepository
import com.hfad.dailyquotes.viewModels.QuoteViewModel
import com.hfad.dailyquotes.viewModels.UserViewModelFactory

class QuotesFragment : Fragment() {
    private  var _binding: FragmentQuotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: QuoteViewModel
    private var motivationNumber : Int = 0
    private  var quotesLists =  ArrayList<Quotedataclass>()
    private val args by navArgs<QuotesFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuotesBinding.inflate(inflater, container, false)


        val repository = QuoteRepository(QuoteDataBase.getInstance(requireContext()))
        viewModel = ViewModelProvider(this, UserViewModelFactory(repository))[QuoteViewModel::class.java]


        val getName = args.name
        binding.headerTitle.text = getName

        viewModel.getQuotesByCategory(getName)

        //val currentQuote = quotesLists[motivationNumber]

        viewModel.currentQuote.observe(viewLifecycleOwner){
            quote ->
            quote?.let {
                setQuotesToTextView(it)
            }
        }

        binding.copyBtn.setOnClickListener{
                viewModel.currentQuote.value?.let {
                    currentQuote ->
                    val combinedText = "${currentQuote.quoteText} -${currentQuote.quoteAuthor}"
                    // copy the combined text to the clipboard
                    val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("Quote", combinedText)
                    clipboard.setPrimaryClip(clip)
                    // show text
                    Toast.makeText(requireContext(), " copied to clipboard!", Toast.LENGTH_SHORT).show()
                }
        }


        // set prev button to get previous quote
        binding.prevButton.setOnClickListener{
            viewModel.previousQuote()
            /*// decrement motivationNumber to get previous quote from QuoteDataBase
            motivationNumber--

            // check if motivationNumber is smaller than 0 then get last quote from QuoteDataBase
            if (motivationNumber < 0){
                motivationNumber = quotesLists.size - 1
            }
*/
            //setQuotesToTextView()
        }

        // set next button to get next quote
        binding.nextButton.setOnClickListener{
            viewModel.nextQuote()
            // increment motivationNumber to get next quote from QuoteDataBase
//            motivationNumber++
//            // check if more quotes are available from QuoteDataBase
//            if (motivationNumber >= quotesLists.size){
//                motivationNumber = 0
//            }
//            setQuotesToTextView()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_quotesFragment_to_addQuoteFragment2)
        }
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

    private fun setQuotesToTextView(quote: Quotedataclass) {


        // get quotes from list from current position
      binding.quoteContent.text = quote.quoteText

        // get quotes from list from current position
        binding.writerName.text = quote.quoteAuthor


        binding.heartCheckbox.setOnCheckedChangeListener(null)

       binding.heartCheckbox.isChecked = quote.isFavorite
        
        binding.heartCheckbox.setOnCheckedChangeListener{
                _, isChecked ->
            //quotesLists[motivationNumber].isFavorite = isChecked
//            val favorites = mutableListOf(quotesLists[motivationNumber].copy(isFavorite = isChecked))
//            //val favorites = mutableListOf(Quotedataclass(0, quoteTxt,quoteAuthor,isChecked,))
//
//            if (isChecked){
//                viewModel.favoriteQuote(favorites)
//                Toast.makeText(requireContext(),"Quotes added to favorites",Toast.LENGTH_SHORT).show()
//            } else{
//
//                Toast.makeText(requireContext(),"Quotes removed from favorites",Toast.LENGTH_SHORT).show()
//            }
        }
    }

}

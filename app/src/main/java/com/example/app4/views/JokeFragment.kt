package com.example.app4.views

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app4.Adapter.JokesAdapter
import com.example.app4.R
import com.example.app4.databinding.FragmentJokeBinding
import com.example.app4.utils.UIState
import dagger.hilt.android.AndroidEntryPoint

class JokeFragment : BaseFragment() {

    private val binding by lazy {
        FragmentJokeBinding.inflate(layoutInflater)

    }

    private val jokesAdapter by lazy {
        JokesAdapter{

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.jokesRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            Log.d(ContentValues.TAG, "ALEXIS onCreateView:" + jokesAdapter)
            adapter = jokesAdapter
        }

        jokesViewModel.jokes.observe(viewLifecycleOwner){ state ->
            when(state){

                is UIState.LOADING -> {
                  // LOADING SPINNER
                    binding.loadingSpinner.visibility = View.VISIBLE
                    binding.jokesRecycler.visibility = View.GONE

                }
                is UIState.SUCCESS -> {
                  //UPDATE RECYCLERVIEW ADAPTER
                    binding.loadingSpinner.visibility = View.GONE
                    binding.jokesRecycler.visibility = View.VISIBLE

                    jokesAdapter.updateNewJokes(state.response.value ?: emptyList())
                    Log.d(ContentValues.TAG, "ALEXIS SUCCESS:" + state.toString())

                }
                is UIState.ERROR -> {
                  //SHOW ERROR DIALOG
                    binding.loadingSpinner.visibility = View.GONE
                    binding.jokesRecycler.visibility = View.GONE

                    Log.d(ContentValues.TAG, "ALEXIS ERROR:" + state.error.toString())


                    showError(state.error.localizedMessage){

                        jokesViewModel.getJokes()

                    }
                }

            }
        }
        jokesViewModel.getJokes()

        return binding.root
    }

}
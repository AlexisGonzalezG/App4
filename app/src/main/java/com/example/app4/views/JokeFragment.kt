package com.example.app4.views

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app4.Adapter.JokesAdapter
import com.example.app4.R
import com.example.app4.databinding.ActivityMainBinding
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
            adapter = jokesAdapter
        }

        binding.btnRandom.setOnClickListener {
            jokesViewModel.getJokes()
            jokesViewModel.jokes.observe(viewLifecycleOwner){ state ->
                when(state){
                    is UIState.LOADING -> {
                    }
                    is UIState.SUCCESS -> {
                        jokesAdapter.updateNewJokes(state.response.value)
                    }
                    is UIState.ERROR -> {
                        showError(state.error.localizedMessage){
                            jokesViewModel.getJokes()
                        }
                    }
                }
            }
        }

        binding.btnRandom2.setOnClickListener {
            var first_Name = binding.tvName.text.toString()
            var last_Name = binding.tvLastname.text.toString()

            jokesViewModel.getJokeNames(first_Name,last_Name)
            jokesViewModel.jokes.observe(viewLifecycleOwner){ state ->
                when(state){
                    is UIState.LOADING -> {
                        // LOADING SPINNER
                    }
                    is UIState.SUCCESS -> {
                        //UPDATE RECYCLERVIEW ADAPTER
                        jokesAdapter.updateNewJokes(state.response.value)
                    }
                    is UIState.ERROR -> {
                        //SHOW ERROR DIALOG
                        showError(state.error.localizedMessage){
                            jokesViewModel.getJokeNames(first_Name,last_Name)
                        }
                    }
                }
            }
        }

        binding.btnRandom3.setOnClickListener {

            jokesViewModel.getJokesList20()
            jokesViewModel.jokes.observe(viewLifecycleOwner){ state ->
                when(state){
                    is UIState.LOADING -> {
                        // LOADING SPINNER
                    }
                    is UIState.SUCCESS -> {
                        //UPDATE RECYCLERVIEW ADAPTER
                        jokesAdapter.updateNewJokes(state.response.value)
                    }
                    is UIState.ERROR -> {
                        //SHOW ERROR DIALOG
                        showError(state.error.localizedMessage){
                            jokesViewModel.getJokesList20()
                        }
                    }
                }
            }
        }

        return binding.root
    }



}
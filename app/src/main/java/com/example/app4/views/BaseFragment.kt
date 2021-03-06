package com.example.app4.views

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.app4.viewmodel.JokesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment: Fragment() {

    protected val jokesViewModel by lazy {
        ViewModelProvider(requireActivity())[JokesViewModel::class.java]
    }

    protected fun showError(message: String, action: () -> Unit) {
        AlertDialog.Builder(requireContext())
            .setTitle("Error has occurred")
            .setMessage(message)
            .setPositiveButton("RETRY") { dialog, _ ->
                action.invoke()
            }
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
    }

}
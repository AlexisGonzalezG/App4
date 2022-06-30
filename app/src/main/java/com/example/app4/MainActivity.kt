package com.example.app4

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.app4.databinding.ActivityMainBinding
import com.example.app4.views.JokeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)
        show_fragment()
    }

    fun show_fragment(){


        val randomFragment_ = JokeFragment()
        val fragment : Fragment?=

            supportFragmentManager.findFragmentByTag(JokeFragment::class.java.simpleName)

        if(fragment !is JokeFragment){
            supportFragmentManager.beginTransaction()
                .add(R.id.LinearFragment_Container,randomFragment_,JokeFragment::class.java.simpleName)
                .commit()

        }

    }

}
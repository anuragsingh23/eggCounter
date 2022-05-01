package com.example.eggcounter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eggcounter.R
import com.example.eggcounter.databinding.FragmentEggBinding
import com.example.eggcounter.viewmodel.EggViewModel

class Egg : Fragment(R.layout.fragment_egg){


    private  var _mBinding: FragmentEggBinding? = null
    private val mBinding get()  = _mBinding!!

    private val viewModel : EggViewModel by lazy {
        ViewModelProvider(this).get(EggViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.score.observe(viewLifecycleOwner, Observer {
            mBinding.tvScore.text = it.toString()
        })
        _mBinding = FragmentEggBinding.inflate(layoutInflater,container,false)

        mBinding.imgEgg.setOnClickListener{
            viewModel.increment()
        }

        mBinding.btnReset.setOnClickListener {
            viewModel.reset()
        }

        mBinding.tvScore.text = viewModel.score.toString()
        return mBinding.root
    }

    override fun onDestroy() {
        _mBinding = null
        super.onDestroy()
    }

}
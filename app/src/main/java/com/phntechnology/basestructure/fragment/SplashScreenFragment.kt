package com.phntechnology.basestructure.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.commonutils.util.backPressedHandle
import com.phntechnology.basestructure.R
import com.phntechnology.basestructure.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenFragment : Fragment() {
    private var _binding: FragmentSplashScreenBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)
        backPressedHandle {
            requireActivity().finishAffinity()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postAtTime({
            findNavController().navigate(R.id.action_splashScreenFragment_to_searchFragment)
        }, 300)

    }
}
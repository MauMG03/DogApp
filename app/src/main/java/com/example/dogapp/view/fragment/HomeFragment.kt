package com.example.dogapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapp.R
import com.example.dogapp.databinding.FragmentHomeBinding
import com.example.dogapp.view.adapter.DatesAdapter
import com.example.dogapp.view.model.Date

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerDates()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }

    private fun observerDates() {
        val datesList: MutableList<Date> = mutableListOf(
            Date(1, "Max", "Fiebre", "imagen1.jpg"),
            Date(2, "Bella", "Vómitos", "imagen2.jpg"),
            Date(3, "Charlie", "Diarrea", "imagen3.jpg")
        )

        val recycler = binding.recyclerview
        val layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layoutManager
        val adapter = DatesAdapter(datesList, findNavController())
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
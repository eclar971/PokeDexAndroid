package com.example.pokedex.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.*
import com.example.pokedex.ui.home.HomeViewModel
import com.example.pokedex.databinding.FragmentHomeBinding


open class HomeFragment : Fragment() {
    private lateinit var  adapter: RecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentHomeBinding? = null
    private val sharedViewModel: HomeViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val binding = binding.root
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = (activity as MainActivity)
        val temp = mainActivity.pokemonList[1007]
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = RecyclerAdapter(
            mainActivity.pokemonList,
            mainActivity.pokeArray,
            fun(color: Int,currentPoke: pokemon) {
                sharedViewModel.pokePallet.value = color
                sharedViewModel.currentPoke.value = currentPoke
            }
        )
        recyclerView.adapter = adapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}

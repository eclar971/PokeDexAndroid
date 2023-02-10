package com.example.pokedex.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
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
    var pokemonList : MutableList<pokemon> = mutableListOf()

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
        val mainActivity = (activity as MainActivity)
        pokemonList.addAll(mainActivity.pokemonList)
        setHasOptionsMenu(true)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        val mainActivity = (activity as MainActivity)
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = RecyclerAdapter(
            mainActivity,
            pokemonList,
            fun(color: Int,currentPoke: pokemon) {
                sharedViewModel.pokePallet.value = color
                sharedViewModel.currentPoke.value = currentPoke
            }
        )
        recyclerView.adapter = adapter
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main,menu)
        val tempPokeList = mutableListOf<pokemon>()
        val mainActivity = (activity as MainActivity)
        val item = menu.findItem(R.id.search_action)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val searchText = query!!.lowercase()
                if(searchText.isNotEmpty()){
                    pokemonList.forEach{
                        if (it.pokeName.lowercase().contains(searchText)){
                            tempPokeList.add(it)
                        }
                    }
                    adapter.setFilter(tempPokeList)
                }else{
                    tempPokeList.addAll(mainActivity.pokemonList)
                    adapter.setFilter(tempPokeList)
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                tempPokeList.clear()
                pokemonList.clear()
                pokemonList.addAll(mainActivity.pokemonList)
                val searchText = newText!!.lowercase()
                if(searchText.isNotEmpty()){
                    pokemonList.forEach{
                        if (it.pokeName.lowercase().contains(searchText)){
                            tempPokeList.add(it)
                        }
                    }
                    adapter.setFilter(tempPokeList)
                }else{
                    tempPokeList.addAll(mainActivity.pokemonList)
                    adapter.setFilter(tempPokeList)
                }
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}

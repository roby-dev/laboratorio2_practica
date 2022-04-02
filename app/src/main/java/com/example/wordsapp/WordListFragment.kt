package com.example.wordsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.FragmentWordListBinding

class  WordListFragment : Fragment () {

    private var _binding: FragmentWordListBinding? = null
    private val binding get() = _binding!!

    lateinit var letterId : String

    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WordAdapter(letterId,requireContext())

        recyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))

        var conteo = view.findViewById<TextView>(R.id.cantidad_votos)
        var mayor = view.findViewById<TextView>(R.id.mayor_votos)
        var total :Int = LetterListFragment.votos_pelicula_1 + LetterListFragment.votos_pelicula_2
        var mayor_pelicula:String =""
        if(LetterListFragment.votos_pelicula_1>LetterListFragment.votos_pelicula_2){
            mayor_pelicula = "La película con mayor cantidad de votos es: Tick, Tick... Boom!"
        }
        else if(LetterListFragment.votos_pelicula_2>LetterListFragment.votos_pelicula_1){
            mayor_pelicula = "La película con mayor cantidad de votos es: El poder del perro"
        }else if(LetterListFragment.votos_pelicula_2==LetterListFragment.votos_pelicula_1){
            mayor_pelicula = "Los votos están igualados"
        }



        conteo.text = "Cantidad de votos totales: "+ total.toString()
        mayor.text = mayor_pelicula




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
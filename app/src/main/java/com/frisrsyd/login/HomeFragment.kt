package com.frisrsyd.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.frisrsyd.login.databinding.FragmentHomeBinding
import com.frisrsyd.login.model.Materi

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

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val list = ArrayList<Materi>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.rvMateri.setHasFixedSize(true)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
        list.addAll(getListMateri())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRecyclerList()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun getListMateri(): ArrayList<Materi> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataStatus = resources.getStringArray(R.array.data_status)
        val dataLevel = resources.getStringArray(R.array.data_level)
        val dataChapters = resources.getStringArray(R.array.data_chapters)
        val dataImg = resources.getStringArray(R.array.data_img)
        val dataDesc = resources.getStringArray(R.array.data_desc)
        val listMateri = ArrayList<Materi>()
        for (i in dataTitle.indices) {
//            val materi = Materi(dataTitle[i], dataStatus[i], dataLevel[i], dataChapters[i], dataImg.getResourceId(i, -1))
            val materi = Materi(
                dataTitle[i],
                dataStatus[i],
                dataLevel[i],
                dataChapters[i],
                dataImg[i],
                dataDesc[i]
            )
            listMateri.add(materi)
        }
        return listMateri
    }

    private fun showRecyclerList() {
        binding.rvMateri.layoutManager = LinearLayoutManager(this.context)
        val listMateriAdapter = ListMateriAdapter(list)
        binding.rvMateri.adapter = listMateriAdapter

        listMateriAdapter.setOnItemClickCallback(object : ListMateriAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Materi) {
                showDetailSelectedMateri(data)
            }
        })

    }

//    private fun showSelectedMateri(materi: Materi) {
//        Toast.makeText(context, "Kamu memilih " + materi.title, Toast.LENGTH_SHORT).show()
//    }

    private fun showDetailSelectedMateri(materi: Materi) {
        val moveIntent = Intent(this.context, DetailActivity::class.java)
        moveIntent.putExtra(DetailActivity.EXTRA_MATPEL, materi)
        startActivity(moveIntent)
    }
}
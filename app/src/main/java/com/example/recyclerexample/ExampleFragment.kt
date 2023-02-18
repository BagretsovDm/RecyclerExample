package com.example.recyclerexample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerexample.databinding.FragmentExampleBinding
import com.google.android.material.divider.MaterialDividerItemDecoration

class ExampleFragment : Fragment(R.layout.fragment_example) {

    private val viewModel: ExampleViewModel by viewModels()

    private val exampleAdapter by lazy {
        viewModel.positionList.value?.toList()?.let { Adapter(it, ::onClickedFirstPlus) }
    }

    private var _binding: FragmentExampleBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentExampleBinding.bind(view)

        _binding?.toolbar?.setOnMenuItemClickListener {
            viewModel.addPosition()
            true
        }

        setupObservers()
        setupRv()
    }

    private fun setupRv() {
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        divider.setDividerColorResource(requireContext(), R.color.white)
        divider.dividerThickness = 8
        divider.isLastItemDecorated = false

        with(_binding!!) {
            recyclerView.adapter = exampleAdapter
            recyclerView.addItemDecoration(divider)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun setupObservers() {
        viewModel.firstCount.observe(viewLifecycleOwner) {
            _binding?.firstResult?.text = "First: $it"
        }

        viewModel.secondCount.observe(viewLifecycleOwner) {
            _binding?.secondResult?.text = "Second $it"
        }

        viewModel.positionList.observe(viewLifecycleOwner) {
            exampleAdapter?.setList(it.toList())
        }
    }

    private fun onClickedFirstPlus(position: Int) {
        viewModel.updateList(position)
        exampleAdapter?.notifyDataSetChanged()
    }
}

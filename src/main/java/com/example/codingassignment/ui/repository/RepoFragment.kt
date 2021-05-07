package com.example.codingassignment.ui.repository
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.example.codingassignment.R
import com.example.codingassignment.data.model.Languages
import com.example.codingassignment.databinding.FragmentRepoListBinding
import com.example.codingassignment.ui.repository.adapter.ReposAdapter
import com.example.codingassignment.ui.repository.adapter.ReposLoadStateAdapter
import com.example.codingassignment.ui.viewmodel.ReposViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class RepoFragment : Fragment(R.layout.fragment_repo_list) {

    private val viewModel by viewModels<ReposViewModel>()

    private var _binding: FragmentRepoListBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        _binding = FragmentRepoListBinding.bind(view)

        val adapter = ReposAdapter()

        binding.apply {

            recycler.apply {
                setHasFixedSize(true)
                itemAnimator = null
                this.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = ReposLoadStateAdapter { adapter.retry() },
                    footer = ReposLoadStateAdapter { adapter.retry() }
                )
                postponeEnterTransition()
                viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
            }

            btnRetry.setOnClickListener {
                adapter.retry()
            }
        }


        viewModel.repos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progress.isVisible = loadState.source.refresh is LoadState.Loading
                recycler.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnRetry.isVisible = loadState.source.refresh is LoadState.Error
                error.isVisible = loadState.source.refresh is LoadState.Error

                // no results found
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recycler.isVisible = false
                    emptyTv.isVisible = true
                } else {
                    emptyTv.isVisible = false
                }
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_repos, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        val searchAutoComplete: SearchView.SearchAutoComplete = searchView.findViewById(R.id.search_src_text)

        // Get SearchView autocomplete object
        searchAutoComplete.setTextColor(Color.WHITE)
        searchAutoComplete.setDropDownBackgroundResource(R.color.design_default_color_on_primary)

        val newsAdapter: ArrayAdapter<String> = ArrayAdapter(
            this.requireContext(),
            R.layout.dropdown_item,
            Languages.data
        )
        searchAutoComplete.setAdapter(newsAdapter)

        // Listen to search view item on click event
        searchAutoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, _, itemIndex, _ ->
                val queryString = adapterView.getItemAtPosition(itemIndex) as String
                searchAutoComplete.setText(
                    String.format(
                        getString(R.string.search_query),
                        queryString
                    )
                )
                binding.recycler.scrollToPosition(0)
                val languageQuery = String.format(getString(R.string.query), queryString)
                viewModel.searchRepos(languageQuery)
                searchView.clearFocus()
                (activity as AppCompatActivity).supportActionBar?.title = queryString.capitalize(
                    Locale.ROOT
                )
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}
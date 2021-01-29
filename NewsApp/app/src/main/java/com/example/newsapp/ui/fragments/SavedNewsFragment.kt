package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.databinding.FragmentBreakingNewsBinding
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.model.Article
import com.example.newsapp.ui.NewsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SavedNewsFragment : Fragment() {

    private var _binding: FragmentSavedNewsBinding? = null
    private val binding get() = _binding!!

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedNewsBinding.inflate(inflater, container, false)

        setupRecyclerView()

        coroutineScope.launch {
            var articles: List<Article>

            withContext(Dispatchers.IO) {
                articles = ArticleDatabase.invoke(requireContext()).getArticleDao().getAllArticles()

                withContext(Dispatchers.Main) {
                    newsAdapter.differ.submitList(articles)
                }
            }
        }

        binding.removeButton.setOnClickListener {
            coroutineScope.launch {

                withContext(Dispatchers.IO){
                    ArticleDatabase.invoke(requireContext()).getArticleDao().deleteArticle()
                }

                Toast.makeText(activity, "All Data Deleted in Database", Toast.LENGTH_SHORT).show()

                newsAdapter.differ.submitList(mutableListOf())
            }
        }

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )
        }

        return binding.root
    }


    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvSavedNews.adapter = newsAdapter
        binding.rvSavedNews.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
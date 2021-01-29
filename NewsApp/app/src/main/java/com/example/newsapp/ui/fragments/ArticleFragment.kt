package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.db.ArticleDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)

        val article = args.article
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(article.url)

        binding.fab.setOnClickListener {
            coroutineScope.launch {
                withContext(Dispatchers.IO){
                    ArticleDatabase.invoke(requireContext()).getArticleDao().upsert(article)
                }

                Toast.makeText(activity, "Article saved successfully", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
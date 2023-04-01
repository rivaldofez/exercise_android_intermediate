package com.dicoding.newsapp.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.newsapp.data.NewsRepository
import org.junit.Assert.*

import org.junit.Assert
import com.dicoding.newsapp.data.Result
import com.dicoding.newsapp.data.local.entity.NewsEntity
import com.dicoding.newsapp.utils.DataDummy
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import androidx.lifecycle.Observer
import org.junit.Rule
import org.mockito.Mockito

@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var newsRepository : NewsRepository
    private lateinit var newsViewModel: NewsViewModel
    private val dummyNews = DataDummy.generateDummyNewsEntity()

    @Before
    fun setUp() {
        newsViewModel = NewsViewModel(newsRepository)
    }

    @Test
    fun `when Get HeadlineNews Should Not Null and Return Success`() {
        val observer = Observer<Result<List<NewsEntity>>> {}
        try {
            val expectedNews = MutableLiveData<Result<List<NewsEntity>>>()
            expectedNews.value = Result.Success(dummyNews)
            `when`(newsRepository.getHeadlineNews()).thenReturn(expectedNews)

            val actualNews = newsViewModel.getHeadlineNews().observeForever(observer)

            Mockito.verify(newsRepository).getHeadlineNews()
            Assert.assertNotNull(actualNews)
        } finally {
            newsViewModel.getHeadlineNews().removeObserver(observer)
        }
    }

}

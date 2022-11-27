package com.example.sbertestapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sbertestapp.data.models.PhotoModel
import com.example.sbertestapp.domain.interactors.Interactor
import com.example.sbertestapp.ui.appresources.ResourceManager
import com.example.sbertestapp.ui.entities.Photo
import com.example.sbertestapp.ui.entities.State
import com.example.sbertestapp.ui.handleerror.FailureFactory
import com.example.sbertestapp.ui.handleerror.NoConnectionException
import com.example.sbertestapp.ui.viewmodels.MainViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ViewModelUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `test_success_fetch`() = runBlocking {

        val fakeInteractor = FakeInteractor()

        val mainViewModel =
            MainViewModel(interactor = fakeInteractor, dispatcher = TestCoroutineDispatcher())

        mainViewModel.fetchData()

        val photoList = listOf(
            Photo("1", "URL1"),
            Photo("2", "URL2")
        )

        assertEquals(photoList[0], (mainViewModel.liveData.value as State.Loaded<List<Photo>>).getData()[0])
        assertEquals(photoList[1], (mainViewModel.liveData.value as State.Loaded<List<Photo>>).getData()[1])
    }

    @Test
    fun `test_failed_fetch`() = runBlocking {

        val fakeInteractor = FakeInteractor().also { it.sendError = true }

        val mainViewModel =
            MainViewModel(interactor = fakeInteractor, dispatcher = TestCoroutineDispatcher())

        mainViewModel.fetchData()

        val expectation = "No connection"
        assertEquals(expectation, (mainViewModel.liveData.value as State.Error<List<Photo>>).getErrorMessage())
    }

    private class FakeInteractor : Interactor<List<Photo>> {

        val photoModelList = listOf(
            PhotoModel("1", PhotoModel.Urls("URL1")),
            PhotoModel("2", PhotoModel.Urls("URL2"))
        )

        var sendError = false

        override suspend fun getDataState(): State<List<Photo>> {
            return try {
                State.Loaded(provideData().map { it.map() })
            } catch (e: Exception) {
                State.Error(FailureFactory(FakeResourceManager()).handle(e))
            }
        }

        private fun provideData(): List<PhotoModel> {
            if (sendError) {
                throw NoConnectionException()
            }
            return photoModelList
        }
    }

    private class FakeResourceManager : ResourceManager {
        override fun getString(stringResId: Int): String {
            return "No connection"
        }
    }
}
package com.example.sbertestapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sbertestapp.data.*
import com.example.sbertestapp.domain.InteractorImpl
import com.example.sbertestapp.ui.MainViewModel
import com.example.sbertestapp.ui.Photo
import com.example.sbertestapp.ui.State
import com.example.sbertestapp.ui.theme.SberTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SberTestAppTheme {
        Greeting("Android")
    }
}

 */

/*
@Composable
fun PhotoContent() {
    val puppies = remember { DataProvider.puppyList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = puppies,
            itemContent = {
                PuppyListItem(puppy = it)
            })
    }
}

 */

@Composable
fun Main() {
    SberTestAppTheme {
        val remoteDataSourceImpl = RemoteDataSourceImpl(RetrofitClient().retrofitServices)
        val repositoryImpl = RepositoryImpl(remoteDataSourceImpl)
        val resourceManagerImpl = ResourceManagerImpl(context = LocalContext.current)
        val handler = FailureFactory(resourceManagerImpl)
        val interactorImpl = InteractorImpl(repositoryImpl, handler)
        val viewModel = MainViewModel(interactorImpl)

        val state by viewModel.liveData.observeAsState()

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(viewModel)
            }
        ) {
            when (val s = state) {
                is State.Error<List<Photo>> -> ErrorState(s.getErrorMessage())
                is State.Loaded<List<Photo>> -> LoadedState(s.getData(), viewModel)
                is State.IsLoading<List<Photo>> -> LoadState()
                is State.Default<List<Photo>> -> DefaultState()
            }
        }
    }
}

@Composable
fun SetState() {

}

@Composable
fun PhotoCard(photo: Photo, viewModel: MainViewModel<Photo>) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable {
                viewModel.deleteItem(photo)
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(photo.url),
            contentDescription = null,
            modifier = Modifier.size(300.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun LoadedState(state: List<Photo>, viewModel: MainViewModel<Photo>) {
    val photos = remember { state }
    Log.d("KEK", photos.toString())
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = state
        ) {
            PhotoCard(it, viewModel)
        }
    }
}

@Composable
fun ErrorState(error: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun LoadState() {
    Column(modifier = Modifier.fillMaxWidth()) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp),
            backgroundColor = Blue,
            color = Gray
        )
    }
}

@Composable
fun DefaultState() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = "Welcome",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun FloatingActionButton(viewModel: MainViewModel<Photo>) {
    return ExtendedFloatingActionButton(
        icon = { Icon(Icons.Filled.Search, contentDescription = "Find") },
        text = { Text("Find") },
        onClick = { viewModel.fetchData() }
    )
}
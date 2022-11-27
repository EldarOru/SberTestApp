package com.example.sbertestapp.ui.activities

import android.os.Bundle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberAsyncImagePainter
import com.example.sbertestapp.ProvideInjection
import com.example.sbertestapp.R
import com.example.sbertestapp.ui.entities.Photo
import com.example.sbertestapp.ui.entities.State
import com.example.sbertestapp.ui.theme.SberTestAppTheme
import com.example.sbertestapp.ui.viewmodels.MainViewModel
import com.example.sbertestapp.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as ProvideInjection).handle().inject(this)

        viewModel = (ViewModelProvider(this, vmFactory)
            .get(MainViewModel::class.java))

        setContent {
            Main(viewModel)
        }
    }
}

@Composable
private fun Main(viewModel: MainViewModel) {
    SberTestAppTheme {
        val state by viewModel.liveData.observeAsState()

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(viewModel)
            }
        ) {
            SetState(s = state!!, viewModel = viewModel)
        }
    }
}

@Composable
private fun SetState(s: State<List<Photo>>, viewModel: MainViewModel) {
    when (s) {
        is State.Error -> ErrorState(s.getErrorMessage())
        is State.Loaded -> LoadedState(s.getData(), viewModel)
        is State.IsLoading -> LoadState()
        is State.Default -> DefaultState()
    }

}

@Composable
private fun PhotoCard(photo: Photo, viewModel: MainViewModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(photo.url),
            contentDescription = null,
            modifier = Modifier.size(300.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_delete_24),
            contentDescription = "delete",
            Modifier
                .clickable {
                    viewModel.deleteItem(photo)
                }
                .size(50.dp)
        )
        Divider(color = Black, thickness = 1.dp)
    }
}

@Composable
private fun LoadedState(state: List<Photo>, viewModel: MainViewModel) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(
            items = state
        ) {
            PhotoCard(it, viewModel)
        }
    }
}

@Composable
private fun ErrorState(error: String) {
    Text(
        text = error,
        fontSize = 40.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .wrapContentHeight(Alignment.CenterVertically)
    )
}

@Composable
private fun LoadState() {
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
private fun DefaultState() {
    Text(
        text = stringResource(R.string.welcome),
        fontSize = 40.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .wrapContentHeight(Alignment.CenterVertically)
    )
}

@Composable
fun FloatingActionButton(viewModel: MainViewModel) {
    return ExtendedFloatingActionButton(
        icon = { Icon(Icons.Filled.Search, contentDescription = "Find") },
        text = { Text(stringResource(R.string.find)) },
        onClick = { viewModel.fetchData() }
    )
}
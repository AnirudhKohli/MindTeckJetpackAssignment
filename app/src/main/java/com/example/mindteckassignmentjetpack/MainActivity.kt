package com.example.mindteckassignmentjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mindteckassignmentjetpack.ui.theme.MindteckAssignmentJetpackTheme
import com.example.mindteckassignmentjetpack.ui.theme.MyApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MindteckAssignmentJetpackTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }

                MyApp()
            }
        }
    }
}

/*@Composable
fun MyApp() {
    var searchText by remember { mutableStateOf("") }
    val images = listOf("Image1", "Image2", "Image3")
    val items = remember { mutableStateListOf("apple", "banana", "orange", "blueberry") }
    val filteredItems = items.filter { it.contains(searchText, ignoreCase = true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    BasicTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = LocalTextStyle.current.copy(fontSize = 18.sp)
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showBottomSheet(items)
            }) {
                Text("+")
            }
        }
    ) {
        Column {
            LazyRow(modifier = Modifier.height(200.dp)) {
                items(images.size) { index ->
                    Text(images[index], modifier = Modifier.padding(16.dp))
                }
            }
            LazyColumn {
                items(filteredItems.size) { index ->
                    Text(filteredItems[index], modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}*/

/*@Composable
fun showBottomSheet(items: List<String>) {
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("List 1 (${items.size} items)")
                val charCounts = items.joinToString("").groupingBy { it }.eachCount()
                val topChars = charCounts.entries.sortedByDescending { it.value }.take(3)
                topChars.forEach { (char, count) ->
                    Text("$char = $count")
                }
            }
        }
    ) {}
}*/

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MindteckAssignmentJetpackTheme {
        Greeting("Android")
    }
}
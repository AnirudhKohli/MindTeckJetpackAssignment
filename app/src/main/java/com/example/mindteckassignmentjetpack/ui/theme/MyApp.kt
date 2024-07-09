package com.example.mindteckassignmentjetpack.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mindteckassignmentjetpack.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyApp() {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val listItems = remember { mutableStateOf(getListItems()) }
    val filteredItems = remember { mutableStateOf(listItems.value) }

    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetDialog(listItems.value)
        },
        sheetPeekHeight = 0.dp,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    scaffoldState.bottomSheetState.expand()
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = "Info")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column {
            Carousel(images = getImages())
            SearchBar(
                text = searchText,
                onTextChange = {
                    searchText = it
                    filteredItems.value = listItems.value.filter { item ->
                        item.title.contains(searchText.text, ignoreCase = true)
                    }
                }
            )
            ItemList(items = filteredItems.value)
        }
    }
}

@Composable
fun Carousel(images: List<Int>) {
    LazyRow(modifier = Modifier.padding(16.dp)) {
        items(images.size) { index ->
            Image(
                painter = painterResource(id = images[index]),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .width(300.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun SearchBar(text: TextFieldValue, onTextChange: (TextFieldValue) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Gray.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
        Spacer(modifier = Modifier.width(8.dp))
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ItemList(items: List<ListItem>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(items.size) { index ->
            ListItemCard(item = items[index])
        }
    }
}

@Composable
fun ListItemCard(item: ListItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = item.title, fontSize = 18.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                Text(text = item.subtitle, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun BottomSheetDialog(items: List<ListItem>) {
    val itemCount = items.size
    val topCharacters = getTopCharacters(items)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "List 1 ($itemCount items)")
        Spacer(modifier = Modifier.height(8.dp))
        topCharacters.forEach { (char, count) ->
            Text(text = "$char = $count")
        }
    }
}

fun getImages(): List<Int> = listOf(R.drawable.apple, R.drawable.banana, R.drawable.mango)

fun getListItems(): List<ListItem> = listOf(
    ListItem(R.drawable.apple, "Apple", "List item Apple "),
    ListItem(R.drawable.banana, "Banana", "List item Banana "),
    ListItem(R.drawable.mango, "Mango", "List item Mango ")
)

data class ListItem(val imageRes: Int, val title: String, val subtitle: String)

fun getTopCharacters(items: List<ListItem>): List<Pair<Char, Int>> {
    val charCount = mutableMapOf<Char, Int>()
    items.forEach { item ->
        item.title.forEach { char ->
            if (char.isLetter()) {
                charCount[char] = charCount.getOrDefault(char, 0) + 1
            }
        }
    }
    return charCount.toList().sortedByDescending { it.second }.take(3)
}
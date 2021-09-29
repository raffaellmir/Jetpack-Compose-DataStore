package com.example.datastorecomposev2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.datastorecomposev2.DataStoreViewModelFactory
import com.example.datastorecomposev2.MainViewModel
import com.example.datastorecomposev2.repository.DatastoreRepository
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(
        factory = DataStoreViewModelFactory(DatastoreRepository(LocalContext.current))
    )
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    val userName: String = viewModel.getUserName.observeAsState().value.toString()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text(text = "dude...", fontSize = 16.sp) },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 24.dp),
        )

        Button(
            onClick = {
                scope.launch {
                    viewModel.setUserName(textState.value.text)
                }
            },
            modifier = Modifier.padding(bottom = 24.dp)
        )
        { Text(text = "Submit", modifier = Modifier.padding(vertical = 4.dp, horizontal = 24.dp)) }

        Text(
            text = userName,
            fontSize = 20.sp
        )
    }
}
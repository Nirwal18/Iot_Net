package com.nirwal.iotnet.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nirwal.iotnet.ui.vm.DeviceState


@Composable
fun DeviceTemplateScreen(
    modifier: Modifier = Modifier,
    state: DeviceState,
    onClickOnCmd:(Boolean)-> Unit
) {

    if(state.isLoading){
        Box(contentAlignment = Alignment.Center) {
            Text("Loading content...")
        }
    }

    if (!state.error.isNullOrEmpty()){
        Box(contentAlignment = Alignment.Center){
            Text("Something went wrong.\n ${state.error} ")
        }
    }

    PumpScreenTemplate(onChecked = onClickOnCmd)

}


@Composable
fun PumpScreenTemplate(modifier: Modifier = Modifier, onChecked:(Boolean)->Unit) {
    var isChecked by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {

            Row {
            Text(
                style = MaterialTheme.typography.labelLarge,
                text = "Pump Status : "
            )
            Text("Running")
            }

            OutlinedButton(
                enabled = true,
                onClick = {
                    isChecked = !isChecked
                    onChecked.invoke(isChecked)
                }
            ) {
                Text(
                    text = if(isChecked) "On" else "Off",
                    style = MaterialTheme.typography.titleMedium
                    )
            }


        }
    }
}



@Preview
@Composable
fun DeviceTemplateScreenPreview() {
    DeviceTemplateScreen(
        state = DeviceState(),
        modifier = TODO(),
        onClickOnCmd = TODO()
    )
}



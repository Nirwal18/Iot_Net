package com.nirwal.iotnet.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nirwal.iotnet.model.Device
import com.nirwal.iotnet.ui.vm.DashboardUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    state: DashboardUiState,
    onClickDeviceStartCmdBtn: (Boolean)->Unit
) {




    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("IoT Dashboard") },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) {

        Box(modifier = Modifier.padding(it)){
            LazyColumn(
                contentPadding = PaddingValues(all = 12.dp),
                //horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(space = 12.dp)
            ) {
                items(state.devices) { device ->
                    DeviceCard(device = device, onCmDBtnClick = onClickDeviceStartCmdBtn)
                }
            }
        }


    }

}


@Composable
fun DeviceCard(device: Device, onCmDBtnClick:(Boolean)->Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp)
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {




            Column {

                Text(text = device.name, style = MaterialTheme.typography.headlineSmall)
                device.value?.let {
                    Text(text = it, style = MaterialTheme.typography.bodyMedium)
                }

                Canvas(
                    modifier = Modifier.size(10.dp),
                    onDraw = {
                        drawCircle(color = Color.Green)
                    }
                )
            }
            Switch(
                checked = device.isOn,
                onCheckedChange = onCmDBtnClick
            )
        }
    }
}




@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(
        state = DashboardUiState(),
        onClickDeviceStartCmdBtn = {}
    )
}
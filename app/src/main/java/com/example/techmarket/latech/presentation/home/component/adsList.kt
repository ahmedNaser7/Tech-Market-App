package com.example.techmarket.latech.presentation.home.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.techmarket.latech.presentation.home.model.AdsUi


@PreviewScreenSizes
@Composable
private fun AdsPreview() {
    AdsList(
        adsList = listOf(
            AdsUi(
                id = 1,
                title = "hello",
                discountPercentage = 10,
                isActive = true,
                productID = 1,
                imageUrl = ""
            )
        )
    )
}


@Composable
fun AdsList(adsList: List<AdsUi>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(adsList) { adsItem ->
            AdsItemContainer(adsItem)
        }
    }
}

@Composable
fun AdsItemContainer(adsItem: AdsUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Blue)
            .padding(13.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = adsItem.title,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "${adsItem.discountPercentage} %",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

            AsyncImage(
                model =  adsItem.imageUrl ,
                contentDescription = "ads image",
               modifier = Modifier.size(90.dp).align(Alignment.CenterVertically)
            )


    }

}

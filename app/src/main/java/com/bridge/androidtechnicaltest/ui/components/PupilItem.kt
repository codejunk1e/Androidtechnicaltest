package com.bridge.androidtechnicaltest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.bridge.androidtechnicaltest.R
import com.bridge.androidtechnicaltest.domain.Pupil

@Composable
fun PupilItem(pupil: Pupil, onPupilClicked: (Pupil) -> Unit) {
    Column(
        modifier = Modifier.padding(4.dp)
            .clickable { onPupilClicked(pupil) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            PupilImage(pupil.image, "${pupil.name} photo")
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = pupil.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = pupil.country, fontSize = 14.sp, color = Color.Gray)
            }
        }

        HorizontalDivider()
    }
}

@Composable
fun PupilImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier.size(72.dp)
) {
    val painter = rememberAsyncImagePainter(
        placeholder = painterResource(id = R.drawable.placeholder_image),
        error = painterResource(id = R.drawable.default_image),
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .build()
    )
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
    }
}
package com.karakoca.baseproject.presentation.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.karakoca.baseproject.data.model.local.FavMovie
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kmpbaseproject.composeapp.generated.resources.Res
import kmpbaseproject.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun FavItem(item: FavMovie, favClick: (FavMovie) -> Unit) {
    Box {
        IconButton(onClick = {
            favClick.invoke(item)
        }, modifier = Modifier.align(Alignment.TopEnd).zIndex(4f)) {
            Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.Red)
        }

        Column {
            Card(shape = RoundedCornerShape(8.dp), modifier = Modifier) {
                KamelImage(
                    resource = { asyncPainterResource(item.image) },
                    contentDescription = item.title + "image",
                    contentScale = ContentScale.Crop,
                    onFailure = {
                        Image(
                            painterResource(Res.drawable.compose_multiplatform),
                            contentDescription = "logo",
                            modifier = Modifier.align(
                                Alignment.BottomEnd
                            ).fillMaxWidth(0.5f)
                        )
                    },
                    onLoading = {
                        Box(Modifier.fillMaxWidth().heightIn(300.dp)) {
                            androidx.compose.material3.CircularProgressIndicator(
                                Modifier.align(Alignment.Center)
                            )
                        }
                    }
                )
            }

            Text(
                item.title.toString(),
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }

}
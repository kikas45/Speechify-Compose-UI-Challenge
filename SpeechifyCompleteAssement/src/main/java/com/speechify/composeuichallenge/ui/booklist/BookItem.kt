package com.speechify.composeuichallenge.ui.booklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.speechify.composeuichallenge.data.Book
import com.speechify.composeuichallenge.ui.anim.SharedElementImage

@Composable
fun BookItem(
    book: Book,
    onDetailsClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        SharedElementImage(targetKey = book.id) {
            AsyncImage(
                model = book.imageUrl,
                contentDescription = book.name,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(3f /4f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }


        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Text(
                text = book.name,
                style = MaterialTheme.typography.titleMedium
            )


            Text(
                text = book.author,
                style = MaterialTheme.typography.labelMedium
            )

            Text(
                text = "${book.rating} (${book.reviewCount}",
                style = MaterialTheme.typography.labelSmall
            )


        }


        Spacer(modifier = Modifier.width(12.dp))


        Button(onClick = onDetailsClick){
            Text (text = "Details")
        }

    }

}
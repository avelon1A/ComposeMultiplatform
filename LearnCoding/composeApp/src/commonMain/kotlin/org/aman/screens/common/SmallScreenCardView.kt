package org.aman.screens.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import org.aman.coding.data.Category

@Composable
fun DsaScreenAndroid(categories: List<Category>,column:Int =2) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(column), // 2 columns
        contentPadding = PaddingValues(8.dp)
    ) {
        items(categories.size) { index ->
            val category = categories[index]
            DsaCategoryCard(
                title = category.title,
                problemsSolved = category.problemsSolved,
                totalProblems = category.totalProblems,
                status = category.status
            )
        }
    }
}

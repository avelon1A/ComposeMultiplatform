package org.aman.coding.presentaion.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import org.aman.coding.domain.model.appModel.Category
import org.aman.coding.domain.model.respnonse.Problem

@Composable
fun DsaScreenAndroid(categories: List<Category>, column:Int =2, onCategoryClick: (Any) -> Unit) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(column),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(categories.size) { index ->
            val category = categories[index]
            DsaCategoryCard(
                title = category.title,
                problemsSolved = category.problemsSolved,
                totalProblems = category.totalProblems,
                status = category.status,
                onClick = { onCategoryClick(index) }
            )
        }
    }
}

@Composable
fun ListScreenAndroid(problem: List<Problem>, column:Int =2, onListClick: (name:String) -> Unit) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(column),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(problem.size) { index ->
            val problem = problem[index]
            ListCategoryCard(
                title = problem.name,
                onClick = { onListClick(problem.name) }
            )
        }
    }
}

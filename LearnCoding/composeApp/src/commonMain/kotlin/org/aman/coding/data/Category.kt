package org.aman.coding.data
data class Category(
    val title: String,
    val problemsSolved: Int,
    val totalProblems: Int,
    val status: String
)

fun getMockCategories(): List<Category> {
    return listOf(
        Category("Math", 50, 100, "Completed"),
        Category("Science", 80, 120, "In Progress"),
        Category("History", 30, 50, "Not Started"),
        Category("Geography", 60, 80, "Completed"),
        Category("Computer Science", 90, 150, "In Progress"),
        Category("English", 45, 60, "Completed"),
        Category("Art", 15, 25, "Not Started")
    )
}
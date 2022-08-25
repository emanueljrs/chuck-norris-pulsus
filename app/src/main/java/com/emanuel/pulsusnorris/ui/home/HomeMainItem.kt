package com.emanuel.pulsusnorris.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.emanuel.pulsusnorris.R

data class HomeMainItem(
    val id: Int,
    @DrawableRes
    val image: Int,
    @StringRes
    val text: Int
) {
    companion object{
        fun getItemsMain(): List<HomeMainItem> {
            return listOf(
                HomeMainItem(
                    id = 0,
                    image = R.drawable.ic_chuck_norris,
                    text = R.string.text_random_item
                ),
                HomeMainItem(
                    id = 1,
                    image = R.drawable.ic_search_joke,
                    text = R.string.text_search_item
                )
            )
        }
    }
}

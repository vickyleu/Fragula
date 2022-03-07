package com.fragula2

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class SwipeBackTransformer : ViewPager2.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        when {
            // Все экраны в стеке справа от текущего
            position <= -1 -> {
                page.visibility = View.INVISIBLE
            }
            // Экран, который появляется справа от текущего при открытии нового фрагмента
            position > 0 && position < 1 -> {
                page.visibility = View.VISIBLE
                page.translationX = 0f
            }
            // Анимация ухода текущего фрагмента влево при открытии нового
            position > -1 && position <= 0 -> {
                page.visibility = View.VISIBLE
                page.translationX = -page.width * position / 1.3F
            }
            // Все фрагменты слева от текущего, убираем их из отрисовки
            else -> {
                page.visibility = View.INVISIBLE
            }
        }
    }
}
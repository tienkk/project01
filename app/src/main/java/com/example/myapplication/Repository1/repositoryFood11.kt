package com.example.myapplication.Repository1

import com.example.myapplication.R

class repositoryFood11 {
    companion object {
        fun getFoodName():List<String>{
            return listOf("Vegetables Salad", "BreakFast", "Cake", "Vegetables Salad", "BreakFast", "Cake","Vegetables Salad", "BreakFast", "Cake","Vegetables Salad", "BreakFast", "Cake")
        }
        fun getPrice():List<String>{
            return listOf("10.00", "5.00", "8.00","10.00", "5.00", "8.00","10.00", "5.00", "8.00","10.00", "5.00", "8.00" )
        }
        fun getImageFood():List<Int>{
            return listOf(
                R.drawable.menu1,
                R.drawable.menu2,
                R.drawable.menu3,
                R.drawable.menu1,
                R.drawable.menu2,
                R.drawable.menu3,
                R.drawable.menu1,
                R.drawable.menu2,
                R.drawable.menu3,
                R.drawable.menu1,
                R.drawable.menu2,
                R.drawable.menu3,
            )
        }
        fun getDescription(): List<String> {
            return listOf(
                "Rau nộm là món ăn thanh mát, kết hợp rau sống tươi ngon, cà rốt, đu đủ bào sợi, trộn cùng nước mắm chua ngọt, lạc rang và rau thơm, tạo hương vị hấp dẫn, giòn ngon.",
                "Bữa sáng rau củ và các loại quả là sự kết hợp dinh dưỡng giữa rau tươi, trái cây đa dạng, cung cấp vitamin, chất xơ, kèm thêm sốt nhẹ tạo hương vị tươi ngon, lành mạnh",
                "Bánh quy phủ hoa quả là món ăn hấp dẫn, gồm đế bánh giòn tan, phủ kem mịn và hoa quả tươi như dâu, kiwi, nho, tạo hương vị ngọt ngào, tươi mới, đầy màu sắc."
            )
        }
        fun getIngredients(): List<String> {
            return listOf(
                """Rau sống (xà lách, rau mùi, húng quế, kinh giới  
                   Cà rốt, đu đủ xanh bào sợi 
                   Nước mắm, chanh, tỏi, ớt, đường, lạc rang giã nhỏ.""",
                """Rau củ tươi: xà lách, cải bó xôi, cà chua bi, dưa leo.
                   Trái cây: táo, chuối, nho, bơ hoặc kiwi.
                   Sốt ăn kèm: sữa chua, mật ong hoặc nước cốt chanh.""",
                """Bánh quy: loại giòn tan, vị ngọt nhẹ.
                   Kem hoặc sữa chua: để phủ lên mặt bánh.
                   Hoa quả tươi: dâu tây, kiwi, nho, xoài hoặc việt quất."""
            )
        }
    }

}
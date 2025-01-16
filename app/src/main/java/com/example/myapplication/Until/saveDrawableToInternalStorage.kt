package com.example.myapplication.Until

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream

fun saveDrawableToInternalStorage(context: Context, drawableId: Int, fileName: String): String {
    val bitmap = BitmapFactory.decodeResource(context.resources, drawableId)
    val directory = context.filesDir // Thư mục nội bộ
    val file = File(directory, fileName)
    FileOutputStream(file).use { outputStream ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    }
    return file.absolutePath // Trả về đường dẫn
}
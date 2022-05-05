package com.emelyanov.rassvet.shared.domain.utils

import android.content.Context
import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix


object BarcodeGenerator {

    fun createBarcode(
        contents: String?,
        desiredWidth: Int,
        desiredHeight: Int
    ): Bitmap {
        /**
         * Encoding type of barcode
         */
        val barcodeFormat = BarcodeFormat.CODE_128
        return encodeAsBitmap(
            contents, barcodeFormat,
            desiredWidth, desiredHeight
        )
    }


    private fun encodeAsBitmap(
        contents: String?,
        format: BarcodeFormat?,
        desiredWidth: Int,
        desiredHeight: Int
    ): Bitmap {
        val WHITE = 0xFFFFFFFF.toInt()
        val BLACK = 0xFF000000.toInt()
        val writer = MultiFormatWriter()
        var result: BitMatrix? = null
        try {
            result = writer.encode(
                contents, format, desiredWidth,
                desiredHeight, null
            )
        } catch (e: WriterException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        val width = result!!.width
        val height = result.height
        val pixels = IntArray(width * height)
        // All are 0, or black, by default
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (result[x, y]) BLACK else WHITE
            }
        }
        val bitmap = Bitmap.createBitmap(
            width, height,
            Bitmap.Config.ARGB_8888
        )
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }
}
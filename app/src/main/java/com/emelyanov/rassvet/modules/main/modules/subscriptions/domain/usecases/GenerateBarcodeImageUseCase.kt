package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.usecases

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.emelyanov.rassvet.shared.domain.utils.BarcodeGenerator
import javax.inject.Inject

class GenerateBarcodeImageUseCase
@Inject
constructor(

) {
    operator fun invoke(barcodeString: String) : ImageBitmap
    = BarcodeGenerator.createBarcode(
        contents = barcodeString,
        desiredHeight = 100,
        desiredWidth = 100
    ).asImageBitmap()
}
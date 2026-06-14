package com.mymeals.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.CoreGraphics.CGSizeMake
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSData
import platform.UIKit.*
import platform.darwin.NSObject

@Composable
actual fun rememberPhotoPickerLauncher(onPhotoPicked: (ByteArray) -> Unit): () -> Unit {
    val delegate = remember { ImagePickerDelegate(onPhotoPicked) }

    return {
        val rootVC = UIApplication.sharedApplication.keyWindow?.rootViewController
        if (rootVC == null) return@remember

        val alert = UIAlertController.alertControllerWithTitle(
            "Добавить фото",
            message = null,
            preferredStyle = UIAlertControllerStyleActionSheet,
        )

        if (UIImagePickerController.isSourceTypeAvailable(UIImagePickerControllerSourceTypeCamera)) {
            alert.addAction(
                UIAlertAction.actionWithTitle(
                    "Сделать фото",
                    style = UIAlertActionStyleDefault,
                ) {
                    val picker = UIImagePickerController()
                    picker.sourceType = UIImagePickerControllerSourceTypeCamera
                    picker.delegate = delegate
                    rootVC.presentViewController(picker, animated = true, completion = null)
                }
            )
        }

        alert.addAction(
            UIAlertAction.actionWithTitle(
                "Выбрать из галереи",
                style = UIAlertActionStyleDefault,
            ) {
                val picker = UIImagePickerController()
                picker.sourceType = UIImagePickerControllerSourceTypePhotoLibrary
                picker.delegate = delegate
                rootVC.presentViewController(picker, animated = true, completion = null)
            }
        )

        alert.addAction(
            UIAlertAction.actionWithTitle("Отмена", style = UIAlertActionStyleCancel, handler = null)
        )

        rootVC.presentViewController(alert, animated = true, completion = null)
    }
}

private class ImagePickerDelegate(
    private val onPhotoPicked: (ByteArray) -> Unit,
) : NSObject(), UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegateProtocol {

    override fun imagePickerController(
        picker: UIImagePickerController,
        didFinishPickingMediaWithInfo: Map<Any?, *>,
    ) {
        val image = didFinishPickingMediaWithInfo[UIImagePickerControllerOriginalImage] as? UIImage
        if (image != null) {
            val resized = resizeImage(image)
            val data = UIImageJPEGRepresentation(resized, 0.8)
            if (data != null) {
                val size = data.length.toInt()
                val bytes = ByteArray(size)
                if (size > 0) {
                    bytes.usePinned { pinned ->
                        data.getBytes(pinned.addressOf(0), data.length)
                    }
                }
                onPhotoPicked(bytes)
            }
        }
        picker.dismissViewControllerAnimated(true, completion = null)
    }

    override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
        picker.dismissViewControllerAnimated(true, completion = null)
    }
}

private const val MAX_DIMENSION = 1024.0

private fun resizeImage(image: UIImage): UIImage {
    val w = image.size.width
    val h = image.size.height
    if (w <= MAX_DIMENSION && h <= MAX_DIMENSION) return image

    val (newW, newH) = if (w >= h) {
        Pair(MAX_DIMENSION, h * MAX_DIMENSION / w)
    } else {
        Pair(w * MAX_DIMENSION / h, MAX_DIMENSION)
    }

    val renderer = UIGraphicsImageRenderer(CGSizeMake(newW, newH))
    return renderer.image { _ ->
        image.drawInRect(CGRectMake(0.0, 0.0, newW, newH))
    }
}

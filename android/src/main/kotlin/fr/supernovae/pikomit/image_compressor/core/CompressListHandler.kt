package fr.supernovae.pikomit.image_compressor.core

import android.content.Context
import fr.supernovae.pikomit.image_compressor.ImageCompressorPlugin
import fr.supernovae.pikomit.image_compressor.exception.CompressError
import fr.supernovae.pikomit.image_compressor.exif.Exif
import fr.supernovae.pikomit.image_compressor.format.FormatRegister
import fr.supernovae.pikomit.image_compressor.logger.log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import java.io.ByteArrayOutputStream

class CompressListHandler(private val call: MethodCall, result: MethodChannel.Result) : ResultHandler(result) {

  fun handle(context: Context) {
    threadPool.execute {
      @Suppress("UNCHECKED_CAST") val args: List<Any> = call.arguments as List<Any>
      val arr = args[0] as ByteArray
      var minWidth = args[1] as Int
      var minHeight = args[2] as Int
      val quality = args[3] as Int
      val rotate = args[4] as Int
      val autoCorrectionAngle = args[5] as Boolean
      val format = args[6] as Int
      val keepExif = args[7] as Boolean
      val inSampleSize = args[8] as Int

      val exifRotate = if (autoCorrectionAngle) Exif.getRotationDegrees(arr) else 0

      if (exifRotate == 270 || exifRotate == 90) {
        val tmp = minWidth
        minWidth = minHeight
        minHeight = tmp
      }

      val formatHandler = FormatRegister.findFormat(format)

      if (formatHandler == null) {
        log("No support format.")
        reply(null)
        return@execute
      }

      val targetRotate = rotate + exifRotate

      val outputStream = ByteArrayOutputStream()
      try {
        formatHandler.handleByteArray(context, arr, outputStream, minWidth, minHeight, quality, targetRotate, keepExif, inSampleSize)
        reply(outputStream.toByteArray())
      } catch (e: CompressError) {
        log(e.message)
        if (ImageCompressorPlugin.showLog) e.printStackTrace()
        reply(null)
      } catch (e: Exception) {
        if (ImageCompressorPlugin.showLog) e.printStackTrace()
        reply(null)
      } finally {
        outputStream.close()
      }

    }
  }


}

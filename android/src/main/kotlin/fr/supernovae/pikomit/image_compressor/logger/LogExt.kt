package fr.supernovae.pikomit.image_compressor.logger

import android.util.Log
import fr.supernovae.pikomit.image_compressor.ImageCompressorPlugin

fun Any.log(any: Any?) {
  if (ImageCompressorPlugin.showLog) {
    Log.i("image_compressor", any?.toString() ?: "null")
  }
}

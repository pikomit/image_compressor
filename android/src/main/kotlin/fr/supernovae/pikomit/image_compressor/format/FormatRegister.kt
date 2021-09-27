package fr.supernovae.pikomit.image_compressor.format

import android.util.SparseArray
import fr.supernovae.pikomit.image_compressor.handle.FormatHandler

object FormatRegister {

  private val formatMap = SparseArray<FormatHandler>()

  fun registerFormat(handler: FormatHandler) {
    formatMap.append(handler.type, handler)
  }

  fun findFormat(formatIndex: Int): FormatHandler? {
    return formatMap.get(formatIndex)
  }


}

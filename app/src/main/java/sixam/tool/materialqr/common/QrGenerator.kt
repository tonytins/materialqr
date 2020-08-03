/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package sixam.tool.materialqr.common

import android.graphics.Bitmap
import net.glxn.qrgen.android.QRCode

class QrGenerator(private val input: String) {
    fun generate(width: Int, height: Int) : Bitmap {
        val qr = QRCode.from(input)
        val previewQR = qr.withSize(width, height)

        return previewQR.bitmap()
    }
}
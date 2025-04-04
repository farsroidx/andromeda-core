@file:Suppress("unused")

package ir.farsroidx.m31.contact

import android.graphics.Bitmap
import androidx.annotation.Keep
import java.io.Serializable

/**
 * Data class representing a contact with a name and a list of phone numbers.
 * This class is marked with @Keep to ensure it is not obfuscated during the build process.
 *
 * @param name The name of the contact.
 * @param phones The list of phone numbers associated with the contact.
 * @param profileThumbnail Thumbnail image of the contact.
 * @param profileOriginally High-quality profile image.
 */
@Keep
data class Contact(
    val name: String,               // The name of the contact
    val phones: List<String>,       // The list of phone numbers associated with the contact
    val profileThumbnail: Bitmap?,  // Thumbnail image of the contact
    val profileOriginally: Bitmap?  // High-quality profile image
) : Serializable

@file:Suppress("unused")

package ir.farsroidx.m31.contact

import android.Manifest
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.AssetFileDescriptor
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import ir.farsroidx.m31.contact.formatter.IranianPhoneFormatter
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream

/**
 * Main class to manage contacts, retrieve phone numbers, and process them.
 */
class ContactManager(private val context: Context) {

    /**
     * ContentResolver instance to interact with the content provider.
     * This resolver allows the application to access data from different content providers
     * like ContactsContract, MediaStore, etc., and perform operations like querying or inserting data.
     *
     * @property resolver The ContentResolver used to query and manage content data.
     */
    private val resolver: ContentResolver = context.contentResolver

    /**
     * Property to check if the required permission to read contacts is granted.
     *
     * @return True if permission is granted, false otherwise.
     */
    val isPermissionGranted: Boolean
        get() = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) ==
            PackageManager.PERMISSION_GRANTED

    /**
     * Method to get mobile contacts along with their profile image and thumbnail.
     *
     * @param phoneFormatter A PhoneFormatter object to normalize and validate phone numbers.
     * @return A list of contacts including name, phone numbers, and profile images.
     */
    fun getMobileContacts(phoneFormatter: PhoneFormatter = IranianPhoneFormatter()): List<Contact> {

        // Initialize an empty list to store the contacts and their phone numbers.
        val contacts = mutableListOf<Contact>()

        // If permission is not granted, return the empty contacts list.
        if (!isPermissionGranted) return contacts

        // Query to get all contacts.
        val cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        cursor?.use { cur ->

            // Loop through the contacts.
            while (cur.moveToNext()) {

                // Get the contact ID and name.

                val contactId = cur.getString(
                    cur.getColumnIndexOrThrow(ContactsContract.Contacts._ID)
                )

                val contactName = cur.getString(
                    cur.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)
                ) ?: "Unknown"

                // Get the phone numbers for the current contact.
                val phoneNumbers = getMobileNumbers(contactId, phoneFormatter)

                // Get profile image and thumbnail
                val profileThumbnail  = getContactProfileThumbnail(contactId)
                val profileOriginally = getContactProfileOriginally(contactId)

                // If valid phone numbers are found, add them to the contacts list.
                if (phoneNumbers.isNotEmpty()) {

                    val contact = Contact(
                        name              = contactName,
                        phones            = phoneNumbers,
                        profileThumbnail  = profileThumbnail,
                        profileOriginally = profileOriginally
                    )

                    contacts.add(contact)
                }
            }
        }

        return contacts
    }

    /**
     * Retrieves the contact's profile thumbnail image.
     * This function queries the contact's thumbnail photo and returns it as a Bitmap.
     * If the thumbnail is not available, it returns null.
     *
     * @param contactId The unique identifier of the contact.
     * @return A Bitmap representing the contact's thumbnail, or null if not found.
     */
    private fun getContactProfileThumbnail(contactId: String): Bitmap? {

        // Retrieve the input stream for the contact's thumbnail image.
        val inputStream = openThumbnailPhoto(contactId)

        // Decode the input stream into a Bitmap and return it.
        return inputStream?.use { BitmapFactory.decodeStream(it) }
    }

    /**
     * Retrieves the contact's original profile image.
     * This function queries the contact's full-size profile photo and returns it as a Bitmap.
     * If the original photo is not available, it returns null.
     *
     * @param contactId The unique identifier of the contact.
     * @return A Bitmap representing the contact's original image, or null if not found.
     */
    private fun getContactProfileOriginally(contactId: String): Bitmap? {

        // Retrieve the input stream for the contact's original profile image.
        val inputStream = openOriginalPhoto(contactId)

        // Decode the input stream into a Bitmap and return it.
        return inputStream?.use { BitmapFactory.decodeStream(it) }
    }

    /**
     * Opens the thumbnail photo of the contact.
     * This function queries the contact's thumbnail photo using the contact ID and returns
     * the photo as an InputStream, which can then be decoded into a Bitmap.
     *
     * @param contactId The unique identifier of the contact.
     * @return An InputStream of the contact's thumbnail photo, or null if the photo is not found.
     */
    private fun openThumbnailPhoto(contactId: String): InputStream? {

        // Create the Uri for the contact's thumbnail photo based on contact ID.
        val contactUri: Uri = ContentUris.withAppendedId(
            ContactsContract.Contacts.CONTENT_URI, contactId.toLong()
        )

        // Create the Uri for accessing the contact's photo.
        val photoUri: Uri = Uri.withAppendedPath(
            contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY
        )

        // Query the content provider to get the thumbnail photo.
        val cursor: Cursor? = resolver.query(
            photoUri, arrayOf(ContactsContract.Contacts.Photo.PHOTO), null, null, null
        )

        cursor?.use {

            // Check if there's a valid result from the cursor.
            if (it.moveToFirst()) {

                // Get the photo data as a byte array.
                val data = it.getBlob(0)

                // If data is available, return it as an InputStream.
                return if (data != null) ByteArrayInputStream(data) else null
            }
        }

        // Return null if no thumbnail photo is found.
        return null
    }

    /**
     * Opens the original display photo of the contact.
     * This function queries the contact's full-size profile photo using the contact ID and returns
     * the photo as an InputStream, which can then be decoded into a Bitmap.
     *
     * @param contactId The unique identifier of the contact.
     * @return An InputStream of the contact's original profile photo, or null if the photo is not found.
     */
    private fun openOriginalPhoto(contactId: String): InputStream? {

        // Create the Uri for the contact's original profile photo based on contact ID.
        val contactUri: Uri = ContentUris.withAppendedId(
            ContactsContract.Contacts.CONTENT_URI, contactId.toLong()
        )

        // Create the Uri for accessing the contact's full-size photo.
        val displayPhotoUri: Uri = Uri.withAppendedPath(
            contactUri, ContactsContract.Contacts.Photo.DISPLAY_PHOTO
        )

        return try {

            // Try to open the original photo using the content resolver.
            val fd: AssetFileDescriptor? = resolver.openAssetFileDescriptor(displayPhotoUri, "r")

            // If the file descriptor is valid, return the input stream.
            fd?.createInputStream()

        } catch (e: IOException) {
            // Return null if there was an error while accessing the photo.
            null
        }
    }

    /**
     * Helper method to get mobile phone numbers for a contact.
     *
     * @param contactId The ID of the contact whose phone numbers are being retrieved.
     * @param phoneFormatter The phone formatter used to normalize and validate phone numbers.
     * @return A list of valid phone numbers for the contact.
     */
    private fun getMobileNumbers(contactId: String, phoneFormatter: PhoneFormatter): List<String> {

        // List to hold valid phone numbers.
        val numbers = mutableListOf<String>()

        // Query to get all phone numbers for the current contact.
        val cursor = resolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
            "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?", arrayOf(contactId), null
        )

        cursor?.use {

            // Loop through the phone numbers.
            while (it.moveToNext()) {

                // Get the raw phone number.
                val rawNumber = it.getString(
                    it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
                )

                // Normalize the phone number.
                val normalized = phoneFormatter.normalize(rawNumber)

                // If the phone number is valid, add it to the list.
                if (normalized != null && phoneFormatter.isValidPhone(normalized)) {
                    numbers.add(normalized)
                }
            }
        }

        return numbers
    }
}
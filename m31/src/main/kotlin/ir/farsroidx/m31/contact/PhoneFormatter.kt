@file:Suppress("unused")

package ir.farsroidx.m31.contact

/**
 * Interface to define methods for phone number normalization and validation.
 */
interface PhoneFormatter {

    /**
     * Normalizes the phone number (formats it into a standard form).
     *
     * @param phoneNumber The phone number to be normalized.
     * @return The normalized phone number or null if it's invalid.
     */
    fun normalize(phoneNumber: String): String?

    /**
     * Validates if the phone number is in the correct format.
     *
     * @param phoneNumber The phone number to be validated.
     * @return True if the phone number is valid, false otherwise.
     */
    fun isValidPhone(phoneNumber: String): Boolean

}
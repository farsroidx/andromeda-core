@file:Suppress("unused")

package ir.farsroidx.m31.contact.formatter

import ir.farsroidx.m31.contact.PhoneFormatter

/**
 * Concrete implementation of PhoneFormatter for Iranian phone numbers.
 */
class IranianPhoneFormatter : PhoneFormatter {

    // Regular expression to check if a phone number starts with +98 or 0 followed by 9 digits.
    private val iranianPhoneRegex = "^((\\+98|0)9\\d{9})$".toRegex()

    // Regular expression to remove whitespace and dashes from the phone number.
    private val removeCharsRegex = "[\\s-]".toRegex()

    // Regular expression to validate if the phone number is a valid Iranian mobile number.
    private val validIranianPhoneRegex = "^09\\d{9}$".toRegex()

    /**
     * Normalize the phone number by removing non-digit characters and adjusting the format.
     *
     * @param phoneNumber The phone number to be normalized.
     * @return The normalized phone number or null if it's invalid.
     */
    override fun normalize(phoneNumber: String): String? {

        // Clean the input phone number by removing spaces and dashes
        val cleaned = phoneNumber.replace(removeCharsRegex, "")

        // Match different formats and return the normalized phone number in the correct format
        return when {
            cleaned.startsWith("0098") && cleaned.length == 14 -> "0" + cleaned.substring(4)
            cleaned.startsWith("+98")  && cleaned.length == 13 -> "0" + cleaned.substring(3)
            cleaned.startsWith("98")   && cleaned.length == 12 -> "0" + cleaned.substring(2)
            cleaned.startsWith("09")   && cleaned.length == 11 -> cleaned
            cleaned.startsWith("9")    && cleaned.length == 10 -> "0$cleaned"
            else -> null  // If no valid format is found, return null
        }
    }

    /**
     * Check if the normalized phone number matches the valid format for Iranian mobile numbers.
     *
     * @param phoneNumber The phone number to be validated.
     * @return True if the phone number is valid, false otherwise.
     */
    override fun isValidPhone(phoneNumber: String): Boolean {
        return phoneNumber.matches(validIranianPhoneRegex) == true
    }
}
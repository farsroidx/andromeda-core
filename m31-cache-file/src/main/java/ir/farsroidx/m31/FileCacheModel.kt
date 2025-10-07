package ir.farsroidx.m31

import java.io.Serializable

internal data class FileCacheModel <T: Any> (
    val value: T, val expireDate: Long
): Serializable
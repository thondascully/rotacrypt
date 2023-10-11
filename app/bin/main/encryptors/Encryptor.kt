/*
 * TEO || 2023
 */

package encryptors

import encryptors.cube.*
import java.security.SecureRandom
import java.security.MessageDigest

abstract class Encryptor {
    abstract fun encrypt(data: ByteArray): ByteArray
    abstract fun decrypt(data: ByteArray): ByteArray
}
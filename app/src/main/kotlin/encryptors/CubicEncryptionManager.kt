/*
 * TEO || 2023
 */

package encryptors

import encryptors.cube.*
import java.security.SecureRandom
import java.security.MessageDigest

class CubicEncryptionManager(val keyManager: KeyManager) : Encryptor() {
    init {
        
    }

    override fun encrypt(data: ByteArray): ByteArray {
        return ByteArray(0)
    }

    override fun decrypt(data: ByteArray): ByteArray {
        return ByteArray(0)
    }
}

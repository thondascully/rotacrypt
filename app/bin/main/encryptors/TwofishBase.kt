/*
 * TEO || 2023
 */

package encryptors

import encryptors.Encryptor

class TwofishBase(private val key: ByteArray) : Encryptor {
    override fun encrypt(data: ByteArray): ByteArray {
        // TODO 
        return data 
    }
    
    override fun decrypt(data: ByteArray): ByteArray {
        // TODO
        return data 
    }
}
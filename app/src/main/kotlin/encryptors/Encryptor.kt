/*
 * TEO || 2023
 */

 package encryptors
 
interface Encryptor {
    fun encrypt(data: ByteArray): ByteArray
    fun decrypt(data: ByteArray): ByteArray
}
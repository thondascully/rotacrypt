/*
 * TEO || 2023
 */

package encryptors

import encryptors.Encryptor

class Rubiks(private val key: ByteArray, private val orientation: Int) : Encryptor {
    fun solveFirstBlock(): List<String> {
        // TODO
        return listOf() 
    }
    
    override fun encrypt(data: ByteArray): ByteArray {
        // TODO
        return data 
    }
    
    override fun decrypt(data: ByteArray): ByteArray {
        // TODO
        return data
    }
}
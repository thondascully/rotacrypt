/*
 * TEO || 2023
 */

package encryptors

import encryptors.cube.*
import java.security.SecureRandom
import java.security.MessageDigest

data class EncryptionInitContainer(val a: Cube, val b: Cube, val c: Cube, val d: Cube, val e: Cube, val f: Cube)

fun EncryptionInitContainer.forEachCubeIndexed(func: (Cube, Int) -> Unit) {
    func(a, 0)
    func(b, 1)
    func(c, 2)
    func(d, 3)
    func(e, 4)
    func(f, 5)
}

class CubicEncryptionManager(val keyManager: KeyManager) : Encryptor() {
    protected val cubes = KeyInitContainer(Cube(), Cube(), Cube(), Cube())
    protected val sequences = Array(6) { Array(6) { 0L } } // An array of 6 sequences; each can hold 64 moves
    
    init {
        
    }

    override fun encrypt(data: ByteArray): ByteArray {
        return ByteArray(0)
    }

    override fun decrypt(data: ByteArray): ByteArray {
        return ByteArray(0)
    }
}

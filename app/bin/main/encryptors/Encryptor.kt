/*
 * TEO || 2023
 */

package encryptors

import encryptors.cube.*
import java.security.SecureRandom
 
interface Encryptor {
    fun encrypt(data: ByteArray): ByteArray
    fun decrypt(data: ByteArray): ByteArray
}

abstract class AbstractRubiksEncryptor: Encryptor {
    protected val cube1 = Cube()
    protected val cube2 = Cube()
    protected val cube3 = Cube()
    protected val cube4 = Cube()

    init {
        initializeCubes()
    }

    private fun initializeCubes() {
        val secureRandom = SecureRandom()
        val count = 6

        for (cube in listOf(cube1, cube2, cube3, cube4)) {
            val moves_ = StringBuilder()
            
            for (i in 1..count) {
                val move = moves[secureRandom.nextInt(moves.size)]
                moves_.append(move).append(" ")
            }

            cube.algorithm(moves_.toString().trim())
            println(moves_.toString().trim())
            println(cube.toString())
            println("...")
        }
    }
}

class RubiksEncryptor: AbstractRubiksEncryptor() {
    override fun encrypt(data: ByteArray): ByteArray {
        // TODO
        return ByteArray(0) 
    }

    override fun decrypt(data: ByteArray): ByteArray {
        // TODO
        return ByteArray(0)
    }
}

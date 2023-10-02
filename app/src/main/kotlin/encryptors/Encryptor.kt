/*
 * TEO || 2023
 */

package encryptors

import encryptors.cube.*
import java.security.SecureRandom
import java.security.MessageDigest

interface Encryptor {
    fun encrypt(data: ByteArray): ByteArray
    fun decrypt(data: ByteArray): ByteArray
}

class Generator {
    public val orderedFace = arrayOf(6, 7, 8, 5, 4, 3, 0, 1, 2)

    fun generateKey(cubes: List<Cube>): String {
        return cubes.map { cube ->
            serialize(cube).let { serialized ->
                hash(serialized)
            }
        }.reduce { acc, hash -> xor(acc, hash) }
    }

    private fun extractLayers(cube: Cube): String {
        val layers = StringBuilder()
        for (i in 0 until 6) {
            for (index in orderedFace) {
                layers.append(cube.getCube()[i * 9 + index])
            }
        }
        return layers.toString()
    }

    private fun cycle(str: String, positions: Int): String {
        return str.substring(9 - positions) + str.substring(0, 9 - positions)
    }

    private fun serialize(cube: Cube): List<String> {
        val layers = extractLayers(cube).chunked(9)
        val rotatedLayers = layers.mapIndexed { index, segment -> 
            cycle(segment, index)
        }
        println(rotatedLayers)
        return rotatedLayers
    }


    private fun hash(data: List<String>): String {
        return ""
    }

    private fun xor(hash1: String, hash2: String): String {
        return "" 
    }
}

abstract class AbstractCubeEncryptor: Encryptor {
    protected val cube1 = Cube()
    protected val cube2 = Cube()
    protected val cube3 = Cube()
    protected val cube4 = Cube()

    protected val gen = Generator()
    protected var key: String

    init {
        initializeCubes()
        key = gen.generateKey(listOf(cube1, cube2, cube3, cube4))
    }

    private fun initializeCubes() {
        val secureRandom = SecureRandom()
        val count = 1

        for (cube in listOf(cube1, cube2, cube3, cube4)) {
            val moves_ = StringBuilder()

            for (i in 1..count) {
                val move = moves[secureRandom.nextInt(moves.size)]
                moves_.append(move).append(" ")
            }

            cube.algorithm(moves_.toString().trim())
        }
    }
}

class CubeEncryptor: AbstractCubeEncryptor() {
    override fun encrypt(data: ByteArray): ByteArray {
        // TODO
        return ByteArray(0)
    }

    override fun decrypt(data: ByteArray): ByteArray {
        // TODO
        return ByteArray(0)
    }
}

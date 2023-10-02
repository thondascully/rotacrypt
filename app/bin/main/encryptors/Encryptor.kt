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
    public val orderedCorners = arrayOf(0, 2, 8, 6)
    public val _moves: MutableList<String> = mutableListOf()

    fun generateKey(cubes: List<Cube>): String {
//    Serialization (2):
//         Use the return from above and label it as the new "og"
//         str1 = Concat of serializedOne[j] + serializedTwo[j]
//         str2 = Concat of serializedThree[j] + serializedFour[j]
//         str3 = Concat of serializedCornersOne[j] + serializedCornersTwo[j]
//         str4 = Concat of serializedCornersThree[j] + serializedCornersFour[j]
//         return str = str3 + str1 + og + str2 + str4

        val faces = cubes.map { cube ->
            serializeFaces(cube)
        }
        val corners = cubes.map { cube ->
            serializeCorners(cube)
        }
        val countU = _moves.map { it.count { it == 'U' } }
        val countF = _moves.map { it.count { it == 'F' } }

        val serializedPartial = corners[0][countU[0] % 6] + corners[1][countU[0] % 6] + 
            faces[0][countU[0] % 6] + faces[1][countU[1] % 6] + faces[2][countU[2] % 6] + 
            faces[3][countU[3] % 6] + corners[2][countU[2] % 6] + corners[3][countU[3] % 6]

        val serializedFinal = corners[0][countF[0] % 6] + corners[1][countF[1] % 6] +
            faces[0][countF[0] % 6] + faces[1][countF[1] % 6] + serializedPartial +
            faces[2][countF[2] % 6] + faces[3][countF[3] % 6] + corners[2][countF[2] % 6] + corners[3][countF[3] % 6]



        println(serializedFinal.toLowerCase().toBinary())
        return ""
    }

    private fun extract(cube: Cube, order: Array<Int>): String {
        val units = StringBuilder()
        for (i in 0 until 6) {
            for (index in order) {
                units.append(cube.getCube()[i * 9 + index])
            }
        }
        return units.toString()
    }

    private fun cycle(str: String, positions: Int): String {
        return str.substring(9 - positions) + str.substring(0, 9 - positions)
    }

    private fun serializeFaces(cube: Cube): List<String> {
        val layers = extract(cube, orderedFace).chunked(9)
        val rotatedLayers = layers.mapIndexed { index, segment -> 
            cycle(segment, index)
        }
        return rotatedLayers
    }

    private fun serializeCorners(cube: Cube): List<String> {
        return extract(cube, orderedCorners).chunked(4)
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
        println(gen._moves)
    }

    private fun initializeCubes() {
        val secureRandom = SecureRandom()
        val count = 5

        for (cube in listOf(cube1, cube2, cube3, cube4)) {
            val moves_ = StringBuilder()
            for (i in 1..count) {
                val move = moves[secureRandom.nextInt(moves.size)]
                moves_.append(move).append(" ")
            }
            val conv = moves_.toString().trim()
            gen._moves.add(conv)
            cube.algorithm(conv)
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

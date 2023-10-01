/*
 * TEO || 2023
 */
 
package encryptors

import kotlin.random.Random

// Up, Front, Right, Back, Left, Down (U=YELLOW, F=BLUE)
// ABCD, IJKL, EFGH, MNOP, QRST, UVWX
enum class Identifier {
    BLUE, GREEN, ORANGE, RED, YELLOW, WHITE, UNDEF
}

class Cube {
    private val cube = Array(54) { Identifier.UNDEF }
    private val orderedColors = arrayOf(
        Identifier.YELLOW,
        Identifier.BLUE,
        Identifier.RED,
        Identifier.GREEN,
        Identifier.ORANGE,
        Identifier.WHITE
    )

    init {
        for (face in 0 until 6) {
            for (unit in 0 until 9) {
                cube[face * 9 + unit] = orderedColors[face]
            }
        }
    }

    /*
    
    LEFT FACE INDXS: 
    
    
    */

    private fun faceToIDXs(face: Array<Identifier>, idx: Int = 0): String {
        val builder = StringBuilder()
        for (i in 0 until 9 step 3) {
            builder
            .append(i + 9 * idx).append(" ")
            .append(i + 1 + 9 * idx).append(" ")
            .append(i + 2 + 9 * idx).append("\n")
        }
        return builder.toString()
    }

    private fun faceToString(face: Array<Identifier>, idx: Int = 0): String {
        val builder = StringBuilder()
        for (i in 0 until 9 step 3) {
            builder
            .append(face[i].toString().first()).append(" ")
            .append(face[i + 1].toString().first()).append(" ")
            .append(face[i + 2].toString().first()).append("\n")
        }
        return builder.toString()
    }

    override fun toString(): String {
        val builder = StringBuilder()

        for (face in 0 until 6) {
            builder.append(faceToIDXs(cube.sliceArray(face * 9 until (face + 1) * 9), face))
            builder.append("\n") 
        }

        return builder.toString()
    }
}

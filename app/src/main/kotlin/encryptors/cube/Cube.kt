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

    private fun faceToString(face: Array<Identifier>): String {
        val builder = StringBuilder()
        for (i in 0 until 9 step 3) {
            builder.append(face[i]).append(" ").append(face[i + 1]).append(" ").append(face[i + 2]).append("\n")
        }
        return builder.toString()
    }

    override fun toString(): String {
        val builder = StringBuilder()

        for (face in 0 until 6) {
            builder.append(faceToString(cube.sliceArray(face * 9 until (face + 1) * 9)))
            if (face < 5) builder.append("\n") 
        }

        return builder.toString()
    }
}

/*
 * TEO || 2023
 */
 
package encryptors.cube

import kotlin.math.floor
import kotlin.random.Random
import encryptors.cube.*

// Up, Front, Right, Back, Left, Down (U=YELLOW, F=BLUE)
// ABCD, IJKL, EFGH, MNOP, QRST, UVWX
enum class Identifier {
    BLUE, GREEN, ORANGE, RED, YELLOW, WHITE, UNDEF
}

enum class Face {
    UP, FRONT, RIGHT, BACK, LEFT, DOWN
}

class Cube {
    private val cube = Array(54) { Identifier.UNDEF }
    private val orderedFace = arrayOf(0, 1, 2, 7, 8, 3, 6, 5, 4)
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
                cube[face * 9 + orderedFace[unit]] = orderedColors[face]
            }
        }
    }

    /*

    0 1 2
    7 8 3   <---   <TOP FACE EXAMPLE>
    6 5 4
    
    TOP FACE INDXS: [0, 1, 2, 3, 4, 5, 6, 7, 8]
    FRONT FACE IDXS: [9, 10, 11, 12, 13, 14, 15, 16, 17]
    RIGHT FACE IDXS: [18, 19, 20, 21, 22, 23, 24, 25, 26]
    BACK FACE IDXS: [27, 28, 29, 30, 31, 32, 33, 34, 35]
    LEFT FACE IDXS: [36, 37, 38, 39, 40, 41, 42, 43, 44]
    BOTTOM FACE IDXS: [45, 46, 47, 48, 49, 50, 51, 52, 53]
    
    For a left clockwise rotation: 
                CORNERS
              36 38 40 42
                   |
                   V
              38 40 42 36

                 EDGES
              37 39 41 43
                   |
                   V
              39 41 43 37

            ADJACENT CORNERS
           0 6 9 15 45 51 31 29
                   |
                   V
           9 15 45 51 31 29 0 6

             ADJACENT EDGES
               7 16 30 52
                   |
                   V
               16 30 52 7
    
    */

    private fun transformToSpiral(indices: List<Int>): List<Int> {
        val spiral = mutableListOf<Int>()
        for (index in indices) {
            spiral.add(orderedFace.indexOf(index % 9) + (index - (index % 9)))
        }
        return spiral
    }

    private fun cycleSwap(indices: List<Int>) {
        val home = mutableListOf<Identifier>()
        val step_ = indices.size / 4
        for (index in indices) {
            home.add(cube[index])
        }
        for (i in 0 until indices.size) {
            cube[transformToSpiral(indices)[(i + step_) % indices.size]] = home[i]
        }
    }

    public fun transform(face: Face, counterclockwise: Boolean = false) {
        val cycles = faceToCyclesMap[face] ?: return
        var mutableCycles = cycles

        if (counterclockwise) 
            mutableCycles = mutableCycles.map { it.reversed() }

        for (cycle in mutableCycles) {
            cycleSwap(cycle)
        }
    }

    private fun faceToIDXs(face: Array<Identifier>, idx: Int = 0): String {
        val builder = StringBuilder()
        for (i in 0 until 9 step 3) {
            builder
            .append(orderedFace[i + 0] + 9 * idx).append(" ")
            .append(orderedFace[i + 1] + 9 * idx).append(" ")
            .append(orderedFace[i + 2] + 9 * idx).append("\n")
        }
        return builder.toString()
    }

    private fun faceToString(face: Array<Identifier>, idx: Int = 0): String {
        val builder = StringBuilder()
        for (i in 0 until 9 step 3) {
            builder
            .append(face[i + 0].toString().first()).append(" ")
            .append(face[i + 1].toString().first()).append(" ")
            .append(face[i + 2].toString().first()).append("\n")
        }
        return builder.toString()
    }

    override fun toString(): String {
        val builder = StringBuilder()

        for (face in 0 until 6) {
            builder.append(faceToString(cube.sliceArray(face * 9 until (face + 1) * 9), face))
            builder.append("\n") 
        }

        return builder.toString()
    }
}

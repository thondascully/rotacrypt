/*
 * TEO || 2023
 */
 
package encryptors

import kotlin.random.Random

class Cube {
    private val cube = Array(6) { Array(3) { Array(3) { ' ' } } }
    private val colors = "WRBOGY".toCharArray()
    
    init {
        for (face in 0 until 6) {
            for (row in 0 until 3) {
                for (col in 0 until 3) {
                    cube[face][row][col] = colors[face]
                }
            }
        }
    }
    

    // Rotates the specified row of the specified face 90 degrees clockwise
    private fun rotateFaceClockwise(face: Int) {
        val temp = cube[face][0][0]
        cube[face][0][0] = cube[face][2][0]
        cube[face][2][0] = cube[face][2][2]
        cube[face][2][2] = cube[face][0][2]
        cube[face][0][2] = temp
    }

    // Rotates the entire left layer 90 degrees clockwise
    fun rotateLeftClockwise() {
        rotateFaceClockwise(2)
        val temp = cube[0][0][0]
        cube[0][0][0] = cube[4][0][0]
        cube[4][0][0] = cube[1][0][0]
        cube[1][0][0] = cube[5][0][0]
        cube[5][0][0] = temp
    }

    // Rotates the entire right layer 90 degrees clockwise
    fun rotateRightClockwise() {
        rotateFaceClockwise(3)
        val temp = cube[0][0][2]
        cube[0][0][2] = cube[5][0][2]
        cube[5][0][2] = cube[1][0][2]
        cube[1][0][2] = cube[4][0][2]
        cube[4][0][2] = temp
    }

    // ... Similar methods for rotateUpClockwise, rotateDownClockwise, rotateFrontClockwise, rotateBackClockwise

    fun randomize() {
        for (i in 0 until 100) {
            val face = Random.nextInt(6)
            when (face) {
                0 -> rotateLeftClockwise()
                1 -> rotateRightClockwise()
                // Add cases for other rotation methods
            }
        }
    }
    
    private fun faceToString(face: Array<Array<Char>>): String {
        val builder = StringBuilder()
        for (row in face) {
            for (cell in row) {
                builder.append("$cell ")
            }
            builder.append('\n')
        }
        return builder.toString()
    }

    override fun toString(): String {
        val builder = StringBuilder()

        // Print empty space, then Up face (index 2)
        builder.append("${faceToString(cube[2])}")

        // Print Left, Front, Right, Back faces (indices 4, 0, 5, 1)
        val left = faceToString(cube[4]).split("\n")
        val front = faceToString(cube[0]).split("\n")
        val right = faceToString(cube[5]).split("\n")
        val back = faceToString(cube[1]).split("\n")

        for (i in 0 until 3) {
            builder.append("${left[i]}  ${front[i]}  ${right[i]}  ${back[i]}\n")
        }

        // Print empty space, then Down face (index 3)
        builder.append("${faceToString(cube[3])}")

        return builder.toString()
    }


}

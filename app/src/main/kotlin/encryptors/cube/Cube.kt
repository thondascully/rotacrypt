/*
 * TEO || 2023
 */
 
package encryptors.cube

import kotlin.math.floor
import kotlin.random.Random
import encryptors.cube.*

class Cube {
    private val cube = Array(54) { Identifier.UNDEF }

    init {
        for (face in 0 until 6) {
            for (unit in 0 until 9) {
                cube[face * 9 + orderedFace[unit]] = orderedColors[face]
            }
        }
    }

    fun getCube(): List<Char> {
        return cube.map { it.name.first() }
    }

    private fun transformToSpiral(indices: List<Int>): List<Int> {
        val spiral = mutableListOf<Int>()
        for (index in indices) {
            spiral.add(orderedFace[(index % 9)] + (index - (index % 9)))
        }
        return spiral
    }

    private fun cycleSwap(indices: List<Int>) {
        val home = mutableListOf<Identifier>()
        val step_ = indices.size / 4
        for (index in transformToSpiral(indices)) {
            home.add(cube[index])
        }
        for (i in 0 until indices.size) {
            cube[transformToSpiral(indices)[(i + step_) % indices.size]] = home[i]
        }
    }

    public fun algorithm(raw_moves: String) {
        raw_moves.split(" ").forEach { move ->
            val counterclockwise = "'" in move
            val move = move.filter { it.isLetter() }
            val face = faceMap[move] ?: error("Invalid move: $move")
            transform(face, counterclockwise)
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

    private fun faceToString(face: Array<Identifier>): String {
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
            builder.append(faceToString(cube.sliceArray(face * 9 until (face + 1) * 9)))
            builder.append("\n") 
        }

        return builder.toString()
    }
}

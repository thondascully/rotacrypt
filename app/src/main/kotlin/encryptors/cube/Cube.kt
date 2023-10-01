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

/*
 * TEO || 2023
 */

package encryptors

import encryptors.cube.*

class Generator {
    private val orderedFace = arrayOf(6, 7, 8, 5, 4, 3, 0, 1, 2)
    private val orderedCorners = arrayOf(0, 2, 8, 6)

    fun generateKey(cubes: KeyInitContainer, sequences: Array(4) { Array(4) { 0L } }): String {
        val faces = mutableListOf<List<List<Units>>>()
        val corners = mutableListOf<List<List<Units>>>()
        
        cubes.forEachCubeIndexed { cube, idx ->
            faces[idx].add(serializeFaces(cube))
            corners[idx].add(serializeCorners(cube))
        }

        val moves = Array(4) { mutableListOf<Moves>() } 
        sequences.forEachIndexed { cubeIdx, sequence ->
            sequence.forEachIndexed { moveIdx, bitRepresentation ->
                val move = retrieve(bitRepresentation, moveIdx) 
                moves[cubeIdx].add(move)
            }
        }

        val countU = moves.map { it.count { it == Moves.U } }
        val countF = moves.map { it.count { it == Moves.F } }

        val serializedPartial = corners[0][countU[0] % 6] + corners[1][countU[0] % 6] + 
            faces[0][countU[0] % 6] + faces[1][countU[1] % 6] + faces[2][countU[2] % 6] + 
            faces[3][countU[3] % 6] + corners[2][countU[2] % 6] + corners[3][countU[3] % 6]

        val serializedFinal = corners[0][(countU[0] + 1) % 6] + corners[1][(countU[1] + 1) % 6] + 
            faces[0][(countU[0] + 1) % 6] + faces[1][(countU[1] + 1) % 6] + serializedPartial +
            faces[2][(countU[2] + 1) % 6] + faces[3][(countU[3] + 1) % 6] + 
            corners[2][(countU[2] + 1) % 6] + corners[3][(countU[3] + 1) % 6]

        val conv = serializedFinal.map { colorToFace[it] ?: it }.joinToString("")
        return ""
    }

    private fun extract(cube: Cube, order: Array<Int>): List<Moves> {
        val units = ArrayList<Moves>()
        for (i in 0 until 6) {
            for (index in order) {
                units.add(cube.getCube()[i * 9 + index])
            }
        }
        return units
    }

    private fun cycle(list: List<Moves>, pos: Int): List<Moves> {
        return list.takeLast(9 - pos) + list.take(9 - pos)
    }

    private fun serializeFaces(cube: Cube): List<List<Moves>> {
        val extracted = extract(cube, orderedFace)
        val layers = List(6) { extracted.subList(it * 9, (it + 1) * 9) }
        return layers.mapIndexed { index, segment ->
            cycle(segment, index)
        }
    }

    private fun serializeCorners(cube: Cube): List<List<Moves>> {
        val extracted = extract(cube, orderedCorners)
        val corners = List(6) { extracted.subList(it * 4, (it + 1) * 4) }
        return corners
    }

    fun hash(raw: String): String {
        val digest = MessageDigest.getInstance("SHA-256").digest(raw.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }
}
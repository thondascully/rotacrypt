/*
 * TEO || 2023
 */

package encryptors

import encryptors.cube.*
import java.security.SecureRandom
import java.security.MessageDigest

enum class Moves(val value: Int) {
    U(0), F(1), R(2), B(3), L(4), D(5), 
    Up(6), Fp(7), Rp(8), Bp(9), Lp(10), Dp(11)
}

const val BITS_PER_MOVE = 4
const val MOVES_PER_LONG = 64 / BITS_PER_MOVE // 64 moves per scramble

data class Container(val a: Cube, val b: Cube, val c: Cube, val d: Cube, val e: Cube, val f: Cube) {
    fun Container.forEachCube(func: (Cube) -> Unit) {
        func(a)
        func(b)
        func(c)
        func(d)
        func(e)
        func(f)
    }
}

class KeyManager {
    protected val cubes = Container(Cube(), Cube(), Cube(), Cube(), Cube(), Cube())
    val sequences = Array(6) { Array(4) { 0L } } // An array of 6 sequences, each can hold 64 moves
    
    init {
        val secureRandom = SecureRandom()
        cubes.forEachCubeIndexed { cube, index ->
            repeat(64) { i ->
                val idx = secureRandom.nextInt(12)
                val move = Moves.values()[idx]
                insert(sequences[index], move, i)
            }
        }
    }

    private fun insert(sequence: Array<Long>, move: Moves, position: Int) {
        val idx = position / MOVES_PER_LONG
        val pos = (position % MOVES_PER_LONG) * BITS_PER_MOVE
        sequence[arrayIndex] = sequence[idx] or ((move.value.toLong() and 0xF) shl pos)
    }

    private fun retrieve(sequence: Array<Long>, position: Int): Moves {
        val idx = position / MOVES_PER_LONG
        val pos = (position % MOVES_PER_LONG) * BITS_PER_MOVE
        val move = ((sequence[idx] shr pos) and 0xF).toInt()
        return Moves.values()[move]
    }
    
    fun generateSubKey(): String {
        // Generate subkeys based on main key and salt
    }

    fun splitSubKey(subKey: String): List<String> {
        // Split subkey into required chunks
    }
}
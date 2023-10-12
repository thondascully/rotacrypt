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

data class KeyInitContainer(val a: Cube, val b: Cube, val c: Cube, val d: Cube)

fun KeyInitContainer.forEachCubeIndexed(func: (Cube, Int) -> Unit) {
    func(a, 0)
    func(b, 1)
    func(c, 2)
    func(d, 3)
}

class KeyManager {
    protected val generator = KeyGenerator()
    protected val cubes = KeyInitContainer(Cube(), Cube(), Cube(), Cube())
    protected val sequences = Array(4) { Array(4) { 0L } } // An array of 4 sequences; each can hold 64 moves
    protected var key = listOf<Char>()
    
    init {
        val secureRandom = SecureRandom()
        cubes.forEachCubeIndexed { cube, index ->
            repeat(64) { i ->
                val idx = secureRandom.nextInt(12)
                val move = Moves.values()[idx]
                insert(sequences[index], move, i)
            }
            val moves = List(64) { i -> retrieve(sequences[index], i) }
            cube.algorithm(moves)
        }
        key = generator.generateKey(cubes, sequences) // four scrambled cubes and their scrambles
        println(key)
    }

    companion object {
        public fun insert(sequence: Array<Long>, move: Moves, position: Int) {
            val idx = position / MOVES_PER_LONG
            val pos = (position % MOVES_PER_LONG) * BITS_PER_MOVE
            sequence[idx] = sequence[idx] or ((move.value.toLong() and 0xF) shl pos)
        }

        public fun retrieve(sequence: Array<Long>, position: Int): Moves {
            val idx = position / MOVES_PER_LONG
            val pos = (position % MOVES_PER_LONG) * BITS_PER_MOVE
            val move = ((sequence[idx] shr pos) and 0xF).toInt()
            return Moves.values()[move]
        }
    }
    
    fun generateSubKey(): List<Char> {
        // Generate subkeys based on main key and salt
        return ""
    }

    fun splitSubKey(subKey: String): List<List<Char>> {
        // Split subkey into required chunks
        return listOf("")
    }
}
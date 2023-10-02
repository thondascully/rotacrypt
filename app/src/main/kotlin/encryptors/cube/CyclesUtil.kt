/*
 * TEO || 2023
 */

package encryptors.cube

import encryptors.cube.*

// Up, Front, Right, Back, Left, Down (U=YELLOW, F=BLUE)
// ABCD, IJKL, EFGH, MNOP, QRST, UVWX
enum class Identifier {
    BLUE, GREEN, ORANGE, RED, YELLOW, WHITE, UNDEF
}

enum class Face {
    UP, FRONT, RIGHT, BACK, LEFT, DOWN
}

public val moves = listOf("U", "U'", "D", "D'", "F", "F'", "B", "B'", "L", "L'", "R", "R'")
public val orderedFace = arrayOf(0, 1, 2, 5, 8, 7, 6, 3, 4)
public val orderedColors = arrayOf(
    Identifier.YELLOW,
    Identifier.BLUE,
    Identifier.RED,
    Identifier.GREEN,
    Identifier.ORANGE,
    Identifier.WHITE
)

val moveToBit = mapOf(
    "U" to "0000", "U'" to "0001",
    "D" to "0010", "D'" to "0011",
    "F" to "0100", "F'" to "0101",
    "B" to "0110", "B'" to "0111",
    "L" to "1000", "L'" to "1001",
    "R" to "1010", "R'" to "1011",
    "R U" to "1100", "R U'" to "1101",
    "R D" to "1110", "R D'" to "1111"
)

val bitToMove = mapOf(
    "0000" to "U ", "0001" to "U' ",
    "0010" to "D ", "0011" to "D' ",
    "0100" to "F ", "0101" to "F' ",
    "0110" to "B ", "0111" to "B' ",
    "1000" to "L ", "1001" to "L' ",
    "1010" to "R ", "1011" to "R' ",
    "1100" to "R U ", "1101" to "R U' ",
    "1110" to "R D ", "1111" to "R D' "
)

fun movesToBits(moves: String): String {
    val bits = StringBuilder()
    for (move in moves.split(" ")) {
        bits.append(moveToBit[move])
    }
    return bits.toString()
}

fun bitsToMoves(bits: List<String>): String {
    val moves = StringBuilder()
    for (bit in bits) {
        moves.append(bitToMove[bit])
    }
    return moves.toString()
}

fun xor(s1: String, s2: String): String {
    if (s1.length != s2.length) {
        throw IllegalArgumentException("Input strings must be of equal length.")
    }

    return s1.zip(s2) { a, b ->
        if (a == b) '0' else '1'
    }.joinToString("")
}

fun stringToMoveCompatibleChunks(text: String): List<String> {
    val fours = mutableListOf<String>()
    for (char in text) {
        val binary = char.code.toString(2).padStart(8, '0')
        fours.add(binary.substring(0, 4))
        fours.add(binary.substring(4, 8))
    }
    return fours
}

fun testDecrypt(moves: String): String {
    val bin = moves.split(" ").map { bitToMove[it] ?: "" }.joinToString("")
    return bin.chunked(8).map { it.toInt(2).toChar() }.joinToString("")
}

val faceToCyclesMap: Map<Face, List<List<Int>>> = mapOf(
    Face.BACK to listOf(
        listOf(27, 29, 31, 33),
        listOf(28, 30, 32, 34),
        listOf(2, 0, 36, 42, 51, 49, 22, 20),
        listOf(1, 43, 50, 21)
    ),
    Face.FRONT to listOf(
        listOf(9, 11, 13, 15),
        listOf(10, 12, 14, 16),
        listOf(6, 4, 18, 24, 47, 45, 40, 38),
        listOf(5, 25, 46, 39)
    ),
    Face.DOWN to listOf(
        listOf(45, 47, 49, 51),
        listOf(46, 48, 50, 52),
        listOf(15, 13, 24, 22, 33, 31, 42, 40),
        listOf(14, 23, 32, 41)
    ),
    Face.UP to listOf(
        listOf(0, 2, 4, 6),
        listOf(1, 3, 5, 7),
        listOf(9, 11, 36, 38, 27, 29, 18, 20),
        listOf(10, 37, 28, 19)
    ),
    Face.RIGHT to listOf(
        listOf(18, 20, 22, 24),
        listOf(19, 21, 23, 25),
        listOf(2, 4, 33, 27, 47, 49, 11, 13),
        listOf(3, 34, 48, 12)
    ),
    Face.LEFT to listOf(
        listOf(36, 38, 40, 42),
        listOf(37, 39, 41, 43),
        listOf(0, 6, 9, 15, 45, 51, 31, 29),
        listOf(7, 16, 52, 30)
    ),
)

val faceMap = mapOf(
    "U" to Face.UP,
    "F" to Face.FRONT,
    "R" to Face.RIGHT,
    "B" to Face.BACK,
    "L" to Face.LEFT,
    "D" to Face.DOWN
)

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

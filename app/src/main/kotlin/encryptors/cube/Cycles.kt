/*
 * TEO || 2023
 */

package encryptors.cube

import encryptors.cube.*

val faceToCyclesMap: Map<Face, List<List<Int>>> = mapOf(
    Face.BACK to listOf(
        listOf(27, 29, 31, 33),
        listOf(28, 30, 32, 34),
        listOf(2, 0, 42, 36, 49, 51, 20, 22),
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
        listOf(14, 21, 32, 41)
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

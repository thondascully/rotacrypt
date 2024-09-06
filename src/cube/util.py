from enum import Enum

class Identifier(Enum):
    BLUE = 0b0000
    GREEN = 0b0001
    ORANGE = 0b0010
    RED = 0b0011
    YELLOW = 0b0100
    WHITE = 0b0101
    UNDEF = -1


class Face(Enum):
    UP = 0b0000
    FRONT = 0b0001
    RIGHT = 0b0010
    BACK = 0b0011
    LEFT = 0b0100
    DOWN = 0b0101


ordered_face = [0b0000, 0b0001, 0b0010, 0b0101, 0b1000, 0b0111, 0b0110, 0b0011, 0b0100]

ordered_colors = [
    Identifier.YELLOW.value,
    Identifier.BLUE.value,
    Identifier.RED.value,
    Identifier.GREEN.value,
    Identifier.ORANGE.value,
    Identifier.WHITE.value
]

move_to_bit = {
    "U": 0b0000, "Up": 0b0001, "D": 0b0010, "Dp": 0b0011,
    "F": 0b0100, "Fp": 0b0101, "B": 0b0110, "Bp": 0b0111,
    "L": 0b1000, "Lp": 0b1001, "R": 0b1010, "Rp": 0b1011
}

bit_to_move = {v: k for k, v in move_to_bit.items()}

color_to_face = {
    0b0100: 0b0000,  # Y -> U
    0b0001: 0b0001,  # B -> F
    0b0011: 0b0010,  # R -> R
    0b0000: 0b0011,  # G -> B
    0b0010: 0b0100,  # O -> L
    0b0101: 0b0101   # W -> D
}


def moves_to_bits(moves: list[str]) -> int:
    bits = 0
    for move in moves:
        bits = (bits << 4) | move_to_bit[move]
    return bits


def bits_to_moves(bits: int, length: int) -> list[str]:
    moves = []
    for _ in range(length):
        move_bits = bits & 0b1111
        moves.append(bit_to_move[move_bits])
        bits >>= 4
    return moves[::-1]


def xor(bit_sequence1: int, bit_sequence2: int = None) -> int:
    return bit_sequence1 ^ bit_sequence2


def string_to_move_compatible_chunks(text: str) -> list[int]:
    return [(ord(char) >> 4) & 0b1111 for char in text] + [ord(char) & 0b1111 for char in text]


def test_decrypt(moves: list[str]) -> str:
    bin_str = moves_to_bits(moves)
    result = ''
    while bin_str:
        result += chr((bin_str >> 8) & 0b11111111)
        bin_str >>= 8
    return result

face_to_cycles_map = {
    Face.BACK.value: [
        [27, 29, 31, 33],
        [28, 30, 32, 34],
        [2, 0, 36, 42, 51, 49, 22, 20],
        [1, 43, 50, 21]
    ],
    Face.FRONT.value: [
        [9, 11, 13, 15],
        [10, 12, 14, 16],
        [6, 4, 18, 24, 47, 45, 40, 38],
        [5, 25, 46, 39]
    ],
    Face.DOWN.value: [
        [45, 47, 49, 51],
        [46, 48, 50, 52],
        [15, 13, 24, 22, 33, 31, 42, 40],
        [14, 23, 32, 41]
    ],
    Face.UP.value: [
        [0, 2, 4, 6],
        [1, 3, 5, 7],
        [9, 11, 36, 38, 27, 29, 18, 20],
        [10, 37, 28, 19]
    ],
    Face.RIGHT.value: [
        [18, 20, 22, 24],
        [19, 21, 23, 25],
        [2, 4, 33, 27, 47, 49, 11, 13],
        [3, 34, 48, 12]
    ],
    Face.LEFT.value: [
        [36, 38, 40, 42],
        [37, 39, 41, 43],
        [0, 6, 9, 15, 45, 51, 29, 29],
        [7, 16, 52, 28]
    ],
}


face_map = {
    'U': Face.UP.value,
    'F': Face.FRONT.value,
    'R': Face.RIGHT.value,
    'B': Face.BACK.value,
    'L': Face.LEFT.value,
    'D': Face.DOWN.value
}

identifier_to_color = {
    Identifier.BLUE.value: 'B',
    Identifier.GREEN.value: 'G',
    Identifier.ORANGE.value: 'O',
    Identifier.RED.value: 'R',
    Identifier.YELLOW.value: 'Y',
    Identifier.WHITE.value: 'W',
    Identifier.UNDEF.value: '*'
}

"""

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
    
"""
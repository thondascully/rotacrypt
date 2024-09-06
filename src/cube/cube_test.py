import os
import pytest
from util import move_to_bit, moves_to_bits, bits_to_moves, xor



def test_moves_to_bits():
    moves = ["U", "R", "F", "B", "L", "D"]
    expected_bits = (
        (move_to_bit["U"] << 20) | 
        (move_to_bit["R"] << 16) | 
        (move_to_bit["F"] << 12) |
        (move_to_bit["B"] << 8)  | 
        (move_to_bit["L"] << 4)  | 
        move_to_bit["D"]
    )
    result_bits = moves_to_bits(moves)
    assert result_bits == expected_bits

def test_bits_to_moves():
    bits = moves_to_bits(["U", "R", "F", "B", "L", "D"])
    expected_moves = ["U", "R", "F", "B", "L", "D"]
    result_moves = bits_to_moves(bits, length=6)
    assert result_moves == expected_moves

def test_xor():
    moves1 = moves_to_bits(["U", "R", "F"])
    moves2 = moves_to_bits(["B", "L", "D"])
    expected_xor = moves1 ^ moves2
    result_xor = xor(moves1, moves2)
    assert result_xor == expected_xor

def test_bits_to_moves_after_xor():
    moves1 = moves_to_bits(["U", "R", "F"])
    moves2 = moves_to_bits(["B", "L", "D"])
    xor_result = xor(moves1, moves2)
    decoded_moves = bits_to_moves(xor_result, length=3)
    expected_moves = ["W", "F", "S"]  # or whatever the expected moves are after XOR
    assert decoded_moves == expected_moves

def test_xor():
    moves_v1 = moves_to_bits(["U", "R", "F", "B", "L", "D"])
    moves_v2 = moves_to_bits(["B", "D", "B", "D", "B", "D"])
    xor_ = xor(moves_v1, moves_v2)
    
    decoded_moves = bits_to_moves(xor_, length=6)
    
    print(decoded_moves)
    expected_moves = ["W", "X", "Z"]  # or whatever the expected moves are after XOR
    assert decoded_moves == expected_moves

if __name__ == "__main__":
    pytest.main()
    
def test_bin_export_import():
    moves = ["U", "R", "F", "B", "L", "D"]
    bit_sequence = moves_to_bits(moves)
    
    if os.path.exists("moves.bin"):
        os.remove("moves.bin")
    
    with open("moves.bin", "wb") as f:
        f.write(bit_sequence.to_bytes((bit_sequence.bit_length() + 7) // 8, byteorder='big'))
        
    assert os.path.exists("moves.bin")
    
    with open("moves.bin", "rb") as f:
        bit_sequence = int.from_bytes(f.read(), byteorder='big')
    os.remove("moves.bin")
    
    new_moves = bits_to_moves(bit_sequence, length=6)
    assert(moves == new_moves)
    


def blah():
    bit_sequence = moves_to_bits(["U", "R", "F", "B", "L", "D"])

    with open("moves.bin", "wb") as f:
        f.write(bit_sequence.to_bytes((bit_sequence.bit_length() + 7) // 8, byteorder='big'))
        
    with open("moves.bin", "rb") as f:
        bit_sequence = int.from_bytes(f.read(), byteorder='big')

    print(bit_sequence)
    moves = bits_to_moves(bit_sequence, length=6) 
    print(moves)  # Output: ['U', "Rp", "F", "B", "L", "D"]

    moves1 = moves_to_bits(["U", "R", "F"])
    moves2 = moves_to_bits(["B", "L", "D"])

    xor_result = xor(moves1, moves2)

    decoded_moves = bits_to_moves(xor_result, length=3)
    print(decoded_moves) 

    encoded = moves_to_bits(['B', 'D', 'B'])
    xor_ = xor(moves1, encoded)
    print(bits_to_moves(xor_, length=3))
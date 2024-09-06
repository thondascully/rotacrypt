import cube

class Rotacube(cube.Cube):
    def __init__(self, bit_sequence):
        self.cube = self.map_bits_to_cubelets(bit_sequence)

    def map_bits_to_cubelets(self, bit_sequence):
        cube = [-1] * 54
        
        bit_index = 0
        for face in range(6):
            for unit in range(1, 9, 2):
                if bit_index < len(bit_sequence):
                    cube[face * 9 + unit] = int(bit_sequence[bit_index])
                    bit_index += 1
                else:
                    cube[face * 9 + unit] = 0  
        return cube
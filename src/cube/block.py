import rotacube

class Block:
    def __init__(self, bit_sequence):
        self.rotacubes = self.create_rotacubes(bit_sequence)
        
    def create_rotacubes(self, bit_sequence):
        cube_size = 24
        rotacubes = []
        
        for i in range(0, len(bit_sequence), cube_size):
            bit_chunk = bit_sequence[i:i + cube_size]
            cube = rotacube.Rotacube(bit_chunk)
            rotacubes.append(cube)
        
        return rotacubes
    
    def p(self):
        for cube in self.rotacubes:
            print(f'{cube}\n')

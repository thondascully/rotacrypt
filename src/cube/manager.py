import rotacube

class Manager:
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

bit_sequence = "01101000 01100101 01101100 01101100 01101111 00100000 01110100 01101000 01100101 01110010 01100101 00100000 01101101 01111001 00100000 01101110 01100001 01101101 01100101 00100000 01101001 01110011 00100000 01110100 01100101 01101111".replace(" ", "")

manager = Manager(bit_sequence)
manager.p()
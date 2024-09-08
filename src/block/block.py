import src.cube.rotacube as rotacube

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
    
    def get_size(self):
        return len(self.rotacubes)
    
    def p(self):
        for cube in self.rotacubes:
            print(f'{cube}\n')
            
    def __str__(self):
        strs = [cube.get_top_face() for cube in self.rotacubes]
        split_faces = [face.splitlines() for face in strs]        
        len_cubes = self.get_size()
        
        combined = []
        for i in range(3):
            combined_line = " | ".join(face[i] for face in split_faces)
            combined_line = f"[ {combined_line} ]"
            combined.append(combined_line)
         
        str_ = ""
        for i in range(len_cubes):
            str_ = f'{str_}  cube{i + 1} '
        combined.append(str_)
        
        return "\n".join(combined)
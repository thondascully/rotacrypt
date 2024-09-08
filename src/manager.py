import block.block as block
import tree

class Manager:
    def __init__(self, bit_sequence):
        self.cube_size = 24
        self.max_rotacubes_per_block = 6
        self.bits_per_block = self.cube_size * self.max_rotacubes_per_block
        self.blocks = self.create_blocks(bit_sequence)

    def create_blocks(self, bit_sequence):
        blocks = []
        
        for i in range(0, len(bit_sequence), self.bits_per_block):
            bit_chunk = bit_sequence[i:i + self.bits_per_block]
            bl = block.Block(bit_chunk)
            blocks.append(bl)
        
        return blocks
    
    def temp_scramble(self):
        for block in self.blocks:
            for cube in block.rotacubes:
                cube.algorithm(['F', 'U', 'R', 'U', 'R'])
                
    def temp_unscramble(self):
        for block in self.blocks:
            for cube in block.rotacubes:
                cube.algorithm(['Rp', 'Up', 'Rp', 'Up', 'Fp'])
    
    def p(self):
        for i, block in enumerate(self.blocks, start=1):
            print(f"\nBlock {i}: \n{block}\n")

input_text = "hello world my name is teo"
bit_sequence = bin(int.from_bytes(input_text.encode(), 'big'))[2:]

manager = Manager(bit_sequence)
manager.p()

tree.run()

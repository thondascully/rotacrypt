import block

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
    
    def p(self):
        for block in self.blocks:
            print(block)

bit_sequence = "1111111111111111111111111".replace(" ", "")

manager = Manager(bit_sequence)
manager.p()

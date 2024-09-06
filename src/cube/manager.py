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
            block.p()
            print('--- End of Block ---\n')


bit_sequence = "01101000 01100101 01101100 01101100 01101111 00100000 01110100 01101000 01100101 01110010 01100101 00100000 01101101 01111001 00100000 01101110 01100001 01101101 01100101 00100000 01101001 01110011 00100000 01110100 01100101 01101111".replace(" ", "")

manager = Manager(bit_sequence)
manager.p()

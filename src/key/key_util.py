from src.cube.cube_util import bits_to_moves
from src.key import key_gen

key = key_gen.run()
bin_ = bits_to_moves(key, length=6)

print(bin_)
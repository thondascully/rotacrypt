import secrets
from hashlib import sha256
import hmac
import os

KEY_LENGTH = 32
MULT = 2

def generate_random_bytes(num_bytes: int) -> bytes:
    return secrets.token_bytes(num_bytes)

def hkdf_expand(input_key_material: bytes, length: int, info: bytes = b'') -> bytes:
    salt = b'\x00' * sha256().digest_size
    prk = hmac.new(salt, input_key_material, sha256).digest()  
    okm = b''
    previous_block = b''
    block_num = 0

    while len(okm) < length:
        block_num += 1
        data = previous_block + info + bytes([block_num])
        previous_block = hmac.new(prk, data, sha256).digest()
        okm += previous_block

    return okm[:length]

def split_into_bit_strings(expanded_key: bytes, num_parts: int) -> list:
    part_length = len(expanded_key) // num_parts
    return [expanded_key[i*part_length:(i+1)*part_length] for i in range(num_parts)]

def hash_final_bit_string(final_bit_string: bytes) -> bytes:
    return sha256(final_bit_string).digest()

def run():
    initial_entropy = generate_random_bytes(KEY_LENGTH)
    final_key = hash_final_bit_string(initial_entropy)
    final_key_binary = ''.join(f'{byte:08b}' for byte in final_key)
    print(int(final_key_binary, 2))
    return int(final_key_binary, 2)

/*
 * TEO || 2023
 */

package rotacrypt

import kotlinx.coroutines.*
import managers.*
import encryptors.*
import encryptors.cube.*
import key.*
import ops.*

fun main() = runBlocking {

    //val keyGenerator = ECCHybridGen()
    //val keyPair = keyGenerator.generateKeyPair()
    //val twofishEncryptor = TwofishBase(keyPair.private.encoded)
    //val fileOperator = Operator()
    
    //val manager = EncryptionManager(twofishEncryptor, fileOperator)
    
    //launch {
    //    manager.encryptFile("input.txt", "encrypted.txt")
    //}
    
    //launch {
    //    manager.decryptFile("encrypted.txt", "decrypted.txt")
    //}

    // key U2 R' F2 R U' F' L' D F2 U' R2 U L D2 B R2 U2 B2 L2 U'

    /*
    PSEUDOCODE ALGORITHM:

    1. Initialize Cube1, Cube2, Cube3, Cube4 with random CSPRNG scrambles (store all)
    2. Serialize each cube into a string by extracting each face (described 
    down-to-up S shape U F R B L D) and cycling them based on the index of the face. 
    Concatenate the strings into a master string for each individual cube.
    3. Additionally serialize the corners of each cube into a string by extracting them
    in standard spiral order and not applying any cycles. 
    
    i = unique index determined by the amount of "U" moves in the cube's unique key (modded by size)
    
    Serialization (1): 
        str1 = Concat of serializedOne[i] + serializedTwo[i] + serializedThree[i] + serializedFour[i]
        str2 = Concat of serializedCornersOne[i] + serializedCornersTwo[i]
        str3 = Concat of serializedCornersThree[i] + serializedCornersFour[i]
        return str = str2 + str1 + str3

    j = unique index determined by the amount of "F" moves in the cube's unique key (modded by size)

    Serialization (2):
        Use the return from above and label it as the new "og"
        str1 = Concat of serializedOne[j] + serializedTwo[j]
        str2 = Concat of serializedThree[j] + serializedFour[j]
        str3 = Concat of serializedCornersOne[j] + serializedCornersTwo[j]
        str4 = Concat of serializedCornersThree[j] + serializedCornersFour[j]
        return str = str3 + str1 + og + str2 + str4

    4. Convert plaintext to binary. Pass through S-box and XOR with binary master key.
    5. Chunk plaintext into groups of 48 bits and create a respective amount of cubes.
    6. Apply the plaintexts to the cubes in the order of the chunks.
    7. Map the serialized master key string of unit colors to their corresponding layer 
    operation (center pieces) and apply the layer operations to the cube(s). Repeat for each cube.

 
    1. Initialize Cube1, Cube2, Cube3, Cube4, S-box, Time-Lock
    2. Generate keys for each cube and a master key
    3. Convert plaintext to binary
    4. Pass through S-box
    5. XOR binary with master key
    6. Encrypt using Cube1
    7. Encrypt result using Cube2
    8. Apply Feistel structure using Cube3 and Cube4
    9. Apply time-lock to convert to ciphertext

    10. Reverse time-lock
    11. Reverse Feistel structure with Cube3 and Cube4
    12. Reverse Cube2
    13. Reverse Cube1
    14. XOR with master key
    15. Reverse S-box
    16. Convert to plaintext

    Initialization: 4 Rubik's cubes, an S-box for substitution, 
    and a time-lock mechanism for key rotation or temporary locks.

    Key Generation: The keys for each individual cube are generated, 
    along with a master key that will be used for XOR operations.

    Encryption: The plaintext is converted into a binary form, 
    passed through an S-box for substitution, XOR'd with a master 
    key, and then encrypted by sequentially applying the encryption 
    algorithms tied to each of your four Rubik's cubes. A Feistel 
    structure is then applied, followed by the time-lock.

    Decryption: The reverse steps are applied to decrypt the 
    message. The time-lock is reversed, the Feistel structure is 
    reversed, decryption is done with each Rubik's Cube in the 
    reverse order, the master key is applied with XOR, the S-box 
    is reversed, and finally, the plaintext is restored from its 
    binary form.
    */

    //val rubiksEncryptor = CubeEncryptor()
    
    //val plaintext = "Hello".toByteArray()
    //val ciphertext = rubiksEncryptor.encrypt(plaintext)
    //println(ciphertext)

    val cube = Cube()
    cube.algorithm("R U R' U'")
    println(cube)
}

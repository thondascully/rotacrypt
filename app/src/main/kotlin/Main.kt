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

    //val rubiksEncryptor = RubiksEncryptor()
    
    //val plaintext = "Hello".toByteArray()
    //val ciphertext = rubiksEncryptor.encrypt(plaintext)
    //println(ciphertext)
    
    val cube = Cube()
    cube.algorithm("F L' D B")
    println(cube.toString())
}

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

    val cube = Cube()
    println("\nBefore LEDGE:\n\n$cube")
    //cube.algorithm("R")
    //println("\nAfter LEDGE:\n\n$cube")

    cube.algorithm("U")
    println("\nAfter LEDGE:\n\n$cube")

    cube.algorithm("U")
    println("\nAfter LEDGE:\n\n$cube")
}

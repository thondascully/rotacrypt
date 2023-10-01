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
    println("Initial state:\n$cube\n\n")
    cube.transform(Face.FRONT)
    //cube.transformD()
    println("After LEDGE:\n$cube\n\n")
}

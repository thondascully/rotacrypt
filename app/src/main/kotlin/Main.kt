/*
 * TEO || 2023
 */

import kotlinx.coroutines.*
import managers.*
import encryptors.*
import key.*
import ops.*

fun main() = runBlocking {
    val keyGenerator = ECCHybridGen()
    val keyPair = keyGenerator.generateKeyPair()
    val twofishEncryptor = TwofishBase(keyPair.private.encoded)
    val fileOperator = Operator()
    
    val manager = EncryptionManager(twofishEncryptor, fileOperator)
    
    launch {
        manager.encryptFile("input.txt", "encrypted.txt")
    }
    
    launch {
        manager.decryptFile("encrypted.txt", "decrypted.txt")
    }
}

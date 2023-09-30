/*
 * TEO || 2023
 */

import kotlinx.coroutines.*

class EncryptionManager(private val encryptor: Encryptor, private val fileOperator: FileOperator) {
    suspend fun encryptFile(inputPath: String, outputPath: String) {
        try {
            val data = fileOperator.readFile(inputPath)
            val encryptedData = encryptor.encrypt(data)
            fileOperator.writeFile(outputPath, encryptedData)
        } catch (e: Exception) {
            println("Encryption failed: ${e.message}")
        }
    }
    
    suspend fun decryptFile(inputPath: String, outputPath: String) {
        try {
            val data = fileOperator.readFile(inputPath)
            val decryptedData = encryptor.decrypt(data)
            fileOperator.writeFile(outputPath, decryptedData)
        } catch (e: Exception) {
            println("Decryption failed: ${e.message}")
        }
    }
}

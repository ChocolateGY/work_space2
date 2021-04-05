package talkingdata

import java.security._
import javax.crypto._
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}

/**
  * AES 加密 解密
  *
  * @author  zhirong.li
  * @version  1.0
  */
object AesUtil {

  /**
    * AES加密 指定密钥password
    *
    * @param content  需要加密的内容
    * @param password 指定密钥
    * @return String
    */
  def encryptAES(content: String, password: String): String = {
    val encryptResult = encrypt(content, password)
    parseByte2HexStr(encryptResult)
  }

  /**
    * 加密
    *
    * @param content
    * @param password
    * @return byte[]
    */
  private def encrypt(content: String, password: String) = {
    val enCodeFormat = parseHexStr2Byte(password)
    val key = new SecretKeySpec(enCodeFormat, "AES")
    val cipher = Cipher.getInstance("AES/CFB/NoPadding")
    val byteContent = content.getBytes("utf-8")
    val ips = new IvParameterSpec("0000000000123456".getBytes)
    cipher.init(Cipher.ENCRYPT_MODE, key, ips)
    cipher.doFinal(byteContent)
  }

  /**
    * AES解密 指定密钥password
    *
    * @param encryptResultStr 需要解密的内容
    * @param password         指定密钥
    * @return String
    */
  def decryptAES(encryptResultStr: String, password: String): String = {
    val decryptFrom = parseHexStr2Byte(encryptResultStr)
    new String(decrypt(decryptFrom, password))
  }

  /**
    * 解密
    *
    * @param content
    * @param password
    * @return byte[]
    */
  private def decrypt(content: Array[Byte], password: String) = {
    val enCodeFormat =  parseHexStr2Byte(password)
    val key = new SecretKeySpec(enCodeFormat, "AES")
    val cipher = Cipher.getInstance("AES/CFB/NoPadding")
    val ips = new IvParameterSpec("0000000000123456".getBytes)
    cipher.init(Cipher.DECRYPT_MODE, key, ips)
    cipher.doFinal(content)
  }

  /**
    * 将二进制转换成16进制
    *
    * @param buf
    * @return String
    */
  def parseByte2HexStr(buf: Array[Byte]) = {
    val sb = new StringBuffer
    var i = 0
    while ( {
      i < buf.length
    }) {
      var hex = Integer.toHexString(buf(i) & 0xFF)
      if (hex.length == 1) hex = '0' + hex
      sb.append(hex.toUpperCase)

      {
        i += 1
        i - 1
      }
    }
    sb.toString
  }

  /**
    * 将16进制转换为二进制
    *
    * @param hexStr
    * @return byte[]
    */
  def parseHexStr2Byte(hexStr: String) = if (hexStr.length < 1) null else {
    val result = new Array[Byte](hexStr.length / 2)
    var i = 0
    while ( {
      i < hexStr.length / 2
    }) {
      val high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16)
      val low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16)
      result(i) = (high * 16 + low).toByte

      {
        i += 1
        i - 1
      }
    }
    result
  }

  /**
    * 生成指定字符串的密钥
    *
    * @param secret 要生成密钥的字符
    * @return secretKey 生成后的密钥
    * @throws GeneralSecurityException
    */
  def getKey(secret: String) =
    try {
      val _generator = KeyGenerator.getInstance("AES")
      val secureRandom = SecureRandom.getInstance("SHA1PRNG")
      secureRandom.setSeed(secret.getBytes)
      _generator.init(128, secureRandom)
      val secretKey = _generator.generateKey
      val enCodeFormat = secretKey.getEncoded
      enCodeFormat
    } catch {
      case e: Exception =>
        throw new RuntimeException("初始化密钥出现异常")
    }

  def main(args: Array[String]): Unit = {
    val a = "ab123456"
    val p = "abc@123"
    val key = parseByte2HexStr(getKey(p))

    println(key)
    val res = encryptAES(a, key)
    println(res)
    val str = decryptAES(res, key)
    println(str)
  }
}

package com.talkingdata

/**
  * Created by root on 2017-11-30.
  */
object Package2Hash {
  def main(args: Array[String]): Unit = {
    print(pkgToHash("com.jobnew.anada"))
//    println(Translator.decode("wx4g0bm"))
  }
  def pkgToHash(pkgName: String): Long = {
    try {
      if(pkgName == null || ("null" == pkgName) || pkgName.trim.length == 0 ) return 0L
      var hashcode = 1125899906842597L
      val len = pkgName.length

      for(i <- 0 until len) {
        hashcode = 131 * hashcode + pkgName.charAt(i)
      }
      hashcode
    } catch {
      case e: Exception =>
        0L
    }
  }
}

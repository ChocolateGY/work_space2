package com.talkingdata

import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.lang.Long
import scala.collection.mutable.ListBuffer
import scala.io.Source

// Created by Jason.Ye
// Description:   
//    Function    --  Changes Foramat
//    Create Time --  2016/09/03
//    Finish Time --  2016/11/03
// Copyright (c) 2016-present, TalkingData 
object Translator {
  def main(args: Array[String]): Unit = {
    val p = (32.1212323423, 121.3234234352)
    val wgsP = bd2wgs(p._1, p._2)
    println(wgsP)
    val (lat, lng) = decode("wx4g0ec1")
    println(lat -> lng)
    val aa = 0.025 * 100000
    val ns = near(39.78983194, 116.3909417, aa)
    var latMin = 0d
    var lngMin = 0d
    var latMax = 0d
    var lngMax = 0d
    for (n <- ns) {
      println(n)
      val ((lat1, lng1), (lat2, lng2)) = decodeArea(n)
      if (latMin == 0 || lat1 < latMin) latMin = lat1
      if (lngMin == 0 || lng1 < lngMin) lngMin = lng1
      if (lat2 > latMax) latMax = lat2
      if (lng2 > lngMax) lngMax = lng2
    }
    println((latMax - latMin) -> (lngMax - lngMin))

    val buf = bufferExtent(116.3909417, 39.78983194, aa, aa)
    println(buf.toList)
    println((buf(3) - buf(1)) -> (buf(2) - buf(0)))
  }

  def polyRegular(poly: Array[(Double, Double)]): Array[(Double, Double)] = {
    var polySet = poly.toSet
    val size = polySet.size
    val newPoly = new Array[(Double, Double)](size)
    newPoly(0) = polySet.head
    polySet -= polySet.head
    for (i <- 1 until size) {
      val currentPoint = newPoly(i - 1)
      val set = polySet.map { p =>
        val x = p._1 - currentPoint._1
        val y = p._2 - currentPoint._2
        val pValue = if (x >= 0 && y >= 0) 3 else if (x >= 0 && y < 0) 2 else if (x < 0 && y < 0) 1 else 0
        (pValue + y.abs / math.sqrt(x * x + y * y), p)
      }
      val nextPoint = set.max
      newPoly(i) = nextPoint._2
      polySet -= nextPoint._2
    }
    newPoly
  }

  var R: Double = 6371000

  def bufferExtent(x: Double, y: Double, dx: Double, dy: Double): Array[Double] = {
    val extent = new Array[Double](4)
    val dLon: Double = Math.abs(dx * 180 / (Math.PI * R * Math.cos(y * Math.PI / 180)))
    val dLat: Double = Math.abs(dy * 180 / (Math.PI * R))
    extent(0) = x - dLon
    extent(1) = y - dLat
    extent(2) = x + dLon
    extent(3) = y + dLat
    extent
  }

  def testRegularPoly(): Unit = {
    val poly = Array((1.0d, 1.0d), (2.0d, 0.0d), (2.0d, 3.0d), (3.0d, 1.0d), (3.0d, 3.0d), (3.0d, 3.0d), (2.0d, 2.0d), (3.0d, 2.0d))
    val newPoly = polyRegular(poly)
    for (i <- newPoly.indices) {
      println("第" + i + "点:" + poly(i) + "\t" + newPoly(i))
    }
  }

  val sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  def appHash(string: String): Long = {
    var h = 1125899906842597L;
    // prime
    val len = string.length()
    for (i <- 0 until len) {
      h = 131 * h + string.charAt(i)
    }
    h
  }

  def isEmpty(value: String): Boolean = {
    value == null || ("null" == value) || value.trim.length == 0
  }

  val devP = "^([0-9a-zA-Z][0-9a-zA-Z\\-]+$)"

  def isDevID(id: String): Boolean = {
    id.matches(devP)
  }

  def ip2Long(ip: String): Long = {
    if (isEmpty(ip)) return 0L
    val segs = ip.split("\\.")
    var value = 0L
    try {
      value += segs(0).toLong << 24L
      value += segs(1).toLong << 16L
      value += segs(2).toLong << 8L
      value += segs(3).toLong
    } catch {
      case e: Exception =>
    }
    value
  }

  def long2ip(value: Long): String = {
    if (value == 0) return null
    val seg_0 = value & 0xFF
    val seg_1 = (value >>> 8) & 0xFF
    val seg_2 = (value >>> 16) & 0xFF
    val seg_3 = (value >>> 24) & 0xFF
    Array(seg_3, seg_2, seg_1, seg_0).mkString(".")
  }

  def bssid2Long(bssid: String): Long = {
    if (isEmpty(bssid)) return 0L
    val segs = bssid.split(":")
    var value = 0L
    try {
      value += Long.parseLong(segs(0), 16) << 40L
      value += Long.parseLong(segs(1), 16) << 32L
      value += Long.parseLong(segs(2), 16) << 24L
      value += Long.parseLong(segs(3), 16) << 16L
      value += Long.parseLong(segs(4), 16) << 8L
      value += Long.parseLong(segs(5), 16)
    } catch {
      case e: Exception =>
    }
    value
  }

  def formatMac(mac: String) = {
    if (mac.indexOf(':') == -1) (for (i <- 0 until mac.length by 2 if i + 2 <= mac.length) yield mac.substring(i, i + 2)).mkString(":")
    else mac
  }

  def decodeMac(value: String) = {
    value.split("_").flatMap {
      mac => if (mac.length == 12) {
        val d = formatMac(mac)
        if (d != null && d.length > 0) Some(d) else None
      } else if ((!mac.exists(c => c > '9' || c < '0')) && mac.trim.length > 0) {
        val d = long2bssid(mac.toLong)
        if (d != null && d.length > 0) Some(d) else None
      } else Some(mac)
    }
  }

  def long2bssid(value: Long): String = {
    if (value == 0) return null
    val seg_0 = value & 0xFF
    val seg_1 = (value >>> 8) & 0xFF
    val seg_2 = (value >>> 16) & 0xFF
    val seg_3 = (value >>> 24) & 0xFF
    val seg_4 = (value >>> 32) & 0xFF
    val seg_5 = (value >>> 40) & 0xFF
    val s = "0"
    def hex(v: Long) = if (v <= 0xF) s + Long.toHexString(v) else Long.toHexString(v)
    Array(hex(seg_5), hex(seg_4), hex(seg_3), hex(seg_2), hex(seg_1), hex(seg_0)).mkString(":")
  }

  def time2Long(time: String): Long = try {
    if (isEmpty(time)) 0L else sf.parse(time).getTime
  } catch {
    case e: Exception => 0L
  }

  def long2Time(value: Long) = if (value == 0) null else sf.format(value)

  def p_idfa(idfa: String): String = {
    if (idfa == null || idfa.indexOf('-') > 0 || idfa.length < 32) return idfa
    val s1 = idfa.substring(0, 8)
    val s2 = idfa.substring(8, 12)
    val s3 = idfa.substring(12, 16)
    val s4 = idfa.substring(16, 20)
    val s5 = idfa.substring(20, 32)
    Array(s1, s2, s3, s4, s5).mkString("-").toUpperCase
  }

  def splitModel(str: String) = {
    val idx = str.indexOf(':')
    val idx2 = str.indexOf(' ')
    if (idx > 0) {
      (str.substring(0, idx), str.substring(idx + 1, str.length))
    } else if (idx == 0 && idx2 > 0) {
      (str.substring(1, idx2), str.substring(idx2 + 1, str.length))
    } else if (idx2 > 0) {
      (str.substring(0, idx2), str.substring(idx2 + 1, str.length))
    } else if (idx == 0) {
      (str.substring(1, str.length), null)
    } else {
      (null, str)
    }
  }

  def continuousBit(n2: Int, b: Int = 1) = {
    var n = n2
    val h = Integer.highestOneBit(n)
    val l = Integer.lowestOneBit(n)
    var m = 0
    var c = 0
    for (i <- l to h) {
      val a = n & 0x01
      if (a == b) {
        c += 1
      } else {
        if (c >= m) m = c
        c = 0
      }
      n = n >>> 1
    }
    m
  }

  def hex2bytes(hex: String) = {
    val bytes = collection.mutable.ListBuffer[Byte]()
    for (i <- 0 until (hex.length / 2) by 2) {
      val b = Integer.parseInt(hex.substring(i, i + 2), 16)
      bytes += b.toByte
    }
    bytes.toArray
  }

  def hex2bigInt(_hex: String): BigInt = {
    val hex = _hex.replaceAll("[^0-9a-fA-F]", "")
    val o = 14
    val size = hex.length / o
    var value = BigInt(0)
    for (i <- 1 to size) {
      value = (value << (o * 4)) + Long.parseLong(hex.substring((i - 1) * o, i * o), 16)
    }
    val left = hex.length % o
    if (left > 0) {
      value = (value << (left * 4)) + Long.parseLong(hex.substring(size * o, hex.length), 16)
    }
    value = (value << 8) + hex.length
    value
  }

  def big2string(big: BigInt): String = {
    val f = big.toByte
    val bytes = (big >> 8).toByteArray
    val str = bytes.map("%02x" format _).mkString
    if (f >= str.length) str else str.substring(str.length - f, str.length)
  }

  val pi = 3.14159265358979324
  val a = 6378245.0
  // 卫星椭球坐标投影到平面地图坐标系的投影因子。
  val ee = 0.00669342162296594323
  //椭球的偏心率。
  val x_pi = 3.14159265358979324 * 3000.0 / 180.0

  def wgs2bd(lat: Double, lng: Double): (Double, Double) = {
    val gcj = wgs2gcj(lat, lng)
    gcj2bd(gcj._1, gcj._2)
  }

  def wgs2bd(latLng: (Double, Double)): (Double, Double) = wgs2bd(latLng._1, latLng._2)

  //gcj转wgs84粗转换,到小数点后5位
  def gcj_decrypt(gcjLat: Double, gcjLon: Double): (Double, Double) = {
    if (outOfChina(gcjLat, gcjLon)) {
      (gcjLat, gcjLon)
    }
    else {
      val d = delta(gcjLat, gcjLon)
      (gcjLat - d._1, gcjLon - d._2)
    }
  }

  def gcj_encrypt(wgsLat: Double, wgsLon: Double): (Double, Double) = {
    if (outOfChina(wgsLat, wgsLon))
      (wgsLat, wgsLon)
    else {
      val d = delta(wgsLat, wgsLon)
      (wgsLat + d._1, wgsLon + d._2)
    }
  }

  def delta(wgsLat: Double, wgsLon: Double): (Double, Double) = {
    var dLat = transformLat(wgsLon - 105.0, wgsLat - 35.0)
    var dLon = transformLng(wgsLon - 105.0, wgsLat - 35.0)
    val radLat = wgsLat / 180.0d * pi
    var magic = Math.sin(radLat)
    magic = 1 - ee * magic * magic
    val sqrtMagic = Math.sqrt(magic)
    dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi)
    dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi)
    (dLat, dLon)
  }

  //GCJ-02 to WGS-84 exactly
  def outOfChina(lat: Double, lon: Double): Boolean = {
    if (lon < 72.004 || lon > 137.8347 || lat < 0.8293 || lat > 55.8271)
      true
    else false
  }

  //gcj转wgs84精转换,到小数点后7位
  def gcj2wgs(gcjLat: Double, gcjLon: Double): (Double, Double) = {
    val initDelta = 0.01
    val threshold = 0.000000001
    var dLat = initDelta
    var dLon = initDelta
    var mLat = gcjLat - dLat
    var mLon = gcjLon - dLon
    var pLat = gcjLat + dLat
    var pLon = gcjLon + dLon
    var wgsLat = 0d
    var wgsLon = 0d
    var i = 0
    while (!((Math.abs(dLat) < threshold) && (Math.abs(dLon) < threshold)) && i <= 10000) {
      wgsLat = (mLat + pLat) / 2
      wgsLon = (mLon + pLon) / 2
      val tmp = gcj_encrypt(wgsLat, wgsLon)
      dLat = tmp._1 - gcjLat
      dLon = tmp._2 - gcjLon
      if (dLat > 0) pLat = wgsLat
      else mLat = wgsLat
      if (dLon > 0) pLon = wgsLon
      else mLon = wgsLon
      i += 1
    }
    //console.log(i);
    (wgsLat, wgsLon)
  }

  //GCJ-02 to BD-09
  def gcj2bd(lat: Double, lng: Double): (Double, Double) = {
    val x = lng
    val y = lat
    val z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi)
    val theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi)
    (z * Math.sin(theta) + 0.006, z * Math.cos(theta) + 0.0065)
  }

  def bd2wgs(lat: Double, lng: Double): (Double, Double) = {
    val gcj = bd2gcj(lat, lng)
    gcj2wgs(gcj._1, gcj._2)
  }

  def bd2gcj(lat: Double, lng: Double): (Double, Double) = {
    val x = lng - 0.0065
    val y = lat - 0.006
    val z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi)
    val theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi)
    (z * Math.sin(theta), z * Math.cos(theta))
  }

  def wgs2gcj(lat: Double, lng: Double): (Double, Double) = {
    var dLat = transformLat(lng - 105.0, lat - 35.0)
    var dLng = transformLng(lng - 105.0, lat - 35.0)
    val radLat = lat / 180.0 * pi
    val t = Math.sin(radLat)
    val magic = 1 - ee * t * t
    val sqrtMagic = Math.sqrt(magic)
    dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi)
    dLng = (dLng * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi)
    (lat + dLat, lng + dLng)
  }

  def transformLat(lonD: Double, latD: Double): Double = {
    var d1 = -100.0 + 2.0 * lonD + 3.0 * latD + 0.2 * latD * latD + 0.1 * lonD * latD + 0.2 * Math.sqrt(Math.abs(lonD))
    d1 += (20.0 * Math.sin(6.0 * lonD * pi) + 20.0 * Math.sin(2.0 * lonD * pi)) * 2.0 / 3.0
    d1 += (20.0 * Math.sin(latD * pi) + 40.0 * Math.sin(latD / 3.0 * pi)) * 2.0 / 3.0
    d1 += (160.0 * Math.sin(latD / 12.0 * pi) + 320 * Math.sin(latD * pi / 30.0)) * 2.0 / 3.0
    d1
  }


  def transformLng(lonD: Double, latD: Double): Double = {
    var d2 = 300.0 + lonD + 2.0 * latD + 0.1 * lonD * lonD + 0.1 * lonD * latD + 0.1 * Math.sqrt(Math.abs(lonD))
    d2 += (20.0 * Math.sin(6.0 * lonD * pi) + 20.0 * Math.sin(2.0 * lonD * pi)) * 2.0 / 3.0
    d2 += (20.0 * Math.sin(lonD * pi) + 40.0 * Math.sin(lonD / 3.0 * pi)) * 2.0 / 3.0
    d2 += (150.0 * Math.sin(lonD / 12.0 * pi) + 300.0 * Math.sin(lonD / 30.0 * pi)) * 2.0 / 3.0
    d2
  }

  // @poly 由多边形构成的点（顺时针或者逆时针均可) Arrayuffer[(lat, lng)]
  // @pt   待判断的点 (lat, lng)
  def isPolyContainsPt(poly: Array[(Double, Double)], pt: (Double, Double)): Boolean = {
    var ret = false
    var latMin = 90.0
    var latMax = -90.0
    var lngMin = 180.0
    var lngMax = -180.0
    poly.foreach { pt =>
      if (pt._1 > latMax) latMax = pt._1
      if (pt._1 < latMin) latMin = pt._1
      if (pt._2 > lngMax) lngMax = pt._2
      if (pt._2 < lngMin) lngMin = pt._2
    }
    if (!(pt._1 < latMin || pt._1 > latMax || pt._2 < lngMin || pt._2 > lngMax)) {
      for (i <- poly.indices) {
        val j = (i + 1) % poly.length
        if ((poly(i)._1 < pt._1) != (poly(j)._1 < pt._1) &&
          (pt._2 < (poly(j)._2 - poly(i)._2) * (pt._1 - poly(i)._1) / (poly(j)._1 - poly(i)._1) + poly(i)._2)) {
          ret = !ret
        }
      }
    }
    ret
  }

  def testWgs2bd(): Unit = {
    val latLng = (31.29960519710613, 121.52181571686867)
    val res = wgs2gcj(latLng._1, latLng._2)
    println(res)
    println(latLng)
    println(gcj2wgs(res._1, res._2))
  }

  val chars = Array(
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
    'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
    'y', 'z'
  )

  val deChars = (for (i <- chars.indices) yield chars(i) -> i).toMap

  def hash(lat: Double, lng: Double, len: Int) = {
    var latb = (-90d, 90d)
    var lngb = (-180d, 180d)
    var hash = 0L
    val times = len * 5
    for (i <- 0 until times) {
      val latm = (latb._1 + latb._2) / 2
      val lngm = (lngb._1 + lngb._2) / 2
      if (i % 2 == 0) {
        if (lng <= lngm) {
          hash = hash << 1L | 0
          lngb = (lngb._1, lngm)
        } else {
          hash = hash << 1L | 1
          lngb = (lngm, lngb._2)
        }
      } else {
        if (lat <= latm) {
          hash = hash << 1L | 0
          latb = (latb._1, latm)
        } else {
          hash = hash << 1L | 1
          latb = (latm, latb._2)
        }
      }
    }
    hash
  }

  def unhash(hash: Long) = {
    var latb = ListBuffer[Byte]()
    var lngb = ListBuffer[Byte]()
    var h = hash
    var i = (java.lang.Long.toBinaryString(hash).length + 1) % 2
    while (h > 0) {
      if (i % 2 == 1) {
        latb +:= (h & 1).toByte
      } else {
        lngb +:= (h & 1).toByte
      }
      h = h >>> 1L
      i += 1
    }
    var lat = (-90d, 90d)
    var lng = (-180d, 180d)
    for (j <- lngb.indices) {
      val latm = (lat._1 + lat._2) / 2
      val lngm = (lng._1 + lng._2) / 2
      if (j < latb.size) if (latb(j) == 1) lat = (latm, lat._2) else lat = (lat._1, latm)
      if (lngb(j) == 1) lng = (lngm, lng._2) else lng = (lng._1, lngm)
    }
    ((lat._1 + lat._2) / 2, (lng._1 + lng._2) / 2, (lat._2 - lat._1, lng._2 - lng._1))
  }

  def encode(latLng: (Double, Double), len: Int): String = encode(latLng._1, latLng._2, len)

  def encode(lat: Double, lng: Double, len: Int): String = encode(hash(lat, lng, len), len)

  def encode(hash: Long, len: Int): String = {
    val list = ListBuffer[Char]()
    var h = hash
    while (h > 0 || list.size < len) {
      list += chars((h & 31).toInt)
      h = h >>> 5
    }
    list.reverse.take(len).mkString("")
  }

  def decode(geo: String) = {
    val (lat, lng, _) = unhash(decodeHash(geo))
    (lat, lng)
  }

  def decodeError(geo: String) = unhash(decodeHash(geo))

  def decodeArea(geo: String) = {
    val (lat, lng, (latE, lngE)) = decodeError(geo)
    ((lat - latE / 2, lng - lngE / 2), (lat + latE / 2, lng + lngE / 2))
  }

  def decodeHash(geo: String) = {
    var hash = 0L
    for (c <- geo) {
      val n = deChars(c)
      hash = hash << 5
      hash += n
    }
    hash
  }

  // 默认9宫格
  def expand(geo: String, gridNum: Int = 3) = {
    val (lat, lng, (latError, lngError)) = decodeError(geo)
    val len = geo.length
    val list = ListBuffer[String]()
    val border = gridNum / 2
    for (i <- -border to border; j <- -border to border) {
      list += encode(lat + (i * latError), (j * lngError) + lng, len)
    }
    list.toArray
  }

  def near(geo: String, d: Double): Array[String] = {
    val (lat, lng) = decode(geo)
    near(lat, lng, d)
  }

  def near(lat: Double, lng: Double, d: Double): Array[String] = {
    val locs = bufferExtent(lng, lat, d, d)
    val latA = (locs(1) - locs(3)).abs
    val lngA = (locs(0) - locs(2)).abs
    near(encode(lat, lng, 8), latA, lngA)
  }

  def near(geo: String, latD: Double, lngD: Double) = {
    var sub = geo.substring(0, 1)
    var (lat, lng, (latE, lngE)) = decodeError(sub)
    while ((latE / latD) >= 2 || (lngE / lngD) >= 2) {
      sub = geo.substring(0, sub.length + 1)
      val (lat2, lng2, (latE2, lngE2)) = decodeError(sub)
      lat = lat2
      lng = lng2
      latE = latE2
      lngE = lngE2
    }
    val latN = (((latD / latE) - 1) / 2).round
    val lngN = (((lngD / lngE) - 1) / 2).round
    val list = ListBuffer[String]()
    for (i <- -latN to latN) {
      for (j <- -lngN to lngN) {
        list += encode(lat + (i * latE), (j * lngE) + lng, sub.length)
      }
    }
    val latA = (latN + 1) * latE
    val lngA = (lngN + 1) * lngE
    val latL = latD - latA
    val lngL = lngD - lngA
    if (latL / latE > .45) {
      for (j <- -lngN to lngN) {
        list += encode(lat - latA, (j * lngE) + lng, sub.length + 1)
        list += encode(lat + latA, (j * lngE) + lng, sub.length + 1)
      }
    }
    if (lngL / lngE > .45) {
      for (i <- -latN to latN) {
        list += encode(lat + (i * latE), lng - lngA, sub.length + 1)
        list += encode(lat + (i * latE), lng + lngA, sub.length + 1)
      }
    }
    list.toSet[String].toArray
  }

  def testIsPolyContainsPt(): Unit = {
    val poly = Array((31.30125, 121.521292),
      (31.299522, 121.52211),
      (31.299542, 121.521876),
      (31.300012, 121.520601),
      (31.300301, 121.520722),
      (31.300405, 121.520708),
      (31.301011, 121.520991),
      (31.301038, 121.52118),
      (31.30125, 121.521292))
    val pt = (31.303474709078177, 121.53267614487562)
    println(isPolyContainsPt(poly, pt))
  }
}

import org.apache.commons.codec.binary.Base64

//case class MyData(para1:String,para2:String)
//
//val a = MyData("guan","yu")
//val b = MyData("guan","yu")
//println(a)
//println(b)
//a == b //true
//val c = new MyData("guan","yu")
//a == c //true
//
//class Data(val a:String,val b :String)
//val d1 = new Data("guan","yu")
//val d2 = new Data("guan","yu")
//d1 == d2 //false
val a = Base64.encodeBase64String("101460010123456789".getBytes)

//        val res = a.encodeToString("101460010123456789".getBytes)
println(a)
val s = "MTAxNDYwMDEwMTIzNDU2Nzg5"
println(new String(Base64.decodeBase64(s)))

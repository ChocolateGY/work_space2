import com.github.plokhotnyuk.jsoniter_scala.macros._
import com.github.plokhotnyuk.jsoniter_scala.core._

case class D(
              date: String,
              data: Seq[D1]
            )

case class D1(
               hour: Int,
               app: Seq[String],
               city: Seq[String],
               province: Seq[String]
             )

case class RootInterface(
                          imei: String,
                          data: Seq[D]
                        )

//https://github.com/plokhotnyuk/jsoniter-scala
object TestJsoniter {
  def main(args: Array[String]): Unit = {
    val str = """{"imei":"865175047468552","data":[{"date":"2019-05-21","data":[{"hour":11,"app":["TD00415"],"city":["320900"],"province":[]}]}]}"""

    implicit val codec: JsonValueCodec[RootInterface] = JsonCodecMaker.make[RootInterface](CodecMakerConfig())

    val root = readFromArray(str.getBytes("UTF-8"))
    val imei = root.imei
    val seq = root.data
    seq.foreach {
      x =>
        val date = x.date
        val dataSeq = x.data
        dataSeq.foreach {
          x =>
            val app = x.app
            println(app)
        }
        println(date)
    }
    println(s"imei:$imei")

  }
}


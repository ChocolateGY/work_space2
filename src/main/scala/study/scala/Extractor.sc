class convariant[+T](t:T){
  def f1 [U>:T](t:U)={

  }
}

//val div2 = new PartialFunction[Int, Int] {
//  override def isDefinedAt(x: Int) = x != 0
//
//  override def apply(v1: Int) = 100 / v1
//}
//
//div2.isDefinedAt(1)
//div2(1)
//div2.isDefinedAt(0)
//val a: PartialFunction[Int, Unit] = {
//  case x: Int => println("int")
//}
//val signal: PartialFunction[Int, Int] = {
//  case x if x > 0 => 1
//  case x if x < 0 => -1
//}
//val newSignal: Function1[Int, Int] = signal.compose {
//  case x => x - 1
//}
//signal(1)
//
//val signal2: PartialFunction[Int, Int] = signal.orElse {
//  case x if x == 0 => 0
//}
//
//signal2(0)


//val scores = new collection.mutable.HashMap[String, Int]
//scores("bobs") = 100
//val bobScore = scores("bobs")
//
//object Name {
//  def unapply(input: String): Option[(String, String)] =
//    if (input.trim == "") None else Some(input.trim.split("\\s+")(0), input.trim.split("\\s+")(1))
//}
//
//val author = "1 2"
//val Name(a, b) = author
//
//object IsCompoud {
//  def unapply(arg: String) = arg.contains(" ")
//}
//
//"1 2 3 4" match {
//  case Name(a, b@IsCompoud()) => println(a + b)
//  case Name(a, b) => println(a + "  " + b)
//}
//
//object Name2 {
//  def unapplySeq(input: String): Option[Seq[String]] =
//    if (input.trim == "") None
//    else
//      Some(input.trim.split("\\s+"))
//}
//author match{
//  case Name2(a) =>print(a)
//  case Name2(a,b) =>print(a+b)
//  case Name2(a,b,c) =>print(a+b+c)
//}
//val pattern = "([0-9]+) ([a-z]+)".r
//"99 bottles" match{
//  case pattern(num,str) => println(s"num: $num . str: $str")
//}
//val (q,r) = BigInt(10) /% 3
//val f : PartialFunction[Char,Int] = {
//  case '-' => -11
//  case '+' => 1
//}
//f('-')
//f.isDefinedAt('-')
//"-3+4".collect{
//  case '-' => -1
//  case '+' => +1
//}
//val sample = 1 to 10
//val isEven :PartialFunction[Int,String] = {
//  case x if x % 2 == 0 => s"$x is even"
//}
//val isOdd :PartialFunction[Int,String] = {
//  case x if x % 2 == 1 => s"$x is odd"
//}
//sample.map( isEven orElse isOdd )
//import PartialFunction._
//def strangeConditional(other: Any): Boolean = cond(other) {
//  case x: String if x == "abc" || x == "def"  => true
//  case x: Int => true
//}
//def onlyInt(v: Any): Option[Int] = condOpt(v) { case x: Int => x }
//strangeConditional(123)
//strangeConditional("abcd")
//strangeConditional("abc")
//onlyInt(1)
//onlyInt("1")
//def honest: PartialFunction[Any, Int] =
//{ case i: Int ⇒ i; case s: String if isParsableAsInt(s) ⇒ s.toInt }
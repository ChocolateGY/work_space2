package study.scalaMP4

/**
  * 默认构造器为  Teacher()
  * 所有构造器都必须直接或间接引用主构造器。
  */
//class Teacher {
//  var name : String = _
//  private var age = 27
//  private[this] val gender = "male"
//
//  def this(name:String){
//    this
//    this.name = name
//  }
//
//  def sayHello(){
//    println(this.name + ":" + this.age + " : " + this.gender)
//  }
//}

/**
  * 重载构造器，加了private，则默认构造器外界不可使用
  *
  * @param name
  * @param age
  */
//class Teacher private (val name : String, val age : Int){
//  println("This is the primary constructor!!!")
//  var gender : String = _
//  println(gender)
//  def this(name : String, age : Int, gender : String){
//    this(name, age)
//
//    this.gender = gender
//  }
//}

/**
  * java内部类出属于外部类，scala内部类纯属于对象
  * @param name
  */
class Outer(val name: String) { outer =>
            class Inner(val name: String){
              def foo(b:Inner) = println("Outer: " + outer.name + 
                  " Inner: " + b.name)
            }
            
        }
object OOPInScala{
  def main(args: Array[String]) {
    
    val outer1 = new Outer("Spark")
    val outer2 = new Outer("Hadoop")
    val inner1 = new outer1.Inner("Scala")
    val inner2 = new outer2.Inner("Java")
    inner1.foo(inner1);
    inner2.foo(inner2);

    //这个是不可以的，内部类隶属于外部类实例本身，这样更符合实际情况，与java的区分
//    inner1.foo(inner2)
    
//	  val p = new Teacher
//	  p.name = "Spark"
//
//	  p.sayHello
    
//	  val p = new Teacher("Spark", 5)
//	  println(" : " + p.age)
    
//    val p = new Teacher("Spark", 5 , "male")
//    println(" : " + p.age)
    
    
  }
  
  
}
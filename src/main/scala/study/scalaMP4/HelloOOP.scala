package study.scalaMP4

class Person {
  private var age = 0
  def increment(){age += 1}
  def current = age
  
  def act(person: Person){
    person.age
  }
    
  
}

class Student{
  private var privateAge = 0
  val name = "Scala"
  def age = privateAge  
  var gendor = "man"
  def getGendor = gendor
  def isYounger(other: Student) = privateAge < other.privateAge
}

object HelloOOP {

  def main(args: Array[String]): Unit = {
    val person = new Person()
    person.increment()
    person.increment()
    println(person.current)

    val student = new Student
//    student.age = 1
    println(student.age)
//
//    val student = new Student
//    println(student.name)
    
  }

}
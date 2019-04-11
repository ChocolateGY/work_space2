import java.io.{File, PrintWriter}
import java.util.Date

def curriedSum(x: Int)(y: Int) = x + y

val first = curriedSum(1) _

first(2)

def sec(x: Int) = (y: Int) => x + y
val sec1 = sec(1)
val res = sec1(2)

def twice(op: Double => Double, num: Double) = op(op(num))

val res2 = twice(_ * 2, 1)

def writerFile(file: File, op: PrintWriter => Unit ) = {
  val write = new PrintWriter(file)
  try {
    op(write)
  } finally {
    write.close()
  }
}
val res3 = writerFile(new File("d:\\temp\\test.txt"),write=>write.println("hello,1"))
def writeFile2(file:File)(op:PrintWriter=>Unit): Unit = {
  val write = new PrintWriter(file)
  try{
    op(write)
  } finally {
    write.close()
  }
}
val res4 = writeFile2(new File("d:/temp/test.txt")){
  _.println("hello")
}

def judge(predicate: =>Boolean) = predicate
val res5 = judge(5>3)

package study.scala

import scala.reflect.ClassTag

/**
  * Scala官方文档中对于ClassTag的定义如下：
  *
  * ClassTag[T]保存着在运行时被JVM擦除的类型T的信息。
  * 当我们在运行时想获得被实例化的Array的类型信息的时候，这个特性会比较有用。
  *
  * 下面请看一个具体的场景：
  *
  * 场景
  * 假定有一个Map[String, Any]，给定一个指定的key，我们需要检查Map中是否存在该key对应的value，
  * 如果存在，则优雅地返回这个值。看起来很简单，下面让我们来实现它，并且逐渐理解ClassTag。
  *
  */
object ClassTagStudy {
  def main(args: Array[String]): Unit = {
    fun3()
  }

  /**
    * 解决方案一
    * 我们的第一次尝试如下所示：
    */
  def fun1() = {
    class Animal
    val myMap: collection.Map[String, Any] = Map("Number" -> 1, "Greeting" -> "Hello World",
      "Animal" -> new Animal)
    /* 下面注释的代码将会不通过编译
     * Any不能被当时Int使用
     */
    //val number:Int = myMap("Number")
    //println("number is " + number)
    //使用类型转换，可以通过编译
    val number: Int = myMap("Number").asInstanceOf[Int]
    println("number  is " + number)
    //下面的代码将会抛出ClassCastException
    val greeting: String = myMap("Number").asInstanceOf[String]

    /**
      * 上面的代码有几个很显然的问题：
      *
      * 首先，当我们把Any直接当成Int来使用的时候，编译器是不会通过的：
      * 编译器看起来没有什么错误，但是问题在于，我们没有办法使用Map中值的具体类型。
      * 换句话说，如果我们只是把值的类型设置成Any我们没办法受益于Scala提供的类型系统，所以我们需要修改代码。
      *
      * 为了通过编译，我们使用了如下的类型转换把获取的值变成了Int类型：
      *
      * 这种做法虽然能有效果，但是当我们尝试转换一个不相关的类型的时候，
      * asInstanceOf会抛出一个ClassCastException异常。
      *
      * 所以下面这行代码，会在运行时抛出异常，因为我们试图转换一个Int值为String。
      * val greeting:String = myMap("Number").asInstanceOf[String]
      *
      * 我们又引入了一个新的问题。
      *
      * 就算我们使用Map的get()方法，编译器还是不会通过，因为Option[Any]也没有办法当成Option[Int]来使用：
      *
      * //下面的代码将不会通过编译
      * val number:Option[Int] = myMap.get("Number")
      * 你可能会想什么谁的代码里面会使用Map[String, Any]，这显然不是一个好的设计。
      * 但是我们先忽略这一点，并假设这个结构确实存在，下面我们回到这个问题。
      *
      * 使用asInstanceOf显然也不是一个好的方案，处理ClassCastException的办法之一是使用try/catch，
      * 但是这个方案并不是一个可靠并且优雅的方案，所以我们并不会采用。
      *
      */
  }

  /**
    * 解决方案二
    */
  def fun2() = {
    class Animal {
      override def toString = "I am Animal"
    }

    // getValueFromMap for the Int, String and Animal
    def getValueFromMapForInt(key: String, dataMap: collection.Map[String, Any]): Option[Int] =
      dataMap.get(key) match {
        case Some(value: Int) => Some(value)
        case _ => None
      }

    def getValueFromMapForString(key: String, dataMap: collection.Map[String, Any]): Option[String] =
      dataMap.get(key) match {
        case Some(value: String) => Some(value)
        case _ => None
      }

    def getValueFromMapForAnimal(key: String, dataMap: collection.Map[String, Any]): Option[Animal] =
      dataMap.get(key) match {
        case Some(value: Animal) => Some(value)
        case _ => None
      }


    val myMap: collection.Map[String, Any] = Map("Number" -> 1, "Greeting" -> "Hello World", "Animal" -> new Animal)
    // returns Some(1)
    val number1: Option[Int] = getValueFromMapForInt("Number", myMap)
    println("number is " + number1)
    // returns None
    val numberNotExists: Option[Int] = getValueFromMapForInt("Number2", myMap)
    println("number is " + numberNotExists)
    println
    // returns Some(Hello World)
    val greeting: Option[String] = getValueFromMapForString("Greeting", myMap)
    println("greeting is " + greeting)
    // returns None
    val greetingDoesNotExists: Option[String] = getValueFromMapForString("Greeting1", myMap)
    println("greeting is " + greetingDoesNotExists)
    println()
    // returns Some[Animal]
    val animal: Option[Animal] = getValueFromMapForAnimal("Animal", myMap)
    println("Animal is " + animal)
    // returns None
    val animalDoesNotExist: Option[Animal] = getValueFromMapForAnimal("Animal1", myMap)
    println("Animal is " + animalDoesNotExist)


    /**
      * 结果如下
      * number is Some(1)
      * number is None
      *
      * greeting is Some(Hello World)
      * greeting is None
      *
      * Animal is Some(I am Animal)
      * Animal is None
      *
      * 现在我们使用getValueFromMapForXXX方法来获取Map中XXX类型的值，
      * 从而避免了ClassCastException。
      *
      * 虽然解决了之前的问题，但是现在的这个解决方案任然不够好，因为，
      * 当我们增加一个新的类型的时候，就要提供一个新的getValueFromMapForXXX方法。
      */
  }

  /**
    * 解决方案三
    * 我们尝试使用类型参数来解决之前方案中getValueFromMapForXXX方法的可扩展性不足的问题。
    */
  def fun3() = {
    def getValueFromMap[T](key: String, dataMap: collection.Map[String, Any]): Option[T] =
      dataMap.get(key) match {
        case Some(value: T) => Some(value)
        case _ => None
      }

    /**
      *  现在我们只有一个getValueFromMap[T]的方法，它的参数和之前的版本一样，但是现在需要传入一个类型参数T。
      */
    class Animal {
      override def toString = "I am Animal"
    }
    val myMap: collection.Map[String, Any] = Map("Number" -> 1, "Greeting" -> "Hello World",
      "Animal" -> new Animal)
    // returns Some(1)
    val number1: Option[Int] = getValueFromMap[Int]("Number", myMap)
    println("number is " + number1)
    // returns None
    val numberNotExists: Option[Int] = getValueFromMap[Int]("Number2", myMap)
    println("number is " + numberNotExists)
    println
    // returns Some(Hello World)
    val greeting: Option[String] = getValueFromMap[String]("Greeting", myMap)
    println("greeting is " + greeting)
    // returns None
    val greetingDoesNotExists: Option[String] = getValueFromMap[String]("Greeting1", myMap)
    println("greeting is " + greetingDoesNotExists)
    println()
    // returns Some[Animal]
    val animal: Option[Animal] = getValueFromMap[Animal]("Animal", myMap)
    println("Animal is " + animal)
    // returns None
    val animalDoesNotExist: Option[Animal] = getValueFromMap[Animal]("Animal1", myMap)
    println("Animal is " + animalDoesNotExist)
    println
    // 注意，这里开始出现问题了
    // 现在编译器不会报错，因为所有的都发生在运行时
    // 即使getValueFromMap 返回的是 Option[String]
    val greetingInt: Option[Int] = getValueFromMap[Int]("Greeting", myMap)
    // 输出 Some(Hello World)
    println("greetingInt is " + greetingInt)
    // 这里会抛出 ClassCastException
    val somevalue = greetingInt.map((x) => x + 5)
    // 下面的不会打印
    println(somevalue)

    /**
      * number is Some(1)
      * number is None
      *
      * greeting is Some(Hello World)
      * greeting is None
      *
      * Animal is Some(I am Animal)
      * Animal is None
      *
      * greetingInt is Some(Hello World)
      * Exception in thread "main" java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
      * at scala.runtime.BoxesRunTime.unboxToInt(BoxesRunTime.java:103)
      * at scala.runtime.java8.JFunction1$mcII$sp.apply(JFunction1$mcII$sp.java:23)
      * at scala.Option.map(Option.scala:163)
      * at learnscala.Test$.main(Test.scala:63)
      * at learnscala.Test.main(Test.scala)
      *
      *
      * 现在，我们所需要做的就是调用getValueFromMap[T]方法，并且传递我们期待的类型和key，
      * 这样Map就可以返回我们需要的value。假如我们需要获取一个key为Number的Int类型的值，我们只需要这样做就可以了：
      *
      * val number1:Option[Int] = getValueFromMap[Int]("Number", myMap)
      * 到现在为止，我们看起来有了一个可靠的解决方案，因为我们只有一个getValeFromMap方法，
      * 并且我们也没有编译错误或者因为调用asInstanceOf不当而抛出的运行时ClassCastException异常。
      *
      * 在解决方案三中，我们解决了如下的几个问题：
      *
      * 用户需要更加了解他正在处理的类型，以减少不必要的运行时异常。
      * 不需要asInstanceOf方法，如果key找到了，那么我们返回对应的Some[value]，否则，返回None。
      * 可扩展性更好。
      * 到现在为止看起来还好，但是就像上面代码中所展示的，当用户不小心使用了错误的类型时，还是会有错误发生。
      *
      * 下面的代码虽然可以正常工作，但是并不符合逻辑：
      *
      * //尝试转换 Option[String] 为 Option[Int]
      * val greetingInt:Option[Int] = getValueFromMap[Int]("Greeting", myMap)
      * Map中key为Greeting的value值为String类型，但是我们把它赋值给Option[Int]。惊人的是，下面的代码可以正常工作：
      *
      * // prints Some(Hello World)
      * println("greetingInt is " + greetingInt)
      * 但是当我们做一个Int操作的时候，会抛出异常。
      *
      * 如下所示的代码，会抛出ClassCaseException：
      *
      * // 这行代码会抛出 ClassCastException 异常
      * val somevalue = greetingInt.map((x) => x + 5)
      * // 下面这行代码不会被打印
      * println(somevalue)
      * 是不是getValueFromMap[T]需要捕获这个异常呢，我们的方法有问题吗？让我们继续看一下这个方法：
      *
      * def getValueFromMap[T](key:String, dataMap: collection.Map[String, Any]): Option[T] =
      *     dataMap.get(key) match {
      * case Some(value:T) => Some(value)
      * case _ => None
      * }
      * 方法看起来是好的，我们所做的就是调用get(key)方法，并且检查value是不是T类型的，
      * 如果是，并且存在这个value，那么返回Some(value: T),否则返回None。
      *
      * 所以，在上面的例子中，key为Greeting的value类型为Int,它应该检查Some(value: Int)是否是符合，
      * 显然，它应该返回None,因为key为Greeting的value的值为：Hello World，这是一个String而不是Int。但是我们使用了一个Int去接收。
      *
      * 问题在于，为什么下面这行代码没有捕捉到异常：
      *
      * case Some(value:T) => Some(value)
      * 运行时只会检查key有没有对应的value，而不会检查value是否是我们传递的T类型。
      * 因为运行时不包含我们传递的任何类型信息，在运行时，T已经被擦除不存在了。在JVM中被称为类型擦除。
      *
      * 所以我们最好可以保证，我们传递的类型T在运行时可以存在，去帮助运行时实现一些逻辑。下面我们引入ClassTag。
      *
      * 我们需要做的就是传递一个隐式的ClassTag[T]参数，如果我们需要获取Map中Int类型的value时，
      * 我们需要传递一个隐式的ClassTag[Int]参数。然后，当我们调用这个方法的时候，我们可以不传递隐式参数（参考Scala隐式转换），编译器会自动为我们提供。
      *
      * 所以现在的getValuleFromMap看起来是下面这样的：
      *
      * def getValueFromMap[T](key:String, dataMap: collection.Map[String, Any])(implicit t:ClassTag[T]): Option[T] = {
      *     dataMap.get(key) match {
      * case Some(value: T) => Some(value)
      * case _ => None
      * }
      * }
      * 上面的方法也可以写成这样，效果是以一样的，但是看起来优雅一些：
      *
      * def getValueFromMap[T : ClassTag](key:String, dataMap: collection.Map[String, Any]): Option[T] = {
      *     dataMap.get(key) match {
      * case Some(value: T) => Some(value)
      * case _ => None
      * }
      * 方法调用不需要做任何改变，因为编译器会为我们提供隐式的ClassTag[T]参数。
      */

    def fun4()={
      // getValueFromMap
      def getValueFromMap[T: ClassTag](key: String, dataMap: collection.Map[String, Any]): Option[T] = {
        dataMap.get(key) match {
          case Some(value: T) => Some(value)
          case _ => None
        }
      }

      def main(args: Array[String]): Unit = {
        class Animal {
          override def toString = "I am Animal"
        }
        val myMap: collection.Map[String, Any] = Map("Number" -> 1, "Greeting" -> "Hello World",
          "Animal" -> new Animal)
        // returns Some(1)
        val number1: Option[Int] = getValueFromMap[Int]("Number", myMap)
        println("number is " + number1)
        // returns None
        val numberNotExists: Option[Int] = getValueFromMap[Int]("Number2", myMap)
        println("number is " + numberNotExists)
        println
        // returns Some(Hello World)
        val greeting: Option[String] = getValueFromMap[String]("Greeting", myMap)
        println("greeting is " + greeting)
        // returns None
        val greetingDoesNotExists: Option[String] = getValueFromMap[String]("Greeting1", myMap)
        println("greeting is " + greetingDoesNotExists)
        println()
        // returns Some[Animal]
        val animal: Option[Animal] = getValueFromMap[Animal]("Animal", myMap)
        println("Animal is " + animal)
        // returns None
        val animalDoesNotExist: Option[Animal] = getValueFromMap[Animal]("Animal1", myMap)
        println("Animal is " + animalDoesNotExist)
        println
        // 现在这行代码没有问题，我们会得到None
        val greetingInt: Option[Int] = getValueFromMap[Int]("Greeting", myMap)
        // prints None
        println("greetingInt is " + greetingInt)
        // 没有 ClassCastException 并且返回 None
        val somevalue = greetingInt.map((x) => x + 5)
        // print None
        println(somevalue)
        println
        // other map with list
        val someMap = Map("Number" -> 1, "Greeting" -> "Hello World",
          "Animal" -> new Animal, "goodList" -> List("good", "better", "best"))
        // gets the list from map
        val goodList: Option[List[String]] = getValueFromMap[List[String]]("goodList", someMap)
        // prints the list
        println(goodList)
        println
        println("Now let us try to get bad list")
        // tries to get bad list from the map
        val badListNotExists: Option[List[String]] = getValueFromMap[List[String]]("badList", someMap)
        // prints None
        println(badListNotExists)

        /**
          * 结果为：
          * number is Some(1)
          * number is None
          *
          * greeting is Some(Hello World)
          * greeting is None
          *
          * Animal is Some(I am Animal)
          * Animal is None
          *
          * greetingInt is None
          * None
          *
          * Some(List(good, better, best))
          *
          * Now let us try to get bad list
          * None
          *
          * 现在，所有的问题都解决了，感谢Scala提供的ClassTag，帮我们在运行时获得我们传递的类型参数的信息。
          *
          * 结论
          * ClassTag对于我们在运行时获得类型参数T的信息是至关重要的，在多数情况下，它会提供许多便利。然而，它也有一些局限性，我们只能从ClassTag获取更高类型的信息，而不是参数类型更高的信息，这听起来有点绕，让我们看下面的例子。
          *
          * 如果我们使用下面的代码：
          *
          * val goodList:Option[List[Int]] = getValueFromMap[List[Int]]("goodList", someMap)
          * // prints the list
          * println(goodList)
          * 替换：
          *
          * val goodList:Option[List[String]] = getValueFromMap[List[String]]("goodList", someMap)
          * // prints the list
          * println(goodList)
          * 它仍然能正常工作，因为ClassTag仅仅在运行时提供更高的类型信息（List）不是参数类型的信息（Int、String）。这就是即使我们使用getValueFromMap[List[Int]]去接受key为goodList的value还能正常工作的原因。但是在有些情况下，我们需要在运行时获得参数的类型信息，这时候我们就需要使用TypeTag。
          *
          * 参考
          * How to Use the Scala ClassTag
          * https://link.zhihu.com/?target=https%3A//dzone.com/articles/scala-classtag-a-simple-use-case
          */
      }
    }
  }
}

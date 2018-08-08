package study.leetcode.Design

import java.util

import scala.collection.mutable

/**
  * Created by root on 2018-8-5.
  */
object MinStack {

  /** initialize your data structure here. */
  val stackData = mutable.Stack[Int]()
  val stackMin = mutable.Stack[Int]()

  /**
    * 节省空间。但取出时消耗时间
    *
    */

  /*def push(x: Int) {
    if (stackMin.isEmpty || x <= stackMin.head)
      stackMin.push(x)
    stackData.push(x)
  }

  def pop() = {
    if (stackData.nonEmpty) {
      val x = stackData.pop()
      if (x == stackMin.head)
        stackMin.pop()
      x
    }
  }

  def top(): Int = {
    stackData.head
  }

  def getMin(): Int = {
    stackMin.head
  }*/


  /**
    * 空间占用多，取出时间短
    *
    */


  def push(x: Int) {
    if (stackMin.isEmpty || stackMin.head > x)
      stackMin.push(x)
    else
      stackMin.push(stackMin.head)
    stackData.push(x)
  }

  def pop() = {
    stackMin.pop()
    stackData.pop()
  }

  def top(): Int = {
    stackData.head
  }

  def getMin(): Int = {
    stackMin.head
  }

}

/**
  * Your MinStack object will be instantiated and called as such:
  * var obj = new MinStack()
  * obj.push(x)
  * obj.pop()
  * var param_3 = obj.top()
  * var param_4 = obj.getMin()
  */
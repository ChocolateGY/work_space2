package study.interviewPrepare.Practice

import java.util.PriorityQueue

object HeapStackQueue {
  def main(args: Array[String]): Unit = {
    //    val arr = Array(3, 2, 1, 5, 6, 4)
    //    println(findKthLargest(arr, 2))
    val str = " 3+5 / 2 "
    val str2 = "3+2*2"
    println(calculate(str2))

  }

  /**
    * 最小栈
    * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
    *
    * push(x) -- 将元素 x 推入栈中。
    * pop() -- 删除栈顶的元素。
    * top() -- 获取栈顶元素。
    * getMin() -- 检索栈中的最小元素。
    * 示例:
    *
    * MinStack minStack = new MinStack();
    * minStack.push(-2);
    * minStack.push(0);
    * minStack.push(-3);
    * minStack.getMin();   --> 返回 -3.
    * minStack.pop();
    * minStack.top();      --> 返回 0.
    * minStack.getMin();   --> 返回 -2.
    */
  class MinStack() {

    /** initialize your data structure here. */

    import scala.collection.mutable.Stack

    //数据栈
    val data = Stack[Int]()
    //辅助栈
    val helper = Stack[Int]()

    def push(x: Int) {
      // 辅助栈在必要的时候才增加
      data.push(x)
      if (helper.isEmpty || helper.top >= x)
        helper.push(x)
    }

    def pop() {
      // 两个栈都得 pop
      if (data.nonEmpty) {
        val top = data.pop()
        if (top == helper.top)
          helper.pop()
      }
    }

    def top(): Int = {
      if (data.nonEmpty)
        data.top
      else
        throw new RuntimeException("stack is empty,the operator is illegal")
    }

    def getMin(): Int = {
      if (helper.nonEmpty) {
        helper.top
      } else
        throw new RuntimeException("stack is empty, the operator is illegal ")
    }
  }

  /**
    * 数组中的第K个最大元素
    * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    *
    * 示例 1:
    *
    * 输入: [3,2,1,5,6,4] 和 k = 2
    * 输出: 5
    * 示例 2:
    *
    * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
    * 输出: 4
    * 说明:
    *
    * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
    *
    *
    * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode/
    *
    */
  //优先队列。还可以使用快速排序原理
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    val heap = scala.collection.mutable.PriorityQueue[Int]()
    nums.foreach { n =>
      heap.enqueue(n)
    }
    for (i <- 1 to k - 1)
      println(heap.dequeue())
    heap.dequeue()
  }

  /**
    * 前 K 个高频元素
    * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
    *
    * 示例 1:
    *
    * 输入: nums = [1,1,1,2,2,3], k = 2
    * 输出: [1,2]
    * 示例 2:
    *
    * 输入: nums = [1], k = 1
    * 输出: [1]
    * 说明：
    *
    * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
    * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
    *
    * https://leetcode-cn.com/problems/top-k-frequent-elements/solution/leetcode-di-347-hao-wen-ti-qian-k-ge-gao-pin-yuan-/
    *
    * 步骤：1、建立map，映射次数。2、放入pq。3、取出k 个
    */
  def topKFrequent(nums: Array[Int], k: Int): List[Int] = {
    val map = scala.collection.mutable.Map[Int, Int]()
    nums.foreach {
      n =>
        map += n -> (map.getOrElse(n, 0) + 1)
    }
    val pq = scala.collection.mutable
      .PriorityQueue[Int]()(new Ordering[Int] {
        override def compare(x: Int, y: Int): Int = map(x) - map(y)
      })
    map.keysIterator.foreach {
      x =>
        pq.enqueue(x)
    }
    val list = scala.collection.mutable.ListBuffer[Int]()
    for (i <- 1 to k) {
      list += pq.dequeue()
    }
    list.toList
  }

  /**
    * 滑动窗口最大值
    * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    *
    * 返回滑动窗口中的最大值。
    *
    *
    *
    * 示例:
    *
    * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
    * 输出: [3,3,5,5,6,7]
    * 解释:
    *
    * 滑动窗口的位置                最大值
    * ---------------               -----
    * [1  3  -1] -3  5  3  6  7       3
    * 1 [3  -1  -3] 5  3  6  7       3
    * 1  3 [-1  -3  5] 3  6  7       5
    * 1  3  -1 [-3  5  3] 6  7       5
    * 1  3  -1  -3 [5  3  6] 7       6
    * 1  3  -1  -3  5 [3  6  7]      7
    *
    *
    * 提示：
    *
    * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
    *
    *
    *
    * 进阶：
    *
    * 你能在线性时间复杂度内解决此题吗？
    *
    *
    *
    *
    * https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetcode-3/
    * 使用堆，因为在最大堆中 heap[0] 永远是最大的元素。在大小为 k 的堆中插入一个元素消耗 log(k) 时间，因此算法的时间复杂度为 O(Nlog(k))。
    * 不符合，使用双向队列
    *
    * 还可以使用动态规划
    */
  def maxSlidingWindow(nums: Array[Int], k: Int): Array[Int] = {
    if (nums.nonEmpty || k == 1) {
      return nums
    }
    val list = new java.util.LinkedList[Int]()
    val res = new Array[Int](nums.length - k + 1)
    for (i <- nums.indices) {
      if (!list.isEmpty && list.peekFirst() == i - k) list.pollFirst()
      while (!list.isEmpty && nums(list.peekLast()) < nums(i)) list.pollLast()
      list.add(i)
      if ((i + 1) >= k) res(i - k + 1) = nums(list.peekFirst())
    }
    res
  }


  /**
    * 基本计算器 II
    * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
    *
    * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
    *
    * 示例 1:
    *
    * 输入: "3+2*2"
    * 输出: 7
    * 示例 2:
    *
    * 输入: " 3/2 "
    * 输出: 1
    * 示例 3:
    *
    * 输入: " 3+5 / 2 "
    * 输出: 5
    * 说明：
    *
    * 你可以假设所给定的表达式都是有效的。
    * 请不要使用内置的库函数 eval。
    *
    */
  def calculate(s: String): Int = {
    val stack = scala.collection.mutable.Stack[Int]()
    var op = '+'
    var num = 0
    //这里必须有索引
    for (i <- s.indices) {
      val ch = s(i)
      if (ch >= '0' && ch <= '9')
        num = num * 10 + (ch - '0')
      //这里必须判断这些条件
      if (((ch < '0' || ch > '9') && ch != ' ') || i == s.length - 1) {
        op match {
          case '+' => stack.push(num)
          case '-' => stack.push(-num)
          case '*' => stack.push(stack.pop() * num)
          case '/' => stack.push(stack.pop() / num)
          case _ =>
        }
        op = ch
        num = 0
      }
    }
    stack.sum
  }

}

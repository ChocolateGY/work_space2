package study.interview.byteDance.DataStructure


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


/**
  * 复杂度分析：
  *
  * 时间复杂度：O(1)O(1)，“出栈”、“入栈”、“查看栈顶元素”的操作不论数据规模多大，都只是有限个步骤，因此时间复杂度是：O(1)O(1)。
  * 空间复杂度：O(N)O(N)，这里 NN 是读出的数据的个数。
  *
  * 作者：liweiwei1419
  * 链接：https://leetcode-cn.com/problems/min-stack/solution/shi-yong-fu-zhu-zhan-tong-bu-he-bu-tong-bu-python-/
  * 来源：力扣（LeetCode）
  * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
  */
class MinStack {
  /** initialize your data structure here. */


  def fun1() = {

    import scala.collection.mutable.Stack

    //数据栈
    val data = Stack[Int]()
    //辅助栈
    val helper = Stack[Int]()

    // 思路 1：数据栈和辅助栈在任何时候都同步
    def push(x: Int) {
      // 数据栈和辅助栈一定会增加元素
      data.push(x)
      if (helper.isEmpty || helper.top >= x)
        helper.push(x)
      else
        helper.push(helper.top)
    }

    def pop() {
      // 两个栈都得 pop
      if (data.nonEmpty) {
        data.pop()
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
      }
      else
        throw new RuntimeException("stack is empty, the operator is illegal ")
    }
  }


  /**
    * // 思路 2：辅助栈和数据栈不同步
    * // 关键 1：辅助栈的元素空的时候，必须放入新进来的数
    * // 关键 2：新来的数小于或者等于辅助栈栈顶元素的时候，才放入（特别注意这里等于要考虑进去）
    * // 关键 3：出栈的时候，辅助栈的栈顶元素等于数据栈的栈顶元素，才出栈，即"出栈保持同步"就可以了
    *
    * 作者：liweiwei1419
    * 链接：https://leetcode-cn.com/problems/min-stack/solution/shi-yong-fu-zhu-zhan-tong-bu-he-bu-tong-bu-python-/
    * 来源：力扣（LeetCode）
    * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    */
  def fun2() = {

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
      }
      else
        throw new RuntimeException("stack is empty, the operator is illegal ")
    }
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
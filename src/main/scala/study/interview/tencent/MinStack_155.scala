package study.interview.tencent

/**
  * 155. 最小栈
  * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
  *
  * push(x) -- 将元素 x 推入栈中。
  * pop() -- 删除栈顶的元素。
  * top() -- 获取栈顶元素。
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
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/min-stack
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
class MinStack() {

  /** initialize your data structure here. */

  import scala.collection.mutable.Stack

  val data = Stack[Int]()
  val helper = Stack[Int]()

  def push(x: Int) {
    data.push(x)
    if (helper.isEmpty || helper.top >= x)
      helper.push(x)
    else
      helper.push(helper.top)
  }

  def pop() {
    if (data.nonEmpty) {
      data.pop()
      helper.pop()
    }
  }

  def top(): Int = {
    if (data.nonEmpty)
      data.top
    else
      throw new RuntimeException("stack is empty")
  }

  def getMin(): Int = {
    if (helper.nonEmpty)
      helper.top
    else
      throw new RuntimeException("stack is empty")
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

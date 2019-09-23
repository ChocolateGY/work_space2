package study.interview.byteDance.ExtendPractice


/**
  * UTF-8 编码验证
  * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
  *
  * 对于 1 字节的字符，字节的第一位设为0，后面7位为这个符号的unicode码。
  * 对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为0，后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。
  * 这是 UTF-8 编码的工作方式：
  *
  *    Char. number range  |        UTF-8 octet sequence
  * (hexadecimal)    |              (binary)
  * --------------------+---------------------------------------------
  * 0000 0000-0000 007F | 0xxxxxxx
  * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
  * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
  * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
  * 给定一个表示数据的整数数组，返回它是否为有效的 utf-8 编码。
  *
  * 注意:
  * 输入是整数数组。只有每个整数的最低 8 个有效位用来存储数据。这意味着每个整数只表示 1 字节的数据。
  *
  * 示例 1:
  *
  * data = [197, 130, 1], 表示 8 位的序列: 11000101 10000010 00000001.
  *
  * 返回 true 。
  * 这是有效的 utf-8 编码，为一个2字节字符，跟着一个1字节字符。
  * 示例 2:
  *
  * data = [235, 140, 4], 表示 8 位的序列: 11101011 10001100 00000100.
  *
  * 返回 false 。
  * 前 3 位都是 1 ，第 4 位为 0 表示它是一个3字节字符。
  * 下一个字节是开头为 10 的延续字节，这是正确的。
  * 但第二个延续字节不以 10 开头，所以是不符合规则的。
  */
object ValidUtf8 {
  def main(args: Array[String]): Unit = {
    val data1 = Array(197, 130, 1)
    val data2 = Array(235, 140, 4)
    val data3 = Array(255)
    println(validUtf82(data1))
  }


  /**
    *
    * 字符串操作
    * 复杂度分析
    *
    * 时间复杂度： O(N)O(N)
    * 我们需要处理数组中的每一个整数，对每个整数提取它的二进制表示字符串。这个过程的复杂度为 O(N)O(N)，其中 NN 是数组大小。
    *
    * 空间复杂度： O(N)O(N)
    * 对于每一个整数都需要创建一个二进制表示字符串。
    *
    * 作者：LeetCode
    * 链接：https://leetcode-cn.com/problems/utf-8-validation/solution/utf-8-bian-ma-yan-zheng-by-leetcode/
    * 来源：力扣（LeetCode）
    * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    *
    * @param data
    * @return
    */
  def validUtf8(data: Array[Int]): Boolean = {
    var num = 0
    var flag = true
    for (x <- data if flag) {
      var str = x.toBinaryString
      if (str.length < 8)
        str = "0" * (8 - str.length) + str
      else
        str = str.substring(str.length - 8, str.length)
      if (num == 0) {
        if (str.startsWith("0")) {}
        else if (str.startsWith("110")) num = 1
        else if (str.startsWith("1110")) num = 2
        else if (str.startsWith("11110")) num = 3
        else
          flag = false
      } else {
        if (str.startsWith("10"))
          num -= 1
        else
          flag = false
      }
    }
    if (num != 0)
      flag = false
    flag
  }


  /**
    * 位操作
    * 前一个方法就可以很好的解决问题了，但是字符串转换的一些操作会花很多时间，我们有更优雅的做法，那就是用位操作。
    *
    * 首先我们看一下一个整数表示的字节中有哪些部分是需要处理的。
    *
    * 如果在处理一个 UTF-8 字符的开始，我们需要提取一个字节的前 NN 比特，其中 NN 不会超过 4，之后的比特就不需要处理了。
    * 如果是在处理一个 UTF-8 字符的过程中，我们只需要检查前两位是不是 10 就可以了。
    * 我们来看一下怎么用位操作来完成以上两个任务。
    *
    * mask = 1 << 7
    * while mask & num:
    * n_bytes += 1
    * mask = mask >> 1
    * 首先我们创建了一个掩码，其值为 10000000。接下来会用这个掩码和整数做 逻辑与 操作，这样就可以知道有多少个 1 了。（记住，整数可能非常大，但我们只需要处理前 8 比特）
    *
    * 检查前两个比特是不是 10，我们可以用下面这两个掩码。
    *
    * mask1 = 1 << 7
    * mask2 = 1 << 6
    *
    * if not (num & mask1 and not (num & mask2)):
    * return False
    * 上面的代码可以高效地判断前两个比特是不是 10。如果不是，直接返回 False。
    *
    * 来看一下具体实现吧。
    *
    * 作者：LeetCode
    * 链接：https://leetcode-cn.com/problems/utf-8-validation/solution/utf-8-bian-ma-yan-zheng-by-leetcode/
    * 来源：力扣（LeetCode）
    * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    *
    * @param data
    * @return
    */
  def validUtf82(data: Array[Int]): Boolean = {
    var num = 0
    var flag = true
    for (x <- data if flag) {

      if (num == 0) {
        var mask = 1 << 7
        while ((x & mask) != 0) {
          num += 1
          mask >>= 1
        }
        if (num > 4 || num == 1)
          flag = false

        //这里应该是continue，改为+1 来抵消循环最后的-1
        if(num == 0)
          num +=1
      } else {
        val mask1 = 1 << 7
        val mask2 = 1 << 6
        if (!((x & mask1) != 0 && (x & mask2) == 0))
          flag = false
      }
      num -= 1
    }
    num == 0 && flag
  }
}

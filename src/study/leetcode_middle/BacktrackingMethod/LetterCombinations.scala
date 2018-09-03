package study.leetcode_middle.BacktrackingMethod

/**
  * Created by root on 2018-8-29.
  *
  * 电话号码的字母组合
  *
  * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
  * *
  * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
  * *
  *
  *
  * 示例:
  * *
  * 输入："23"
  * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
  * 说明:
  * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
  */
object LetterCombinations {
  def letterCombinations(digits: String): List[String] = {
    val table = Map[Char, Array[Char]](
      '2' -> Array('a', 'b', 'c'),
      '3' -> Array('d', 'e', 'f'),
      '4' -> Array('g', 'h', 'i'),
      '5' -> Array('j', 'k', 'l'),
      '6' -> Array('m', 'n', 'o'),
      '7' -> Array('p', 'q', 'r', 's'),
      '8' -> Array('t', 'u', 'v'),
      '9' -> Array('w', 'x', 'y', 'z')
    )
    var result = collection.mutable.ArrayBuffer[String]()
    for (ch <- digits) {
      val arr = table.getOrElse(ch, Array[Char]())
      if (arr.nonEmpty) {
        if (result.isEmpty) {
          result ++= arr.map(_.toString)
        } else {
          result = result.flatMap {
            x =>
              arr.map(x + _)
          }
        }
      }
    }
    result.toList
  }

  def main(args: Array[String]): Unit = {
  }

}

package study.leetcode_middle.ArrayAndString

/**
  * Created by root on 2018-8-15.
  * 字谜分组
  * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
  * *
  * 示例:
  * *
  * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
  * 输出:
  * [
  * ["ate","eat","tea"],
  * ["nat","tan"],
  * ["bat"]
  * ]
  * 说明：
  * *
  * 所有输入均为小写字母。
  * 不考虑答案输出的顺序。
  */
object GroupAnagrams {
  /**
    * 解答错误，原因未找到
    */
  def groupAnagrams(strs: Array[String]): List[List[String]] = {
    val arr = collection.mutable.ArrayBuffer[List[String]]()
    if (strs.length == 0)
      arr.toList
    else {
      arr += List(strs(0))
      if (strs.length == 1)
        arr.toList
      else {
        for (i <- 1 until strs.length) {
          var flag = true
          for (l <- arr.indices if flag) {
            if (arr(l).head.toSet == strs(i).toSet) {
              arr(l) = arr(l) ++ List(strs(i))
              flag = false
            }
          }
          if (flag) {
            arr += List(strs(i))
          }
        }
        arr.toList
      }
    }

  }

  /**
    * 前面我们遇到过简单的 字母异位词的判断，那道题我们是采用位图法按字符个数比较。显然这道题不适合这么做，不然恐怕很轻松就TLE了。
    * *
    * 因此我们换一种想法，我们以某种方式将单词全部按照升序排列，这样字母异位词就会变成相同的单词，这时候我们只要将相同的单词记录到一组不就可以了么？一个简单的map
    */
  def groupAnagrams2(strs: Array[String]): List[List[String]] = {
    if (strs != null && strs.nonEmpty) {
      val map = collection.mutable.Map[String, collection.mutable.ArrayBuffer[String]]()
      for (s <- strs) {
        val s_temp = s.sorted

        if (map.contains(s_temp))
          map(s_temp) += s
        else
          map += (s_temp -> collection.mutable.ArrayBuffer[String](s))
      }
      map.values.map(_.toList).toList
    } else
      List[List[String]]()
  }
}

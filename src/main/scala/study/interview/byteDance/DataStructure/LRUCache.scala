package study.interview.byteDance.DataStructure


/**
  * LRU缓存机制
  * Least Recently Used
  * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
  *
  * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
  * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
  *
  * 进阶:
  *
  * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
  *
  * 示例:
  *
  * LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
  *
  * cache.put(1, 1);
  * cache.put(2, 2);
  * cache.get(1);       // 返回  1
  * cache.put(3, 3);    // 该操作会使得密钥 2 作废
  * cache.get(2);       // 返回 -1 (未找到)
  * cache.put(4, 4);    // 该操作会使得密钥 1 作废
  * cache.get(1);       // 返回 -1 (未找到)
  * cache.get(3);       // 返回  3
  * cache.get(4);       // 返回  4
  * @param _capacity
  *
  * https://leetcode-cn.com/problems/lru-cache/solution/lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/
  */


class LRUCache(_capacity: Int) {
  /**
    * hash表，用来O(1)时间来获取元素
    */
  private val map = scala.collection.mutable.HashMap[Int, Node]()
  //双向列表
  private val cache: DoubleList = new DoubleList()
  //容量
  private var cap = _capacity

  //获取key
  def get(key: Int): Int = {
    val value = map.get(key)
    if (value.isEmpty)
      -1
    else {
      //如果有值，就再存放一次
      put(key, value.get.v)
      value.get.v
    }
  }

  //存值
  def put(key: Int, value: Int) {
    //新键节点
    val x = new Node(key, value)
    //获取当前节点，查看是否有值
    val curNode = map.get(key)
    if (curNode.nonEmpty) {
      //如果有值，则先从链表中删除，再从头节点加入
      cache.remove(curNode.get)
      cache.addFirst(x)
      //将值放入hash表
      map.put(key, x)
    } else {
      //如果没有值，先判断链表是否已满，满的话先删掉末尾
      if (cap == cache.length) {
        val last = cache.removeLast()
        map.remove(last.k)
      }
      //添加元素到头部
      cache.addFirst(x)
      //将值放入hash表
      map.put(key, x)
    }
  }

}

/**
  * 节点
  *
  * @param k
  * @param v
  */
class Node(var k: Int, var v: Int) {
  var next, prev: Node = null
}

/**
  * 双向链表，存放记录数据
  *
  * @param head
  * @param tail
  * @param size
  */
class DoubleList(head: Node = new Node(0, 0), tail: Node = new Node(0, 0), private var size: Int = 0) {
  //  private var head, tail: Node = new Node(0, 0)
  //  private var size: Int = 0
  head.next = tail
  tail.prev = head

  //链表头部添加x
  def addFirst(x: Node) {
    x.next = head.next
    x.prev = head
    head.next.prev = x
    head.next = x
    size += 1
  }

  //删除链表中的x节点（x节点一定存在）
  def remove(x: Node) {
    x.prev.next = x.next
    x.next.prev = x.prev
    size -= 1
  }

  //删除链表中最后一个节点，并返回该节点

  def removeLast(): Node = {
    if (tail.prev == head)
      return null
    val last = tail.prev
    remove(last)
    last
  }

  def length = size
}


/**
  * Your LRUCache object will be instantiated and called as such:
  * var obj = new LRUCache(capacity)
  * var param_1 = obj.get(key)
  * obj.put(key,value)
  */

/**
  * 利用java的hash链表
  *
  * @param _capacity
  */
class LRUCache2(_capacity: Int) extends java.util.LinkedHashMap[Any, Integer](_capacity, 0.75F, true) {
  override def removeEldestEntry(eldest: java.util.Map.Entry[Any, Integer]): Boolean = {
    size() > _capacity
  }

  override def get(key: Any): Integer = super.getOrDefault(key, -1)

  override def put(key: Any, value: Integer): Integer = super.put(key, value)
}

/**
  * scala 类编写
  *
  * @param _capacity
  */
class LRUCache3(_capacity: Int) {
  val map = new scala.collection.mutable.LinkedHashMap[Int, Int]()

  def get(key: Int): Int = {
    val res = map.get(key)
    if (res.nonEmpty) {
      map -= key
      map += (key -> res.get)
      res.get
    } else
      -1
  }

  def put(key: Int, value: Int) {
    val res = map.get(key)
    if (res.nonEmpty)
      map -= key
    if (map.size == _capacity) {
      map.remove(map.head._1)
    }
    map += (key -> value)
  }
}
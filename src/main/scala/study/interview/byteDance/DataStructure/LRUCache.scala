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
  */
class LRUCache(_capacity: Int) {
  private val map = scala.collection.mutable.HashMap[Int, Node]()
  private val cache: DoubleList = new DoubleList()
  private var cap = _capacity

  def get(key: Int): Int = {
    val value = map.get(key)
    if (value.isEmpty)
      -1
    else {
      put(key, value.get.v)
      value.get.v
    }
  }

  def put(key: Int, value: Int) {
    val x = new Node(key, value)
    val curNode = map.get(key)
    if (curNode.nonEmpty) {
      cache.remove(curNode.get)
      cache.addFirst(x)
      map.put(key, x)
    } else {
      if (cap == cache.length) {
        val last = cache.removeLast()
        map.remove(last.k)
      }
      cache.addFirst(x)
      map.put(key, x)
    }
  }

}

class Node(var k: Int, var v: Int) {
  var next, prev: Node = null
}

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
  * class LRUCache(_capacity: Int) extends java.util.LinkedHashMap[Any,Integer](_capacity,0.75F,true) {
  * override def removeEldestEntry(eldest: java.util.Map.Entry[Any, Integer]): Boolean = {
  * size() > _capacity
  * }
  *
  * override def get(key: Any): Integer = super.getOrDefault(key,-1)
  *
  * override def put(key: Any, value: Integer): Integer = super.put(key, value)
  * }
  */
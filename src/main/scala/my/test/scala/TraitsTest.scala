package my.test.scala

import scala.collection.mutable.ArrayBuffer

/**
 * Project: ScalaTestApp
 * Author:  Igor Levoniuk
 * Mail:    igor.levoniuk@gmail.com
 * Date:    8/25/13
 */
object TraitsTest extends App {

  trait NotAQueue

  trait IntQueue {
    def put(v: Int): Unit
    def get(): Int
  }

  class MyQueue extends IntQueue {
    private val elems = new ArrayBuffer[Int]

    def put(v: Int): Unit = elems += v
    def get(): Int = elems.remove(0)

    override def toString = elems.toString()
  }

  trait Filtering extends IntQueue {
    val predicate: Int => Boolean =
      (x: Int) => x > 0

    abstract override def put(v: Int): Unit = if (predicate(v)) super.put(v)
  }

  trait Doubling extends IntQueue {
    abstract override def put(v: Int): Unit = super.put(v * 2)
  }

  trait Incrementing extends IntQueue {
    abstract override def put(v: Int): Unit = super.put(v + 1)
  }

  println("MyQueue with Incrementing with Filtering{override val predicate = (x: Int) => x < 0}:")
  println()
  val q1 = new MyQueue with Incrementing with Filtering{override val predicate = (x: Int) => x < 0}
  q1.put(-1)
  q1.put(0)
  q1.put(1)
  println(q1)

  println("MyQueue with Filtering with Incrementing:")
  println()
  val q2 = new MyQueue with Filtering with Incrementing
  q2.put(-1)
  q2.put(0)
  q2.put(1)
  println(q2)

  println("class MyOwnIntQueue extends MyQueue with Incrementing (should be no inc):")
  println()
  /**
   * Following statement better consider distributed as follows: (class MyOwnIntQueue) extends (MyQueue with Incrementing)
   */
  class MyOwnIntQueue extends MyQueue with Incrementing {
    override def put(v: Int): Unit = super.put(v - 1) //decrement should eliminate increment
  }
  val regularQueue = new MyOwnIntQueue
  regularQueue.put(-1)
  regularQueue.put(0)
  regularQueue.put(1)
  println(regularQueue)


}

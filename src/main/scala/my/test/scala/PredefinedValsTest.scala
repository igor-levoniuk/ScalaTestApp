package my.test.scala

/**
 * Project: ScalaTestApp
 * Author:  Igor Levoniuk
 * Mail:    igor.levoniuk@gmail.com
 * Date:    8/8/13
 */
object PredefinedValsTest extends App {
  trait Foo { val bar: AnyRef}

  val baz = new { val bar = this } with Foo

  println(baz)
  println(baz.bar)
  println(this)
}

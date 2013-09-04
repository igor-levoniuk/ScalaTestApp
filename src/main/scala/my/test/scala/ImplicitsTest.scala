package my.test.scala

/**
 * Project: ScalaTestApp
 * Author:  Igor Levoniuk
 * Mail:    igor.levoniuk@gmail.com
 * Date:    8/30/13
 */
object ImplicitsTest extends App {

  /*implicit*/ class MyRichObject(val v: Any) {
    def doStuff { println("Done doing stuff...")}
    def weirdToString { println("wrapped " + v.toString)}
    def in(args: Any*): Boolean = args exists (v == _) // same as x => v == x
    def badOneOf(args: Any*): Boolean = args exists (_ == v)
  }

  implicit def enrichObject(a: Any) = new MyRichObject(a)

  class MyDumbClass

  val a: Int = 1;
  val b: String = "bar"
  val dummy = new MyDumbClass


  a.doStuff
  a.weirdToString

  b.doStuff
  b.weirdToString

  dummy.doStuff
  dummy.weirdToString


  b in ("foo", "bar", "baz")
  b badOneOf  (null, "foo", "baz", /*oops*/ null )
  val nullValue: Null = null


}

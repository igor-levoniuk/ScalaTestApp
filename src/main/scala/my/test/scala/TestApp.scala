package my.test.scala

/**
 * Created with IntelliJ IDEA.
 * User: Igor Levoniuk
 * Date: 7/4/13
 */
object TestApp {

  def foo: Unit = {
    val bar = "hello"

    def baz: Unit = {
      println(bar)
    }

    baz
  }

  def main(args: Array[String]) {
     foo

    val list = List[(String, String)](
      "foo" -> "foo",
      "foo" -> "bar",
      "foo" -> "baz")

    println(list.groupBy(_._1).mapValues(_.map(_._2)))
  }
}



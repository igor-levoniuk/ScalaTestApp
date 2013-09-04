package my.test.scala

/**
 * Project: ScalaTestApp
 * Author:  Igor Levoniuk
 * Mail:    igor.levoniuk@gmail.com
 * Date:    8/11/13
 */
object ExtractorObjectTest extends App {
  implicit class MegaInt(val num: Int) {
    def even = (0 == (0x1 & num))
    def odd = !even
  }

  object Even {
    def unapply(num: Int) = num.even
  }

  object Odd {
    def unapply(num: Int) = num.odd
  }

  def test(num: Int) {
    num match {
      case Even() => println("It's even")
      case Odd() => println("It's odd")
    }
  }

  test(1)
  test(2)
  test(3)
  test(4)
  test(5)
}

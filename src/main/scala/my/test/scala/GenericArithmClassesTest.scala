package my.test.scala

/**
 * Project: ScalaTestApp
 * Author:  Igor Levoniuk
 * Mail:    igor.levoniuk@gmail.com
 * Date:    9/18/13
 */

object GenericArithmClassesTest extends App {
  val f1 = Foo(1, 2, 3)
  val f2 = Foo(3, 2, 1)

  println(f1 + f2)
  println(f1 multBy 0)
  println(f1 multBy 2)
  println(f1 + f1.one)
  println(f1 + f1.zero)

  val b1 = Bar(1, 2)
  val b2 = Bar(2, 1)

  println(b1 + b2)
  println(b1 multBy 0)
  println(b1 multBy 2)
  println(b1 + b1.one)
  println(b1 + b1.zero)

  case class Foo(a: Int, b: Int, c: Int) extends MathOps[Foo, Int] {
    val fields: Seq[Int] = Seq(a, b, c)
    def fromFields(fields: Seq[Int]): Foo = Foo(fields(0), fields(1), fields(2))
  }

  case class Bar(a: BigDecimal, b: BigDecimal) extends MathOps[Bar, BigDecimal] {
    val fields: Seq[BigDecimal] = Seq(a, b)
    def fromFields(fields: Seq[BigDecimal]): Bar = Bar(fields(0), fields(1))
  }

  trait MathOps[T, N] {
    val fields: Seq[N]
    def fromFields(fields: Seq[N]): T

    def +(that: MathOps[T, N])(implicit num: Numeric[N]): T =
      fromFields {
        for (i <- this.fields.indices) yield num.plus(this.fields(i), that.fields(i))
      }

    def multBy(k: N)(implicit num: Numeric[N]) =
      fromFields {
        this.fields.map(x => num.times(x, k))
      }

    def incBy(v: N)(implicit num: Numeric[N]) =
      fromFields {
        this.fields.map(x => num.plus(x, v))
      }

    def zero(implicit num: Numeric[N]) =
      fromFields {
        for (i <- (1 to fields.size)) yield num.zero
      }

    def one(implicit num: Numeric[N]) =
      fromFields {
        for (i <- (1 to fields.size)) yield num.one
      }
  }

}


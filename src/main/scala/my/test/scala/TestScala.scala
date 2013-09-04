package my.test.scala

/**
 * Project: ScalaTestApp
 * Author:  Igor Levoniuk
 * Mail:    igor.levoniuk@gmail.com
 * Date:    8/12/13
 */
object TestScala extends App {

  trait BaseTransferedType

  case class TransferedString(val s: String) extends BaseTransferedType

  case class TransferedInt(val n: Int) extends BaseTransferedType

  trait Handler {
    type TypeToHandle <: BaseTransferedType
    def handle(p: TypeToHandle): TypeToHandle
  }

  class StringHandler extends Handler {
    type TypeToHandle = TransferedString
    def handle(p: TransferedString): TransferedString = TransferedString(p.s + "1")
  }

  class IntHandler extends Handler {
    type TypeToHandle = TransferedInt
    def handle(p: TransferedInt): TransferedInt = TransferedInt(p.n + 1)
  }

  class HandlerFactory {
    def createHandlerFor(o: Any): Handler = {
      o.getClass match {
        case _: Class[Int] => new IntHandler()
        case _: Class[String] => new StringHandler()
        case _ => throw new RuntimeException("No handler for type " + o.getClass)
      }
    }
  }

/*  val handlerFactory = new HandlerFactory
  val arr = Array(TransferedString("foo"), TransferedInt(1), TransferedString("bar"), TransferedInt(4))
  val handledArr = arr.map {
    e => handlerFactory.createHandlerFor(e).handle(e)
  }*/


}

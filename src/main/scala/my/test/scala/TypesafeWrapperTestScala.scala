package my.test.scala

/**
 * Project: ScalaTestApp
 * Author:  Igor Levoniuk
 * Mail:    igor.levoniuk@gmail.com
 * Date:    8/12/13
 */
object TypesafeWrapperTestScala extends App {

  trait BaseTransferredType

  case class TransferredString(val s: String) extends BaseTransferredType

  case class TransferredInt(val n: Int) extends BaseTransferredType

  trait Handler {
    type TypeToHandle <: BaseTransferredType
    def handle(p: TypeToHandle): TypeToHandle
  }

  class StringHandler extends Handler {
    type TypeToHandle = TransferredString
    def handle(p: TransferredString): TransferredString = TransferredString(p.s + "1")
  }

  class IntHandler extends Handler {
    type TypeToHandle = TransferredInt
    def handle(p: TransferredInt): TransferredInt = TransferredInt(p.n + 1)
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

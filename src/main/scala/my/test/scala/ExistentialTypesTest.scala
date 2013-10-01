package main.scala.my.test.scala

/**
 * Project: ScalaTestApp
 * Author:  Igor Levoniuk
 * Mail:    igor.levoniuk@gmail.com
 * Date:    10/2/13
 */
object ExistentialTypesTest {

  trait VM[A] {
    def compile(source: String): A
    def run(bytecode: A): Unit
  }

  def runAllCompilers[B](vms: Seq[VM[B]], source: String) {
    for (vm <- vms) {
      val bytecode = vm compile source
      vm run bytecode
    }
  }
}

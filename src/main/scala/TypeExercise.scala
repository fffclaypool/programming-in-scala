// quote:
//   https://scala-text.github.io/scala_text/implicit.html
//   https://scala-text.github.io/scala_text/introduction-to-typeclass.html

trait Additive[A] {
  def plus(a: A, b: A): A
  def zero: A
}

object TypeExercise {
  implicit object StringAdditive extends Additive[String] {
    def plus(a: String, b: String): String = a + b
    def zero: String = ""
  }
  implicit object IntAdditive extends Additive[Int] {
    def plus(a: Int, b: Int): Int = a + b
    def zero: Int = 0
  }
  def sum[A](l: List[A])(implicit m: Additive[A]) =
    l.foldLeft(m.zero)((x, y) => m.plus(x, y))

  def main(args: Array[String]): Unit = {
    sum(List(1, 2, 3))
    sum(List("A", "B", "C"))
  }
}

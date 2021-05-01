trait Philosophical {
  def philosophize(): Unit =
    println("I consume memory, therefore I am")

  def say(): Unit =
    println("Hello World")
}

class Frog extends Philosophical {
  // toStringはAnyクラスで定義される
  // ScalaではすべてのクラスがAnyを継承している
  override def toString = "green"
  override def say() =
    println("It ain't easy being " + toString + "!")
}

object Trait {
  def main(args: Array[String]): Unit = {
    // frog: Frog = green
    val frog = new Frog

    // I consume memory, therefore I am
    frog.philosophize()

    // phil: Philosophical = green
    val phil: Philosophical = frog

    // I consume memory, therefore I am
    phil.philosophize()

    // It ain't easy being green!
    phil.say()
  }
}

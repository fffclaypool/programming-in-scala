// quote:
//   https://scala-text.github.io/scala_text/type-parameter.html

/*
  クラスは0個以上の型をパラメータとして取ることができる。これは、クラスを作る時点では何の型か特定
  できない場合（たとえば、コレクションクラスの要素の型）を表したい時に役に立つ
  型パラメータを入れたクラス定義の文法は次のようになる

    class <クラス名>[<型パラメータ1>, <型パラメータ2>, ...](<クラス引数>) {
      (<フィールド定義>|<メソッド定義>)*
    }

  型パラメータの並びにはそれぞれ好きな名前を付け、クラス定義の中で使うことができる
  Scala言語では最初から順に、 A、B、…と命名する慣習がある
 */

class Cell[A](var value: A) {
  def put(newValue: A): Unit =
    value = newValue

  def get(): A = value
}

object ObjectA {
  def main(args: Array[String]): Unit = {
    val cell = new Cell[Int](1)
    cell.put(2)
    cell.get() // res1: Int = 2

    // cell.put("something")
    /*
    src/main/scala/Class.scala:30: error: type mismatch;
     found   : String("something")
     required: Int
        cell.put("something")
                 ^
     */
  }
}

class Pair[A, B](val a: A, val b: B) {
  override def toString(): String = "(" + a + "," + b + ")"
}

object ObjectB {
  def divideX(m: Int, n: Int): Pair[Int, Int] = new Pair[Int, Int](m / n, m % n)
  def divideY(m: Int, n: Int): Pair[Int, Int] = new Pair(m / n, m % n)
  def concat(x: String, y: String): Pair[String, String] = new Pair(x, y)
  def main(args: Array[String]): Unit = {
    divideX(7, 3)            // res0: Pair[Int, Int] = (2,1)
    divideY(7, 3)            // res1: Pair[Int, Int] = (2,1)
    concat("apple", "grape") // res2: Pair[String, String] = (apple,grape)
  }
}

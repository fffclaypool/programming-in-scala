// quote:
//   https://ym.hatenadiary.jp/entry/2016/03/30/092337
//   https://qiita.com/kuropp/items/0be46b2702a9a1081785

object SomeOp {
  def main(args: Array[String]): Unit = {
    exerciseFilter()
    exerciseCollect()
    exerciseFlatten()
    exerciseMap()
    exerciseSome()
  }

  def exerciseFilter(): Unit = {
    val l = List(3, 12, 5, 8, 9)
    val result = l.filter(_ > 8) // List(12, 9)
  }

  def exerciseCollect(): Unit = {
    /*
      collectメソッドを使用することで、Mapから条件に該当する要素を抽出して
      それぞれの要素に対して処理を適用した結果のコレクションを取得する
    */

    val map = Map(
      "apple" -> 150,
      "banana" -> 210,
      "orange" -> 99,
      "strawberry" -> 110,
      "grape" -> 150,
    )

    // HashMap(orange -> 0.9166666666666666, apple -> 1.3888888888888888)
    val resultA = map.collect {
      case (x, y) if (x == "apple" || x == "orange") =>
        (x, y / 108.0)
    }

    /*
      Map内の要素から条件に該当する最初の要素に対して処理を適用した結果を取得するには
      collectFirstメソッドを使用する
     */

    // Some(1.3888888888888888)
    val resultB = map.collectFirst {
      case ("apple", v) => v / 108.0
    }
  }

  def exerciseFlatten(): Unit = {
    List(Set(1, 2, 3), Set(1, 2, 3)).flatten // List(1, 2, 3, 1, 2, 3)
  }

  def exerciseMap(): Unit = {
    val msgOpt: Option[String] = Some("a")

    // Some("a")
    msgOpt.map(a => a) == msgOpt.flatMap(a => Some(a)) // true

    // error
    // msgOpt.flatMap(a => a)
  }

  def exerciseSome(): Unit = {
    val opts: Seq[Option[Int]] = Seq(Some(2), None, Some(3))

    opts.filter(_ != None) // List(Some(2), Some(3))

    // List(2, 3)
    opts.filter(_ != None).map(_.get)
    for (Some(x) <- opts) yield x
    opts.collect{ case Some(x) => x }
    opts.flatten
  }
}

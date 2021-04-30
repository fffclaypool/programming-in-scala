// quote:
//   https://namu-r21.hatenablog.com/entry/2019/07/21/211121
//   https://daybydaypg.com/2017/10/30/post-598/

object ListOp {
  def main(args: Array[String]): Unit = {
    foldLeftRight()
    foldLeftExcersise()
  }

  def foldLeftRight(): Int = {
    /*
      res0: Int = 6
      初期値を0としてListの先頭から加算を繰り返す
      そしてリストの要素を一つの要素にたたみ込んでいく
                 +
                / \
               +   3
              / \
             +   2
            / \
           0   1

      0 + 1 → 1 + 2 → 3 + 3
     */

    List(1, 2, 3).foldLeft(0)((x ,y) => (x + y))

    /*
      res0: Int = 6
      初期値とリストの右から要素を取り出し、関数を適用する
      その結果と1つ左の要素を取り出し関数を適用、という風に順番にたたみ込んで行く。

                 +
                / \
               1   +
                  / \
                 2   +
                    / \
                   3   0

      3 + 0 → 2 + 3 → 1 + 5
     */

    List(1, 2, 3).foldRight(0)((x ,y) => (x + y))
  }

  def foldLeftExcersise(): Unit = {
    val l = List("Anubis", "Isis", "Osiris", "Geb", "Atum")
    val (a, o) = l.foldLeft((List.empty[String], List.empty[String]))((x, y) =>
      y.startsWith("A") match {
        case true => ((x._1 :+ y), x._2)
        case _ => (x._1, (x._2 :+ y))
      }
    )

    /*
      process:
        (List(Anubis),List())
        (List(Anubis),List(Isis))
        (List(Anubis),List(Isis, Osiris))
        (List(Anubis),List(Isis, Osiris, Geb))
        (List(Anubis, Atum),List(Isis, Osiris, Geb))

      reslut:
        a: List(Anubis, Atum)
        o: List(Isis, Osiris, Geb)
     */
  }
}

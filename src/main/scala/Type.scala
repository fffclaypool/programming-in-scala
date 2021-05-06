// quote:
//   https://kazchimo.com/2021/03/29/scala-higher-kinded-type/
//   https://qiita.com/kinshotomoya/items/4a8b52f681a2ff81b855
//   https://qiita.com/kinshotomoya/items/33ea01c7c82d8f208d7e
//   https://scala-text.github.io/scala_text/typeclass.html

import scala.reflect.runtime.universe._


object Type {
  def main(args: Array[String]) = {
    Value()
    ProperType()
    FirstOrderType()
    SecondOrderType()
  }

  def Value(): Unit = {
    /*
     valueとは生データのこと
     抽象化においては最も低い段階にあり、非常に扱いやすい概念である
    */
    val sampleString = "Daniel"
    val sampleInt = 1
    val sampleTuple = (1, 2)
    val sampleList = List(1, 2)
    val sampleArray = Array("apple", "orange", "grape")
    val sampleMap = Map("Japan" -> 1, "France" -> 2, "America" -> 3)
  }

  def ProperType(): Unit = {
    /*
      String、List[Int]などをproper typeという
      proper typeとはvalueよりは上位の概念である
      型はvalueを生み出すためにインスタンス化でき、逆にvalueは型の特定のインスタンスである
      Stringは思いつく限りあらゆるstringリテラルを生み出すことができる
        ex) “a”, “ab”, “algorithmic service operations”など
    */
    val sampleString: String = "Daniel"
    val sampleInt: Int = 1
    val sampleTuple: (Int, Int) = (1, 2)
    val sampleList: List[Int] = List(1, 2)
    val sampleArray: Array[String] = Array("apple", "orange", "grape")
    val sampleMap: Map[String, Int] = Map("Japan" -> 1, "France" -> 2, "America" -> 3)
  }

  def FirstOrderType(): Unit = {
    /*
      List, Option, Mapのような型をfirst-order typeという
      first-order type型は、1つ以上のスロットを持つ（List, Optionなら1つ、Mapなら2つ）
        ex) List[_] / Option[_]/ Map[_, _]

      これらをtype constructorという
      type constructorはproper typeを受け取り、List[int]やMap[String, Int]などの
      proper typeを生み出す
     */
    val sampleTuple: (_, _) = (1, 2)
    val sampleList: List[_] = List(1, 2)
    val sampleArray: Array[_] = Array("apple", "orange", "grape")
    val sampleMap: Map[_, _] = Map("Japan" -> 1, "France" -> 2, "America" -> 3)
  }

  def SecondOrderType(): Unit = {
    new SecondOrderType[List] {}
    new SecondOrderType[Array] {}
  }
}

/*
  F[_]は、1つのスロットを持つ型を抽象化したものである
  これによって、複数の型のメソッドを共通化できる
 */
trait SecondOrderType[F[_]]

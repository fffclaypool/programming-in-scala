// quote:
//   https://scala-text.github.io/scala_text/trait.html

/*
  Scalaのトレイトは、クラスからコンストラクタを定義する機能を抜いたようなもの
    (newを使用して直接インスタンス化できないから、コンストラクタは定義できないという理解)　　
    コンストラクタ: オブジェクト指向のプログラミング言語で新たなオブジェクトを生成する際に
      呼び出されて内容の初期化などを行なう関数あるいはメソッドのこと

  おおまかには次のように定義することができる

　　  trait <トレイト名> {
　　    (<フィールド定義> | <メソッド定義>)*
　　  }

  フィールド定義とメソッド定義は本体がなくてもよい
  トレイト名で指定した名前がトレイトとして定義される
*/

/*
  Scalaのトレイトはクラスに比べて以下のような特徴がある
    複数のトレイトを1つのクラスやトレイトにミックスインできる
    直接インスタンス化できない
    クラスパラメータ（コンストラクタの引数）を取ることができない
 */

/*
  複数のトレイトを1つのクラスやトレイトにミックスインできる
    Scalaのトレイトはクラスとは違い、複数のトレイトを1つのクラスやトレイトにミックスインすることができる
 */

trait TraitA

trait TraitB

class ClassA

class ClassB

// コンパイルが通る
class ClassC extends ClassA with TraitA with TraitB

// コンパイルエラー
// error: class ClassB needs to be a trait to be mixed in
// class ClassD extends ClassA with ClassB

/*
  直接インスタンス化できない
    Scalaのトレイトはクラスと違い、直接インスタンス化できない

  これは、トレイトが単体で使われることをそもそも想定していないための制限である
  トレイトを使うときは、通常、それを継承したクラスを作る
*/

trait TraitX

class ClassX extends TraitX

object ObjectA {
  // コンパイルエラー
  //val a = new TraitX

  // クラスにすればインスタンス化できる
  val a = new ClassX

  // なお、new Trait {}という記法を使うと、一見トレイトをインスタンス化できているように見える
  // しかしこれは、Traitを継承した無名のクラスを作って、そのインスタンスを生成する構文なので、
  // トレイトそのものをインスタンス化できているわけではない
  val b = new TraitX {}
}

/*
  クラスパラメータ（コンストラクタの引数）を取ることができない
    Scalaのトレイトはクラスと違いパラメータ（コンストラクタの引数）を取ることが
    できないという制限がある
 */

// 正しいプログラム
class ClassL(name: String) {
  def printName() = println(name)
}

// コンパイルエラー
// trait TraitL(name: String)

/*
  これもあまり問題になることはない。トレイトに抽象メンバーを持たせることで値を渡すことが
  できる。インスタンス化できない問題のときと同じようにクラスに継承させたり、インスタンス
  化のときに抽象メンバーを実装をすることでトレイトに値を渡すことができる
 */

trait Trait1 {
  val name: String
  def printName(): Unit = println(name)
}

class Class1(val name: String) extends Trait1

object Object1 {
  def main(args: Array[String]): Unit = {
    val x = new Class1("green")
    // green
    x.printName()

    val y = new Trait1 { val name = "blue" }
    // blue
    y.printName()

  }
}

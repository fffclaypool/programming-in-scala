class Rational(n: Int, d: Int) {
  /*
    require:
      渡された値がtrueなら通常通り制御を返す
      そうでなければ、IllegalArgumentExceptionを投げて、オブジェクトが構築されない
   */
  require (d != 0)

  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g
  def this(n: Int) = this(n, 1)
  def + (that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  def + (i: Int): Rational = new Rational(numer + i * denom, denom)
  def - (that: Rational): Rational =
    new Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )
  def - (i: Int): Rational = new Rational(numer - i * denom, denom)
  def * (that: Rational): Rational = new Rational(numer * that.numer, denom * that.denom)
  def * (i: Int): Rational = new Rational(numer * i, denom)
  def / (that: Rational): Rational = new Rational(numer * that.denom, denom * that.numer)
  def / (i: Int): Rational = new Rational(numer, denom * i)

  /*
    Rationalクラスはjava.lang.Objectクラスで定義されたtoStringの実装を継承している
    toStringは、デバッグ出力文等で使える情報を生成する
    この実装は、クラス名、@記号、16進数を表示するように定義されている
      ex) rest0: Rational = Rational@2591e0c9
    次のように、toStringメソッドをオーバーライドすることでRationalの数値が綺麗に表示されるようにする
      ex) rest0: Rational = 1/3
  */
  override def toString = s"$numer/$denom"
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}

object Main {
  def main(args: Array[String]): Unit = {
    val x = new Rational(2, 3) // x: Rational = 2/3
    x * x                      // res12: Rational = 4/9
    x * 2                      // res13: Rational = 4/3
  }
}

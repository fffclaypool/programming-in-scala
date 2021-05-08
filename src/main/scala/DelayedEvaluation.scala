// quote:
//   https://outputable.com/post/scala-lazy-val/

object DelayedEvaluation {
  def main(args: Array[String]) = {
    lazyVal()
  }

  def lazyVal(): Unit = {
    val a = {
      println("test")
      12
    }
    /*
      定義した直後にprintln文が評価されている
      その後は何度呼び出してもprintlnは実行せず、最終的な戻り値「12」のみを返す

      test
      val a: Int = 12

      scala> a
      val res0: Int = 12

      scala> a
      val res1: Int = 12
     */

    def b: Int = {
      println("test")
      12
    }
    /*
      定義直後は何も起こらず、呼び出すと毎回printlnの結果と最後の戻り値を返す

      def b: Int

      scala> b
      test
      val res2: Int = 12

      scala> b
      test
      val res3: Int = 12
     */

    lazy val c = {
      println("test")
      12
    }
    /*
      定義直後はlazyであることがわかるだけでprintln文は実行されない。この点ではメソッドと同様に見える
      しかし、printlnの出力「test」があるのは初回の呼び出し時のみで、以降は普通のvalと同じく、
      戻り値「12」のみが返される。処理が「初回一回のみ」しか実行されないのが特徴である

      lazy val c: Int // unevaluated

      scala> c
      test
      val res4: Int = 12

      scala> c
      val res5: Int = 12
     */
  }
}

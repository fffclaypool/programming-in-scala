// quote:
//   http://xerial.org/scala-cookbook/recipes/2012/11/16/either
//   http://yebisupress.dac.co.jp/2018/12/13/scala-nyumon-jissen01/#:~:text=Either%E5%9E%8B%E3%81%A8%E3%81%AF&text=Either%E5%9E%8B%E3%81%AF%E3%81%9D%E3%81%AE%E5%90%8D,%E3%81%A4%E3%81%AE%E5%80%A4%E3%82%92%E6%8C%81%E3%81%A1%E3%81%BE%E3%81%99%E3%80%82

/*
  Either[A, B] は、AまたはBを返す型である
  主にエラー処理で「成功時の値or失敗時の値」として扱われることが多い
  EitherはRightとLeftという2つの値を持つ
 */

object EitherOp {
  def main(args: Array[String]): Unit = {
    // Right(128)
    parseInt("128")

    // Left(java.lang.NumberFormatException: For input string: "234A")
    parseInt("234A")

    // Right(49.0)
    parseInt("49")
      .map {
        _.toFloat
      }

    // Left(java.lang.NumberFormatException: For input string: "ADF")
    parseInt("ADF")
      .map {
        _.toFloat
      }

    // Some(49.0)
    parseInt("49")
      .toOption
      .map {
        _.toFloat
      }

    // 49.0
    parseInt("49")
      .toOption
      .map{
        _.toFloat
      }
      .get

    // Right(213)
    parseInt("213")
      .left
      .map {
        _.getMessage
      }

    // Left(For input string: "ADF")
    parseInt("ADF")
      .left
      .map {
        _.getMessage
      }

    // Right(1)
    stringOrInt(List("aaa", "bbb", "ccc"))

    // Left(aaa)
    println(stringOrInt(List("aaa")))
  }

  def parseInt(s: String): Either[Exception, Int] = {
    try
      Right(s.toInt)
    catch {
      case e: Exception => Left(e)
    }
  }

  def stringOrInt(l: List[String]): Either[String, Int] =
    (
      for {
        a <- l
      } yield (a)
    ) match {
      case List("aaa") => Left("aaa")
      case _ => Right(1)
    }
}

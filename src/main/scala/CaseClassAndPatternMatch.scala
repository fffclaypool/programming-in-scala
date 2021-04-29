abstract class Expr

case class Var(Name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object CaseClassAndPatternMatch {
  def constantPattern(x: Any): String = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list"
    case _ => "something else"
  }

  def variablePattern(x: Int): String = x match {
    case 0 => "zero"
    case somethingElse => "not zero: " + somethingElse
  }

  def constructorPattern(expr: Expr): String = expr match {
    case BinOp("+", _, Number(0)) => "a deep match"
    case _ => "something else has found"
  }

  def sequencePattern(x: List[Int]): String = x match {
    case List(0, _, _) => "detect fixed length"
    case List(0, _*) => "detect arbitrary length"
    case _ => "something else has found"
  }

  def typePattern(x: Any): Int = x match {
    case s: String => s.length
    case m: Map[_, _] => m.size
    case _ => -1
  }

  def tuplePattern(x: Any): String = x match {
    case (_, _, _) => "found it"
    case _ => "something else has found"
  }

  def optionPattern(x: Option[String]): String = x match {
    case Some(s) => s
    case None => "?"
  }
}

import java.io.File

object For {
  def main(args: Array[String]): Unit = {
    val filesHere = (new File("src/main/scala/").listFiles)

    basic(filesHere)
    filtering(filesHere)
    nesting(filesHere)
    binding(filesHere)
    collection(filesHere)
  }

  def basic(filesHere: Array[File]): Unit =
    for (file <- filesHere) println(file)

  def filtering(filesHere: Array[File]): Unit = {
    for (file <- filesHere if file.getName.endsWith(".scala")) println(file)

    for (file <- filesHere)
      if (file.getName.endsWith(".scala")) println(file)

    for {
      file <- filesHere
      if file.isFile
      if file.getName.endsWith(".scala")
    } println(file)
  }

  def nesting(filesHere: Array[File]): Unit =
  /*
    複数の<-節を追加すると、ループを入れ子にすることがきる
    下記コードだと、外側のループはfilesHereを反復処理し、内側のループはファイル名の末尾が
    .scalaになっているファイルについて、fileLines(file)を反復処理する
   */

    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
      line <- scala.io.Source.fromFile(file).getLines().toList
      if line.trim.matches(".*def.*")
    } println(file + ":" + line.trim)

  /*
    A - a
    A - b
    A - c
    B - a
    B - b
    B - c
    C - a
    C - b
    C - c
   */
  for {
    x <- List("A", "B", "C")
    y <- List("a", "b", "c")
  } println(x + " - " + y)

  def binding(filesHere: Array[File]): Unit =
  /*
    nestingのコードでは、line.trimという式を繰り返している
      ex)
        if line.trim.matches(".*def.*")
        println(file + ":" + line.trim)

    これは、計算量として無視できるものではないので計算を1度だけに制限したい
    等号(=)を使って新しい変数に結果地を束縛すれば可能である
    束縛変数は、val変数と同じように導入されて使われるが、そのスコープがfor式
    の中にとどまり、valキーワードが省略されるところだけが異なる
   */

    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
      line <- scala.io.Source.fromFile(file).getLines().toList
      trimmed = line.trim
      if trimmed.matches(".*def.*")
    } println(file + ":" + trimmed)

  def collection(filesHere: Array[File]): Array[File] =
  /*
    値を反復生成して記憶させる場合は、yieldキーワードを使用する
    例えば、次に示す関数は、.scalaファイルだけを選び出して配列に格納する
      a = {File[2]@941}
        0 = {File@943} "src/main/scala/Trait.scala"
        1 = {File@944} "src/main/scala/For.scala"

    for式の本体が実行されるたびに、結果値が生成される
    for式全体が終了したときの結果値は、このようにして生成された値をコレクションにまとめたものになる
    結果値のコレクションの型は、ジェネレータ節（for式の{}内）で処理されたコレクションの型によって左右される
    この場合、filesHereが配列で、生成された式がFile型なので、結果型はArray[File]になる
   */

    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
    } yield file
}

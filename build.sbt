// quote:
//   https://www.scala-sbt.org/1.x/docs/ja/Basic-Def.html
//   https://www.scala-sbt.org/1.x/docs/ja/Library-Dependencies.html

/*
  ビルド定義はbuild.sbtにて定義され、プロジェクト（型: Project）の集合によって
  構成される。プロジェクトという用語が曖昧であることがあるため、このガイドではこれら
  をサブプロジェクトと呼ぶことが多い

  例えば、カレントディレクトリにあるサブプロジェクトはbuild.sbtで以下のように定義できる
    lazy val root = (project in file("."))
      .settings(
        name := "programming-in-scala",
        scalaVersion := "2.13.5"
      )

  それぞれのサブプロジェクトは、キーと値のペアによって詳細が設定される
  例えば、nameというキーがあるが、それはサブプロジェクト名という文字列の値に関連付けられる
  キーと値のペア列は.settings(...)メソッド内に列挙される
 */

/*
  build.sbtはどのようにsettingsを定義するか
  build.sbtにおいて定義されるサブプロジェクトは、キーと値のペア列を持つがこのペアは
  セッティング式と呼ばれ、build.sbt DSLにて記述される

    name := "programming-in-scala"

  それぞれのエントリーはセッティング式と呼ばれる
  セッティング式は以下の3部で構成される
    1. 左辺式をキーという - name
    2. 演算子 - :=
    3. 右辺項は本文、もしくはセッティング本文という - "programming-in-scala"

  name キーは SettingKey[String] に型付けされているため、 name の := 演算子も
  String に型付けされている。誤った型の値を使おうとするとビルド定義はコンパイルエラーになる

  キーはSettingKey[T], TaskKey[T], InputKey[T]のインスタンスで、Tはその値の型である
    - SettingKey[T]
      一度だけ値が計算されるキー（値はサブプロジェクトの読み込み時に計算され、保存される）
    - TaskKey[T]
      毎回再計算されるタスクを呼び出す、副作用を伴う可能性のある値のキー
    - InputKey[T]
      コマンドの引数を入力として受け取るタスクのキー
      ここでは触れない

  組み込みキー
    組み込みキーはKeysと呼ばれるオブジェクトのフィールドのことである
      https://www.scala-sbt.org/1.x/api/sbt/Keys$.html

  カスタムキー
    3種類のいずれかのインスタンスを使用して、独自のキーを定義する
    キーの名前は、valで宣言された変数の名前がそのまま用いられる。例として、新しくhelloと名付けたキーを
    定義してみる（一般的に、初期化順問題を避けるためにvalの代わりにlazy valが用いられることが多い）

      lazy val hello = taskKey[Unit]("An example task")

    build.sbt内にはval, lazy val, defを定義することもできる（objectやclassは不可）

  SettingKey[T]とTaskKey[T]の使い分け
    SettingKey[T]の場合、その値はプロジェクトがロードされた時に一度だけ演算が行われる
      ex) nameなど
    TaskKey[T]の場合は、タスクの実行毎に毎回再実行される
      ex) compile, packageなど
 */

name := "programming-in-scala"

version := "0.1"

scalaVersion := "2.13.5"

/*
  ライブラリ依存性
    ライブラリ依存性は二つの方法で加えることができる:
      - lib ディレクトリに jar ファイルを入れることでできるアンマネージ依存性（unmanaged dependencies）
      - ビルド定義に設定され、リポジトリから自動でダウンロードされるマネージ依存性（managed dependencies）

    多くの場合、アンマネージ依存性ではなくマネージ依存性を使用する

  マネージ依存性
    大体の場合、依存性を libraryDependencies セッティングに列挙するだけ完了する
      libraryDependencies ++= Seq(
        groupID % artifactID % revision,
        groupID % otherID % otherRevision
      )

  %%
    groupID % artifactID % revision のかわりに、groupID %% artifactID % revision を使うと
    （違いは groupID の後ろの二つ連なった %%）、sbtはプロジェクトのScalaのバイナリバージョンをアーティファクト名に
    追加する。下記の2つは等しい
      libraryDependencies += "org.scala-tools" %  "scala-stm_2.11" % "0.3"
      libraryDependencies += "org.scala-tools" %% "scala-stm"      % "0.3"

  Resolvers
    sbtは、デフォルトでMavenの標準リポジトリを使用する。もし依存ライブラリがデフォルトのリポジトリに存在しないなら、
    Ivyがそれを見つけられるようにresolverを追加する必要がある

    リポジトリを追加するには、以下のように
      resolvers += name at location
    二つの文字列の間の特別なatを使う。例えば、次のようになる
      resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
 */

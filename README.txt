1. ファイル

以下の4つのファイルを参照してください。

===
src/main/scala/com/asakusafw/ab/AccountBatch.scala

図7 改善された会計処理バッチの処理フロー

====
src/main/scala/com/asakusafw/ab2/AccountBatch.scala

DataSourceとOperationに情報を設定
Asakusa Java DSLとの接続情報を想定

===
src/main/scala/com/asakusafw/ab3/AccountBatch.scala

入力と出力の部分にサブフローを追加

===
src/main/scala/com/asakusafw/ab4/AccountBatch.scala

中間処理のサブフローに変更

2. コンパイル方法(参考)

bash-3.2$ sbt
Project does not exist, create new project? (y/N/s) y
y
Name: asd
asd
Organization: asd
asd
Version [1.0]: 

Scala version [2.7.7]: 2.8.1
2.8.1
sbt version [0.7.4]: 

Getting Scala 2.7.7 ...
:: retrieving :: org.scala-tools.sbt#boot-scala
	confs: [default]
	2 artifacts copied, 0 already retrieved (9911kB/42ms)
Getting org.scala-tools.sbt sbt_2.7.7 0.7.4 ...
:: retrieving :: org.scala-tools.sbt#boot-app
	confs: [default]
	15 artifacts copied, 0 already retrieved (4096kB/229ms)
[success] Successfully initialized directory structure.
Getting Scala 2.8.1 ...
:: retrieving :: org.scala-tools.sbt#boot-scala
	confs: [default]
	2 artifacts copied, 0 already retrieved (15118kB/197ms)
[info] Building project asd 1.0 against Scala 2.8.1
[info]    using sbt.DefaultProject with sbt 0.7.4 and Scala 2.7.7
> compile
compile
[info] 
[info] == compile ==
[info]   Source analysis: 17 new/modified, 0 indirectly invalidated, 0 removed.
[info] Compiling main sources...
[info] Compilation successful.
[warn] Could not determine source for class com.example.op.ShiireDataTorikomi
[warn] Could not determine source for class com.example.data.ShiireMeisai
[info]   Post-analysis: 220 classes.
[info] == compile ==
[success] Successful.
[info] 
[info] Total time: 10 s, completed 2011/03/06 20:34:19
> 

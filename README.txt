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

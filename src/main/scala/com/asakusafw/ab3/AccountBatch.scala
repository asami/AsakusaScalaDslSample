package com.asakusafw.ab3

import com.asakusafw.dsl._

class 仕入明細データ extends DataSource {
  java_dsl_class = classOf[com.example.data.ShiireMeisai]
}

class 仕入返品データ extends DataSource {
  java_dsl_class = "com.example.data.ShiireHenpinData"
}

class 費用振替データ extends DataSource
class 売価変更データ extends DataSource

class 修正在庫振替TRN extends DataSource
class 修正未収収益TRN extends DataSource
class 修正在庫移動TRN extends DataSource
class 未払計上TRN extends DataSource

class 仕入TRN extends DataSource
class 在庫振替TRN extends DataSource
class 在庫移動TRN extends DataSource
class 未収収益TRN extends DataSource

class 計上済仕入TRN extends DataSource
class 計上済未収収益TRN extends DataSource
class 計上済未払費用TRN extends DataSource
class 更新済買掛残高TRN extends DataSource

class 請求エラーTRN extends DataSource
class 支払不可消込TRN extends DataSource
class 支払可消込TRN extends DataSource
class 照合済支払費用TRN extends DataSource
class 照合済未収収益TRN extends DataSource
class 照合済仕入TRN extends DataSource
class 照合済請求TRN extends DataSource

class 仕入データ extends DataSource4[仕入明細データ, 仕入返品データ,
                                     費用振替データ, 売価変更データ]
class 修正データ extends DataSource4[修正在庫振替TRN, 修正未収収益TRN,
                                     修正在庫移動TRN, 未払計上TRN]
class 売価変更在庫変更TRN extends DataSource
class 仕入データTRN extends DataSource4[仕入TRN, 在庫振替TRN,
                                        在庫移動TRN, 未収収益TRN]
class 残高更新TRN extends DataSource4[計上済仕入TRN, 計上済未収収益TRN,
                                      計上済未払費用TRN, 更新済買掛残高TRN]
class 請求TRN extends DataSource
class 会計データTRN extends DataSource7[請求エラーTRN, 支払不可消込TRN, 支払可消込TRN,
                                        照合済支払費用TRN, 照合済未収収益TRN,
                                        照合済仕入TRN, 照合済請求TRN]

case class 仕入データ取り込み(cout: Port[売価変更在庫変更TRN]) extends Operation12[仕入データ, 仕入データTRN, 売価変更在庫変更TRN](cout) {
  java_dsl_class = classOf[com.example.op.ShiireDataTorikomi]
}

case class 残高更新(cin: Port[修正データ]) extends Operation21[仕入データTRN, 修正データ, 残高更新TRN](cin) {
  java_dsl_class = "com.example.op.ZandakaKoshin"
}

case class 照合処理(cin: Port[請求TRN]) extends Operation21[残高更新TRN, 請求TRN, 会計データTRN](cin) {
}

case object 売価変更在庫変更TRN修正 extends Operation11[売価変更在庫変更TRN, 売価変更在庫変更TRN]

case object 修正データ追加修正 extends Operation11[修正データ, 修正データ]

// 2.26
// 図7 改善された会計処理バッチの処理フロー
class 会計処理バッチ extends Flow32[仕入データ, 修正データ, 請求TRN,
                                    会計データTRN, 売価変更在庫変更TRN] {

  val 売価変更在庫変更TRN補正 = new Flow11[売価変更在庫変更TRN, 売価変更在庫変更TRN] {
    start proc11(売価変更在庫変更TRN修正) end(会計処理バッチ.this.out2)
  }

  val 修正データ補正 = new Flow11[修正データ, 修正データ] {
    start(会計処理バッチ.this.in2) proc11(修正データ追加修正) end
  }

  start proc12(仕入データ取り込み(売価変更在庫変更TRN補正.post1)) proc21(残高更新(修正データ補正.get1)) proc21(照合処理(in3)) end
}

package com.asakusafw.dsl

// 1.17
abstract class DataSource {
}

abstract class DataSource2[D1 <: DataSource, D2 <: DataSource](implicit val m1: Manifest[D1], implicit val m2: Manifest[D2]) extends DataSource {
}

abstract class DataSource3[D1 <: DataSource, D2 <: DataSource, D3 <: DataSource](implicit val m1: Manifest[D1], implicit val m2: Manifest[D2], implicit val m3: Manifest[D3]) extends DataSource {
}

abstract class DataSource4[D1 <: DataSource, D2 <: DataSource, D3 <: DataSource, D4 <: DataSource](implicit val m1: Manifest[D1], implicit val m2: Manifest[D2], implicit val m3: Manifest[D3], implicit val m4: Manifest[D4]) extends DataSource {
}

abstract class DataSource5[D1 <: DataSource, D2 <: DataSource, D3 <: DataSource, D4 <: DataSource, D5 <: DataSource](implicit val m1: Manifest[D1], implicit val m2: Manifest[D2], implicit val m3: Manifest[D3], implicit val m4: Manifest[D4], implicit val m5: Manifest[D5]) extends DataSource {
}

abstract class DataSource6[D1 <: DataSource, D2 <: DataSource, D3 <: DataSource, D4 <: DataSource, D5 <: DataSource, D6 <: DataSource](implicit val m1: Manifest[D1], implicit val m2: Manifest[D2], implicit val m3: Manifest[D3], implicit val m4: Manifest[D4], implicit val m5: Manifest[D5], implicit val m6: Manifest[D6]) extends DataSource {
}

abstract class DataSource7[D1 <: DataSource, D2 <: DataSource, D3 <: DataSource, D4 <: DataSource, D5 <: DataSource, D6 <: DataSource, D7 <: DataSource](implicit val m1: Manifest[D1], implicit val m2: Manifest[D2], implicit val m3: Manifest[D3], implicit val m4: Manifest[D4], implicit val m5: Manifest[D5], implicit val m6: Manifest[D6], implicit val m7: Manifest[D7]) extends DataSource {
}

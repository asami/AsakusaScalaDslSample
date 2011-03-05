package com.asakusafw.dsl

// 1.25
// SubFlow
class Channel[CIN <: DataSource, COUT <: DataSource](val name: Symbol) {
  val in1 = new Port[CIN]
  val out1 = new Port[COUT]
}

class Port[P <: DataSource]

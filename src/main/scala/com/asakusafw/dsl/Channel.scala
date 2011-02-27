package com.asakusafw.dsl

// 1.25
class Channel[CIN <: DataSource](val name: Symbol) {
}

class Port[P <: DataSource]

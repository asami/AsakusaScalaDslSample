package com.asakusafw.app

import com.asakusafw.dsl._

class MyInput extends DataSource
class MyOutput extends DataSource
class MyArtifact extends DataSource

case object op1 extends Operation11[MyInput, MyArtifact]
case object op2 extends Operation11[MyArtifact, MyOutput]

// 1.1
class MyFlow extends Flow11[MyInput, MyOutput] {
  start proc11(op1) proc11(op2) end
}

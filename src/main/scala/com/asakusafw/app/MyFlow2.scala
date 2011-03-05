package com.asakusafw.app

import com.asakusafw.dsl._

class MyArtifactAux extends DataSource

case class opp1(cout: Port[MyArtifactAux]) extends Operation12[MyInput, MyArtifact, MyArtifactAux](cout)
case class opp2(cin: Port[MyArtifactAux]) extends Operation21[MyArtifact, MyArtifactAux, MyOutput](cin)
case object oppA extends Operation11[MyArtifactAux, MyArtifactAux]

// 1.24
class MyFlow2 extends Flow11[MyInput, MyOutput] {
//  val f = flow11[MyArtifactAux, MyArtifactAux]('a) proc11(oppA) end
  val f = new Flow11[MyArtifactAux, MyArtifactAux] {
    start proc11(oppA) end
  }

  start proc12(opp1(f.in1)) proc21(opp2(f.out1)) end
}

package com.asakusafw.dsl

import scala.collection.mutable.HashMap
import scala.collection.mutable.LinkedHashMap

// 1.4
abstract class Flow {
  private var _start: Option[FlowNode] = None
   val channels = new HashMap[Symbol, Channel[_, _]]

  protected final def set_start[T <: FlowNode](node: T): T = {
    _start = Some(node)
    node
  }

  def channel[CINOUT <: DataSource](atom: Symbol): Channel[CINOUT, CINOUT] = {
    val ch = new Channel[CINOUT, CINOUT](atom)
    channels(atom) = ch
    ch
  }

  def channel11[CIN1 <: DataSource, COUT1 <: DataSource](atom: Symbol): Channel[CIN1, COUT1] = {
    val ch = new Channel[CIN1, COUT1](atom)
    channels(atom) = ch
    ch
  }
}

class SubFlows[F <: Flow] {
  val flows = new LinkedHashMap[AnyRef, F]

  def apply(key: AnyRef): F = {
    flows(key)
  }

  def update(key: AnyRef, flow: F) = {
    flows(key, flow)
  }
}

abstract class Flow11[IN1 <: DataSource, OUT1 <: DataSource] extends Flow {
  val in1 = new Port[IN1]
  val out1 = new Port[OUT1]

  def start: FlowNode1[IN1] = {
    set_start(new FlowNode1[IN1]())
  }
}

abstract class Flow32[IN1 <: DataSource, IN2 <: DataSource, IN3 <: DataSource, OUT1 <: DataSource, OUT2 <: DataSource] extends Flow {
  private var _start: Option[FlowNode] = None

  val in1 = new Port[IN1]
  val in2 = new Port[IN2]
  val in3 = new Port[IN3]
  val out1 = new Port[OUT1]
  val out2 = new Port[OUT2]

  def start: FlowNode1[IN1] = {
    set_start(new FlowNode1[IN1]())
  }

  def start2 = {
    set_start(new FlowNode2[IN1, IN2]())
  }

  def start3 = {
    set_start(new FlowNode3[IN1, IN2, IN3]())
  }
}

abstract class FlowNode(val prev: Option[FlowNode] = None)

class FlowNode1[FIN1 <: DataSource](prev: Option[FlowNode] = None) extends FlowNode(prev) {
  private var _operation: Option[Operation] = None

  def proc1[FOUT1 <: DataSource](op: Operation11[FIN1, FOUT1]): FlowNode1[FOUT1] = {
    _operation = Some(op);
    new FlowNode1[FOUT1](Some(this))
  }

  def proc2[FOUT1 <: DataSource, FOUT2 <: DataSource](op: Operation12[FIN1, FOUT1, FOUT2]): FlowNode1[FOUT1] = {
    _operation = Some(op);
    new FlowNode1[FOUT1](Some(this))
  }

  def proc3[FOUT1 <: DataSource, FOUT2 <: DataSource, FOUT3 <: DataSource](op: Operation13[FIN1, FOUT1, FOUT2, FOUT3]): FlowNode1[FOUT1] = {
    _operation = Some(op);
    new FlowNode1[FOUT1](Some(this))
  }

  def proc11[FOUT1 <: DataSource](op: Operation11[FIN1, FOUT1]): FlowNode1[FOUT1] = {
    _operation = Some(op);
    new FlowNode1[FOUT1](Some(this))
  }

  def proc12[FOUT1 <: DataSource, FOUT2 <: DataSource](op: Operation12[FIN1, FOUT1, FOUT2]): FlowNode1[FOUT1] = {
    _operation = Some(op);
    new FlowNode1[FOUT1](Some(this))
  }

  def proc21[FIN2 <: DataSource, FOUT1 <: DataSource](op: Operation21[FIN1, FIN2, FOUT1]): FlowNode1[FOUT1] = {
    _operation = Some(op);
    new FlowNode1[FOUT1](Some(this))
  }

  def end {
  }
}

class FlowNode2[FIN1 <: DataSource, FIN2 <: DataSource](prev: Option[FlowNode] = None) extends FlowNode(prev) {
  private var _operation: Option[Operation] = None

  def proc1[FOUT1 <: DataSource](op: Operation21[FIN1, FIN2, FOUT1]): FlowNode1[FOUT1] = {
    _operation = Some(op);
    new FlowNode1[FOUT1](Some(this))
  }

  def proc2[FOUT1 <: DataSource, FOUT2 <: DataSource](op: Operation22[FIN1, FIN2, FOUT1, FOUT2]): FlowNode1[FOUT1] = {
    _operation = Some(op);
    new FlowNode1[FOUT1](Some(this))
  }

  def proc3[FOUT1 <: DataSource, FOUT2 <: DataSource, FOUT3 <: DataSource](op: Operation23[FIN1, FIN2, FOUT1, FOUT2, FOUT3]): FlowNode1[FOUT1] = {
    _operation = Some(op);
    new FlowNode1[FOUT1](Some(this))
  }

  def end {
  }
}

class FlowNode3[FIN1 <: DataSource, FIN2 <: DataSource, FIN3 <: DataSource](prev: Option[FlowNode] = None) extends FlowNode(prev) {
  private var _operation: Option[Operation] = None

  def proc1[FOUT1 <: DataSource](op: Operation31[FIN1, FIN2, FIN3, FOUT1]): FlowNode1[FOUT1] = {
    _operation = Some(op);
    new FlowNode1[FOUT1](Some(this))
  }

  def proc2[FOUT1 <: DataSource, FOUT2 <: DataSource](op: Operation32[FIN1, FIN2, FIN3, FOUT1, FOUT2]): FlowNode1[FOUT1] = {
    _operation = Some(op);
    new FlowNode1[FOUT1](Some(this))
  }

  def proc3[FOUT1 <: DataSource, FOUT2 <: DataSource, FOUT3 <: DataSource](op: Operation33[FIN1, FIN2, FIN3, FOUT1, FOUT2, FOUT3]): FlowNode1[FOUT1] = {
    _operation = Some(op);
    new FlowNode1[FOUT1](Some(this))
  }

  def end {
  }
}

abstract class Operation

class Operation11[FIN1 <: DataSource, FOUT1 <: DataSource](implicit val min1: Manifest[FIN1], implicit val mout1: Manifest[FOUT1]) extends Operation

class Operation12[FIN1 <: DataSource, FOUT1 <: DataSource, FOUT2 <: DataSource](cout2: Port[FOUT2])(implicit val min1: Manifest[FIN1], implicit val mout1: Manifest[FOUT1], implicit val mout2: Manifest[FOUT2]) extends Operation

class Operation13[FIN1 <: DataSource, FOUT1 <: DataSource, FOUT2 <: DataSource, FOUT3 <: DataSource](cout2: Port[FOUT2], cout3: Port[FOUT3])(implicit val min1: Manifest[FIN1], implicit val mout1: Manifest[FOUT1], implicit val mout2: Manifest[FOUT2], implicit val mout3: Manifest[FOUT3]) extends Operation

class Operation21[FIN1 <: DataSource, FIN2 <: DataSource, FOUT1 <: DataSource](cin2: Port[FIN2])(implicit val min1: Manifest[FIN1], implicit val min2: Manifest[FIN2], implicit val mout1: Manifest[FOUT1]) extends Operation

class Operation22[FIN1 <: DataSource, FIN2 <: DataSource, FOUT1 <: DataSource, FOUT2 <: DataSource](cin2: Port[FIN2], cout2: Port[FOUT2])(implicit val min1: Manifest[FIN1], implicit val min2: Manifest[FIN2], implicit val mout1: Manifest[FOUT1], implicit val mout2: Manifest[FOUT2]) extends Operation

class Operation23[FIN1 <: DataSource, FIN2 <: DataSource, FOUT1 <: DataSource, FOUT2 <: DataSource, FOUT3 <: DataSource](cin2: Port[FIN2], cout2: Port[FOUT2], cout3: Port[FOUT3])(implicit val min1: Manifest[FIN1], implicit val min2: Manifest[FIN2], implicit val mout1: Manifest[FOUT1], implicit val mout2: Manifest[FOUT2], implicit val mout3: Manifest[FOUT3]) extends Operation

class Operation31[FIN1 <: DataSource, FIN2 <: DataSource, FIN3 <: DataSource, FOUT1 <: DataSource](cin2: Port[FIN2], cin3: Port[FIN3])(implicit val min1: Manifest[FIN1], implicit val min2: Manifest[FIN2], implicit val min3: Manifest[FIN3], implicit val mout1: Manifest[FOUT1]) extends Operation

class Operation32[FIN1 <: DataSource, FIN2 <: DataSource, FIN3 <: DataSource, FOUT1 <: DataSource, FOUT2 <: DataSource](cin2: Port[FIN2], cin3: Port[FIN3], cout2: Port[FOUT2])(implicit val min1: Manifest[FIN1], implicit val min2: Manifest[FIN2], implicit val min3: Manifest[FIN3], implicit val mout1: Manifest[FOUT1], implicit val mout2: Manifest[FOUT2]) extends Operation

class Operation33[FIN1 <: DataSource, FIN2 <: DataSource, FIN3 <: DataSource, FOUT1 <: DataSource, FOUT2 <: DataSource, FOUT3 <: DataSource](cin2: Port[FIN2], cin3: Port[FIN3], cout2: Port[FOUT2], cout3: Port[FOUT3])(implicit val min1: Manifest[FIN1], implicit val min2: Manifest[FIN2], implicit val min3: Manifest[FIN3], implicit val mout1: Manifest[FOUT1], implicit val mout2: Manifest[FOUT2], implicit val mout3: Manifest[FOUT3]) extends Operation

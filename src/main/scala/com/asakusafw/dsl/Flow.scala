package com.asakusafw.dsl

import scala.collection.mutable.HashMap

// 1.4
abstract class Flow {
  private var _start: Option[FlowNode] = None

  protected final def set_start[T <: FlowNode](node: T): T = {
    _start = Some(node)
    node
  }

  def channel[FIN1 <: DataSource, FOUT1 <: DataSource](atom: Symbol, op: Operation11[FIN1, FOUT1]): FlowNode1[FOUT1] = {
    val channels = new HashMap[Symbol, Channel[_]]

    val ch = new Channel(atom)
    channels(atom) = ch
    new FlowNode1[FOUT1](None)
  }
}

abstract class Flow11 extends Flow {
  type IN1 <: DataSource
  type OUT1 <: DataSource

  val in1 = new Channel[IN1]('in1)
  val out1 = new Channel[OUT1]('out1)

  def start: FlowNode1[IN1] = {
    set_start(new FlowNode1[IN1]())
  }
}

abstract class Flow32 extends Flow {
  type IN1 <: DataSource
  type IN2 <: DataSource
  type IN3 <: DataSource
  type OUT1 <: DataSource
  type OUT2 <: DataSource

  private var _start: Option[FlowNode] = None

  val in1 = new Channel[IN1]('in1)
  val in2 = new Channel[IN2]('in2)
  val in3 = new Channel[IN3]('in3)
  val out1 = new Channel[OUT1]('out1)
  val out2 = new Channel[OUT2]('out2)

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

class Operation12[FIN1 <: DataSource, FOUT1 <: DataSource, FOUT2 <: DataSource](cout2: Channel[FOUT2])(implicit val min1: Manifest[FIN1], implicit val mout1: Manifest[FOUT1], implicit val mout2: Manifest[FOUT2]) extends Operation

class Operation13[FIN1 <: DataSource, FOUT1 <: DataSource, FOUT2 <: DataSource, FOUT3 <: DataSource](cout2: Channel[FOUT2], cout3: Channel[FOUT3])(implicit val min1: Manifest[FIN1], implicit val mout1: Manifest[FOUT1], implicit val mout2: Manifest[FOUT2], implicit val mout3: Manifest[FOUT3]) extends Operation

class Operation21[FIN1 <: DataSource, FIN2 <: DataSource, FOUT1 <: DataSource](cin2: Channel[FIN2])(implicit val min1: Manifest[FIN1], implicit val min2: Manifest[FIN2], implicit val mout1: Manifest[FOUT1]) extends Operation

class Operation22[FIN1 <: DataSource, FIN2 <: DataSource, FOUT1 <: DataSource, FOUT2 <: DataSource](cin2: Channel[FIN2], cout2: Channel[FOUT2])(implicit val min1: Manifest[FIN1], implicit val min2: Manifest[FIN2], implicit val mout1: Manifest[FOUT1], implicit val mout2: Manifest[FOUT2]) extends Operation

class Operation23[FIN1 <: DataSource, FIN2 <: DataSource, FOUT1 <: DataSource, FOUT2 <: DataSource, FOUT3 <: DataSource](cin2: Channel[FIN2], cout2: Channel[FOUT2], cout3: Channel[FOUT3])(implicit val min1: Manifest[FIN1], implicit val min2: Manifest[FIN2], implicit val mout1: Manifest[FOUT1], implicit val mout2: Manifest[FOUT2], implicit val mout3: Manifest[FOUT3]) extends Operation

class Operation31[FIN1 <: DataSource, FIN2 <: DataSource, FIN3 <: DataSource, FOUT1 <: DataSource](cin2: Channel[FIN2], cin3: Channel[FIN3])(implicit val min1: Manifest[FIN1], implicit val min2: Manifest[FIN2], implicit val min3: Manifest[FIN3], implicit val mout1: Manifest[FOUT1]) extends Operation

class Operation32[FIN1 <: DataSource, FIN2 <: DataSource, FIN3 <: DataSource, FOUT1 <: DataSource, FOUT2 <: DataSource](cin2: Channel[FIN2], cin3: Channel[FIN3], cout2: Channel[FOUT2])(implicit val min1: Manifest[FIN1], implicit val min2: Manifest[FIN2], implicit val min3: Manifest[FIN3], implicit val mout1: Manifest[FOUT1], implicit val mout2: Manifest[FOUT2]) extends Operation

class Operation33[FIN1 <: DataSource, FIN2 <: DataSource, FIN3 <: DataSource, FOUT1 <: DataSource, FOUT2 <: DataSource, FOUT3 <: DataSource](cin2: Channel[FIN2], cin3: Channel[FIN3], cout2: Channel[FOUT2], cout3: Channel[FOUT3])(implicit val min1: Manifest[FIN1], implicit val min2: Manifest[FIN2], implicit val min3: Manifest[FIN3], implicit val mout1: Manifest[FOUT1], implicit val mout2: Manifest[FOUT2], implicit val mout3: Manifest[FOUT3]) extends Operation

package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
  private final NandGate nandLeft;
  private final NandGate nandTop;
  private final NandGate nandBottom;
  private final NandGate nandRight;

  public XorGate() {
    super(2);
    nandLeft = new NandGate();
    nandTop = new NandGate();
    nandBottom = new NandGate();
    nandRight = new NandGate();

    nandTop.connect(1, nandLeft);

    nandBottom.connect(0, nandLeft);

    nandRight.connect(0, nandTop);
    nandRight.connect(1, nandBottom);
  }

  @Override
  public boolean read() {
    return nandRight.read();
  }

  @Override
  public void connect(int inputPin, SignalEmitter emitter) {
    if (inputPin < 0 || inputPin > 1) {
      throw new IndexOutOfBoundsException(inputPin);
    }
    if (inputPin == 0) {
      nandLeft.connect(0, emitter);
      nandTop.connect(0, emitter);
    }
    if (inputPin == 1) {
      nandLeft.connect(1, emitter);
      nandBottom.connect(1, emitter);
    }
  }
}

package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
  private final NandGate nandRight;
  private final NandGate nandTop;
  private final NandGate nandBottom;

  public OrGate() {
    super(2);
    nandBottom = new NandGate();
    nandTop = new NandGate();
    nandRight = new NandGate();

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
      nandTop.connect(0, emitter);
      nandTop.connect(1, emitter);
    }
    if (inputPin == 1) {
      nandBottom.connect(0, emitter);
      nandBottom.connect(1, emitter);
    }
  }
}

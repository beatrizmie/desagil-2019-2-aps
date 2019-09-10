package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
  private final NandGate nandRight;
  private final NandGate nandLeft;

  public AndGate() {
    super(2);
    nandLeft = new NandGate();
    nandRight = new NandGate();

    nandRight.connect(0, nandLeft);
    nandRight.connect(1, nandLeft);
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
    }
    if (inputPin == 1) {
      nandLeft.connect(1, emitter);
    }
  }
}

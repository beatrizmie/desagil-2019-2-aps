package br.pro.hashi.ensino.desagil.aps.model;

public class TripleInputOrGate extends Gate {

  private final NandGate nandTop;
  private final NandGate nandMiddle;
  private final NandGate nandBottom;

  private final NandGate nandRight;

  public TripleInputOrGate() {
    super("TripleInputOR", 3);

    nandTop = new NandGate();
    nandMiddle = new NandGate();
    nandBottom = new NandGate();

    NandGate nandTopMiddle1 = new NandGate();
    nandTopMiddle1.connect(0, nandTop);
    nandTopMiddle1.connect(1, nandMiddle);

    NandGate nandTopMiddle2 = new NandGate();
    nandTopMiddle2.connect(0, nandTopMiddle1);
    nandTopMiddle2.connect(1, nandTopMiddle1);

    nandRight = new NandGate();
    nandRight.connect(0, nandTopMiddle2);
    nandRight.connect(1, nandBottom);

  }

  @Override
  public boolean read(int outputPin) {
    if (outputPin != 0) {
      throw new IndexOutOfBoundsException(outputPin);
    }
    return nandRight.read();
  }

  @Override
  public void connect(int inputPin, SignalEmitter emitter) {
    switch (inputPin) {
      case 0:
        nandTop.connect(0, emitter);
        nandTop.connect(1, emitter);
        break;
      case 1:
        nandMiddle.connect(0, emitter);
        nandMiddle.connect(1, emitter);
        break;
      case 2:
        nandBottom.connect(0, emitter);
        nandBottom.connect(1, emitter);
        break;
      default:
        throw new IndexOutOfBoundsException(inputPin);
    }
  }
}

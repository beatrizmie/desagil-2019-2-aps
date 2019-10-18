package br.pro.hashi.ensino.desagil.aps.model;

public class MockEmitter implements SignalEmitter {
  private final boolean signal;

  public MockEmitter(boolean signal) {
    this.signal = signal;
  }

  @Override
  public boolean read(int outputPin) {
    if (outputPin != 0) {
      throw new IndexOutOfBoundsException(outputPin);
    }
    return signal;
  }
}

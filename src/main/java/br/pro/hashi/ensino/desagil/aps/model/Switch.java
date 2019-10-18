package br.pro.hashi.ensino.desagil.aps.model;

public class Switch implements SignalEmitter {
  private boolean signal;

  public Switch() {
    signal = false;
  }

  public void turnOn() {
    signal = true;
  }

  public void turnOff() {
    signal = false;
  }

  @Override
  public boolean read(int outputPin) {
    if (outputPin != 0) {
      throw new IndexOutOfBoundsException(outputPin);
    }
    return signal;
  }
}

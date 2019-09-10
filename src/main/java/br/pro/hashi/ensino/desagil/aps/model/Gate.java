package br.pro.hashi.ensino.desagil.aps.model;

public abstract class Gate implements SignalEmitter, SignalReceiver {
  private final int inputSize;

  protected Gate(int inputSize) {
    this.inputSize = inputSize;
  }

  public int getInputSize() {
    return inputSize;
  }
}

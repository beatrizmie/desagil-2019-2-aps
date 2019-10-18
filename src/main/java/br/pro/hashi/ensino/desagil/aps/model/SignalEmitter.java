package br.pro.hashi.ensino.desagil.aps.model;

public interface SignalEmitter {
  boolean read(int outputPin);

  default boolean read() {
    return read(0);
  }
}

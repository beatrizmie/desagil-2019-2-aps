package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GateView extends FixedPanel implements ItemListener {
  private final Gate gate;
  private final Switch[] switches;
  private final JCheckBox[] entradaBox;
  private final JCheckBox saidaBox;


  public GateView(Gate gate) {
    super(245, 300);

    this.gate = gate;

    JLabel entrada = new JLabel("Entrada");
    JLabel saida = new JLabel("Saída");

    int inputSize = gate.getInputSize();

    switches = new Switch[inputSize];
    entradaBox = new JCheckBox[inputSize];

    for (int i = 0; i < inputSize; i++) {
      switches[i] = new Switch();
      entradaBox[i] = new JCheckBox();

      gate.connect(i, switches[i]);
    }

    saidaBox = new JCheckBox();

    for (JCheckBox i : entradaBox) {
      i.addItemListener(this);
    }

    saidaBox.setEnabled(false);

    update();

    add(entrada, 10, 10, 75, 25);
    for (int i = 0; i < inputSize; i++) {
      add(entradaBox[i], 10, 50 + i * 30, 75, 25);
    }
    add(saida, 10, 175, 75, 25);
    add(saidaBox, 10, 211, 75, 25);

  }

  private void update() {
    for (int i = 0; i < gate.getInputSize(); i++) {
      if (entradaBox[i].isSelected()) {
        switches[i].turnOn();
      } else {
        switches[i].turnOff();
      }
    }

    boolean resultado = gate.read();
    saidaBox.setSelected(resultado);
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    update();
  }
}

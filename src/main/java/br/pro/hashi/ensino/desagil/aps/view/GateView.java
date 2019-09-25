package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener, MouseListener {

    private static final int BORDER = 60;
    private static final int SWITCH_SIZE = 25;
    private static final int LIGHT_SIZE = 18;
    private static final int GATE_WIDTH = 120;
    private static final int GATE_HEIGHT = 160;

    private final Switch[] switches;
    private final Gate gate;
    private final Light light;
    private final JCheckBox[] inputBoxes;
    private final Image image;

    public GateView(Gate gate) {

        super(300, 200);

        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }

        light = new Light();
        light.setR(255);
        light.setG(0);
        light.setB(0);
        light.connect(0, gate);

        int x, y, step;

        x = BORDER + 8;
        y = -((SWITCH_SIZE ) / 2);
        step = (GATE_HEIGHT / (inputSize + 1));

        for (JCheckBox inputBox : inputBoxes) {
          y += step;
          add(inputBox, x, y, SWITCH_SIZE - 10, SWITCH_SIZE - 10);
          y += 15;
        }

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        addMouseListener(this);

        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        update();
    }

    private void update() {
      for (int i = 0; i < gate.getInputSize(); i++) {
        if (inputBoxes[i].isSelected()) {
          switches[i].turnOn();
        } else {
          switches[i].turnOff();
        }
      }
      repaint();
    }


    @Override
    public void paintComponent(Graphics g) {

      // Não podemos esquecer desta linha, pois não somos os
      // únicos responsáveis por desenhar o painel, como era
      // o caso nos Desafios. Agora é preciso desenhar também
      // componentes internas, e isso é feito pela superclasse.
      super.paintComponent(g);

      g.drawImage(image, BORDER + SWITCH_SIZE, 0, GATE_WIDTH + 30, GATE_HEIGHT, this);

      g.setColor(new Color(light.getR(), light.getG(), light.getB()));

      g.fillOval(BORDER + SWITCH_SIZE + GATE_WIDTH + 28, (GATE_HEIGHT - LIGHT_SIZE) / 2, LIGHT_SIZE + 2, 2 + LIGHT_SIZE);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
      }


  @Override
  public void mouseClicked(MouseEvent e) {

    int r = LIGHT_SIZE / 2;
    int x = BORDER + SWITCH_SIZE + GATE_WIDTH + r;
    int y = GATE_HEIGHT / 2;

    if (Math.pow(x - e.getX(), 2) + Math.pow(y - e.getY(), 2) < Math.pow(r, 2)) {
      Color oldColor = new Color(light.getR(), light.getG(), light.getB());
      Color newColor = JColorChooser.showDialog(this, null, oldColor);

      if (newColor != null) {
        light.setR(newColor.getRed());
        light.setG(newColor.getGreen());
        light.setB(newColor.getBlue());
      }
    }
    repaint();
    update();
  }


  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    update();
  }
}

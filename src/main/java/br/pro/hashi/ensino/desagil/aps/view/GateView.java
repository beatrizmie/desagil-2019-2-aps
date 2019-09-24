package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener, MouseListener {

    private static final int BORDER = 60;
    private static final int SWITCH_SIZE = 25;
    private static final int LIGHT_SIZE = 12;
    private static final int GATE_WIDTH = 90;
    private static final int GATE_HEIGHT = 200;

    private final Switch[] switches;
    private final Gate gate;

    private final JCheckBox[] inputBoxes;

    private final Image image;
    private Color color;

    public GateView(Gate gate) {

        super(345, 246);

        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }


        int x, y, step;

        x = BORDER;
        y = -(SWITCH_SIZE - 40 / 2);
        step = (GATE_HEIGHT / (inputSize + 1));

        for (JCheckBox inputBox : inputBoxes) {
          y += step;
          add(inputBox, x, y, SWITCH_SIZE - 10, SWITCH_SIZE - 10);
        }

        color = Color.BLACK;

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);


        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        update();

        this.addMouseListener(this);
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

      if (gate.getOutputSize() == 1){
        if (gate.read()) {
          g.setColor(color.RED);
        }else{
          g.setColor(color);
        }
        g.drawImage(image, 80, 30, 150, 150, this);
        g.fillOval(230, 100, 15, 15);
      }
        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
      }


  @Override
  public void mouseClicked(MouseEvent e) {
    int x = BORDER + SWITCH_SIZE + GATE_WIDTH + LIGHT_SIZE / 2;
    int y = GATE_HEIGHT / 2;

    if (gate.getOutputSize() == 1) {
      if (Math.sqrt(Math.pow(x - e.getX(), 2) + Math.pow(y - e.getY(), 2)) < LIGHT_SIZE / 2) {
        Color color = JColorChooser.showDialog(this, null, this.color);
        if (color != null) {
          this.color = color;
        }
        repaint();
      }
    }
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

  }
}

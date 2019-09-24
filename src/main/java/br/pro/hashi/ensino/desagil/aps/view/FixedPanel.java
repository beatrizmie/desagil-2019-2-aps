package br.pro.hashi.ensino.desagil.aps.view;

import javax.swing.*;
import java.awt.*;

public class FixedPanel extends JPanel {

  protected FixedPanel(int width, int height){

    setLayout(null);
    setPreferredSize(new Dimension(width, height));
  }

  protected Component add(Component component, int x, int y, int width, int height){

    super.add(component);
    component.setBounds(x, y, width, height);

    return component;
  }

}

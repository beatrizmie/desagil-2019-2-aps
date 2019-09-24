package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener {
    private final Switch[] switches;
    private final Gate gate;

    private final JCheckBox[] inputBoxes;
    private final JCheckBox outputBox;

    private final Image image;
    private Color color;

    public GateView(Gate gate) {

        super(245, 346);

        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }

        outputBox = new JCheckBox();

        JLabel inputLabel = new JLabel("Input");
        JLabel outputLabel = new JLabel("Output");

//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(inputLabel, 10, 10, 75, 25);
        for (JCheckBox inputBox : inputBoxes) {
            add(inputBox, 10, inputBox.getY()*20, 75, 25);
        }
        add(outputLabel, 10, 311, 75, 25);
        add(outputBox, 85, 311, 120, 25);

        color = Color.BLACK;

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        addMouseListener(this);

        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addActionListener(this);
        }

        outputBox.setEnabled(false);

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

        boolean result = gate.read();

        if (result == false){

        }

        outputBox.setSelected(result);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
      update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

      // Descobre em qual posição o clique ocorreu.
      int x = event.getX();
      int y = event.getY();

      // Se o clique foi dentro do quadrado colorido...
      if (x >= 210 && x < 235 && y >= 311 && y < 336) {

        // ...então abrimos a janela seletora de cor...
        color = JColorChooser.showDialog(this, null, color);

        // ...e chamamos repaint para atualizar a tela.
        repaint();
      }
    }

    @Override
    public void mousePressed(MouseEvent event) {
      // Não precisamos de uma reação específica à ação de pressionar
      // um botão do mouse, mas o contrato com MouseListener obriga
      // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
      // Não precisamos de uma reação específica à ação de soltar
      // um botão do mouse, mas o contrato com MouseListener obriga
      // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseEntered(MouseEvent event) {
      // Não precisamos de uma reação específica à ação do mouse
      // entrar no painel, mas o contrato com MouseListener obriga
      // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseExited(MouseEvent event) {
      // Não precisamos de uma reação específica à ação do mouse
      // sair do painel, mas o contrato com MouseListener obriga
      // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void paintComponent(Graphics g) {

      // Não podemos esquecer desta linha, pois não somos os
      // únicos responsáveis por desenhar o painel, como era
      // o caso nos Desafios. Agora é preciso desenhar também
      // componentes internas, e isso é feito pela superclasse.
      super.paintComponent(g);

      // Desenha a imagem, passando sua posição e seu tamanho.
      g.drawImage(image, 10, 80, 221, 221, this);

      // Desenha um quadrado cheio.
      g.setColor(color);
      g.fillRect(210, 311, 25, 25);

      // Linha necessária para evitar atrasos
      // de renderização em sistemas Linux.
      getToolkit().sync();
    }
}

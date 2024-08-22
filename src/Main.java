import novo.Aula02;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Configurações básicas da janela principal
        JFrame frame = new JFrame("Cadastro de Pessoas");
        frame.setContentPane(new Aula02().mainPanel); // Supõe que você tenha um painel principal chamado 'mainPanel'
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

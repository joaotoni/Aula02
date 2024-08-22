package novo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Aula02 {
    public JTextField nomeField;
    public JFormattedTextField cpfField;
    public JRadioButton masculinoRadio;
    public JRadioButton femininoRadio;
    public JFormattedTextField dataNascimentoField;
    public JComboBox<String> estadoCivilComboBox;
    public JTextField profissaoField;
    public JButton cadastrarButton;
    public JLabel resultadoLabel;
    public JPanel mainPanel;

    public Aula02() {
        // Adicionando opções ao ComboBox do estado civil
        estadoCivilComboBox.addItem("-");
        estadoCivilComboBox.addItem("Solteiro");
        estadoCivilComboBox.addItem("Casado");
        estadoCivilComboBox.addItem("Viúvo");

        // Adicionando listener ao botão de cadastro
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarPessoa();
            }
        });
    }

    private void cadastrarPessoa() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String sexo = masculinoRadio.isSelected() ? "Masculino" : "Feminino";
        String dataNascimentoStr = dataNascimentoField.getText();
        String estadoCivil = (String) estadoCivilComboBox.getSelectedItem();
        String profissao = profissaoField.getText();

        // Validações
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome não pode estar em branco.");
            return;
        }

        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) { // Validação básica de CPF
            JOptionPane.showMessageDialog(null, "CPF inválido. Use o formato XXX.XXX.XXX-XX.");
            return;
        }
        if(estadoCivil.matches("-")){
            JOptionPane.showMessageDialog(null, "Estado civil do tipo inválido.");
            return;
        }
        if (profissao.isEmpty()) {
            profissao = "Desempregado(a)";
        }

        // Cálculo da idade
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int idade = 0;
        try {
            Date dataNascimento = sdf.parse(dataNascimentoStr);
            idade = calcularIdade(dataNascimento);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data de nascimento inválida.");
            return;
        }

        // Montagem da mensagem de resultado
        StringBuilder resultado = new StringBuilder();
        resultado.append("Nome: ").append(nome).append("\n");
        resultado.append("Idade: ").append(idade).append("\n");
        resultado.append("Sexo: ").append(sexo).append("\n");
        resultado.append("Estado Civil: ").append(estadoCivil).append("\n");
        resultado.append("Profissão: ").append(profissao).append("\n");

        if (profissao.equalsIgnoreCase("Engenheiro") || profissao.equalsIgnoreCase("Analista de Sistemas")) {
            resultado.append("Vagas disponíveis na área.");
        }

        // Exibição do resultado
        resultadoLabel.setText("<html>" + resultado.toString().replace("\n", "<br>") + "</html>");
    }

    private int calcularIdade(Date dataNascimento) {
        Date hoje = new Date();
        int idade = hoje.getYear() - dataNascimento.getYear();
        if (hoje.getMonth() < dataNascimento.getMonth() ||
                (hoje.getMonth() == dataNascimento.getMonth() && hoje.getDate() < dataNascimento.getDate())) {
            idade--;
        }
        return idade;
    }
}

import Controller.EstoqueController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        EstoqueController controller = new EstoqueController();
        EstoqueForm estoqueForm = new EstoqueForm(controller);

        JFrame frame = new JFrame("Sistema de Gerenciamento de Estoque");
        frame.setContentPane(estoqueForm.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

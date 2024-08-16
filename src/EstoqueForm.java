import Controller.EstoqueController;
import entity.Estoque;
import entity.Produto;
import entity.Setor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EstoqueForm {
    private JPanel mainPanel;
    private JComboBox<Estoque> estoqueComboBox;
    private JButton adicionarEstoqueButton;
    private JButton removerEstoqueButton;
    private JList<Setor> setorList;
    private JButton adicionarSetorButton;
    private JButton removerSetorButton;
    private JList<Produto> produtoList;
    private JButton adicionarProdutoButton;
    private JButton removerProdutoButton;
    private JButton alterarProdutoButton;

    private EstoqueController controller;

    public EstoqueForm(EstoqueController controller) {
        this.controller = controller;

        adicionarEstoqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeEstoque = JOptionPane.showInputDialog("Nome do Estoque:");
                if (nomeEstoque != null && !nomeEstoque.isEmpty()) {
                    controller.adicionarEstoque(nomeEstoque);
                    updateEstoqueComboBox();
                }
            }
        });

        removerEstoqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = estoqueComboBox.getSelectedIndex();
                if (selectedIndex >= 0) {
                    controller.removerEstoque(selectedIndex);
                    updateEstoqueComboBox();
                    clearSetorAndProdutoLists();
                }
            }
        });

        adicionarSetorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Estoque estoqueSelecionado = (Estoque) estoqueComboBox.getSelectedItem();
                if (estoqueSelecionado != null) {
                    String nomeSetor = JOptionPane.showInputDialog("Nome do Setor:");
                    if (nomeSetor != null && !nomeSetor.isEmpty()) {
                        controller.adicionarSetor(estoqueSelecionado, nomeSetor);
                        updateSetorList(estoqueSelecionado);
                    }
                }
            }
        });

        removerSetorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Estoque estoqueSelecionado = (Estoque) estoqueComboBox.getSelectedItem();
                Setor setorSelecionado = setorList.getSelectedValue();
                if (estoqueSelecionado != null && setorSelecionado != null) {
                    controller.removerSetor(estoqueSelecionado, setorSelecionado);
                    updateSetorList(estoqueSelecionado);
                    clearProdutoList();
                }
            }
        });

        adicionarProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Setor setorSelecionado = setorList.getSelectedValue();
                if (setorSelecionado != null) {
                    String nomeProduto = JOptionPane.showInputDialog("Nome do Produto:");
                    String precoProdutoStr = JOptionPane.showInputDialog("Preço do Produto:");
                    String quantidadeProdutoStr = JOptionPane.showInputDialog("Quantidade do Produto:");

                    if (nomeProduto != null && !nomeProduto.isEmpty() && precoProdutoStr != null && !precoProdutoStr.isEmpty() && quantidadeProdutoStr != null && !quantidadeProdutoStr.isEmpty()) {
                        try {
                            double precoProduto = Double.parseDouble(precoProdutoStr);
                            int quantidadeProduto = Integer.parseInt(quantidadeProdutoStr);
                            controller.adicionarProduto(setorSelecionado, nomeProduto, precoProduto, quantidadeProduto);
                            updateProdutoList(setorSelecionado);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(mainPanel, "Preço ou quantidade inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        removerProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Setor setorSelecionado = setorList.getSelectedValue();
                Produto produtoSelecionado = produtoList.getSelectedValue();
                if (setorSelecionado != null && produtoSelecionado != null) {
                    controller.removerProduto(setorSelecionado, produtoSelecionado);
                    updateProdutoList(setorSelecionado);
                }
            }
        });

        alterarProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Setor setorSelecionado = setorList.getSelectedValue();
                Produto produtoSelecionado = produtoList.getSelectedValue();
                if (setorSelecionado != null && produtoSelecionado != null) {
                    String novoNome = JOptionPane.showInputDialog("Novo Nome do Produto:", produtoSelecionado.getNome());
                    String novoPrecoStr = JOptionPane.showInputDialog("Novo Preço do Produto:", produtoSelecionado.getPreco());
                    String novaQuantidadeStr = JOptionPane.showInputDialog("Nova Quantidade do Produto:", produtoSelecionado.getQuantidade());

                    if (novoNome != null && !novoNome.isEmpty() &&
                            novoPrecoStr != null && !novoPrecoStr.isEmpty() &&
                            novaQuantidadeStr != null && !novaQuantidadeStr.isEmpty()) {
                        try {
                            double novoPreco = Double.parseDouble(novoPrecoStr);
                            int novaQuantidade = Integer.parseInt(novaQuantidadeStr);
                            controller.alterarProduto(setorSelecionado, produtoSelecionado, novoNome, novoPreco, novaQuantidade);
                            updateProdutoList(setorSelecionado);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(mainPanel, "Preço ou quantidade inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        estoqueComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Estoque estoqueSelecionado = (Estoque) estoqueComboBox.getSelectedItem();
                if (estoqueSelecionado != null) {
                    updateSetorList(estoqueSelecionado);
                    clearProdutoList();
                }
            }
        });

        setorList.addListSelectionListener(e -> {
            Setor setorSelecionado = setorList.getSelectedValue();
            if (setorSelecionado != null) {
                updateProdutoList(setorSelecionado);
            }
        });

        updateEstoqueComboBox();
    }

    private void updateEstoqueComboBox() {
        estoqueComboBox.removeAllItems();
        for (Estoque estoque : controller.getEstoques()) {
            estoqueComboBox.addItem(estoque);
        }
    }

    private void updateSetorList(Estoque estoque) {
        DefaultListModel<Setor> setorListModel = new DefaultListModel<>();
        for (Setor setor : estoque.getSetores()) {
            setorListModel.addElement(setor);
        }
        setorList.setModel(setorListModel);
    }

    private void updateProdutoList(Setor setor) {
        DefaultListModel<Produto> produtoListModel = new DefaultListModel<>();
        for (Produto produto : setor.getProdutos()) {
            produtoListModel.addElement(produto);
        }
        produtoList.setModel(produtoListModel);
    }

    private void clearSetorAndProdutoLists() {
        DefaultListModel<Setor> setorListModel = new DefaultListModel<>();
        setorList.setModel(setorListModel);
        clearProdutoList();
    }

    private void clearProdutoList() {
        DefaultListModel<Produto> produtoListModel = new DefaultListModel<>();
        produtoList.setModel(produtoListModel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        // Custom component creation code if needed
    }
}

package Controller;

import entity.Estoque;
import entity.Produto;
import entity.Setor;

import java.util.ArrayList;

public class EstoqueController {
    private ArrayList<Estoque> estoques;

    public EstoqueController() {
        estoques = new ArrayList<>();
    }

    public void adicionarEstoque(String nomeEstoque) {
        Estoque novoEstoque = new Estoque(nomeEstoque);
        estoques.add(novoEstoque);
    }

    public void removerEstoque(int estoqueSelecionado) {
        estoques.remove(estoqueSelecionado);
    }

    public void adicionarSetor(Estoque estoqueSelecionado, String nomeSetor) {
        Setor novoSetor = new Setor(nomeSetor);
        estoqueSelecionado.adicionarSetor(novoSetor);
    }

    public void removerSetor(Estoque estoqueSelecionado, Setor setorSelecionado) {
        estoqueSelecionado.removerSetor(setorSelecionado);
    }

    public void adicionarProduto(Setor setorSelecionado, String nomeProduto, double precoProduto, int quantidadeProduto) {
        Produto novoProduto = new Produto(nomeProduto, precoProduto, quantidadeProduto);
        setorSelecionado.adicionarProduto(novoProduto);
    }

    public void removerProduto(Setor setorSelecionado, Produto produtoSelecionado) {
        setorSelecionado.removerProduto(produtoSelecionado);
    }

    public ArrayList<Estoque> getEstoques() {
        return estoques;
    }


    public void alterarProduto(Setor setor, Produto produto, String novoNome, double novoPreco, int novaQuantidade) {

        Setor setorEncontrado = setor;

        if (setorEncontrado != null) {

            Produto produtoEncontrado = null;
            for (Produto p : setorEncontrado.getProdutos()) {
                if (p.equals(produto)) {
                    produtoEncontrado = p;
                    break;
                }
            }
            if (produtoEncontrado != null) {
                produtoEncontrado.setNome(novoNome);
                produtoEncontrado.setPreco(novoPreco);
                produtoEncontrado.setQuantidade(novaQuantidade);

            } else {
                throw new IllegalArgumentException("Produto não encontrado no setor.");
            }
        } else {
            throw new IllegalArgumentException("Setor não encontrado.");
        }
    }
}



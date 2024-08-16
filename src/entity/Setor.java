package entity;
import java.util.ArrayList;

public class Setor {
    private String nome;
    private ArrayList<Produto> produtos;

    public Setor(String nome) {
        this.nome = nome;
        this.produtos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    @Override
    public String toString() {
        return nome;
    }
}


package entity;
import java.util.ArrayList;

public class Estoque {
    private String nome;
    private ArrayList<Setor> setores;

    public Estoque(String nome) {
        this.nome = nome;
        this.setores = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Setor> getSetores() {
        return setores;
    }

    public void adicionarSetor(Setor setor) {
        setores.add(setor);
    }

    public void removerSetor(Setor setor) {
        setores.remove(setor);
    }

    @Override
    public String toString() {
        return nome;
    }
}


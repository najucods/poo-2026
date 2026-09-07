/* USO DE IA */
/* Ferramenta utilizada: ChatGPT */
/* A IA foi utilizada para auxiliar na implementação da classe abstrata, polimorfismo, 
método habilidade() e no uso de instanceof.*/
/* O instanceof foi usado apenas para identificar os Magos e acessar uma característica exclusiva deles: a mana, não 
foi usado dentro do laço da batalha porque isso faria verificações do tipo de cada personagem. */

import java.util.ArrayList;
import java.util.List;

class Item {

    private String nome;
    private int bonus;

    public Item(String nome, int bonus) {
        this.nome = nome;
        setBonus(bonus);
    }

    public String getNome() {
        return nome;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        if (bonus >= 0) {
            this.bonus = bonus;
        } else {
            System.out.println("Bonus não pode ser negativo.");
        }
    }

    public String descricao() {
        return nome + " (+" + bonus + ")";
    }
}

abstract class Personagem {

    protected String nome;
    protected int vida;
    protected int nivel;
    protected int forca;

    private Item[] inventario;
    private int quantidadeItens;

    public Personagem(String nome, int vida, int nivel, int forca) {
        setNome(nome);
        setVida(vida);
        setNivel(nivel);
        setForca(forca);

        inventario = new Item[10];
        quantidadeItens = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!nome.equals("")) {
            this.nome = nome;
        } else {
            System.out.println("Nome não pode ser vazio.");
        }
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida >= 0 && vida <= 200) {
            this.vida = vida;
        } else {
            System.out.println("Vida deve estar entre 0 e 200.");
        }
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if (nivel >= 1) {
            this.nivel = nivel;
        } else {
            System.out.println("Nivel deve ser maior ou igual a 1.");
        }
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        if (forca >= 0) {
            this.forca = forca;
        } else {
            System.out.println("Forca não pode ser negativa.");
        }
    }

    public void pegar(Item item) {
        if (quantidadeItens < inventario.length) {
            inventario[quantidadeItens] = item;
            quantidadeItens++;
        } else {
            System.out.println("Inventario cheio.");
        }
    }

    public void receberDano(int dano) {
        setVida(Math.max(0, vida - dano));
    }

    public void atacar(Personagem alvo) {
        alvo.receberDano(forca);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public String ficha() {

        String resultado =
                "Nome: " + nome +
                "\nVida: " + vida +
                "\nNivel: " + nivel +
                "\nForca: " + forca +
                "\nInventario:";

        if (quantidadeItens == 0) {
            resultado += "\nNenhum item";
        } else {
            for (int i = 0; i < quantidadeItens; i++) {
                resultado += "\n- " + inventario[i].descricao();
            }
        }

        return resultado;
    }

    public abstract String habilidade();
}

class Mago extends Personagem {

    private int mana;

    public Mago(String nome, int vida, int nivel, int forca) {
        super(nome, vida, nivel, forca);
        mana = 50;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana >= 0) {
            this.mana = mana;
        } else {
            System.out.println("Mana não pode ser negativa.");
        }
    }

    @Override
    public String habilidade() {
        return "rajada arcana";
    }

    @Override
    public String ficha() {
        return super.ficha() +
                "\nMana: " + mana;
    }
}

class Guerreiro extends Personagem {

    private int defesa;

    public Guerreiro(String nome, int vida, int nivel, int forca) {
        super(nome, vida, nivel, forca);
        defesa = 5;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        if (defesa >= 0) {
            this.defesa = defesa;
        } else {
            System.out.println("Defesa não pode ser negativa.");
        }
    }

    @Override
    public void receberDano(int dano) {
        int danoEfetivo = Math.max(0, dano - defesa);
        super.receberDano(danoEfetivo);
    }

    @Override
    public String habilidade() {
        return "escudo de aço";
    }

    @Override
    public String ficha() {
        return super.ficha() +
                "\nDefesa: " + defesa;
    }
}

class Chefe extends Personagem {

    public Chefe(String nome) {
        super(nome, 200, 1, 20);
    }

    @Override
    public String habilidade() {
        return "ataque devastador";
    }

    @Override
    public String ficha() {
        return "[CHEFE] " + nome +
                " (vida: " + vida +
                ", forca: " + forca + ")";
    }
}

public class Main {

    public static void main(String[] args) {

        Item espada = new Item("Espada de Aço", 3);

        Mago mago = new Mago("Elara", 100, 1, 30);
        mago.pegar(espada);

        Guerreiro guerreiro = new Guerreiro("Bran", 100, 1, 25);

        Chefe chefe = new Chefe("Rei Lich");

        List<Personagem> herois = new ArrayList<>();

        herois.add(mago);
        herois.add(guerreiro);

        System.out.println("----- BATALHA FINAL -----");
        System.out.println();

        for (Personagem heroi : herois) {

            System.out.println(heroi.ficha());

            System.out.println(
                    heroi.getNome() +
                    " usa " +
                    heroi.habilidade()
            );

            heroi.atacar(chefe);

            System.out.println(
                    "Rei Lich recebeu " +
                    heroi.getForca() +
                    " de dano."
            );

            System.out.println();
        }


        System.out.println("----- RESULTADO -----");
        System.out.println(chefe.ficha());

        for (Personagem personagem : herois) {

            if (personagem instanceof Mago) {

                Mago magoAtual = (Mago) personagem;

                System.out.println(
                        "Mana do Mago " +
                        magoAtual.getNome() +
                        ": " +
                        magoAtual.getMana()
                );
            }
        }
    }
}

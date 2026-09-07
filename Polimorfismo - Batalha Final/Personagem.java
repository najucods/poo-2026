/* USO DE IA */
/* Ferramenta utilizada: ChatGPT */

/* A IA foi utilizada para auxiliar na implementação do polimorfismo, das classes 
abstratas, do método habilidade(), e da classe Chefe e uso de instanceof, recebi
o auxílio  para enteder onde e por que usar.*/

import java.util.ArrayList;
import java.util.List;


abstract class Personagem {
    protected String nome;
    protected int vida;
    protected int forca;

    public Personagem(String nome, int vida, int forca) {
        this.nome = nome;
        this.vida = vida;
        this.forca = forca;
    }

    public void receberDano(int dano) {
        vida -= dano;

        if (vida < 0) {
            vida = 0;
        }
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void atacar(Personagem alvo) {
        alvo.receberDano(forca);
    }

    public String ficha() {
        return nome + " (vida: " + vida + ", forca: " + forca + ")";
    }

    public abstract String habilidade();
}

class Mago extends Personagem {
    private int mana;

    public Mago(String nome, int vida, int forca, int mana) {
        super(nome, vida, forca);
        this.mana = mana;
    }

    @Override
    public String habilidade() {
        return "rajada arcana";
    }

    public int getMana() {
        return mana;
    }
}

class Guerreiro extends Personagem {

    public Guerreiro(String nome, int vida, int forca) {
        super(nome, vida, forca);
    }

    @Override
    public String habilidade() {
        return "escudo de aço";
    }
}

class Chefe extends Personagem {

    public Chefe(String nome) {
        super(nome, 200, 20);
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

        List<Personagem> herois = new ArrayList<>();

        Mago elara = new Mago("Elara", 100, 30, 100);
        Guerreiro thor = new Guerreiro("Thor", 150, 25);

        herois.add(elara);
        herois.add(thor);

        Chefe chefe = new Chefe("Rei Lich");

        System.out.println("----- Batalha final -----");
        System.out.println();

        for (Personagem heroi : herois) {

            System.out.println(heroi.ficha());

            System.out.println(
                heroi.nome + " usa " + heroi.habilidade()
            );

            heroi.atacar(chefe);

            System.out.println("Rei Lich recebeu " +
                               heroi.forca + " de dano.");

            System.out.println();
        }

        System.out.println("----- Resultado -----");
        System.out.println(chefe.ficha());

        for (Personagem personagem : herois) {

            if (personagem instanceof Mago) {
                Mago mago = (Mago) personagem;

                System.out.println(
                    "Mana do Mago " + mago.nome + ": " + mago.getMana()
                );
            }
        }
    }
}

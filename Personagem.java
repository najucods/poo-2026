public class Personagem {

    String nome;
    int vida;
    int forca;

    public Personagem(String nomePersonagem, int vidaPersonagem, int forcaPersonagem) {
        nome = nomePersonagem;
        vida = vidaPersonagem;
        forca = forcaPersonagem;
    }

    void receberDano(int dano) {
        vida = vida - dano;

        if (vida < 0) {
            vida = 0;
        }

        System.out.println(nome + " sofreu " + dano + " de dano.");
    }

    boolean estaVivo() {
        return vida > 0;
    }

    String ficha() {
        return "Nome: " + nome + "\nVida: " + vida + "\nForca: " + forca;
    }

    void atacar(Personagem alvo) {
        System.out.println(nome + " atacou " + alvo.nome + ".");
        alvo.receberDano(forca);
    }
}

public class Main {

    public static void main(String[] args) {

        Personagem heroi = new Personagem("Jane", 200, 40);
        Personagem chefe = new Personagem("Angela", 200, 5);

        System.out.println("-----Jogadores-----");
        System.out.println(heroi.ficha());
        System.out.println();
        System.out.println(chefe.ficha());

        System.out.println("\n-----Hora da batalha-----");

        while (heroi.estaVivo() && chefe.estaVivo()) {

            heroi.atacar(chefe);

            if (!chefe.estaVivo()) {
                System.out.println(chefe.nome + " foi derrotado! \n");
                break;
            }

            chefe.atacar(heroi);

            if (!heroi.estaVivo()) {
                System.out.println(heroi.nome + " foi derrotado! \n");
                break;
            }
        }

        if (heroi.estaVivo()) {
            System.out.println(heroi.nome + " venceu! \n");
        } else {
            System.out.println(chefe.nome + " venceu! \n");
        }

        System.out.println("\n-----Jogardores-----");
        System.out.println(heroi.ficha());
        System.out.println();
        System.out.println(chefe.ficha());
    }
}

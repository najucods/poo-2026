public class Personagem {
    String nome;
    int vida;
    int forca;

    public Personagem(String n, int v, int f) {
        nome = n;
        vida = v;
        forca = f;
    }

    public void receberDano(int dano) {
        vida = vida - dano;

        if (vida < 0) {
            vida = 0;
        }
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void ficha() {
        System.out.println("Nome: " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Força: " + forca);
    }

    public void atacar(Personagem alvo) {
        alvo.receberDano(forca);

        System.out.println(nome + " atacou " + alvo.nome +" causando " + forca + " de dano!");
    }
}

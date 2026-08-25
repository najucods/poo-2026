class Personagem {

    private String nome;
    private int vida;
    private int nivel;

    public Personagem(String nomePersonagem, int vidaPersonagem, int nivelPersonagem) {
        setNome(nomePersonagem);
        setVida(vidaPersonagem);
        setNivel(nivelPersonagem);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!nome.isEmpty()) {
            this.nome = nome;
        } else {
            System.out.println("Nome não pode ser vazio.");
        }
    }

    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        if (vida >= 0 && vida <= 100) {
            this.vida = vida;
        } else {
            System.out.println("Vida deve estar entre 0 e 100.");
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

    String ficha() {
        return "Nome: " + nome + "\nVida: " + vida + "\nNivel: " + nivel;
    }
}
public class Main {

    public static void main(String[] args) {

        Personagem heroi = new Personagem("Jane", 100, 1);

        System.out.println("-----Personagem-----");
        System.out.println(heroi.ficha());

        System.out.println("\n-----Testando valores validos-----");

        heroi.setNome("Angela");
        heroi.setVida(80);
        heroi.setNivel(5);

        System.out.println(heroi.ficha());

        System.out.println("\n-----Testando valores invalidos-----");

        heroi.setNome("");
        heroi.setVida(150);
        heroi.setVida(-10);
        heroi.setNivel(0);

        System.out.println("\n-----Valores finais-----");
        System.out.println(heroi.ficha());
    }
}

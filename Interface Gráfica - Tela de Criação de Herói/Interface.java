/*Uso de IA: 
Ferramenta utilizada: ChatGPT

Usei o ChatGPT para me ajudar a entender e montar algumas partes do código, principalmente 
as exceções em Java e a nova parte da interface gráfica.Na parte das exceções, ele me ajudou 
com try/catch, finally, IllegalArgumentException, SemManaException e ForcaInsuficienteException.
Na interface gráfica, ajudou a montar a janela usando JFrame, os campos de texto, os rótulos, o botão e a 
organização dos * componentes com BorderLayout e GridLayout.*/


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        if (bonus < 0) {
            throw new IllegalArgumentException(
                "O bônus deve ser maior ou igual a 0."
            );
        }

        this.bonus = bonus;
    }

    public String descricao() {
        return nome + " (Bônus: " + bonus + ")";
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

        if (nome == null || nome.equals("")) {
            throw new IllegalArgumentException(
                "O nome não pode ser vazio."
            );
        }

        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {

        if (vida < 0 || vida > 200) {
            throw new IllegalArgumentException(
                "A vida deve estar entre 0 e 200."
            );
        }

        this.vida = vida;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {

        if (nivel < 1) {
            throw new IllegalArgumentException(
                "O nível deve ser maior ou igual a 1."
            );
        }

        this.nivel = nivel;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {

        if (forca < 0) {
            throw new IllegalArgumentException(
                "A força deve ser maior ou igual a 0."
            );
        }

        this.forca = forca;
    }

    public void pegar(Item item) {

        if (quantidadeItens >= 10) {
            System.out.println("Inventário cheio!");
            return;
        }

        inventario[quantidadeItens] = item;
        quantidadeItens++;

        System.out.println(nome + " pegou " + item.getNome() + ".");
    }

    public void receberDano(int dano) {

        int novaVida = Math.max(0, vida - dano);

        setVida(novaVida);
    }

    public void atacar(Personagem alvo) {

        int dano = forca;

        System.out.println(
            nome + " atacou " + alvo.getNome()
            + " e causou " + dano + " de dano!"
        );

        alvo.receberDano(dano);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void ficha() {

        System.out.println("\n--- FICHA ---");
        System.out.println("Nome: " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Nível: " + nivel);
        System.out.println("Força: " + forca);
    }

    public abstract String habilidade();
}

class SemManaException extends Exception {

    public SemManaException(int manaAtual) {

        super(
            "Mana insuficiente. Mana atual: "
            + manaAtual
            + ". É necessário ter pelo menos 10 de mana."
        );
    }
}

class ForcaInsuficienteException extends Exception {

    public ForcaInsuficienteException(int forcaAtual) {

        super(
            "Força insuficiente. Força atual: "
            + forcaAtual
            + ". É necessário ter pelo menos 30 de força."
        );
    }
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

        if (mana < 0 || mana > 100) {
            throw new IllegalArgumentException(
                "A mana deve estar entre 0 e 100."
            );
        }

        this.mana = mana;
    }

    @Override
    public String habilidade() {
        return "rajada arcana";
    }

    public void lancarFeitico() throws SemManaException {

        if (mana < 10) {
            throw new SemManaException(mana);
        }

        mana -= 10;

        System.out.println(
            getNome() + " lançou " + habilidade() + "!"
        );
    }

    @Override
    public void ficha() {

        super.ficha();

        System.out.println("Mana: " + mana);
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

        if (defesa < 0) {
            throw new IllegalArgumentException(
                "A defesa deve ser maior ou igual a 0."
            );
        }

        this.defesa = defesa;
    }

    @Override
    public void receberDano(int dano) {

        int danoFinal = Math.max(0, dano - defesa);

        System.out.println(
            getNome()
            + " recebeu "
            + danoFinal
            + " de dano (defesa: "
            + defesa
            + ")."
        );

        int novaVida = Math.max(0, getVida() - danoFinal);

        setVida(novaVida);
    }

    @Override
    public String habilidade() {
        return "escudo de aço";
    }

    public void golpeEspecial(Personagem alvo)
            throws ForcaInsuficienteException {

        if (forca < 30) {
            throw new ForcaInsuficienteException(forca);
        }

        int dano = forca + 10;

        System.out.println(
            getNome()
            + " usou "
            + habilidade()
            + " e causou "
            + dano
            + " de dano!"
        );

        alvo.receberDano(dano);
    }

    @Override
    public void ficha() {

        super.ficha();

        System.out.println("Defesa: " + defesa);
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
    public void ficha() {

        super.ficha();

        System.out.println("Tipo: Chefe");
    }
}

class TelaCriacaoHeroi extends JFrame {

    private JLabel titulo;

    private JTextField campoNome;
    private JTextField campoVida;
    private JTextField campoForca;

    private JButton botaoCriar;

    public TelaCriacaoHeroi() {

        super("IF Quest");

        setSize(500, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titulo = new JLabel(
            "Criação de Herói",
            SwingConstants.CENTER
        );

        JLabel labelNome = new JLabel("Nome do herói:");
        campoNome = new JTextField();

        JLabel labelVida = new JLabel("Vida inicial:");
        campoVida = new JTextField();


        JLabel labelForca = new JLabel("Força inicial:");
        campoForca = new JTextField();

        botaoCriar = new JButton("Criar Herói");

        setLayout(new BorderLayout(10, 10));

        add(titulo, BorderLayout.NORTH);

        JPanel painelFormulario = new JPanel();

        painelFormulario.setLayout(
            new GridLayout(3, 2, 5, 5)
        );


        painelFormulario.add(labelNome);
        painelFormulario.add(campoNome);

        painelFormulario.add(labelVida);
        painelFormulario.add(campoVida);

        painelFormulario.add(labelForca);
        painelFormulario.add(campoForca);

        add(
            painelFormulario,
            BorderLayout.CENTER
        );

        titulo.setText("Crie seu Herói!");

        System.out.println(
            "Nome digitado: " + campoNome.getText()
        );

        botaoCriar.setEnabled(false);

        add(
            botaoCriar,
            BorderLayout.SOUTH
        );
    }
}

public class Main {

    public static void main(String[] args) {

        Mago mago = null;
        Guerreiro guerreiro = null;
        Chefe chefe = null;
        try {

            mago = new Mago(
                "Elara",
                100,
                1,
                30
            );

            guerreiro = new Guerreiro(
                "Bran",
                100,
                1,
                25
            );

            chefe = new Chefe("Rei Lich");

            Guerreiro teste = new Guerreiro(
                "Teste",
                100,
                1,
                -10
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "\nErro ao criar personagem:"
            );

            System.out.println(e.getMessage());
        }

        Item espada = new Item(
            "Espada",
            10
        );

        mago.pegar(espada);

        ArrayList<Personagem> herois = new ArrayList<>();

        herois.add(mago);
        herois.add(guerreiro);

        System.out.println("       INÍCIO DA BATALHA");
        while (
            chefe.estaVivo()
            && (mago.estaVivo() || guerreiro.estaVivo())
        ) {
            for (Personagem personagem : herois) {

                if (!personagem.estaVivo()) {
                    continue;
                }
                if (!chefe.estaVivo()) {
                    break;
                }
                System.out.println("\n------------------------------");
                System.out.println(
                    "Turno de "
                    + personagem.getNome()
                );
                personagem.ficha();
                try {

                    if (personagem instanceof Mago) {

                        Mago m = (Mago) personagem;

                        try {

                            m.lancarFeitico();

                            m.atacar(chefe);

                        } catch (SemManaException e) {

                            System.out.println(
                                "\n" + e.getMessage()
                            );

                            System.out.println(
                                m.getNome()
                                + " ficou sem mana e perdeu o turno."
                            );
                            if (
                                guerreiro.estaVivo()
                                && chefe.estaVivo()
                            ) {

                                System.out.println(
                                    "O Guerreiro atacará "
                                    + "no lugar do Mago."
                                );

                                guerreiro.atacar(chefe);
                            }
                        }

                    } else if (
                        personagem instanceof Guerreiro
                    ) {

                        Guerreiro g =
                            (Guerreiro) personagem;


                        try {

                            g.golpeEspecial(chefe);

                        } catch (
                            ForcaInsuficienteException e
                        ) {

                            System.out.println(
                                "\n" + e.getMessage()
                            );

                            System.out.println(
                                "O Guerreiro não pode usar "
                                + "o golpe especial."
                            );

                            System.out.println(
                                "Ele fará um ataque normal."
                            );

                            g.atacar(chefe);
                        }

                    } else {

                        personagem.atacar(chefe);
                    }
                } finally {
                    System.out.println(
                        "Fim do turno."
                    );
                }
                if (!chefe.estaVivo()) {

                    System.out.println(
                        "\nO Rei Lich foi derrotado!"
                    );
                    break;
                }
            }
        }
        System.out.println("------RESULTADO DA BATALHA-----");
     
        if (!chefe.estaVivo()) {

            System.out.println(
                "Os heróis venceram!"
            );
        } else {
            System.out.println(
                "O Rei Lich venceu!"
            );
        }
        System.out.println(
            "\n--- STATUS DOS PERSONAGENS ---"
        );

        for (Personagem personagem : herois) {

            System.out.println(
                personagem.getNome()
                + " - Vida: "
                + personagem.getVida()
            );
            if (personagem instanceof Mago) {

                Mago m = (Mago) personagem;

                System.out.println(
                    "Mana: "
                    + m.getMana()
                );
            }
        }
        TelaCriacaoHeroi tela =
            new TelaCriacaoHeroi();

        tela.setVisible(true);
    }
}

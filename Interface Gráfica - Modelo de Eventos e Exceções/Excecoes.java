
/* Uso de IA */
/* Ferramenta utilizada: Chat GPT */
/* Utilizei a IA como auxílio para criar o botão da interface gráfica, principalmente 
na parte de configuração do botão e na hora de dar vida a ele. */

public class Item {

    private String nome;
    private int bonus;

    public Item(String nome, int bonus) {
        this.nome = nome;
        this.bonus = bonus;
    }

    public String getNome() {
        return nome;
    }

    public int getBonus() {
        return bonus;
    }
}

public abstract class Personagem {

    private String nome;
    private int vida;
    private int nivel;
    private int forca;

    private Item[] inventario;

    public Personagem(String nome, int vida, int nivel, int forca) {

        setNome(nome);
        setVida(vida);

        if (nivel < 1) {
            throw new IllegalArgumentException(
                    "O nível deve ser maior ou igual a 1."
            );
        }

        this.nivel = nivel;
        this.forca = forca;

        this.inventario = new Item[10];
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
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

    public int getForca() {
        return forca;
    }

    public void receberDano(int dano) {

        vida -= dano;

        if (vida < 0) {
            vida = 0;
        }
    }

    public void adicionarItem(Item item) {

        for (int i = 0; i < inventario.length; i++) {

            if (inventario[i] == null) {
                inventario[i] = item;
                return;
            }
        }
    }

    public abstract void habilidade();
}

public class Mago extends Personagem {

    private int mana;

    public Mago(String nome, int vida, int nivel, int forca) {

        super(nome, vida, nivel, forca);

        this.mana = 30;
    }

    public int getMana() {
        return mana;
    }

    public void lancarFeitico() throws SemManaException {

        if (mana < 10) {

            throw new SemManaException("Mago sem mana suficiente.");
        }

        mana -= 10;

        System.out.println(getNome() + " lançou um feitiço!");

        System.out.println("Mana restante: " + mana);
    }

    @Override
    public void habilidade() {

        System.out.println(getNome() + " usou uma habilidade de Mago.");
    }
}


public class Guerreiro extends Personagem {

    private int defesa;

    public Guerreiro(String nome, int vida, int nivel, int forca) {

        super(nome, vida, nivel, forca);

        this.defesa = 5;
    }

    public int getDefesa() {
        return defesa;
    }

    public void golpeEspecial(Personagem alvo)
            throws ForcaInsuficienteException {

        if (getForca() < 30) {

            throw new ForcaInsuficienteException(
                    "Força insuficiente para usar o golpe especial."
            );
        }

        System.out.println(getNome() + " usou o golpe especial!");

        alvo.receberDano(20);
    }

    @Override
    public void habilidade() {

        System.out.println(getNome() + " usou uma habilidade de Guerreiro.");
    }
}

public class Chefe extends Personagem {

    public Chefe(String nome) {

        super(nome, 200, 1, 40);
    }

    @Override
    public void habilidade() {

        System.out.println(getNome() + " usou uma habilidade de Chefe.");
    }
}

public class SemManaException extends Exception {

    public SemManaException(String mensagem) {
        super(mensagem);
    }
}

public class ForcaInsuficienteException extends Exception {

    public ForcaInsuficienteException(String mensagem) {
        super(mensagem);
    }
}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCriacaoHeroi extends JFrame {

    private JTextField campoNome;
    private JTextField campoVida;
    private JTextField campoForca;

    private JComboBox<String> campoClasse;

    private JLabel titulo;
    private JLabel rotuloStatus;

    private JButton botaoCriar;

    public TelaCriacaoHeroi() {

        super("IF Quest");

        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());


        titulo = new JLabel(
                "Criação de Herói",
                SwingConstants.CENTER
        );

        titulo.setText("Crie seu Herói!");

        add(titulo, BorderLayout.NORTH);


        JPanel painelCampos = new JPanel();

        painelCampos.setLayout(
                new GridLayout(4, 2, 5, 5)
        );


        painelCampos.add(
                new JLabel("Nome:")
        );

        campoNome = new JTextField();

        painelCampos.add(campoNome);


        painelCampos.add(
                new JLabel("Vida inicial:")
        );

        campoVida = new JTextField();

        painelCampos.add(campoVida);


        painelCampos.add(
                new JLabel("Força inicial:")
        );

        campoForca = new JTextField();

        painelCampos.add(campoForca);


        painelCampos.add(
                new JLabel("Classe:")
        );

        campoClasse = new JComboBox<>(
                new String[]{
                        "Mago",
                        "Guerreiro",
                        "Chefe"
                }
        );

        painelCampos.add(campoClasse);


        add(
                painelCampos,
                BorderLayout.CENTER
        );


        JPanel painelInferior = new JPanel();

        botaoCriar = new JButton("Criar Herói");

        botaoCriar.setEnabled(false);

        painelInferior.add(
                botaoCriar
        );

        rotuloStatus = new JLabel("");

        painelInferior.add(
                rotuloStatus
        );


        add(
                painelInferior,
                BorderLayout.SOUTH
        );

        botaoCriar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        botaoCriar.setEnabled(false);

                        criarHeroi();
                    }
                }
        );

        campoVida.addActionListener(
                e -> criarHeroi()
        );

        campoNome.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {

                    public void insertUpdate(
                            javax.swing.event.DocumentEvent e) {

                        verificarCampos();
                    }

                    public void removeUpdate(
                            javax.swing.event.DocumentEvent e) {

                        verificarCampos();
                    }

                    public void changedUpdate(
                            javax.swing.event.DocumentEvent e) {

                        verificarCampos();
                    }
                }
        );

        campoVida.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {

                    public void insertUpdate(
                            javax.swing.event.DocumentEvent e) {

                        verificarCampos();
                    }

                    public void removeUpdate(
                            javax.swing.event.DocumentEvent e) {

                        verificarCampos();
                    }

                    public void changedUpdate(
                            javax.swing.event.DocumentEvent e) {

                        verificarCampos();
                    }
                }
        );

        campoForca.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {

                    public void insertUpdate(
                            javax.swing.event.DocumentEvent e) {

                        verificarCampos();
                    }

                    public void removeUpdate(
                            javax.swing.event.DocumentEvent e) {

                        verificarCampos();
                    }

                    public void changedUpdate(
                            javax.swing.event.DocumentEvent e) {

                        verificarCampos();
                    }
                }
        );


        setVisible(true);
    }


    private void verificarCampos() {

        boolean preenchido =
                !campoNome.getText()
                        .trim()
                        .isEmpty()

                && !campoVida.getText()
                        .trim()
                        .isEmpty()

                && !campoForca.getText()
                        .trim()
                        .isEmpty();


        botaoCriar.setEnabled(
                preenchido
        );
    }

    private void criarHeroi() {

        try {

            String nome = campoNome.getText();


            int vida = Integer.parseInt(campoVida.getText());

            int forca = Integer.parseInt(campoForca.getText());


            String classe =
                    (String) campoClasse.getSelectedItem();


            Personagem heroi;


            if (classe.equals("Mago")) {

                heroi = new Mago(
                        nome,
                        vida,
                        1,
                        forca
                );

            } else if (classe.equals("Guerreiro")) {

                heroi = new Guerreiro(
                        nome,
                        vida,
                        1,
                        forca
                );

            } else {

                heroi = new Chefe(
                        nome
                );
            }

            rotuloStatus.setText("Herói criado");

            System.out.println("Herói criado");

            System.out.println("Classe: " + classe);

            System.out.println(
                    "Nome: " + heroi.getNome()
            );

            System.out.println("Vida: " + heroi.getVida());

            System.out.println("Força: " + heroi.getForca());


        } catch (NumberFormatException ex) {

            rotuloStatus.setText("Vida e força devem ser números!");


        } catch (IllegalArgumentException ex) {

            rotuloStatus.setText(
                    ex.getMessage()
            );


        } finally {

            botaoCriar.setEnabled(true);
        }
    }
}


public class Main {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(
                () -> {
                    new TelaCriacaoHeroi();
                }
        );


        try {

            Mago mago = new Mago(
                    "Elara",
                    100,
                    1,
                    30
            );


            Guerreiro guerreiro = new Guerreiro(
                    "Bran",
                    100,
                    1,
                    35
            );


            Chefe chefe = new Chefe(
                    "Rei Lich"
            );


            Item espada = new Item(
                    "Espada",
                    10
            );


            guerreiro.adicionarItem(
                    espada
            );


            System.out.println();
            System.out.println("=== TESTES ===");


            System.out.println("Mago: " + mago.getNome() );

            System.out.println("Mana: " + mago.getMana() );


            try {

                mago.lancarFeitico();

            } catch (SemManaException ex) {

                System.out.println(
                        ex.getMessage()
                );
            }


            try {

                guerreiro.golpeEspecial(
                        chefe
                );

            } catch (
                    ForcaInsuficienteException ex
            ) {

                System.out.println(
                        ex.getMessage()
                );
            }


            System.out.println("Vida do chefe: " + chefe.getVida()
            );


            System.out.println("Fim do turno.");


        } catch (
                IllegalArgumentException ex
        ) {

            System.out.println("Erro: " + ex.getMessage()
            );
        }
    }
}

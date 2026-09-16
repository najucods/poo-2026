/* REGISTRO DE USO DE INTELIGÊNCIA ARTIFICIAL*/
/*Ferramenta utilizada:ChatGPT*/

/* Para que foi utilizada: Foi utilizada para auxiliar na implementação do tratamento de
exceções em Java, incluindo IllegalArgumentException,try/catch, finally, SemManaException 
e ForcaInsuficienteException. 

O que foi modificado manualmente: o código foi revisado pelo chat, e o mesmo corrigiu alguns erros.*/*/

import java.util.ArrayList;
import java.util.List;



class SemManaException extends Exception {

    public SemManaException(int manaAtual) {
        super("Mana insuficiente. Mana atual: " + manaAtual
                + ". É necessário ter pelo menos 10 de mana.");
    }
}

class ForcaInsuficienteException extends Exception {

    public ForcaInsuficienteException(int forcaAtual) {
        super("Forca insuficiente. Forca atual: " + forcaAtual
                + ". É necessário ter pelo menos 30 de forca.");
    }
}

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
            throw new IllegalArgumentException(
                    "Bonus deve ser maior ou igual a 0."
            );
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
            throw new IllegalArgumentException(
                    "Nome não pode ser vazio."
            );
        }
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida >= 0 && vida <= 200) {
            this.vida = vida;
        } else {
            throw new IllegalArgumentException(
                    "Vida deve estar entre 0 e 200."
            );
        }
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if (nivel >= 1) {
            this.nivel = nivel;
        } else {
            throw new IllegalArgumentException(
                    "Nivel deve ser maior ou igual a 1."
            );
        }
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        if (forca >= 0) {
            this.forca = forca;
        } else {
            throw new IllegalArgumentException(
                    "Forca deve ser maior ou igual a 0."
            );
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
            throw new IllegalArgumentException(
                    "Mana deve ser maior ou igual a 0."
            );
        }
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
            throw new IllegalArgumentException(
                    "Defesa deve ser maior ou igual a 0."
            );
        }
    }

    public void golpeEspecial(Personagem alvo)
            throws ForcaInsuficienteException {

        if (forca < 30) {
            throw new ForcaInsuficienteException(forca);
        }

        System.out.println(
                getNome() + " usou o golpe especial!"
        );

        alvo.receberDano(forca + 10);
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

        Mago mago = null;
        Guerreiro guerreiro = null;
        Chefe chefe = null;

        try {
            
            mago = new Mago("Elara", 100, 1, 30);
            mago.pegar(espada);

            System.out.println(
                    "Mago criado com sucesso."
            );

            guerreiro = new Guerreiro("Bran", 100, 1, 25);

            System.out.println(
                    "Guerreiro criado com sucesso."
            );

            Guerreiro teste = new Guerreiro(
                    "Teste",
                    100,
                    1,
                    -10
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Não foi possível criar o personagem."
            );

            System.out.println(
                    "Motivo: " + e.getMessage()
            );
        }

        chefe = new Chefe("Rei Lich");

        List<Personagem> herois = new ArrayList<>();

        herois.add(mago);
        herois.add(guerreiro);

        System.out.println();
        System.out.println("----- BATALHA FINAL -----");
        System.out.println();


        for (Personagem heroi : herois) {

            System.out.println(heroi.ficha());

            try {

                if (heroi instanceof Mago) {

                    Mago magoAtual = (Mago) heroi;

                    magoAtual.lancarFeitico();

                    magoAtual.atacar(chefe);

                    System.out.println(
                            "Rei Lich recebeu " +
                            magoAtual.getForca() +
                            " de dano."
                    );
                }
                else if (heroi instanceof Guerreiro) {

                    Guerreiro guerreiroAtual =
                            (Guerreiro) heroi;

                    try {
                        guerreiroAtual.golpeEspecial(chefe);

                    } catch (ForcaInsuficienteException e) {

                        System.out.println(
                                "O Guerreiro não conseguiu usar "
                                + "o golpe especial."
                        );

                        System.out.println(
                                "Motivo: " + e.getMessage()
                        );
                        guerreiroAtual.atacar(chefe);

                        System.out.println(
                                "O Guerreiro realizou um ataque normal."
                        );

                        System.out.println(
                                "Rei Lich recebeu " +
                                guerreiroAtual.getForca() +
                                " de dano."
                        );
                    }
                }
            } catch (SemManaException e) {

                System.out.println(
                        "O Mago ficou sem mana e perdeu o turno."
                );

                System.out.println(
                        "Guerreiro atacará no lugar dele."
                );

                guerreiro.atacar(chefe);

                System.out.println(
                        "Rei Lich recebeu " +
                        guerreiro.getForca() +
                        " de dano."
                );


            } finally {
                System.out.println(
                        "Fim do turno."
                );

                System.out.println();
            }
        }

        System.out.println("----- RESULTADO -----");
        System.out.println(chefe.ficha());

        for (Personagem personagem : herois) {

            if (personagem instanceof Mago) {

                Mago magoAtual =
                        (Mago) personagem;

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

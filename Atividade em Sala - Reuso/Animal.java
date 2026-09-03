*/Utilizei a IA como um auxílio para entender herança e como aplicar polimorfismo no código e também para ajudar a organizar a estrutura do código*/
  
abstract class Animal {

    private String nome;
    private int idade;

    public Animal(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    public void mostrarDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }

}

class Cachorro extends Animal {

    private String raca;

    public Cachorro(String nome, int idade, String raca) {

        super(nome, idade);
        this.raca = raca;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();

        System.out.println("Raça do animal: " + raca);
    }
}

class Gato extends Animal {

    private String cor;

    public Gato(String nome, int idade, String cor) {

        super(nome, idade);

        this.cor = cor;
    }

    @Override
    public void mostrarDados() {

        super.mostrarDados();

        System.out.println("Cor da pelagem: " + cor);
    }
}

public class Main {

    public static void main(String[] args) {

        Animal[] animais = {
            new Cachorro("Kiara", 2, "Burriler"),
            new Gato("Bina", 3, "Branca"),
            new Cachorro("Felipe", 5, "Pitbull"),
            new Gato("Cleiton", 1, "Preto com branco")
        };

        System.out.println("----- Animais -----");

        for (Animal animal : animais) {

            animal.mostrarDados();
         
            System.out.println(" ");
        }
    }
}

/*È uma classe animal com duas subclasses que estão dentro de animal, sendo essas cachorro que é um animal, é gato que tambem é um animal, na classe animal
tem o que "todo animal tem", e cada subclasse tem um atributos próprio. Os atributos de animal são o nome e a idade do animal, e o atributo próprio de cachorro 
é a raça dele, ja o do gato e a cor da sua pelagem.*/

/* USO DE IA */

/* Ferramenta utilizada: ChatGPT */
/*A IA foi utilizada para auxiliar na aplicação da classe abstrata, polimorfismo, método habilidade () e o uso do isistance*/
/* O isinstance foi usado apenas para identificar os Magos e acessar uma característica exclusiva deles: a mana. Não foi usado
isinstance dentro do laço da batalha porque isso faria verificações do tipo de cada personagem.*/

from abc import ABC, abstractmethod

class Item:

    def __init__(self, nome, bonus):
        self.__nome = nome
        self.set_bonus(bonus)

    def get_nome(self):
        return self.__nome

    def get_bonus(self):
        return self.__bonus

    def set_bonus(self, bonus):
        if bonus >= 0:
            self.__bonus = bonus
        else:
            print("Bonus não pode ser negativo.")

    def descricao(self):
        return self.__nome + " (+" + str(self.__bonus) + ")"

class Personagem(ABC):

    def __init__(self, nome, vida, nivel, forca):
        self.set_nome(nome)
        self.set_vida(vida)
        self.set_nivel(nivel)
        self.set_forca(forca)

        self.__inventario = [None] * 10
        self.__quantidadeItens = 0

    def get_nome(self):
        return self.__nome

    def set_nome(self, nome):
        if nome != "":
            self.__nome = nome
        else:
            print("Nome não pode ser vazio.")

    def get_vida(self):
        return self.__vida

    def set_vida(self, vida):
        if 0 <= vida <= 200:
            self.__vida = vida
        else:
            print("Vida deve estar entre 0 e 200.")

    def get_nivel(self):
        return self.__nivel

    def set_nivel(self, nivel):
        if nivel >= 1:
            self.__nivel = nivel
        else:
            print("Nivel deve ser maior ou igual a 1.")

    def get_forca(self):
        return self.__forca

    def set_forca(self, forca):
        if forca >= 0:
            self.__forca = forca
        else:
            print("Forca não pode ser negativa.")

    def pegar(self, item):
        if self.__quantidadeItens < len(self.__inventario):
            self.__inventario[self.__quantidadeItens] = item
            self.__quantidadeItens += 1
        else:
            print("Inventario cheio.")

    def receber_dano(self, dano):
        self.set_vida(max(0, self.__vida - dano))

    def atacar(self, alvo):
        alvo.receber_dano(self.__forca)

    def ficha(self):

        resultado = (
            "Nome: " + self.__nome +
            "\nVida: " + str(self.__vida) +
            "\nNivel: " + str(self.__nivel) +
            "\nForca: " + str(self.__forca) +
            "\nInventario:"
        )

        if self.__quantidadeItens == 0:
            resultado += "\nNenhum item"
        else:
            for i in range(self.__quantidadeItens):
                resultado += "\n- " + self.__inventario[i].descricao()

        return resultado

    @abstractmethod
    def habilidade(self):
        pass


class Mago(Personagem):

    def __init__(self, nome, vida, nivel, forca):
        super().__init__(nome, vida, nivel, forca)
        self.__mana = 50

    def get_mana(self):
        return self.__mana

    def set_mana(self, mana):
        if mana >= 0:
            self.__mana = mana
        else:
            print("Mana não pode ser negativa.")

    def habilidade(self):
        return "rajada arcana"

    def ficha(self):
        return super().ficha() + "\nMana: " + str(self.__mana)


class Guerreiro(Personagem):

    def __init__(self, nome, vida, nivel, forca):
        super().__init__(nome, vida, nivel, forca)
        self.__defesa = 5

    def get_defesa(self):
        return self.__defesa

    def set_defesa(self, defesa):
        if defesa >= 0:
            self.__defesa = defesa
        else:
            print("Defesa não pode ser negativa.")

    def receber_dano(self, dano):
        dano_efetivo = max(0, dano - self.__defesa)
        super().receber_dano(dano_efetivo)

    def habilidade(self):
        return "escudo de aço"

    def ficha(self):
        return super().ficha() + "\nDefesa: " + str(self.__defesa)


class Chefe(Personagem):

    def __init__(self, nome):
        super().__init__(nome, 200, 1, 20)

    def habilidade(self):
        return "ataque devastador"

    def ficha(self):
        return (
            "[CHEFE] " + self.get_nome() +
            " (vida: " + str(self.get_vida()) +
            ", forca: " + str(self.get_forca()) + ")"
        )

espada = Item("Espada de Aço", 3)

mago = Mago("Elara", 100, 1, 30)
mago.pegar(espada)

guerreiro = Guerreiro("Bran", 100, 1, 25)

chefe = Chefe("Rei Lich")

herois = [mago, guerreiro]

print("----- BATALHA FINAL -----")
print()

for heroi in herois:

    print(heroi.ficha())
    print(heroi.get_nome() + " usa " + heroi.habilidade())

    heroi.atacar(chefe)

    print("Rei Lich recebeu",
          heroi.get_forca(), "de dano.")
    print()


print("----- RESULTADO -----")
print(chefe.ficha())

for personagem in herois:

    if isinstance(personagem, Mago):
        print(
            "Mana do Mago",
            personagem.get_nome() + ":",
            personagem.get_mana()
        )

/*Uso de IA:*/
/*Ferramenta utilizada: ChatGPT.*/

/* Usei o ChatGPT para me ajudar a entender e fazer algumas partes
do código e das exceções, como try/except, finally, e SemManaException e ForcaInsuficienteException.*/

/*Depois o chat revisou e corrigiu alguns erros.*/


class Item:
    def __init__(self, nome, bonus):
        self.__nome = nome
        self.set_bonus(bonus)

    def get_nome(self):
        return self.__nome

    def get_bonus(self):
        return self.__bonus

    def set_bonus(self, bonus):
        if bonus < 0:
            raise ValueError("O bônus deve ser maior ou igual a 0.")
        self.__bonus = bonus

    def descricao(self):
        return f"{self.__nome} (Bônus: {self.__bonus})"


class Personagem:
    def __init__(self, nome, vida, nivel, forca):
        self.set_nome(nome)
        self.set_vida(vida)
        self.set_nivel(nivel)
        self.set_forca(forca)

        self.__inventario = [None] * 10
        self.__quantidade_itens = 0

    def get_nome(self):
        return self.__nome

    def set_nome(self, nome):
        if nome == "":
            raise ValueError("O nome não pode ser vazio.")
        self.__nome = nome

    def get_vida(self):
        return self.__vida

    def set_vida(self, vida):
        if vida < 0 or vida > 200:
            raise ValueError("A vida deve estar entre 0 e 200.")
        self.__vida = vida

    def get_nivel(self):
        return self.__nivel

    def set_nivel(self, nivel):
        if nivel < 1:
            raise ValueError("O nível deve ser maior ou igual a 1.")
        self.__nivel = nivel

    def get_forca(self):
        return self.__forca

    def set_forca(self, forca):
        if forca < 0:
            raise ValueError("A força deve ser maior ou igual a 0.")
        self.__forca = forca

    def pegar(self, item):
        if self.__quantidade_itens >= 10:
            print("Inventário cheio!")
            return

        self.__inventario[self.__quantidade_itens] = item
        self.__quantidade_itens += 1
        print(f"{self.__nome} pegou {item.get_nome()}.")

    def receber_dano(self, dano):
        nova_vida = max(0, self.__vida - dano)
        self.set_vida(nova_vida)

    def atacar(self, alvo):
        dano = self.__forca
        print(f"{self.__nome} atacou {alvo.get_nome()} e causou {dano} de dano!")
        alvo.receber_dano(dano)

    def esta_vivo(self):
        return self.__vida > 0

    def ficha(self):
        print("\n--- FICHA ---")
        print(f"Nome: {self.__nome}")
        print(f"Vida: {self.__vida}")
        print(f"Nível: {self.__nivel}")
        print(f"Força: {self.__forca}")

    def habilidade(self):
        raise NotImplementedError("A classe filha deve implementar habilidade().")

class SemManaException(Exception):
    def __init__(self, mana_atual):
        super().__init__(
            f"Mana insuficiente. Mana atual: {mana_atual}. "
            f"É necessário ter pelo menos 10 de mana."
        )

class ForcaInsuficienteException(Exception):
    def __init__(self, forca_atual):
        super().__init__(
            f"Força insuficiente. Força atual: {forca_atual}. "
            f"É necessário ter pelo menos 30 de força."
        )


class Mago(Personagem):
    def __init__(self, nome, vida, nivel, forca):
        super().__init__(nome, vida, nivel, forca)
        self.__mana = 50

    def get_mana(self):
        return self.__mana

    def set_mana(self, mana):
        if mana < 0 or mana > 100:
            raise ValueError("A mana deve estar entre 0 e 100.")
        self.__mana = mana

    def habilidade(self):
        return "rajada arcana"

    def lancar_feitico(self):
        if self.__mana < 10:
            raise SemManaException(self.__mana)

        self.__mana -= 10
        print(f"{self.get_nome()} lançou {self.habilidade()}!")

    def ficha(self):
        super().ficha()
        print(f"Mana: {self.__mana}")


class Guerreiro(Personagem):
    def __init__(self, nome, vida, nivel, forca):
        super().__init__(nome, vida, nivel, forca)
        self.__defesa = 5

    def get_defesa(self):
        return self.__defesa

    def set_defesa(self, defesa):
        if defesa < 0:
            raise ValueError("A defesa deve ser maior ou igual a 0.")
        self.__defesa = defesa

    def receber_dano(self, dano):
        dano_final = max(0, dano - self.__defesa)

        print(
            f"{self.get_nome()} recebeu {dano_final} de dano "
            f"(defesa: {self.__defesa})."
        )

        nova_vida = max(0, self.get_vida() - dano_final)
        self.set_vida(nova_vida)

    def habilidade(self):
        return "escudo de aço"

   
    def golpe_especial(self, alvo):
        if self.get_forca() < 30:
            raise ForcaInsuficienteException(self.get_forca())

        dano = self.get_forca() + 10

        print(
            f"{self.get_nome()} usou {self.habilidade()} "
            f"e causou {dano} de dano!"
        )

        alvo.receber_dano(dano)

    def ficha(self):
        super().ficha()
        print(f"Defesa: {self.__defesa}")


class Chefe(Personagem):
    def __init__(self, nome):
        super().__init__(nome, 200, 1, 20)

    def habilidade(self):
        return "ataque devastador"

    def ficha(self):
        super().ficha()
        print("Tipo: Chefe")

try:
    
    mago = Mago("Elara", 100, 1, 30)
    guerreiro = Guerreiro("Bran", 100, 1, 25)
    chefe = Chefe("Rei Lich")

    teste = Guerreiro("Teste", 100, 1, -10)

except ValueError as e:
    print("\nErro ao criar personagem:")
    print(e)

herois = [mago, guerreiro]

espada = Item("Espada", 10)

mago.pegar(espada)

print("-----INÍCIO DA BATALHA-----")

while chefe.esta_vivo() and any(personagem.esta_vivo() for personagem in herois):

    for personagem in herois:

        if not personagem.esta_vivo():
            continue

        if not chefe.esta_vivo():
            break

        print("\n------------------------------")
        print(f"Turno de {personagem.get_nome()}")
        personagem.ficha()

        try:

            if isinstance(personagem, Mago):

                try:
                    personagem.lancar_feitico()
                    personagem.atacar(chefe)

                except SemManaException as e:
                    print(f"\n{e}")
                    print(f"{personagem.get_nome()} ficou sem mana e perdeu o turno.")

                    if guerreiro.esta_vivo() and chefe.esta_vivo():
                        print("O Guerreiro atacará no lugar do Mago.")
                        guerreiro.atacar(chefe)

            elif isinstance(personagem, Guerreiro):

                try:
                    personagem.golpe_especial(chefe)

                except ForcaInsuficienteException as e:
                    print(f"\n{e}")
                    print("O Guerreiro não pode usar o golpe especial.")
                    print("Ele fará um ataque normal.")

                    personagem.atacar(chefe)

            else:
                personagem.atacar(chefe)

        finally:

            print("Fim do turno.")

        if not chefe.esta_vivo():
            print("\nO Rei Lich foi derrotado!")
            break

print("-----RESULTADO DA BATALHA-----")

if not chefe.esta_vivo():
    print("Os heróis venceram!")

else:
    print("O Rei Lich venceu!")

print("\n--- STATUS DOS PERSONAGENS ---")

for personagem in herois:
    print(
        f"{personagem.get_nome()} - "
        f"Vida: {personagem.get_vida()}"
    )
    if isinstance(personagem, Mago):
        print(f"Mana: {personagem.get_mana()}")

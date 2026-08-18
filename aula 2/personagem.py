class Personagem:

    def _init_(self, nome, vida, forca):
        self.nome = nome
        self.vida = vida
        self.forca = forca

    def receberDano(self, dano):
        self.vida = self.vida - dano
        print(self.nome, "sofreu", dano, "de dano")

    def estarvivo(self):
        if self.vida > 0:
            return True
        else:
            return False

    def ficha(self):
        return self.nome + " vida: " + str(self.vida) + " força: " + str(self.forca)

    def atacar(self, alvo):
        print(self.nome, "ataca", alvo.nome)
        alvo.receberDano(self.forca)


if _name_ == "_main_":

    heroi = Personagem("Mulan", 200, 40)
    chefe = Personagem("Thanos", 200, 5)

    while heroi.estarvivo() and chefe.estarvivo():

        heroi.atacar(chefe)
        print(heroi.nome, "atacou", chefe.nome,
              ". Vida do chefe:", chefe.vida)

        if not chefe.estarvivo():
            print(chefe.nome, "foi derrotado!", heroi.nome, "venceu!")
            break

        chefe.atacar(heroi)
        print(chefe.nome, "atacou", heroi.nome,
              ". Vida do herói:", heroi.vida)

        if not heroi.estarvivo():
            print(heroi.nome, "foi derrotado!", chefe.nome, "venceu!")
            break

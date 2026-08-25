class Personagem:

    def __init__(self, nome, vida, nivel):
        self.nome = nome
        self.vida = vida
        self.nivel = nivel

    @property
    def nome(self):
        return self._nome

    @nome.setter
    def nome(self, nome):
        if nome != "":
            self._nome = nome
        else:
            print("Nome não pode ser vazio.")

    @property
    def vida(self):
        return self._vida

    @vida.setter
    def vida(self, vida):
        if vida >= 0 and vida <= 100:
            self._vida = vida
        else:
            print("Vida deve estar entre 0 e 100.")

    @property
    def nivel(self):
        return self._nivel

    @nivel.setter
    def nivel(self, nivel):
        if nivel >= 1:
            self._nivel = nivel
        else:
            print("Nivel deve ser maior ou igual a 1.")

    def ficha(self):
        return "Nome: " + self.nome + "\nVida: " + str(self.vida) + "\nNivel: " + str(self.nivel)

print("-----Personagem-----")
heroi = Personagem("Jane", 100, 1)
print(heroi.ficha())

print("\n-----Testando valores validos-----")
heroi.nome = "Angela"
heroi.vida = 80
heroi.nivel = 5
print(heroi.ficha())

print("\n-----Testando valores invalidos-----")
heroi.nome = ""
heroi.vida = 150
heroi.vida = -10
heroi.nivel = 0

print("\n-----Valores finais-----")
print(heroi.ficha())

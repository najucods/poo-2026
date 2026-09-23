/* Uso de IA */
/* Ferramenta utilizada: Chat GPT */
/* Utilizei a IA como auxílio para criar o botão da interface 
gráfica em Python e configurar sua função de clique, também recebi ajuda 
para adaptar essa parte ao código da atividade */


import tkinter as tk
from tkinter import messagebox

class Personagem:
    def __init__(self, nome, vida, nivel, forca):
        if nome.strip() == "":
            raise ValueError("O nome não pode ser vazio.")

        if vida < 0 or vida > 200:
            raise ValueError("A vida deve estar entre 0 e 200.")

        if nivel < 1:
            raise ValueError("O nível deve ser maior ou igual a 1.")

        self.nome = nome
        self.vida = vida
        self.nivel = nivel
        self.forca = forca

    def habilidade(self):
        pass
    
class Mago(Personagem):
    def __init__(self, nome, vida, nivel, forca):
        super().__init__(nome, vida, nivel, forca)
        self.mana = 30

    def habilidade(self):
        print(self.nome + " usou uma habilidade de Mago.")

class Guerreiro(Personagem):
    def __init__(self, nome, vida, nivel, forca):
        super().__init__(nome, vida, nivel, forca)
        self.defesa = 5

    def habilidade(self):
        print(self.nome + " usou uma habilidade de Guerreiro.")

class Chefe(Personagem):
    def __init__(self, nome):
        super().__init__(nome, 200, 1, 40)

    def habilidade(self):
        print(self.nome + " usou uma habilidade de Chefe.")

def criar_heroi():

    botao_criar.config(state="disabled")

    try:
        nome = campo_nome.get()
        vida = int(campo_vida.get())
        forca = int(campo_forca.get())
        classe = campo_classe.get()

        if classe == "Mago":
            heroi = Mago(nome, vida, 1, forca)

        elif classe == "Guerreiro":
            heroi = Guerreiro(nome, vida, 1, forca)

        else:
            heroi = Chefe(nome)

        rotulo_status.config(text="Herói criado")

        print("Herói criado")
        print("Classe:", classe)
        print("Nome:", heroi.nome)
        print("Vida:", heroi.vida)
        print("Força:", heroi.forca)

    except ValueError as erro:
        rotulo_status.config(text=str(erro))

    finally:
        botao_criar.config(state="normal")

def verificar_campos(*args):

    preenchido = (
        campo_nome.get().strip() != ""
        and campo_vida.get().strip() != ""
        and campo_forca.get().strip() != ""
    )

    if preenchido:
        botao_criar.config(state="normal")
    else:
        botao_criar.config(state="disabled")

janela = tk.Tk()

janela.title("IF Quest")
janela.geometry("500x300")

titulo = tk.Label(
    janela,
    text="Crie seu Herói!",
    font=("Arial", 16)
)

titulo.pack(pady=10)

painel_campos = tk.Frame(janela)
painel_campos.pack()

tk.Label(
    painel_campos,
    text="Nome:"
).grid(row=0, column=0, padx=5, pady=5)

campo_nome = tk.Entry(painel_campos)
campo_nome.grid(row=0, column=1, padx=5, pady=5)

tk.Label(
    painel_campos,
    text="Vida inicial:"
).grid(row=1, column=0, padx=5, pady=5)

campo_vida = tk.Entry(painel_campos)
campo_vida.grid(row=1, column=1, padx=5, pady=5)

tk.Label(
    painel_campos,
    text="Força inicial:"
).grid(row=2, column=0, padx=5, pady=5)

campo_forca = tk.Entry(painel_campos)
campo_forca.grid(row=2, column=1, padx=5, pady=5)

tk.Label(
    painel_campos,
    text="Classe:"
).grid(row=3, column=0, padx=5, pady=5)

campo_classe = tk.StringVar()
campo_classe.set("Mago")

opcoes_classe = tk.OptionMenu(
    painel_campos,
    campo_classe,
    "Mago",
    "Guerreiro",
    "Chefe"
)

opcoes_classe.grid(row=3, column=1, padx=5, pady=5)

botao_criar = tk.Button(
    janela,
    text="Criar Herói",
    command=criar_heroi,
    state="disabled"
)

botao_criar.pack(pady=10)

rotulo_status = tk.Label(
    janela,
    text=""
)

rotulo_status.pack()

campo_nome.bind("<KeyRelease>", verificar_campos)
campo_vida.bind("<KeyRelease>", verificar_campos)
campo_forca.bind("<KeyRelease>", verificar_campos)

janela.mainloop()

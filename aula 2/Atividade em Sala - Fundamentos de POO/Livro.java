class Livro {

    private String titulo;
    private String autor;
    private int ano;
    
    public Livro(String titulo, String autor, int ano){
        setTitulo(titulo);
        setAutor(autor);
        setAno(ano);
    }
    
    public void setTitulo(String Titulo){
        if(titulo != NULL && titulo.trim().isEmpty()){
            this.titulo = titulo;
        } else {
            System.out.println("O titulo do livro não pode estar vazio");
        }
        
    public void setAutor(String Autor){
        if(autor != NULL && autor.trim().isEmpty()){
            this.autor = autor;
        } else {
            System.out.println("O nome do autor não pode estar vazio");
        }
        
    public int ano(){
        return ano;
    }
    public void setAno(String ano){
        if(ano <= 0) {
            this.ano = ano;
        } else {
            System.out.println("O ano de publicação do livro tem que ser maior que 0");
        }
    }
    }
    }
    
class Biblioteca{
    private String nomebibliotecca;
    private Livro livro;
    
    public Biblioteca(String nomebiblioteca, Livro livro){
        setNomebiblioteca(nomebiblioteca);
        setLivro(livro);
    }
    
    public void setNomebiblioteca(String Nomebiblioteca){
        if(titulo != NULL && titulo.trim().isEmpty()){
            this.nomebiblioteca = nomebiblioteca;
        } else {
            System.out.println("O nome da biblioteca não pode estar vazio");
        }
        
    public Livro getLivro(){
        return livro;
        
    public void setLivro(Livro livro){
        if(livro != NULL){
            this.livro = livro;
        } else {
            System.out.println("A biblioteca tem que ter um livro que seja válido");
        }
    }
    }
}
public class Main{
    public static void main(String[]args){
        Livro livro = new Livro("Verity", "Collen Hover", 2020);
        
        System.out.println("Titulo: " + )
    }
}



*/ Duas classes, uma livro e outra biblioteca, sendo os atributos de livro, o titulo, autor e ano que foi publicado, e em biblioteca sendo livro um objeto da prpria classe 
  Livro, tem o set para observar se o titulo do livro está vazio, tem para verificar tambem se o nome do livro esta vazio, e se o ano de publicação do livro e negativo sendo menor que 0*/

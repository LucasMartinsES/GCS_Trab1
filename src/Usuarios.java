public class Usuarios {
    
    private String identificador;
    private String nome;
    
    public Usuarios(String aIdentificador, String aNome) {
        this.identificador = aIdentificador;
        this.nome = aNome;
    }

    public String getId() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }


    public String toString() {
        return "Usuário [" + identificador + "] nome: " + nome; 

}
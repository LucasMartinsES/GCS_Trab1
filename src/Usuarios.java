public class Usuarios {
    public enum TipoUsuario {
        AUTOR, ADM
    }

    private static Integer GLOBAL_IDENTIFICADOR = 0;
    private TipoUsuario tipoUsuario;
    private Integer identificador;
    private String nome;

    protected Usuarios(String nome, TipoUsuario tipoUsuario) {
        this.identificador = GLOBAL_IDENTIFICADOR++;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }


    public String toString() {
        return "Usuário [" + identificador + "] nome: " + nome;

    }
}
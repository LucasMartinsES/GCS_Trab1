public class Usuario {
    public enum TipoUsuario {
        AUTOR, ADM
    }

    private static Integer GLOBAL_IDENTIFICADOR = 0;
    private TipoUsuario tipoUsuario;
    private int identificador;
    private String nome;

    protected Usuario(String nome, TipoUsuario tipoUsuario) {
        this.identificador = GLOBAL_IDENTIFICADOR++;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "tipoUsuario=" + tipoUsuario +
                ", identificador=" + identificador +
                ", nome='" + nome + '\'' +
                '}';
    }
}
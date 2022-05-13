public class Usuario {
    public enum TipoUsuario {
        AUTOR, ADM
    }

    private static Integer GLOBAL_IDENTIFICADOR = 0;
    private TipoUsuario tipoUsuario;
    private int identificador;
    private String nome;
    private int qtdPostagem = 0;
    private int qtdComentario = 0;

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

    public int getQtdPostagem() {
        return qtdPostagem;
    }

    public int getQtdComentario() {
        return qtdComentario;
    }

    public void setQtdPostagem(int qtdPostagem) {
        this.qtdPostagem = qtdPostagem;
    }

    public void setQtdComentario(int qtdComentario) {
        this.qtdComentario = qtdComentario;
    }

    public void zeraQtdComentarios() {
        this.qtdComentario = 0;
    }

    public void zeraQtdPostagens() {
        this.qtdPostagem =0;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "tipoUsuario=" + tipoUsuario + ", identificador=" + identificador + ", nome='" + nome
                + '\'' + '}';
    }
}

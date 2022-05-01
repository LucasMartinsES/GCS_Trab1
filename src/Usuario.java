import java.util.stream.Stream;

public class Usuario {
    public enum TipoUsuario {
        ADM(1), AUTOR(2);
        private final int id;

        private TipoUsuario(int id) {
            this.id = id;
        }

        public static TipoUsuario ofId(int id) {
            return Stream.of(values())
                    .filter(it -> it.id == id)
                    .findFirst()
                    .orElse(TipoUsuario.AUTOR);
        }
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
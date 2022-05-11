import java.time.LocalDateTime;

public class Comentario {
    private static Integer GLOBAL_COMENTARIO_ID = 0;
    private final int comentarioId;
    private String comentario;
    private Usuario usuario;
    private LocalDateTime momentoCriado;
    private LocalDateTime momentoEditado;

    public Comentario(String comentario, Usuario usuario) {
        this.comentarioId = GLOBAL_COMENTARIO_ID++;
        if (comentario.length() > 100) {
            this.comentario = comentario.substring(0, 100);
        } else {
            this.comentario = comentario;
        }
        this.usuario = usuario;
        this.momentoCriado = LocalDateTime.now();
    }

    public int getComentarioId() {
        return comentarioId;
    }

    public String getComentario() {
        return comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getMomentoCriado() {
        return momentoCriado;
    }

    public LocalDateTime getMomentoEditado() {
        return momentoEditado;
    }

    public boolean editar(String comentario) {
        if (comentario != null && !comentario.isEmpty()) {
            if (comentario.length() > 100) {
                this.comentario = comentario.substring(0, 100);
            } else {
                this.comentario = comentario;
            }
            this.momentoEditado = LocalDateTime.now();
            return true;
        }
        return false;
    }

    public boolean setUsuario(Usuario usuario) {
        if (usuario != null) {
            this.usuario = usuario;
            return true;
        }
        return false;
    }

    public String toString() {
        return comentario;
    }
}

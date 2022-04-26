import java.time.LocalDateTime;

public class Comentario {
    private String comentario;
    private Usuarios usuario;
    private LocalDateTime dataHora;

    public Comentario(String comentario, Usuarios usuario) {
        if (comentario.length() > 100) {
            this.comentario = comentario.substring(0, 100);
        } else {
            this.comentario = comentario;
        }
        this.usuario = usuario;
        this.dataHora = LocalDateTime.now();
    }

    public String getComentario() {
        return comentario;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public boolean setComentario(String comentario) {
        if (comentario != null && comentario.isEmpty() == false) {
            if (comentario.length() > 100) {
                this.comentario = comentario.substring(0, 100);
            } else {
                this.comentario = comentario;
            }
            this.dataHora = LocalDateTime.now();
            return true;
        }
        return false;
    }

    public boolean setUsuario(Usuarios usuario) {
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

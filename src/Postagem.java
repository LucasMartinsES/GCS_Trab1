import java.time.LocalDateTime;
import java.util.ArrayList;

public class Postagem {
    private static int POSTAGEM_GLOBAL_ID = 0;
    private int postagemId;
    private Usuario usuario;
    private LocalDateTime momentoCriado;
    private LocalDateTime momentoEditado;
    private String texto;
    private ArrayList<String> tags;
    private String link;
    private ArrayList<Comentario> comentarios;

    public Postagem(Usuario usuario, String texto) {
        this.postagemId = POSTAGEM_GLOBAL_ID++;
        this.usuario = usuario;
        this.momentoCriado = LocalDateTime.now();
        this.texto = texto;
        tags = new ArrayList<String>();
        comentarios = new ArrayList<Comentario>();
    }

    public int getPostagemId() {
        return postagemId;
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

    public String getTexto() {
        return texto;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getLink() {
        return link;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public boolean setUsuario(Usuario usuario) {
        if (usuario != null) {
            this.usuario = usuario;
            return true;
        }
        return false;
    }

    public boolean setLink(String link) {
        if (link != null) {
            if (link.indexOf("http://") == 0 || link.indexOf("https://") == 0) {
                this.link = link;
                return true;
            }
        }
        return false;
    }

    public boolean editar(String texto) {
        if (texto != null && texto.isEmpty() == false) {
            this.texto = texto;
            this.momentoEditado = LocalDateTime.now();
            return true;
        }
        return false;
    }

    public boolean adicionarTag(String tag) {
        if (tag != null && !(tags.contains(tag))) {
            tags.add(tag);
            return true;
        }
        return false;
    }

    public boolean removerTag(String tag) {
        if (tags.contains(tag)) {
            tags.remove(tag);
            return true;
        }
        return false;
    }

    public boolean consultarTag(String tag) {
        return tags.contains(tag);
    }

    public boolean adicionarComentario(Comentario comentario) {
        if (comentario != null) {
            comentarios.add(comentario);
            return true;
        }
        return false;
    }

    public boolean removerComentario(Comentario comentario) {
        return comentarios.removeIf(it -> it.getComentarioId() == comentario.getComentarioId());
    }

    public int quantidadeComentarios() {
        return comentarios.size();
    }
}

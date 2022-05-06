import java.util.*;
import java.util.stream.Collectors;

public class PublicacaoService {
    private final Map<Integer, Postagem> postagens;

    public PublicacaoService() {
        postagens = new HashMap<>();
    }

    public boolean removePostagem(Usuario usuario, int postagemId) {
        Postagem postagemToDelete = postagens.get(postagemId);
        if (postagemToDelete == null) return false;
        if (postagemPodeSerRemovida(usuario, postagemToDelete)) {
            postagens.remove(postagemId);
        }
        return false;
    }

    public boolean removeComentario(Usuario usuario, int comentarioId) {
        Optional<Map.Entry<Integer, Comentario>> comentarioWithPostagemId = buscaComentarioComPostagemId(comentarioId);
        if (comentarioWithPostagemId.isEmpty()) return false;
        if (comentarioPodeSerDeletado(usuario, comentarioWithPostagemId.get().getValue())) {
            Comentario comentario = comentarioWithPostagemId.get().getValue();
            int postagemId = comentarioWithPostagemId.get().getKey();
            Postagem postagem = postagens.get(postagemId);
            postagem.removerComentario(comentario);
            postagens.put(postagemId, postagem);
        }
        return false;
    }

    public List<Comentario> buscaComentarioPorTexto(String texto) {
        List<Comentario> comentarios = new ArrayList<Comentario>();
        for(Postagem p : postagens.values()) {
            for(Comentario c : p.getComentarios()) {
                if(c.getComentario().contains(texto)) comentarios.add(c);
            }
        }
        return comentarios
                .stream()
                .sorted(Comparator.comparing(Comentario :: getMomentoCriado))
                .collect(Collectors.toList());
    }

    public List<Postagem> buscaPostagemPorTexto(String texto) {        
        return postagens.values().stream()
                .filter(postagem -> postagem.getTexto().contains(texto))
                .sorted(Comparator.comparing(Postagem :: getMomentoCriado))
                .collect(Collectors.toList());
    }

    public List<Postagem> buscaPostagemsPorTag(ArrayList<String> tags) {
        return postagens
                .values().stream()
                .filter(postagem -> postagem.getTags().stream()
                .anyMatch(tag -> tags.contains(tag)))
                .sorted(Comparator.comparing(Postagem :: getMomentoCriado))
                .collect(Collectors.toList());
    }

    private Optional<Map.Entry<Integer, Comentario>> buscaComentarioComPostagemId(int comentarioId) {
        return postagens.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, it -> it.getValue().getComentarios()))
                .entrySet().stream()
                .filter(it -> it.getValue().stream()
                        .anyMatch(comentario -> comentario.getComentarioId() == comentarioId)
                ).collect(Collectors.toMap(Map.Entry::getKey, it -> it.getValue().get(0)))
                .entrySet().stream()
                .findFirst();
    }

    private boolean postagemPodeSerRemovida(Usuario usuario, Postagem postagem) {
        return usuario.getTipoUsuario() == Usuario.TipoUsuario.AUTOR &&
                postagem.getUsuario().getIdentificador() == usuario.getIdentificador() ||
                usuario.getTipoUsuario() == Usuario.TipoUsuario.ADM;
    }

    private boolean comentarioPodeSerDeletado(Usuario usuario, Comentario comentario) {
        return usuario.getTipoUsuario() == Usuario.TipoUsuario.AUTOR &&
                comentario.getUsuario().getIdentificador() == usuario.getIdentificador() ||
                usuario.getTipoUsuario() == Usuario.TipoUsuario.ADM;
    }
}

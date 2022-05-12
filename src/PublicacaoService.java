import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PublicacaoService {
	Integer contadorPostagem =0;
	private final Map<Integer, Postagem> postagens;

	public PublicacaoService() {
		postagens = new HashMap<>();
	}

	public void criaPostagem(Usuario usuario, String texto) {
		Postagem postagem = new Postagem(usuario, texto);
		postagens.put(contadorPostagem, postagem);
		contadorPostagem++;
	}
	
	public void comentarPostagem(int id,String comentario, Usuario usuario) {
		Comentario comentarioAdd = new Comentario(comentario, usuario);
		for(int i=0;i<postagens.size();i++) {
			if(postagens.get(i).getPostagemId()== id) {
				postagens.get(i).adicionarComentario(comentarioAdd);
			}
		}
	}

	public boolean removePostagem(Usuario usuario, int postagemId) {
		Postagem postagemToDelete = postagens.get(postagemId);
		if (postagemToDelete == null)
			return false;
		if (postagemPodeSerRemovida(usuario, postagemToDelete)) {
			postagens.remove(postagemId);
		}
		return false;
	}

	public boolean removeComentario(Usuario usuario, int comentarioId) {
		Optional<Map.Entry<Integer, Comentario>> comentarioWithPostagemId = buscaComentarioComPostagemId(comentarioId);
		if (comentarioWithPostagemId.isEmpty())
			return false;
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
		for (Postagem p : postagens.values()) {
			for (Comentario c : p.getComentarios()) {
				if (c.getComentario().contains(texto))
					comentarios.add(c);
			}
		}
		return comentarios.stream().sorted(Comparator.comparing(Comentario::getMomentoCriado))
				.collect(Collectors.toList());
	}

	public List<Postagem> buscaPostagemPorTexto(String texto) {
		return postagens.values().stream().filter(postagem -> postagem.getTexto().contains(texto))
				.sorted(Comparator.comparing(Postagem::getMomentoCriado)).collect(Collectors.toList());
	}

	public List<Postagem> buscaPostagemsPorTag(ArrayList<String> tags) {
		return postagens.values().stream()
				.filter(postagem -> postagem.getTags().stream().anyMatch(tag -> tags.contains(tag)))
				.sorted(Comparator.comparing(Postagem::getMomentoCriado)).collect(Collectors.toList());
	}

	private Optional<Map.Entry<Integer, Comentario>> buscaComentarioComPostagemId(int comentarioId) {
		return postagens.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, it -> it.getValue().getComentarios())).entrySet().stream()
				.filter(it -> it.getValue().stream()
						.anyMatch(comentario -> comentario.getComentarioId() == comentarioId))
				.collect(Collectors.toMap(Map.Entry::getKey, it -> it.getValue().get(0))).entrySet().stream()
				.findFirst();
	}

	private boolean postagemPodeSerRemovida(Usuario usuario, Postagem postagem) {
		return usuario.getTipoUsuario() == Usuario.TipoUsuario.AUTOR
				&& postagem.getUsuario().getIdentificador() == usuario.getIdentificador()
				|| usuario.getTipoUsuario() == Usuario.TipoUsuario.ADM;
	}

	private boolean comentarioPodeSerDeletado(Usuario usuario, Comentario comentario) {
		return usuario.getTipoUsuario() == Usuario.TipoUsuario.AUTOR
				&& comentario.getUsuario().getIdentificador() == usuario.getIdentificador()
				|| usuario.getTipoUsuario() == Usuario.TipoUsuario.ADM;
	}

	public void salvaPostagensArqCsv(String arq, Usuario usuario) {
		Path path = Paths.get(arq + ".csv");
		List<Postagem> postagens = new ArrayList<Postagem>();
		postagens = buscaPostagensPorUsuario(usuario);
		try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.defaultCharset()))) {

			for (Postagem p : postagens) {
				writer.print(p.getTexto() + ";" + p.getTags() + ";" + p.getLink());
			}

		} catch (IOException e) {
			System.err.format("Erro de E/S: %s%n", e);
		}
	}

	private List<Postagem> buscaPostagensPorUsuario(Usuario usuario) {

		return postagens.values().stream()
				.filter(postagem -> postagem.getUsuario().getIdentificador() == usuario.getIdentificador())
				.sorted(Comparator.comparing(Postagem::getMomentoCriado)).collect(Collectors.toList());
	}
}

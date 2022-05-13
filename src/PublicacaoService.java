import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PublicacaoService {

	private final Map<Integer, Postagem> postagens;
	// ArrayList de Usuarios que fizeram alguma postagem
	private UsuarioRepository repositorioUsuario;
	private PalavrasProibidas proibidas;

	public PublicacaoService(UsuarioRepository usuarioRepository, PalavrasProibidas proibidas) {
		postagens = new HashMap<>();
		repositorioUsuario = usuarioRepository;
		this.proibidas = proibidas;
	}

	public boolean criaPostagem(Usuario usuario, String texto) {
		Postagem postagem = new Postagem(usuario, texto);
		boolean permitido = proibidas.verificarSeHaPalavrasProibidas(texto, usuario.getNome());
		if (permitido) {
			postagens.put(postagem.getPostagemId(), postagem);
		}
		return permitido;
	}
	
	public boolean comentarPostagem(int id,String comentario, Usuario usuario) {
		boolean permitido = proibidas.verificarSeHaPalavrasProibidas(comentario, usuario.getNome());
		if (permitido) {
			Comentario comentarioAdd = new Comentario(comentario, usuario);
			Postagem postagem = postagens.get(id);
			if (postagem != null) {
				postagem.adicionarComentario(comentarioAdd);
			}
		}
		return permitido;
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

	// 8.1Retorna uma string com o total de: Posts, Comentarios e Usuarios
	// cadastrados no programa.
	public String totalPostsComentariosUsuarios(Usuario usuario) {
		if (usuario.getTipoUsuario() == Usuario.TipoUsuario.ADM) {
			String usuarios = Integer.toString(getTotalDeUsuarios());
			String comentarios = Integer.toString(getTotalDeComentarios());
			String postagem = Integer.toString(postagens.size());

			return ("Total de Usuarios: " + usuarios + " - Total de Comentarios: " + comentarios
					+ " - Total de Postagens: " + postagem);

		}
		return "Esta funcao eh somente para usuarios administradores";

	}
	
	// 8.2 Retorna uma String do ranking de usuarios com mais postagens
	public String rankingTop5UsuariosComMaisPostagens(Usuario usuario) {
		if (usuario.getTipoUsuario() == Usuario.TipoUsuario.ADM) {
			atualizarUsuariosNoRepositorio();
			atualizaQtdPostagensNoUsuarioDoRepositorio();
			Usuario usuario1 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario2 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario3 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario4 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario5 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			for (int i = 0; i < repositorioUsuario.getUsuarios().size(); i++) {
				int valor = repositorioUsuario.getUsuarios().get(i).getQtdPostagem();
				if (valor >= usuario1.getQtdPostagem()) {
					usuario2 = usuario1;
					usuario1 = repositorioUsuario.getUsuarios().get(i);
				} else if (valor >= usuario2.getQtdPostagem()) {
					usuario3 = usuario2;
					usuario2 = repositorioUsuario.getUsuarios().get(i);
				} else if (valor >= usuario3.getQtdPostagem()) {
					usuario4 = usuario3;
					usuario3 = repositorioUsuario.getUsuarios().get(i);
				} else if (valor >= usuario4.getQtdPostagem()) {
					usuario5 = usuario4;
					usuario4 = repositorioUsuario.getUsuarios().get(i);
				} else if (valor > usuario5.getQtdPostagem()) {
					usuario5 = repositorioUsuario.getUsuarios().get(i);
				}
			}

			return ("Ranking dos usurios com mais postagens: 1 -" + usuario1.getNome() + " // 2 -  "
					+ usuario2.getNome() + " // 3 - " + usuario3.getNome() + " // 4 - " + usuario4.getNome()
					+ " // 5 - " + usuario5.getNome());
		}
		return "Esta funo  somente para usurios administradores";
	}

	// 8.3 Retorna uma String com o ranking dos 10 usuarios com mais comentarios
	public String rankingTop10UsuariosComMaisComentarios(Usuario usuario) {
		if (usuario.getTipoUsuario() == Usuario.TipoUsuario.ADM) {
			atualizarUsuariosNoRepositorio();
			atualizaQtdComentariosNoUsuarioDoRepositorio();
			Usuario usuario1 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario2 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario3 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario4 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario5 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario6 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario7 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario8 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario9 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario10 = new Usuario("No Cadastrado", Usuario.TipoUsuario.AUTOR);
			for (int i = 0; i < repositorioUsuario.getUsuarios().size(); i++) {
				int valor = repositorioUsuario.getUsuarios().get(i).getQtdComentario();
				if (valor >= usuario1.getQtdComentario()) {
					usuario2 = usuario1;
					usuario1 = repositorioUsuario.getUsuarios().get(i);
				} else if (valor >= usuario2.getQtdComentario()) {
					usuario3 = usuario2;
					usuario2 = repositorioUsuario.getUsuarios().get(i);
				} else if (valor >= usuario3.getQtdComentario()) {
					usuario4 = usuario3;
					usuario3 = repositorioUsuario.getUsuarios().get(i);
				} else if (valor >= usuario4.getQtdComentario()) {
					usuario5 = usuario4;
					usuario4 = repositorioUsuario.getUsuarios().get(i);
				} else if (valor >= usuario5.getQtdComentario()) {
					usuario6 = usuario5;
					usuario5 = repositorioUsuario.getUsuarios().get(i);
				} else if (valor >= usuario6.getQtdComentario()) {
					usuario7 = usuario6;
					usuario6 = repositorioUsuario.getUsuarios().get(i);
				} else if (valor >= usuario7.getQtdComentario()) {
					usuario8 = usuario7;
					usuario7 = repositorioUsuario.getUsuarios().get(i);
				} else if (valor >= usuario8.getQtdComentario()) {
					usuario9 = usuario8;
					usuario8 = repositorioUsuario.getUsuarios().get(i);
				} else if (valor >= usuario9.getQtdComentario()) {
					usuario10 = usuario9;
					usuario9 = repositorioUsuario.getUsuarios().get(i);
				} else if (valor > usuario10.getQtdComentario()) {
					usuario10 = repositorioUsuario.getUsuarios().get(i);
				}
			}

			return ("Ranking dos usurios com mais comentarios: 1 -" + usuario1.getNome() + " // 2 -  "
					+ usuario2.getNome() + " // 3 - " + usuario3.getNome() + " // 4 - " + usuario4.getNome()
					+ " // 5 - " + usuario5.getNome() + " // 6 - " + usuario6.getNome() + " // 7 - "
					+ usuario7.getNome() + " // 8 - " + usuario8.getNome() + " // 9 - " + usuario9.getNome()
					+ " // 10 - " + usuario10.getNome());
		}
		return "Esta funo  somente para usurios administradores";
	}

	// 8.4 Retorna uma String com o ranking das postagens (id) com maior numero de
	// comentarios
	public String top5PostagensComMaisComentarios(Usuario usuario) {
		if (usuario.getTipoUsuario() == Usuario.TipoUsuario.ADM) {
			Postagem postagem1 = new Postagem(null, "Sem cadastro");
			Postagem postagem2 = new Postagem(null, "Sem cadastro");
			Postagem postagem3 = new Postagem(null, "Sem cadastro");
			Postagem postagem4 = new Postagem(null, "Sem cadastro");
			Postagem postagem5 = new Postagem(null, "Sem cadastro");
			for (int i = 0; i < postagens.size(); i++) {
				int qtd = postagens.get(i).getComentarios().size();
				if (qtd >= postagem1.getComentarios().size()) {
					postagem2 = postagem1;
					postagem1 = postagens.get(i);
				} else if (qtd >= postagem2.getComentarios().size()) {
					postagem3 = postagem2;
					postagem2 = postagens.get(i);
				} else if (qtd >= postagem3.getComentarios().size()) {
					postagem4 = postagem3;
					postagem3 = postagens.get(i);
				} else if (qtd >= postagem4.getComentarios().size()) {
					postagem5 = postagem4;
					postagem4 = postagens.get(i);
				} else if (qtd >= postagem5.getComentarios().size()) {
					postagem5 = postagens.get(i);
				}

			}
			return ("Ranking das postagens com mais comentarios: Id Postagem 1 -" + postagem1.getPostagemId()
					+ " // Id Postagem 2 - " + postagem2.getPostagemId() + " // Id Postagem 3 - "
					+ postagem3.getPostagemId() + " // Id Postagem 4 - " + postagem4.getPostagemId()
					+ " // Id Postagem 5 - " + postagem5.getPostagemId());
		}

		return "Esta funcaoo e somente para usuarios administradores";
	}


	// Retorna a quantidade de usuarios no ArrayList de Usuario
	private int getTotalDeUsuarios() {
		atualizarUsuariosNoRepositorio();
		return repositorioUsuario.getUsuarios().size();
	}

	// Soma a quantidade de comentarios de todas as postagens e retorna a mesma
	public int getTotalDeComentarios() {
		int contador = 0;
		for (int i = 0; i < postagens.size(); i++) {
			contador += postagens.get(i).getComentarios().size();
		}
		return contador;
	}

	// Atribui a quantidade de postagens a cada um dos usuarios dentro do
	// repositorio ate o momento
	private void atualizaQtdPostagensNoUsuarioDoRepositorio() {
		atualizarUsuariosNoRepositorio();
		int contador = 0;
		for (int i = 0; i < repositorioUsuario.getUsuarios().size(); i++) {
			contador = 0;
			repositorioUsuario.getUsuarios().get(i).zeraQtdPostagens();
			for (int j = 0; j < postagens.size(); j++) {
				if (repositorioUsuario.getUsuarios().get(i).getIdentificador() == postagens.get(j).getUsuario()
						.getIdentificador()) {
					contador++;
				}

			}
			repositorioUsuario.getUsuarios().get(i).setQtdPostagem(contador);
		}
	}

	// Acrescenta novos usuarios de acordo com o atributo usuario das postagens
	public void atualizarUsuariosNoRepositorio() {
		repositorioUsuario.getUsuarios().clear();
		for (int i = 0; i < postagens.size(); i++) {
			int contador = 0;
			for (int j = 0; j < repositorioUsuario.getUsuarios().size(); i++) {
				if (postagens.get(i).getUsuario().getIdentificador() == repositorioUsuario.getUsuarios().get(j)
						.getIdentificador()) {
					contador++;
				}
			}
			if (contador == 0) {
				repositorioUsuario.getUsuarios().add(postagens.get(i).getUsuario());
			}
		}
	}

	// Atribui a quantidade de comentarios a cada um dos usuarios dentro do
	// repositorio ate o momento
	private void atualizaQtdComentariosNoUsuarioDoRepositorio() {
		atualizarUsuariosNoRepositorio();
		int contador = 0;
		for (int i = 0; i < repositorioUsuario.getUsuarios().size(); i++) {
			contador = 0;
			repositorioUsuario.getUsuarios().get(i).zeraQtdComentarios();
			for (int j = 0; j < postagens.size(); j++) {
				if (repositorioUsuario.getUsuarios().get(i).getIdentificador() == postagens.get(j).getUsuario()
						.getIdentificador()) {
					contador += postagens.get(j).getComentarios().size();
				}

			}
			repositorioUsuario.getUsuarios().get(i).setQtdComentario(contador);
		}
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

    public List<Postagem> buscaPostagemsPorTag(ArrayList<String> tags) {
        return postagens
                .values().stream()
                .filter(postagem -> postagem.getTags().stream()
                .anyMatch(tags::contains))
                .sorted(Comparator.comparing(Postagem :: getMomentoCriado))
                .collect(Collectors.toList());
    }

	private List<Postagem> buscaPostagensPorUsuario(Usuario usuario) {
		return postagens.values().stream()
				.filter(postagem -> postagem.getUsuario().getIdentificador() == usuario.getIdentificador())
				.sorted(Comparator.comparing(Postagem :: getMomentoCriado))
				.collect(Collectors.toList());
	}
}

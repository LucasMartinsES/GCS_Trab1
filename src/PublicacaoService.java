import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class PublicacaoService {
	private final Map<Integer, Postagem> postagens;
	// ArrayList de Usuarios que fizeram alguma postagem
	private UsuarioRepository repositorioUsuario;

	public PublicacaoService() {
		postagens = new HashMap<>();
		repositorioUsuario = new UsuarioRepository();
	}

	// Soma a quantidade de comentarios de todas as postagens e retorna a mesma
	public int getTotalDeComentarios() {
		int contador = 0;
		for (int i = 0; i < postagens.size(); i++) {
			contador += postagens.get(i).getComentarios().size();
		}
		return contador;
	}

	// Atribui a quantidade de comentarios a cada um dos usuarios dentro do
	// repositorio ate o momento
	public void atualizaQtdComentariosNoUsuarioDoRepositorio() {
		atualizarUsuariosNoRepositorio();
		int contador = 0;
		for (int i = 0; i < repositorioUsuario.getRUsuarios().size(); i++) {
			contador = 0;
			repositorioUsuario.getRUsuarios().get(i).zeraQtdComentarios();
			for (int j = 0; j < postagens.size(); j++) {
				if (repositorioUsuario.getRUsuarios().get(i).getIdentificador() == postagens.get(j).getUsuario()
						.getIdentificador()) {
					contador += postagens.get(j).getComentarios().size();
				}

			}
			repositorioUsuario.getRUsuarios().get(i).setQtdComentario(contador);
		}
	}

	// Acrescenta novos usuarios de acordo com o atributo usuario das postagens
	public void atualizarUsuariosNoRepositorio() {
		repositorioUsuario.getRUsuarios().clear();
		for (int i = 0; i < postagens.size(); i++) {
			int contador = 0;
			for (int j = 0; j < repositorioUsuario.getRUsuarios().size(); i++) {
				if (postagens.get(i).getUsuario().getIdentificador() == repositorioUsuario.getRUsuarios().get(j)
						.getIdentificador()) {
					contador++;
				}
			}
			if (contador == 0) {
				repositorioUsuario.getRUsuarios().add(postagens.get(i).getUsuario());
			}
		}
	}

	// Atribui a quantidade de postagens a cada um dos usuarios dentro do
	// repositorio ate o momento
	public void atualizaQtdPostagensNoUsuarioDoRepositorio() {
		atualizarUsuariosNoRepositorio();
		int contador = 0;
		for (int i = 0; i < repositorioUsuario.getRUsuarios().size(); i++) {
			contador = 0;
			repositorioUsuario.getRUsuarios().get(i).zeraQtdPostagens();
			for (int j = 0; j < postagens.size(); j++) {
				if (repositorioUsuario.getRUsuarios().get(i).getIdentificador() == postagens.get(j).getUsuario()
						.getIdentificador()) {
					contador++;
				}

			}
			repositorioUsuario.getRUsuarios().get(i).setQtdPostagem(contador);
		}
	}

	// Retorna a quantidade de usuarios no ArrayList de Usuario
	public int getTotalDeUsuarios() {
		atualizarUsuariosNoRepositorio();
		return repositorioUsuario.getRUsuarios().size();
	}

	// 8.1Retorna uma string com o total de: Posts, Comentarios e Usuarios
	// cadastrados no programa.
	public String totalPostsComentariosUsuarios(Usuario usuario) {
		if (usuario.getTipoUsuario() == Usuario.TipoUsuario.ADM) {
			String usuarios = Integer.toString(getTotalDeUsuarios());
			String comentarios = Integer.toString(getTotalDeComentarios());
			String postagem = Integer.toString(postagens.size());

			return ("Total de Usuários: " + usuarios + " - Total de Comentários: " + comentarios
					+ " - Total de Postagens: " + postagem);

		}
		return "Esta função é somente para usuários administradores";

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

	// 8.2 Retorna uma String do ranking de usuarios com mais postagens
	public String rankingTop5UsuariosComMaisPostagens(Usuario usuario) {
		if (usuario.getTipoUsuario() == Usuario.TipoUsuario.ADM) {
			atualizarUsuariosNoRepositorio();
			atualizaQtdPostagensNoUsuarioDoRepositorio();
			Usuario usuario1 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario2 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario3 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario4 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario5 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			for (int i = 0; i < repositorioUsuario.getRUsuarios().size(); i++) {
				int valor = repositorioUsuario.getRUsuarios().get(i).getQtdPostagem();
				if (valor >= usuario1.getQtdPostagem()) {
					usuario2 = usuario1;
					usuario1 = repositorioUsuario.getRUsuarios().get(i);
				} else if (valor >= usuario2.getQtdPostagem()) {
					usuario3 = usuario2;
					usuario2 = repositorioUsuario.getRUsuarios().get(i);
				} else if (valor >= usuario3.getQtdPostagem()) {
					usuario4 = usuario3;
					usuario3 = repositorioUsuario.getRUsuarios().get(i);
				} else if (valor >= usuario4.getQtdPostagem()) {
					usuario5 = usuario4;
					usuario4 = repositorioUsuario.getRUsuarios().get(i);
				} else if (valor > usuario5.getQtdPostagem()) {
					usuario5 = repositorioUsuario.getRUsuarios().get(i);
				}
			}

			return ("Ranking dos usuários com mais postagens: 1ª -" + usuario1.getNome() + " // 2ª -  "
					+ usuario2.getNome() + " // 3ª - " + usuario3.getNome() + " // 4ª - " + usuario4.getNome()
					+ " // 5ª - " + usuario5.getNome());
		}
		return "Esta função é somente para usuários administradores";
	}

	// 8.3 Retorna uma String com o ranking dos 10 usuarios com mais comentarios
	public String rankingTop10UsuariosComMaisComentarios(Usuario usuario) {
		if (usuario.getTipoUsuario() == Usuario.TipoUsuario.ADM) {
			atualizarUsuariosNoRepositorio();
			atualizaQtdComentariosNoUsuarioDoRepositorio();
			Usuario usuario1 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario2 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario3 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario4 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario5 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario6 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario7 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario8 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario9 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			Usuario usuario10 = new Usuario("Não Cadastrado", Usuario.TipoUsuario.AUTOR);
			for (int i = 0; i < repositorioUsuario.getRUsuarios().size(); i++) {
				int valor = repositorioUsuario.getRUsuarios().get(i).getQtdComentario();
				if (valor >= usuario1.getQtdComentario()) {
					usuario2 = usuario1;
					usuario1 = repositorioUsuario.getRUsuarios().get(i);
				} else if (valor >= usuario2.getQtdComentario()) {
					usuario3 = usuario2;
					usuario2 = repositorioUsuario.getRUsuarios().get(i);
				} else if (valor >= usuario3.getQtdComentario()) {
					usuario4 = usuario3;
					usuario3 = repositorioUsuario.getRUsuarios().get(i);
				} else if (valor >= usuario4.getQtdComentario()) {
					usuario5 = usuario4;
					usuario4 = repositorioUsuario.getRUsuarios().get(i);
				} else if (valor >= usuario5.getQtdComentario()) {
					usuario6 = usuario5;
					usuario5 = repositorioUsuario.getRUsuarios().get(i);
				} else if (valor >= usuario6.getQtdComentario()) {
					usuario7 = usuario6;
					usuario6 = repositorioUsuario.getRUsuarios().get(i);
				} else if (valor >= usuario7.getQtdComentario()) {
					usuario8 = usuario7;
					usuario7 = repositorioUsuario.getRUsuarios().get(i);
				} else if (valor >= usuario8.getQtdComentario()) {
					usuario9 = usuario8;
					usuario8 = repositorioUsuario.getRUsuarios().get(i);
				} else if (valor >= usuario9.getQtdComentario()) {
					usuario10 = usuario9;
					usuario9 = repositorioUsuario.getRUsuarios().get(i);
				} else if (valor > usuario10.getQtdComentario()) {
					usuario10 = repositorioUsuario.getRUsuarios().get(i);
				}
			}

			return ("Ranking dos usuários com mais comentarios: 1ª -" + usuario1.getNome() + " // 2ª -  "
					+ usuario2.getNome() + " // 3ª - " + usuario3.getNome() + " // 4ª - " + usuario4.getNome()
					+ " // 5ª - " + usuario5.getNome() + " // 6ª - " + usuario6.getNome() + " // 7ª - "
					+ usuario7.getNome() + " // 8ª - " + usuario8.getNome() + " // 9ª - " + usuario9.getNome()
					+ " // 10ª - " + usuario10.getNome());
		}
		return "Esta função é somente para usuários administradores";
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
			return ("Ranking das postagens com mais comentarios: Id Postagem 1ª -" + postagem1.getPostagemId()
					+ " // Id Postagem 2ª - " + postagem2.getPostagemId() + " // Id Postagem 3ª - "
					+ postagem3.getPostagemId() + " // Id Postagem 4ª - " + postagem4.getPostagemId()
					+ " // Id Postagem 5ª - " + postagem5.getPostagemId());
		}

		return "Esta função é somente para usuários administradores";
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

	public boolean postagemPodeSerEditada(Usuario usuario, int postagemId) {
		Postagem postagemToEdite = postagens.get(postagemId);
		if (postagemToEdite == null){
			return false;
		}
		if (postagemPodeSerRemovida(usuario, postagemToEdite)) {
			return true;
		}
		return false;
	}

	public boolean comentarioPodeSerEditado(Usuario usuario, int comentarioId) {
		Optional<Map.Entry<Integer, Comentario>> comentarioWithPostagemId = buscaComentarioComPostagemId(comentarioId);
		if (comentarioWithPostagemId.isEmpty()){
			return false;
		}
		if (comentarioPodeSerDeletado(usuario, comentarioWithPostagemId.get().getValue())) {
			return true;
		}
		return false;
	}

	public void editaPostagem(String textoEditado, int postagemId){
		Postagem postagemToEdite = postagens.get(postagemId);
		postagemToEdite.editar(textoEditado);
	}

	public void editaComentario(String textoEditado, int comentarioId){
		Optional<Map.Entry<Integer, Comentario>> comentarioWithPostagemId = buscaComentarioComPostagemId(comentarioId);
		Comentario comentario = comentarioWithPostagemId.get().getValue();
		int postagemId = comentarioWithPostagemId.get().getKey();
		Postagem postagem = postagens.get(postagemId);
		comentario.editar(textoEditado);
		postagens.put(postagemId, postagem);
	}

}

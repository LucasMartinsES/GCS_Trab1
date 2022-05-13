import java.util.ArrayList;
import java.util.Optional;

public class UsuarioRepository {
    private final ArrayList<Usuario> usuarios;

    public UsuarioRepository() {
        usuarios = new ArrayList<>();
    }
    
    
    public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public Usuario connectUser(String username, Usuario.TipoUsuario tipoUsuario) {
        Optional<Usuario> possivelUsuario = usuarios.stream()
                .filter(it -> it.getNome().equals(username))
                .findFirst();

        if (possivelUsuario.isPresent()) return possivelUsuario.get();
        Usuario usuario = new Usuario(username, tipoUsuario);
        usuarios.add(usuario);
        return usuario;
    }

    public void salva(Usuario usuario){
        usuarios.add(usuario);
    }
}

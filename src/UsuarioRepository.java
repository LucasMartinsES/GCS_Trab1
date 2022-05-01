import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {
    private final List<Usuario> usuarios;

    public UsuarioRepository() {
        usuarios = new ArrayList<>();
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
}

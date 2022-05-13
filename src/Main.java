import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        PalavrasProibidas proibidas = new PalavrasProibidas(new Scanner(System.in));
        PublicacaoService publicacaoService = new PublicacaoService(usuarioRepository, proibidas);
        Blog blog = new Blog();
        blog.inicializa();
        blog.executa();
    }

}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        PalavrasProibidas proibidas = new PalavrasProibidas(scanner);
        PublicacaoService publicacaoService = new PublicacaoService(usuarioRepository, proibidas);
        Blog blog = new Blog(scanner, publicacaoService, proibidas, usuarioRepository);
        blog.inicializa();
        blog.executa();
    }

}

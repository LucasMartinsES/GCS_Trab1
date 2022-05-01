import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        PublicacaoService publicacaoService = new PublicacaoService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem vindo a rede social { Nome rede social que ainda não sei }");
        System.out.println("Por favor se logue");
        while (true) {
            Usuario usuario = login(scanner, usuarioRepository);
            executeOperations(scanner, usuario, publicacaoService);
        }
    }

    private static void executeOperations(Scanner scanner, Usuario usuario, PublicacaoService publicacaoService) {
        boolean logged = true;
        while (logged) {
            if (usuario.getTipoUsuario() == Usuario.TipoUsuario.ADM) {
                logged = operacoesAdm(scanner, usuario, publicacaoService);
            } else if (usuario.getTipoUsuario() == Usuario.TipoUsuario.AUTOR) {
                logged = operacoesAutor(scanner, usuario, publicacaoService);
            }
        }
    }

    private static boolean operacoesAutor(Scanner scanner, Usuario usuario, PublicacaoService publicacaoService) {
        showMenuAutor();

        return false;
    }

    private static boolean operacoesAdm(Scanner scanner, Usuario usuario, PublicacaoService publicacaoService) {
        showMenuAdm();

        return false;
    }

    private static Usuario login(Scanner scanner, UsuarioRepository usuarioRepository) {
        System.out.println("Entre com o nome do usuario que você quer entrar (sem espaço): ");
        String userName = scanner.next();
        int opc;
        do {
            showMenuDeslogado();
            opc = scanner.nextInt();
        } while (opc != 1 && opc != 2);
        Usuario.TipoUsuario tipoUsuario = Usuario.TipoUsuario.ofId(opc);
        return usuarioRepository.connectUser(userName, tipoUsuario);
    }

    //@Todo adicionar ou ajustar as opcoes
    private static void showMenuAdm() {
        System.out.println("/-----------------------------------------/");
        System.out.println("/ O que voce quer fazer?                  /");
        System.out.println("/                                         /");
        System.out.println("/ 0. Deslogar                             /");
        System.out.println("/ 1. Ver postagens                        /");
        System.out.println("/ 5. Buscar postagem                      /");
        System.out.println("/ 2. Adicionar postagem                   /");
        System.out.println("/ 3. remover postagem                     /");
        System.out.println("/ 4. Adicionar comentário                 /");
        System.out.println("/ 5. remover comentário                   /");
        System.out.println("/ 5. gerar relátorio                      /");
        System.out.println("/-----------------------------------------/");
        System.out.print("input: ");
    }

    //@Todo adicionar ou ajustar as opcoes
    private static void showMenuAutor() {
        System.out.println("/-----------------------------------------/");
        System.out.println("/ O que voce quer fazer?                  /");
        System.out.println("/                                         /");
        System.out.println("/ 0. Deslogar                             /");
        System.out.println("/ 1. Ver postagens                        /");
        System.out.println("/ 5. Buscar postagem                      /");
        System.out.println("/ 2. Adicionar postagem                   /");
        System.out.println("/ 3. remover postagem                     /");
        System.out.println("/ 4. Adicionar comentário                 /");
        System.out.println("/ 5. remover comentário                   /");
        System.out.println("/ 5. gerar relátorio                      /");
        System.out.println("/-----------------------------------------/");
        System.out.print("input: ");
    }

    private static void showMenuDeslogado() {
        System.out.println("/-----------------------------------------/");
        System.out.println("/ Agora qual o nível de acesso: ----------/");
        System.out.println("/ 1. Logar como adm                       /");
        System.out.println("/ 2. Logar como autor                     /");
        System.out.println("/-----------------------------------------/");
        System.out.print("input: ");
    }
}

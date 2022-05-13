import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Blog {
    private Scanner input;
    private PublicacaoService publicacaoService;
    private PalavrasProibidas palavrasProibidas;
    private UsuarioRepository repo;

    public Blog(Scanner input, PublicacaoService publicacaoService, PalavrasProibidas palavrasProibidas,
                UsuarioRepository repo) {
        this.input = input;
        this.publicacaoService = publicacaoService;
        this.palavrasProibidas = palavrasProibidas;
        this.repo = repo;
    }

    public void inicializa() {
        repo.salva(new Usuario("José", Usuario.TipoUsuario.ADM));
        repo.salva(new Usuario("Pedro", Usuario.TipoUsuario.AUTOR));
        repo.salva(new Usuario("Maria", Usuario.TipoUsuario.ADM));
        repo.salva(new Usuario("Leandro", Usuario.TipoUsuario.AUTOR));
        repo.salva(new Usuario("Clara", Usuario.TipoUsuario.AUTOR));
        repo.salva(new Usuario("Bernardo", Usuario.TipoUsuario.AUTOR));
    }

    public void executa() {
        showMenuLogin();
        interacaoLogin();
    }


    public void showMenuLogin() {
        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/ SELECIONE 1 PARA CRIAR UM NOVO USUARIO OU SELECIONE SUA CATEGORIA /");
        System.out.println("/ 1-Logar                                                           /");
        System.out.println("/ 2-Sair                                                            /");
        System.out.println("/-------------------------------------------------------------------/");
    }

    private void showMenuAdm() {
        System.out.println("/--------------------------------------/");
        System.out.println("/ Escolha uma opcao                    /");
        System.out.println("/ 0. Deslogar                          /");
        System.out.println("/ 1. Listar Postagem                   /");
        System.out.println("/ 2. Busca postagens por tags          /");
        System.out.println("/ 3. Buscar postagens por texto        /");
        System.out.println("/ 4. Busca comentario por texto        /");
        System.out.println("/ 5. Adicionar palavras proibida       /");
        System.out.println("/ 6. Remover palavras proibidas        /");
        System.out.println("/ 7. Consultar log de ocorrencias      /");
        System.out.println("/ 8. Remover postagem                 /");
        System.out.println("/ 9. Remover comentário               /");
        System.out.println("/ 10. Painel de informações do blog    /");
        System.out.println("/--------------------------------------/");
    }

    public void showMenuAutor() {
        System.out.println("/--------------------------------------/");
        System.out.println("/ Escolha uma opcao                    /");
        System.out.println("/ 0. Deslogar                          /");
        System.out.println("/ 1. Listar Postagem                   /");
        System.out.println("/ 2. Busca postagens por tags          /");
        System.out.println("/ 3. Buscar postagens por texto        /");
        System.out.println("/ 4. Busca comentario por texto        /");
        System.out.println("/ 5. Adicionar postagem                /");
        System.out.println("/ 6. Remover postagem                  /");
        System.out.println("/ 7. Adicionar comentário              /");
        System.out.println("/ 8. Remover comentário                /");
        System.out.println("/ 9. Geração csv das postagens         /");
        System.out.println("/--------------------------------------/");
    }

    public void cadastroUsuario() {
        String escolha = null;
        Usuario.TipoUsuario tipo = null;
        System.out.println("entre com o seu nome: ");
        String nome = input.nextLine();
        int aux = 0;
        while (aux == 0) {
            System.out.println("entre com o tipo do usuario: ");
            System.out.println("1-Adm");
            System.out.println("2-Autor");
            escolha = input.nextLine();
            switch (escolha) {
                case "1":
                    tipo = Usuario.TipoUsuario.ADM;
                    aux = 1;
                    break;
                case "2":
                    tipo = Usuario.TipoUsuario.AUTOR;
                    aux = 1;
                    break;
                default:
                    System.out.println("Entrada invalida");
                    break;
            }
        }
        Usuario usuario = repo.connectUser(nome, tipo);
        if (usuario.getTipoUsuario().equals(Usuario.TipoUsuario.ADM)) {
            showMenuAdm();
            interacaoAdm(usuario);

        } else {
            showMenuAutor();
            interacaoAutor(usuario);
        }

    }

    public void interacaoLogin() {
        String escolha = input.nextLine();
        switch (escolha) {
            case "1":
                cadastroUsuario();
                break;

            case "2":
                System.out.println("Programa encerrado.");
                break;

            default:
                System.out.println("Entrada invalida.");
                showMenuLogin();
                interacaoLogin();
                break;
        }
    }

    public void interacaoAdm(Usuario usuario) {
        String escolha = input.nextLine();
        if (escolha.isBlank()) {
            escolha = input.nextLine();
        }
        switch (escolha) {
            case "0":
                showMenuLogin();
                interacaoLogin();
                break;

            case "1":
                System.out.println("POSTAGENS");
                publicacaoService.listarPostagens()
                        .forEach(this::apresentaPostagem);
                showMenuAdm();
                interacaoAdm(usuario);
                break;

            case "2":
                System.out.println("Digite a tag a ser buscada: ");
                String tag = input.nextLine();
                List<Postagem> postagemList = publicacaoService.buscaPostagemsPorTag(tag);
                if (postagemList.isEmpty()) {
                    System.out.println("Nenhum poastagem encontrada");
                } else {
                    postagemList.forEach(this::apresentaPostagem);
                }
                showMenuAdm();
                interacaoAdm(usuario);
                break;

            case "3":
                System.out.println("Insira o texto a ser buscado");
                String text = input.nextLine();
                List<Postagem> postagems = publicacaoService.buscaPostagemPorTexto(text);
                if (postagems.isEmpty()) {
                    System.out.println("Nenhum poastagem encontrada");
                } else {
                    postagems.forEach(this::apresentaPostagem);
                }
                showMenuAdm();
                interacaoAdm(usuario);
                break;

            case "4":
                System.out.println("Insira o texto a ser buscado");
                String comentario = input.nextLine();
                List<Comentario> comentarios = publicacaoService.buscaComentarioPorTexto(comentario);
                if (comentarios.isEmpty()) {
                    System.out.println("Nenhum poastagem encontrada");
                } else {
                    comentarios.forEach(this::apresentaComentarios);
                }
                showMenuAdm();
                interacaoAdm(usuario);
                break;

            case "5":
                palavrasProibidas.inserirNovaPalavra();
                showMenuAdm();
                interacaoAdm(usuario);
                break;

            case "6":
                palavrasProibidas.removerPalavraProibida();
                showMenuAdm();
                interacaoAdm(usuario);
                break;

            case "7":
                palavrasProibidas.imprimirLogsNoConsole();
                showMenuAdm();
                interacaoAdm(usuario);
                break;

            case "8":
                System.out.println("Qual id da postagem deseja deletar: ");
                int postagemId = input.nextInt();
                boolean possivel = publicacaoService.removePostagem(usuario, postagemId);
                if (possivel) {
                    System.out.println("Removido");
                } else {
                    System.out.println("Não foi possivel remover a postagem");
                }
                showMenuAdm();
                interacaoAdm(usuario);
                break;

            case "9":
                System.out.println("Qual id do comentario deseja deletar: ");
                int cometarioID = input.nextInt();
                boolean removido = publicacaoService.removeComentario(usuario, cometarioID);
                if (removido) {
                    System.out.println("Removido");
                } else {
                    System.out.println("Não foi possivel remover o comentario");
                }
                showMenuAdm();
                interacaoAdm(usuario);
                break;

            case "10":
                System.out.println("=====================");
                System.out.println(publicacaoService.top5PostagensComMaisComentarios(usuario));
                System.out.println("====================================");
                System.out.println(publicacaoService.rankingTop5UsuariosComMaisPostagens(usuario));
                System.out.println("====================================");
                System.out.println(publicacaoService.rankingTop10UsuariosComMaisComentarios(usuario));
                System.out.println("====================================");
                showMenuAdm();
                interacaoAdm(usuario);
                break;

            default:
                System.out.println("Entrada invalida");
                showMenuAdm();
                interacaoAdm(usuario);
                break;
        }
    }

    public void interacaoAutor(Usuario usuario) {
        String escolha = input.nextLine();
        if (escolha.isBlank()) {
            escolha = input.nextLine();
        }
        switch (escolha) {
            case "0":
                showMenuLogin();
                interacaoLogin();
                break;

            case "1":
                System.out.println("POSTAGENS");
                publicacaoService.listarPostagens()
                        .forEach(this::apresentaPostagem);
                showMenuAutor();
                interacaoAutor(usuario);
                break;

            case "2":
                System.out.println("Digite a tag a ser buscada: ");
                String tag = input.nextLine();
                List<Postagem> postagemList = publicacaoService.buscaPostagemsPorTag(tag);
                if (postagemList.isEmpty()) {
                    System.out.println("Nenhum poastagem encontrada");
                } else {
                    postagemList.forEach(this::apresentaPostagem);
                }
                showMenuAutor();
                interacaoAutor(usuario);
                break;

            case "3":
                System.out.println("Insira o texto a ser buscado");
                String text = input.nextLine();
                List<Postagem> postagems = publicacaoService.buscaPostagemPorTexto(text);
                if (postagems.isEmpty()) {
                    System.out.println("Nenhum poastagem encontrada");
                } else {
                    postagems.forEach(this::apresentaPostagem);
                }
                showMenuAutor();
                interacaoAutor(usuario);
                break;

            case "4":
                System.out.println("Insira o texto a ser buscado");
                String comentario = input.nextLine();
                List<Comentario> comentarios = publicacaoService.buscaComentarioPorTexto(comentario);
                if (comentarios.isEmpty()) {
                    System.out.println("Nenhum poastagem encontrada");
                } else {
                    comentarios.forEach(this::apresentaComentarios);
                }
                showMenuAutor();
                interacaoAutor(usuario);
                break;

            case "5":
                System.out.println("INSIRA O TEXTO DA POSTAGEM");
                String textoPostagem = input.nextLine();
                List<String> postagemTags = geraTags();
                boolean postagemCriada = publicacaoService.criaPostagem(usuario, textoPostagem, postagemTags);
                if (postagemCriada) {
                    System.out.println("Postagem criada");
                } else {
                    System.out.println("Postagem não foi possivel ser criada");
                }
                showMenuAutor();
                interacaoAutor(usuario);
                break;

            case "6":
                System.out.println("Qual id da postagem deseja deletar: ");
                int postagemId = input.nextInt();
                boolean possivel = publicacaoService.removePostagem(usuario, postagemId);
                if (possivel) {
                    System.out.println("Removido");
                } else {
                    System.out.println("Não foi possivel remover a postagem");
                }
                showMenuAutor();
                interacaoAutor(usuario);
                break;

            case "7":
                System.out.println("INSIRA O TEXTO DO COMETARIO");
                String textoComentario = input.nextLine();
                System.out.println("De qual postagem gostaria de comentar");
                int postagemIdToComment = input.nextInt();
                boolean comentarioCriado = publicacaoService.comentarPostagem(postagemIdToComment, textoComentario, usuario);
                if (comentarioCriado) {
                    System.out.println("Comentario Feito");
                } else {
                    System.out.println("Cometario não foi possivel ser criada");
                }
                showMenuAutor();
                interacaoAutor(usuario);
                break;
            case "8":
                System.out.println("Qual id do comentario deseja deletar: ");
                int cometarioID = input.nextInt();
                boolean removido = publicacaoService.removeComentario(usuario, cometarioID);
                if (removido) {
                    System.out.println("Removido");
                } else {
                    System.out.println("Não foi possivel remover o comentario");
                }
                showMenuAutor();
                interacaoAutor(usuario);
                break;

            case "9":
                System.out.println("Insira o nome do arquivo para salvar as postagens");
                String arqName = input.nextLine();
                publicacaoService.salvaPostagensArqCsv(arqName, usuario);
                showMenuAutor();
                interacaoAutor(usuario);
                break;

            default:
                System.out.println("Entrada invalida");
                showMenuAutor();
                interacaoAutor(usuario);
                break;
        }
    }

    private List<String> geraTags() {
        String tag;
        List<String> tags = new ArrayList<>();
        do {
            System.out.println("Inira as tags, digite -1 para sair");
            tag = input.nextLine();
            if (!tag.equals("-1")) {
                tags.add(tag);
            }
        } while (!tag.equals("-1") || tags.isEmpty());
        return tags;
    }

    private void apresentaComentarios(Comentario comentario) {
        System.out.println("======================");
        System.out.println(comentario);
        System.out.println("======================");
    }

    private void apresentaPostagem(Postagem postagem) {
        System.out.println("======================");
        System.out.println(postagem);
        System.out.println("======================");
    }
}

import java.util.Scanner;

public class Blog {
    Scanner input = new Scanner(System.in);

    public void inicializa(){

        Usuarios usuario0 = new Usuarios("José", Usuarios.TipoUsuario.ADM);

    }
    public void executa(){
        showMenuLogin();
        interacaoLogin();
    }


    public void showMenuLogin() {

        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/SELECIONE 1 PARA CRIAR UM NOVO USUARIO OU SELECIONE SUA CATEGORIA  /");
        System.out.println("/ 1-Voce é um novo usuario?                                         /");
        System.out.println("/ 2-você ja possui uma conta?                                       /");
        System.out.println("/ 3-Sair                                                            /");
        System.out.println("/-------------------------------------------------------------------/");
    }

    private void showMenuAdm() {
        System.out.println("/--------------------------------------/");
        System.out.println("/ Escolha uma opcao                    /");
        System.out.println("/ 0. Deslogar                          /");
        System.out.println("/ 1. Busca postagens por tags          /");
        System.out.println("/ 2. Buscar postagens por texto        /");
        System.out.println("/ 3. Busca comentario por texto        /");
        System.out.println("/ 4. Editar comentario                 /");
        System.out.println("/ 5. Editar postagem                   /");
        System.out.println("/ 6. Adicionar palavras proibida       /");
        System.out.println("/ 7. Remover palavras proibidas        /");
        System.out.println("/ 8. Consultar log de ocorrencias      /");
        System.out.println("/ 9. Adicionar postagem                /");
        System.out.println("/ 10. Remover postagem                 /");
        System.out.println("/ 11. Adicionar comentário             /");
        System.out.println("/ 12. Remover comentário               /");
        System.out.println("/ 13. Salvar em arquivo CSV            /");
        System.out.println("/ 14. Painel de informações do blog    /");
        System.out.println("/--------------------------------------/");
    }

    public void showMenuAutor() {
        System.out.println("/--------------------------------------/");
        System.out.println("/ Escolha uma opcao                    /");
        System.out.println("/ 0. Deslogar                          /");
        System.out.println("/ 1. Busca postagens por tags          /");
        System.out.println("/ 2. Buscar postagens por texto        /");
        System.out.println("/ 3. Busca comentario por texto        /");
        System.out.println("/ 4. Editar comentario                 /");
        System.out.println("/ 5. Editar postagem                   /");
        System.out.println("/ 6. Adicionar postagem                /");
        System.out.println("/ 7. Remover postagem                  /");
        System.out.println("/ 8. Adicionar comentário              /");
        System.out.println("/ 9. Remover comentário                /");
        System.out.println("/--------------------------------------/");
    }

    public void cadastroUsuario(){
        String escolha = null;
        Usuarios.TipoUsuario tipo = null;
        System.out.println("entre com o seu nome: ");
        String nome = input.nextLine();
        int aux = 0;
        while (aux == 0) {
            System.out.println("entre com o tipo do usuario: ");
            System.out.println("1-Adm");
            System.out.println("2-Autor");
            escolha = input.nextLine();
            switch (escolha){
                case "1":
                    tipo = Usuarios.TipoUsuario.ADM;
                    aux = 1;
                    break;
                case "2":
                    tipo = Usuarios.TipoUsuario.AUTOR;
                    aux = 1;
                    break;
                default:
                    System.out.println("Entrada invalida");
                    break;
            }
        }
        Usuarios u = new Usuarios(nome,tipo);
        if(u.getTipoUsuario().equals(Usuarios.TipoUsuario.ADM)){
            showMenuAdm();
            interacaoAdm();

        }
        else{
            showMenuAutor();
            interacaoAutor();
        }

    }
    public void usuarioCadastrado(){
        System.out.println("/Listra de usuarios para escolher/");

        /*if(usuario.getTipoUsuario().equals(Usuarios.TipoUsuario.ADM)){
            showMenuAdm();
            interacaoAdm();

        }
        else{
            showMenuAutor();
            interacaoAutor();
        }*/
    }

    public void interacaoLogin(){
        String escolha = input.nextLine();
        switch (escolha){
            case "1":
                cadastroUsuario();
                break;

            case "2":
                showMenuAutor();
                interacaoAutor();
                break;

            case "3":
                System.out.println("Programa encerrado.");
                break;

            default:
                System.out.println("Entrada invalida.");
                showMenuLogin();
                interacaoLogin();
                break;
        }
    }

    public void interacaoAdm(){
        Scanner input = new Scanner(System.in);
        String escolha = input.nextLine();
        switch (escolha){
            case "0":
                showMenuLogin();
                interacaoLogin();
                break;

            case "1":
                System.out.println("1");
                showMenuAdm();
                interacaoAdm();
                break;

            case "2":
                System.out.println("2");
                showMenuAdm();
                interacaoAdm();
                break;

            case "3":
                System.out.println("3");
                showMenuAdm();
                interacaoAdm();
                break;

            case "4":
                System.out.println("4");
                showMenuAdm();
                interacaoAdm();
                break;

            case "5":
                System.out.println("5");
                showMenuAdm();
                interacaoAdm();
                break;

            case "6":
                System.out.println("6");
                showMenuAdm();
                interacaoAdm();
                break;

            case "7":
                System.out.println("7");
                showMenuAdm();
                interacaoAdm();
                break;

            case "8":
                System.out.println("8");
                showMenuAdm();
                interacaoAdm();
                break;

            case "9":
                System.out.println("9");
                showMenuAdm();
                interacaoAdm();
                break;

            case "10":
                System.out.println("10");
                showMenuAdm();
                interacaoAdm();
                break;

            case "11":
                System.out.println("11");
                showMenuAdm();
                interacaoAdm();
                break;

            case "12":
                System.out.println("12");
                showMenuAdm();
                interacaoAdm();
                break;

            case "13":
                System.out.println("13");
                showMenuAdm();
                interacaoAdm();
                break;

            case "14":
                System.out.println("14");
                showMenuAdm();
                interacaoAdm();
                break;

            default:
                System.out.println("Entrada invalida");
                showMenuAdm();
                interacaoAdm();
                break;
        }
    }

    public void interacaoAutor(){
        Scanner input = new Scanner(System.in);
        String escolha = input.nextLine();
        switch (escolha){
            case "0":
                showMenuLogin();
                interacaoLogin();
                break;

            case "1":
                System.out.println("1");
                showMenuAutor();
                interacaoAutor();
                break;

            case "2":
                System.out.println("2");
                showMenuAutor();
                interacaoAutor();
                break;

            case "3":
                System.out.println("3");
                showMenuAutor();
                interacaoAutor();
                break;

            case "4":
                System.out.println("4");
                showMenuAutor();
                interacaoAutor();
                break;

            case "5":
                System.out.println("5");
                showMenuAutor();
                interacaoAutor();
                break;

            case "6":
                System.out.println("6");
                showMenuAutor();
                interacaoAutor();
                break;

            case "7":
                System.out.println("7");
                showMenuAutor();
                interacaoAutor();
                break;

            case "8":
                System.out.println("8");
                showMenuAdm();
                interacaoAdm();
                break;

            case "9":
                System.out.println("9");
                showMenuAdm();
                interacaoAdm();
                break;

            default:
                System.out.println("Entrada invalida");
                showMenuAutor();
                interacaoAutor();
                break;
        }
    }
}

import java.util.Scanner;

public class Blog {
    Scanner input = new Scanner(System.in);


    public void executa(){
        showMenuLogin();
        interacaoLogin();
    }


    public void showMenuLogin() {
        System.out.println("/--------------------------------/");
        System.out.println("/ Qual o seu nível de acesso: ---/");
        System.out.println("/ 1-Adm                          /");
        System.out.println("/ 2-Autor                        /");
        System.out.println("/ 3-Sair                         /");
        System.out.println("/--------------------------------/");
    }

    private void showMenuAdm() {
        System.out.println("/--------------------------------------/");
        System.out.println("/ Escolha uma opcao                    /");
        System.out.println("/ 0. Deslogar                          /");
        System.out.println("/ 1. Busca postagens por tags          /");
        System.out.println("/ 2. Buscar postagens por texto        /");
        System.out.println("/ 3. Busca comentario por texto        /");
        System.out.println("/ 4. Adicionar palavras proibida       /");
        System.out.println("/ 5. Remover palavras proibidas        /");
        System.out.println("/ 6. Consultar log de ocorrencias      /");
        System.out.println("/ 7. Adicionar postagem                /");
        System.out.println("/ 8. Remover postagem                  /");
        System.out.println("/ 9. Adicionar comentário              /");
        System.out.println("/ 10. Remover comentário               /");
        System.out.println("/ 11. Salvar em arquivo CSV            /");
        System.out.println("/ 12. Painel de informações do blog    /");
        System.out.println("/--------------------------------------/");
    }

    public void showMenuAutor() {
        System.out.println("/--------------------------------------/");
        System.out.println("/ Escolha uma opcao                    /");
        System.out.println("/ 0. Deslogar                          /");
        System.out.println("/ 1. Busca postagens por tags          /");
        System.out.println("/ 2. Buscar postagens por texto        /");
        System.out.println("/ 3. Busca comentario por texto        /");
        System.out.println("/ 4. Adicionar postagem                /");
        System.out.println("/ 5. Remover postagem                  /");
        System.out.println("/ 6. Adicionar comentário              /");
        System.out.println("/ 7. Remover comentário                /");
        System.out.println("/--------------------------------------/");
    }



    public void interacaoLogin(){
        String escolha = input.nextLine();
        switch (escolha){
            case "1":
                showMenuAdm();
                interacaoAdm();
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
                System.out.println("7");
                showMenuAdm();
                interacaoAdm();
                break;

            case "9":
                System.out.println("7");
                showMenuAdm();
                interacaoAdm();
                break;

            case "10":
                System.out.println("7");
                showMenuAdm();
                interacaoAdm();
                break;

            case "11":
                System.out.println("7");
                showMenuAdm();
                interacaoAdm();
                break;

            case "12":
                System.out.println("7");
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

            default:
                System.out.println("Entrada invalida");
                showMenuAutor();
                interacaoAutor();
                break;
        }
    }


}

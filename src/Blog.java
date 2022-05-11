import java.util.Scanner;

public class Blog {
    public void executa(){
        showMenuLogin();
        interacaoLogin();
    }

    private void showMenuAdm() {
        System.out.println("/--------------------------------/");
        System.out.println("/ Escolha uma opcao              /");
        System.out.println("/ 0. Deslogar                    /");
        System.out.println("/ 1. Ver postagens               /");
        System.out.println("/ 2. Buscar postagem             /");
        System.out.println("/ 3. Adicionar postagem          /");
        System.out.println("/ 4. remover postagem            /");
        System.out.println("/ 5. Adicionar comentário        /");
        System.out.println("/ 6. remover comentário          /");
        System.out.println("/ 7. gerar relátorio             /");
        System.out.println("/--------------------------------/");
    }

    public void showMenuAutor() {
        System.out.println("/--------------------------------/");
        System.out.println("/ Escolha uma opcao              /");
        System.out.println("/ 0. Deslogar                    /");
        System.out.println("/ 1. Ver postagens               /");
        System.out.println("/ 2. Buscar postagem             /");
        System.out.println("/ 3. Adicionar postagem          /");
        System.out.println("/ 4. remover postagem            /");
        System.out.println("/ 5. Adicionar comentário        /");
        System.out.println("/ 6. remover comentário          /");
        System.out.println("/ 7. gerar relátorio             /");
        System.out.println("/--------------------------------/");
    }

    public void showMenuLogin() {
        System.out.println("/--------------------------------/");
        System.out.println("/ Qual o seu nível de acesso: ---/");
        System.out.println("/ 1-Adm                          /");
        System.out.println("/ 2-Autor                        /");
        System.out.println("/ 3-Sair                         /");
        System.out.println("/--------------------------------/");
    }

    public void interacaoLogin(){
        Scanner input = new Scanner(System.in);
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

            default:
                System.out.println("Entrada invalida");
                showMenuAdm();
                interacaoAdm();
                break;
        }
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PalavrasProibidas {

    private Scanner sc;
    private List<String> palavrasProibidas = new ArrayList<>();
    private List<String> listaDeLogs = new ArrayList<>();
    public String retornoDeTesteDeMensagem = "Não há palavras proibidas!";
    private Boolean oMenuEstaAtivo = true;



    PalavrasProibidas(Scanner _sc) {
        palavrasProibidas.add("Bobao");
        palavrasProibidas.add("Drogas");
        palavrasProibidas.add("Otorrinolaringologista");
        sc = _sc;
    }

    public void iniciarMenuPalavrasProibidas(){
        oMenuEstaAtivo = true;
        while(oMenuEstaAtivo){
            for(int i = 0; i < 5; i++) System.out.print('\n');
            int valorMenu = iniciarMenuPrincipal();
            for(int i = 0; i < 5; i++) System.out.print('\n');
            selecionarOpcoesDoMenu(valorMenu);
        }
    }
    private String menu =
            "###################################################\n" +
                    "        P A L A V R A S   P R O I B I D A S        \n" +
                    "###################################################\n" +
                    "1 - Inserir nova palavra proibida\n" +
                    "2 - Remover palavra proibida\n" +
                    "3 - Lista de palavras proibidas\n" +
                    "4 - Ver Logs de mensagens proibidas\n" +
                    "5 - Sair do menu Palavras proibidas\n" +
                    "###################################################\n" +
                    "Imput: ";

    private String menu1 =
                        "###################################################\n" +
                        "           I N S E R I R   P A L A V R A           \n" +
                        "###################################################\n";
    private String menu2 =
                        "###################################################\n" +
                        "           R E M O V E R   P A L A V R A           \n" +
                        "###################################################\n";
    private String menu3 =
                        "###################################################\n" +
                        "           T E S T A R   M E N S A G E M           \n" +
                        "###################################################\n" +
                        "Imput: ";
    private String menu4 =
                        "###################################################\n" +
                        "        P A L A V R A S   P R O I B I D A S        \n" +
                        "###################################################\n";
    private String menu5 =
                        "###################################################\n" +
                        "             L I S T A   D E   L O G S             \n" +
                        "###################################################\n";

    //  Imprime no console a lista de palavras proibidas
    private void listarPalavrasProibidas() {
        System.out.print(menu4);
        System.out.println("Lista de palavras cadastradas:");
        for (int i = 0; i < palavrasProibidas.size(); i++)
            System.out.println(" * " + palavrasProibidas.get(i));
        System.out.println("###################################################");
    }

    private int iniciarMenuPrincipal() {
        System.out.print(menu);
        var imput = sc.nextLine(); //aguarda o imput do teclado
        boolean isNumeric = imput.matches("[+-]?\\d*(\\.\\d+)?"); //verifica se o texto é numérico ou não, se for, retorna um inteiro contido caso esteja dentro do range do menu
        if (isNumeric) {
            return Integer.parseInt(imput); //converte de string para inteiro
        } else {
            return 0;
        }
    }

    //  converte a String de minusculo para maiúsculo
    private List<String> converterListaDePalavrasProibidasParaUpperCase(List<String> listaDePalavrasProibidas) {
        List<String> palavrasProibidasMaiusculas = new ArrayList<>();
        for (int i = 0; i < listaDePalavrasProibidas.size(); i++) {
            palavrasProibidasMaiusculas.add(palavrasProibidas.get(i).toUpperCase());
        }
        return palavrasProibidasMaiusculas;
    }

    //  Insere palavra proibida da lista de palavras proibidas
    public void inserirNovaPalavra() {
        System.out.print(menu1);
        listarPalavrasProibidas();
        System.out.println("###################################################");
        System.out.print("Nova palavra: ");
        String novaPalavra = sc.nextLine();

        if (!converterListaDePalavrasProibidasParaUpperCase(palavrasProibidas).contains(novaPalavra.toUpperCase())) {
            palavrasProibidas.add(novaPalavra);
            System.out.println("Palavra inserida com sucesso!");
            System.out.println("###################################################");
        } else {
            System.out.println("Palavra já existente!");
            System.out.println("###################################################");
        }
    }

    //  Remove palavra proibida da lista de palavras proibidas
    public void removerPalavraProibida() {
        System.out.print(menu2);
        listarPalavrasProibidas();
        System.out.println("###################################################");
        System.out.print("Palavra para remover: ");
        String palavraParaRemover = sc.nextLine();
        Boolean contemPalavraNaLista = false;
        for (int i = 0; i < palavrasProibidas.size(); i++) {
            if (palavrasProibidas.get(i).equalsIgnoreCase(palavraParaRemover)) {
                contemPalavraNaLista = true;
                palavrasProibidas.remove(i);
            }
        }
        if (contemPalavraNaLista) {
            System.out.println("Palavra removida com sucesso!");
            System.out.println("###################################################");
        } else {
            System.out.println("Palavra não cadastrada!");
            System.out.println("###################################################");
        }
    }

    //  Retorna data com horário, ex.: 10/05/2022 - 00:33:52
    private String dataAtual() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu - HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    //  Retorna true se haver palavras proibidas e salva na lista de string "listaDeLogs" um log no formato a seguir:
    //  Palavra proibida: Bobao em 07/05/2022 - 12:43:33 Usuario: nomeDoUsuario
    //  A mensagem de retorno fica armazenada na variável PalavrasProibidasNaMensagem.
    public Boolean verificarSeHaPalavrasProibidas(String mensagem, String usuario) {
        Boolean haPalavrasProibidas = false;
        String PalavrasProibidasNaMensagem = "A sua mensagem contém: ";
        List<String> mensagemDoUsuario = Arrays.asList(mensagem.replaceAll("[^à-úÀ-ÚA-Za-z0-9 ]","").split(" "));
        String log = mensagem + "\nPalavra(s) proibida(s): ";;
        for (int i = 0; i < palavrasProibidas.size(); i++) {
            for (int j = 0; j < mensagemDoUsuario.size(); j++) {
                if (mensagemDoUsuario.get(j).equalsIgnoreCase(palavrasProibidas.get(i))) {
                    log += palavrasProibidas.get(i) + ", ";
                    PalavrasProibidasNaMensagem += palavrasProibidas.get(i) + ", ";
                    haPalavrasProibidas = true;
                }
            }
        }
        log += dataAtual() + " - User: " + usuario;
        listaDeLogs.add(log);
        PalavrasProibidasNaMensagem += "e isto não é permitido!";

        if (haPalavrasProibidas) {
            retornoDeTesteDeMensagem = PalavrasProibidasNaMensagem;
        } else {
            retornoDeTesteDeMensagem = "Não há palavras proibidas";
        }
        System.out.println(retornoDeTesteDeMensagem);
        return haPalavrasProibidas;
    }

    //  Imprime no console a lista de logs
    public void imprimirLogsNoConsole() {
        System.out.print(menu5);
        if (listaDeLogs.isEmpty()) {
            System.out.println("Não há logs registrados no momento");
        } else {
            for (int i = 0; i < listaDeLogs.size(); i++) {
                System.out.println(listaDeLogs.get(i));
            }
        }
        System.out.println("###################################################");
    }
    private void selecionarOpcoesDoMenu(int opcao){
        switch (opcao){
            case 1:
                inserirNovaPalavra();
                break;
            case 2:
                removerPalavraProibida();
                break;
            case 3:
                listarPalavrasProibidas();
                break;
            case 4:
                imprimirLogsNoConsole();
                break;
            case 5:
                oMenuEstaAtivo = false;
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}

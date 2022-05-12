import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Usuarios u1 = new Usuarios("adm-111", "");
        Usuarios u2 = new Usuarios("adm-222", "userMaster");

        //Para iniciar o menu
        PalavrasProibidas palavrasProibidas = new PalavrasProibidas(sc);

        //Para entrar no menu
        palavrasProibidas.iniciarMenuPalavrasProibidas();

        //Para enviar mensagem
        Boolean enviarMensagem = palavrasProibidas.verificarSeHaPalavrasProibidas("Cala a boca, eu sou bobao", u2.getNome());

        //Para entrar no menu
        palavrasProibidas.iniciarMenuPalavrasProibidas();

    }
}

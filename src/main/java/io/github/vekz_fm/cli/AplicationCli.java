package io.github.vekz_fm.cli;

public class AplicationCli {


    public static void run(String[] args) {

        if(args.length == 0) {
            System.out.println("No se aceptan comandos en blanco, intenta de nuevo.");
        }
        else if (args.length >= 2) {
            System.out.println("El comando recibe más parametros de los solicitados.");
        }else {
            String user = args[0];
        }

    }


}

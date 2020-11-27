//Galindo Salinas Matias Alejandro
import java.util.*;
import java.util.Scanner;

public class Buscaminas{
    private static final int TAMANO = 10;
    private char tablero[][] = new char [TAMANO][TAMANO];
    private int intNumeroMinas = 8;
    private Random random =  new Random();
    private boolean esFinal=false;
    private String digitos = "0123456789";

    private void asignarMina(){
        int iColumna, iRenglon;
        iColumna = random.nextInt(TAMANO);
        iRenglon = random.nextInt(TAMANO);
        if (tablero[iRenglon][iColumna]=='M'){
            asignarMina();
            return;
        }
        tablero[iRenglon][iColumna]='M';
    }

    public void iniciarTablero(){
        
        for(int i=0;i<tablero.length;i++){
            for(int j=0;j<tablero[i].length;j++){
                tablero[i][j]=' ';
            }
        }
        
        for(int i=0;i<intNumeroMinas;i++){
            asignarMina();
        }
    }

    private void imprimirRenglon(int intRenglon){
        System.out.print((intRenglon)+"   ");
        for(int j=0;j<tablero[intRenglon].length;j++){
            System.out.print("| ");
            if ((tablero[intRenglon][j]==' ' || tablero[intRenglon][j]=='M') && !esFinal){
                System.out.print("X");
            } else {
                System.out.print(tablero[intRenglon][j]);
            }
            System.out.print(" ");
        }
        System.out.println("|");
    }


    public void imprimirTablero(){
        char cEncabezado = 'A';
        System.out.print("    ");
        for(int i=0;i<TAMANO;i++){
            System.out.print("  "+cEncabezado+" ");
            cEncabezado++;
        }
        System.out.println();
        System.out.print("    ");
        for(int i=0;i<TAMANO;i++){
            System.out.print("----");
        }
        System.out.println("-");
        for(int i=0;i<TAMANO;i++){
            imprimirRenglon(i);
        }
        System.out.print("    ");
        for(int i=0;i<TAMANO;i++){
            System.out.print("----");
        }
        System.out.println("-");
        
    }

    private void iniciar(){
        esFinal=false;
        iniciarTablero();
        System.out.println("***** BUSCAMINAS *****");
        imprimirTablero();
    }

    private int tieneMina(int iRenglon, int iColumna){
        if(iRenglon<0 || iRenglon >= TAMANO || iColumna<0 || iColumna >= TAMANO ) {
            return 0;
        }
        if (tablero[iRenglon][iColumna]=='M') {
            return 1;
        }
        return 0;
    }

    private int contarMinas(int iRenglon, int iColumna){
        return tieneMina(iRenglon-1,iColumna-1) +
            tieneMina(iRenglon-1,iColumna) +
            tieneMina(iRenglon-1,iColumna+1) +
            tieneMina(iRenglon,iColumna-1) +
            tieneMina(iRenglon,iColumna+1) +
            tieneMina(iRenglon+1,iColumna-1) +
            tieneMina(iRenglon+1,iColumna) +
            tieneMina(iRenglon+1,iColumna+1) ;
    }

    private boolean esJuegoGanador(){
        
        for(int i=0;i<tablero.length;i++){
            for(int j=0;j<tablero[i].length;j++){
                if ( tablero[i][j]==' '){
                    return false;
                }
            }
        }
        return true;
    }

    private void asignarEntrada(int iRenglon, int iColumna){
        
        if (tablero[iRenglon][iColumna]=='M'){
            System.out.println("\n\n XXXXXXXXXX PERDISTE! .\n\n\n");
            esFinal=true;
            imprimirTablero();
            System.out.println("comenzando otro juego...\n\n");
            iniciar();
            return;
        }
        tablero[iRenglon][iColumna]= digitos.charAt( contarMinas(iRenglon, iColumna) );
        if (esJuegoGanador()){
            System.out.println("\n\n !!!!! GANASTE !!!!! .\n\n");
            esFinal=true;
            imprimirTablero();
            System.out.println("comenzando otro juego...\n\n");
            iniciar();
            return;
        }
        imprimirTablero();
    }

    public void registrarEntrada(String sEntrada){
        char cRenglon, cColumna;
        if( sEntrada.length()>2){
            System.out.println("Error, opci'on incorrecta.");
            return;
        }
        sEntrada = sEntrada.toUpperCase();
        cColumna = sEntrada.charAt(0);
        cRenglon = sEntrada.charAt(1);
        if (cColumna<'A'||cColumna>'J'){
            System.out.println("Error, columna incorrecta.");
            return;
        }
        if (cRenglon<'0'||cRenglon>'9'){
            System.out.println("Error, rengl'on incorrecto.");
            return;
        }
        asignarEntrada(cRenglon-'0', cColumna-'A');
    }

    public char leerOpcion(Scanner entrada){
        String sEntrada;
        System.out.println("Seleccione una opci'on:");
        System.out.println("(R)einiciar, (S)alir, <Introducir Coordenada>");
        sEntrada = entrada.next();
        switch (sEntrada.charAt(0)){
            case 's':
            case 'S':
                return 's';
            case 'r':
            case 'R':
                iniciar();
                return 'r';
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
                registrarEntrada(sEntrada);
                return ' ';
            default:
                System.out.println("Error, opci'on incorrecta.");
                return ' ';
        }
    }

    public static void main(String[] args){
        Buscaminas buscaminas = new Buscaminas();
        Scanner entrada = new Scanner(System.in);
        char cOpcion = ' ';
        buscaminas.iniciar();
        while (cOpcion!='s'){
            cOpcion = buscaminas.leerOpcion(entrada);
        }
    }




}
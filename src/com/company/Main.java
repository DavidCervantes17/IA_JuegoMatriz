package com.company;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here
        int [][] mat = new int[4][4];
        int [][] matfin = new int[4][4];
        boolean comenzar = true;
        do{
            System.out.println("Seleccione una opción");
            System.out.println("1. Usar valores por default para matriz de entrada y salida");
            System.out.println("2. Modificar matriz de entrada");
            System.out.println("3. Modificar matriz de salida");
            System.out.println("4. Comenzar");
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            switch (s){
                case "1":
                    mat = init(mat);
                    matfin = initFin(matfin);
                    break;
                case "2":
                    mat = llenarMat(mat);
                    break;
                case "3":
                    matfin = llenarMat(matfin);
                    break;
                case "4":
                    comenzar = false;
                    break;
            }
        }while(comenzar != false);
        System.out.println("----------------------------------- Matriz inicial -----------------------------------");
        printMat(mat);
        System.out.println("----------------------------------- Matriz Final -----------------------------------");
        printMat(matfin);
        jugar(mat, matfin);
    }

    public static void jugar(int[][] mat, int[][] matfin) throws InterruptedException {
        System.out.println("----------------------------------- Jugando -----------------------------------");
        int i,j;
        int i1 = 0;
        int j1 = 0;
        int aux;
        //arriba, abajo, izquierda, derecha
        boolean [] direcciones = new boolean [4];
        int mover = 0;
        int ultimoMov = -1;
        Stack<Integer> movs4 = new Stack<Integer>();

        while(true){
            //ver movimientos posibles
            for(i=0; i<4; i++){
                for(j=0; j<4; j++){
                    if(mat[i][j] == -1){
                        i1=i;
                        j1=j;
                        direcciones[0] = false;
                        direcciones[1] = false;
                        direcciones[2] = false;
                        direcciones[3] = false;
                        System.out.println("Opciones: ");
                        if(i != 0){
                            System.out.print(" arriba");
                            direcciones[0] = true;
                        }
                        if(i != 3){
                            System.out.print(" abajo");
                            direcciones[1] = true;
                        }
                        if(j != 0){
                            System.out.print(" izquierda");
                            direcciones[2] = true;
                        }
                        if(j != 3){
                            System.out.print(" derecha");
                            direcciones[3] = true;
                        }
                    }
                }
            }

            //bloquear movimiento inmediato anterior
            if(ultimoMov != -1){
                switch (ultimoMov){
                    case 0:
                        direcciones[1] = false;
                        break;
                    case 1:
                        direcciones[0] = false;
                        break;
                    case 2:
                        direcciones[3] = false;
                        break;
                    case 3:
                        direcciones[2] = false;
                        break;
                }
            }
            /*
            if(movs4.size()>4){
                //Bucles
                if(movs4.get(0) == 3 && movs4.get(1)==0 && movs4.get(2)==2 &&movs4.get(3)==1 ){
                    System.out.println("CICLO!!!");
                    //bloquear movimiento
                    direcciones[1] = false;

                }
                if(movs4.get(0) == 1 && movs4.get(1)==3 && movs4.get(2)==0 &&movs4.get(3)==2 ){
                    System.out.println("CICLO!!!");
                    //bloquear movimiento
                    direcciones[1] = false;

                }
                if(movs4.get(0) == 2 && movs4.get(1)==1 && movs4.get(2)==3 &&movs4.get(3)==0 ){
                    System.out.println("CICLO!!!");
                    //bloquear movimiento
                    direcciones[1] = false;

                }
                if(movs4.get(0) == 0 && movs4.get(1)==2 && movs4.get(2)==1 &&movs4.get(3)==3 ){
                    System.out.println("CICLO!!!");
                    //bloquear movimiento
                    direcciones[1] = false;

                }



                if(movs4.get(0) == 3 && movs4.get(1)==1 && movs4.get(2)==2 &&movs4.get(3)==0 ){
                    System.out.println("Ciclo!!!");
                    //bloquear movimient
                    direcciones[1] = false;


                }
                if(movs4.get(0) == 0 && movs4.get(1)==3 && movs4.get(2)==1 &&movs4.get(3)==2 ){
                    System.out.println("Ciclo!!!");
                    //bloquear movimient
                    direcciones[1] = false;


                }
                if(movs4.get(0) == 2 && movs4.get(1)==0 && movs4.get(2)==3 &&movs4.get(3)==1 ){
                    System.out.println("Ciclo!!!");
                    //bloquear movimient
                    direcciones[1] = false;


                }
                if(movs4.get(0) == 1 && movs4.get(1)==2 && movs4.get(2)==0 &&movs4.get(3)==3 ){
                    System.out.println("Ciclo!!!");
                    //bloquear movimient
                    direcciones[1] = false;


                }

                movs4.remove(0);

            }*/


            //bloquear mismo mov

            //elegir uno con random
            /*while(true){
                Random random = new Random();
                int r = random.nextInt(3 - 0) + 0;
                if(direcciones[r]==true){
                    mover = r;
                    break;
                }
            }*/

            //calcular el costo para cada uno de los movimientos validos
            int [] costos = new int[4];
            int m, n;
            for(i=0;i<4;i++){
                costos[i]=100;
                //calcular matriz en el movimiento
                //copiar matriz en la nueva
                int [][] matTemp = new int[4][4];
                for(m=0;m<4;m++){
                    for(n=0;n<4;n++){
                        matTemp[m][n] = mat[m][n];
                    }
                }
                if(direcciones[i] == true){
                    //hacer el movimiento
                    if(i == 0){
                        aux = matTemp[i1-1][j1];
                        matTemp[i1-1][j1] = -1;
                        matTemp[i1][j1] = aux;
                    }else if(i == 1){
                        aux = matTemp[i1+1][j1];
                        matTemp[i1+1][j1] = -1;
                        matTemp[i1][j1] = aux;
                    }else if(i == 2){
                        aux = matTemp[i1][j1-1];
                        matTemp[i1][j1-1] = -1;
                        matTemp[i1][j1] = aux;
                    }else if(i == 3){
                        aux = matTemp[i1][j1+1];
                        matTemp[i1][j1+1] = -1;
                        matTemp[i1][j1] = aux;
                    }
                    //
                    costos[i] = calcularCostos(matTemp, matfin);
                }
            }
            //elegir el costo menor
            int menor = 100;
            int imenor = -1;

            for(i=0;i<4;i++){
                if(costos[i] < menor){
                    menor = costos[i];
                    imenor = i;
                }
            }
            Stack<Integer> menors = new Stack<Integer>();

            for(i=0;i<4;i++){
                if(costos[i] == menor){
                    menors.push(i);
                }
            }

            int tam = menors.size();
            Random random = new Random();
            int r = random.nextInt(tam - 0) + 0;
            // no
            mover = menors.get(r);

            //mover = imenor;
            movs4.push(mover);
            ultimoMov = mover;
            //moverlo
            //arriba abajo izquierda derecha
            System.out.println();
            System.out.println("Elegido:");

            if(mover == 0){
                System.out.println(" arriba");
                //System.out.println("i1:"+i1+" j1:"+j1);
               aux = mat[i1-1][j1];
               mat[i1-1][j1] = -1;
               mat[i1][j1] = aux;
            }else if(mover == 1){
                System.out.println(" abajo");
                //System.out.println("i1:"+i1+" j1:"+j1);
                aux = mat[i1+1][j1];
                mat[i1+1][j1] = -1;
                mat[i1][j1] = aux;
            }else if(mover == 2){
                System.out.println(" izquierda");
                //System.out.println("i1:"+i1+" j1:"+j1);
                aux = mat[i1][j1-1];
                mat[i1][j1-1] = -1;
                mat[i1][j1] = aux;
            }else if(mover == 3){
                System.out.println(" derecha");
                //System.out.println("i1:"+i1+" j1:"+j1);
                aux = mat[i1][j1+1];
                mat[i1][j1+1] = -1;
                mat[i1][j1] = aux;
            }

            printMat(mat);

            //detener si matrices son iguales
            int fin = calcularCostos(mat, matfin);
            if(fin==0) break;

            //esperar 5 segundos
            //Thread.sleep(5000);



        }
    }

    private static int calcularCostos(int[][] mat, int[][] matfin) {
        int i,j;
        int costo = 0;
        for(i=0; i<4; i++){
            for(j=0; j<4; j++){
                if(mat[i][j] != matfin[i][j]){
                    costo++;
                }
            }
        }
        return costo;
    }

    public static int[][] init(int[][] mat){
        int i,j;
        for(i=0; i<4; i++){
            for(j=0; j<4; j++){
                mat[i][j] = (j+1)+(i*4);
            }
        }
        mat[3][3] = -1;
        return mat;
    }

    public static int[][] initFin(int[][] mat){
        int i,j;
        for(i=0; i<4; i++){
            for(j=0; j<4; j++){
                if(i == 0 || i == 2){
                    mat[i][j] = (j+1)+(i*4);
                }else{
                    mat[i][j] = (-j)+((i+1)*4);
                }
            }
        }
        mat[3][0] = -1;
        return mat;
    }

    public static int[][] llenarMat(int[][] mat){
        int i,j;
        for(i=0; i<4; i++){
            for(j=0; j<4; j++){
                Scanner sc=new Scanner(System.in);
                int a= sc.nextInt();
                mat[i][j] = a;
            }
        }
        return mat;
    }

    public static void printMat(int[][] mat){
        int i,j;
        for(i=0; i<4; i++){
            for(j=0; j<4; j++){
                System.out.print("\t"+mat[i][j]);
            }
            System.out.println();
        }
    }
}

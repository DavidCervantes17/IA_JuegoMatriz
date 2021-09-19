package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here
        int [][] mat = new int[4][4];
        mat = init(mat);
        printMat(mat);
        jugar(mat);
    }

    public static void jugar(int[][] mat) throws InterruptedException {
        System.out.println("-----------------------------------Jugando-----------------------------------");
        int i,j;
        int i1 = 0;
        int j1 = 0;
        int aux;
        //arriba, abajo, izquierda, derecha
        boolean [] direcciones = new boolean [4];
        int mover = 0;
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

            //elegir uno con random
            while(true){
                Random random = new Random();
                int r = random.nextInt(3 - 0) + 0;
                if(direcciones[r]==true){
                    mover = r;
                    break;
                }
            }
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
            //esperar 3 segundos
            Thread.sleep(5000);

        }
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

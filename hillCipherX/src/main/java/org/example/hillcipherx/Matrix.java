package org.example.hillcipherx;

public class Matrix {
    int [][]matrix=new int[2][2];
    int []vector =new int[2];


    Matrix(){

    }


    Matrix(int[][]matrix,  int []vector){
        this.matrix=matrix;
        this.vector=vector;
    }


    public int[][] addNum(int[][]matrix,int num,int reminder){
        int[][] newMatrix=new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[i].length;j++){
                newMatrix[i][j]=(matrix[i][j]+num)%reminder;
            }
        }

        return newMatrix;
    }
    public int[][] multiNum(int[][]matrix,int num){
        int[][] newMatrix=new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[i].length;j++){
                newMatrix[i][j]=(matrix[i][j]*num);
            }
        }
        return newMatrix;
    }
    public int[] multiply(int[][]matrix,int[]vector){
        int [] newVector=new int[2];
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j < matrix[0].length ; j++) {
                newVector[i]=newVector[i]+(vector[j]*matrix[i][j]);
            }

            newVector[i]=newVector[i]%26;
        }
        return newVector;
    }

    public int det(int[][]matrix){
        return matrix[0][0]*matrix[1][1]-matrix[0][1]*matrix[1][0];
    }

    public int [][] inverseMat(int[][]matrix){
        int [][]newMatrix=new int[2][2];

        newMatrix[0][0]=matrix[1][1];
        newMatrix[0][1]=-matrix[0][1];
        newMatrix[1][0]=-matrix[1][0];
        newMatrix[1][1]=matrix[0][0];

   return newMatrix; }

    enum operation{
        PLUS ,MINUS,MULTIPLY,DIVIDE
    }
}

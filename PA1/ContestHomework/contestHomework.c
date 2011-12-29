/*
 * =====================================================================================
 *
 *       Filename:  contestHomework.c
 *
 *    Description:  Contest Homework
 *
 *        Version:  1.0
 *        Created:  26/12/11 20:07:53
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Luis Ildefonso Gómez Solana (), luis.ild@gmail.com
 *        Company:  CVUT
 *
 * =====================================================================================
 */
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

int matrix[32][32][2];
int *mkakuro[32][32];
/*
 * Return error message and error value
 */
int invalidInput () {
	printf("Invalid input.\n");
	return 1;
}

/*
 * show matrix
 */
int showMatrix (int *row, int* column) {
	int i, j;
	for (i=0; i<*row; i++) {
		for (j=0; j<*column; j++) {
			if (j>0) {
				printf(" ");
			}
			switch (matrix[i][j][0]) {
				case -1:
					printf("X");
					break;
				case 0:
					printf(".");
					break;
				default:
					printf("%d", matrix[i][j][0]);
					break;
			}
			switch (matrix[i][j][1]) {
				case -1:
					if ((matrix[i][j][0])!=-1) {
						printf("\\X");
					}
					break;
				case 0:
					break;
				default:
					printf("\\%d", matrix[i][j][1]);
					break;
			}
		}
		printf("\n");
	}
	return 0;
}

/*
 * my stdin is correct or its incorrect
 */
int correctInput (char * letra, int * Ncolumn, int *Nrow, int *NcActual) {
	int blanco = 0;	/* anterior char blanco */
	int x = 0;	/* x character */
	int slash = 0;
	int numero = 0;
	float  potencia=0.0;

/* im studying *ltra */
/*printf("letra: %c\n", *letra);*/
	while (scanf("%c", letra) != EOF) {
		if (*letra != ' ') {
			/*printf("has leido esto:%c\n", *letra);*/
			if (*letra == '\n') {
				*Nrow = *Nrow + 1;
				if (*Ncolumn == 0) {
					*Ncolumn = *NcActual;
				} else if (*NcActual != *Ncolumn) {
					return invalidInput();
				}
				*NcActual = 0;
				x=0;
				slash=0;
				potencia=0;
				numero = 0;
			} else if (*letra == 'X') {
				/* this charaacter means black */
				if (x==1) {
					return invalidInput();
				}
				x = 1;
				slash=0;
			} else if (*letra == '\\') {
				/* i find a \ */
				if (slash==1) {
					invalidInput();
				}
				slash = 1;
				x = 0;
				potencia=0;
				numero = 0;
			} else if (*letra == '.') {
				/* its a dot */
/*				printf("Meto ceros en %d,%d \n", *Nrow, *NcActual);*/
				matrix[*Nrow][*NcActual][0] = 0; 
				matrix[*Nrow][*NcActual][1] = 0; 
			} else if ((atoi(letra) <= 9) && (0 <= atoi(letra))) {
				/*ok*/
				numero = numero*(pow(10.0, potencia)) + atoi(letra);
				matrix[*Nrow][*NcActual][slash] = numero;
				potencia++;
				/*slash=0;*/
				x=0;
			} else {
				/* one character */
/*				printf("fail!!!!!!!!!!!!1\n");*/
			}
			blanco = 0; /* vuelta a contar */
 		} else if ((*letra==' ') && (blanco == 0)) {
			/* Encontrado blanco */
			*NcActual = *NcActual + 1;
/*				printf("jajaja %d\n", *NcActual);*/
			blanco = 1;
			potencia=0;
			numero = 0;
			x = 0;
		} 
		/* i have to clean my stdin */
		fflush( stdin );
	}
	return 0;
}


/*
 * Searchit
 */
int searchit (int row, int column, int kind) {
	int fila=row, columna = column;
	if (kind==0) { /* row */
		while ((matrix[fila][column][0])==0) {
			fila--;
		}
	printf("%d\n", fila);
		return fila;
	} else {
		while ((matrix[row][columna][0])==0) {
			columna--;
		}
		return columna;
	}
}

/*
 * Array with all posibilities
 */
int posibilities (int *row, int*column) {
	int i, j, max, pos;

	/* i have to read all cells*/
	for (i=0; i<*row; i++) {		/* row		*/
		for (j=0; j<*column; j++) {	/* column	*/
			pos = 0;
			if ((max=matrix[i][j][0]) > 0) {
				mkakuro[i][j] =  (int *) malloc(sizeof(int)*(max-1));
				while (max > 1) {
					max--;
					mkakuro[i][j][pos]=max;
					pos++;
				}
			}
			if ((max=matrix[i][j][1]) > 0) {
				mkakuro[i][j] =  (int *) malloc(sizeof(int)*(max-1));
				while (max > 1) {
					max--;
					mkakuro[i][j][pos]=max;
					pos++;
				}
			}
			if (max==0) {
				searchit(i, j, 0);
					printf("(%d %d)", matrix[i-1][j][0] , matrix[i][j-1][1]);
				/* hacer menos lista */
				if (matrix[searchit(i, j, 0)][j][0] <= matrix[i][searchit(i, j, 1)][1]) {
					printf("Position %d, %d valorado con columna\n", searchit(i, j, 0), j);
					mkakuro[i][j] = &matrix[searchit(i, j, 0)][j][0];
				} else {
					mkakuro[i][j] = &matrix[i][searchit(i, j, 0)][1];
				}
			}
		}
	}
	return 0;
}


int main () {
	char letra[2];
	int Ncolumn=0, Nrow=0, NcActual=0;
	int i, j;
	/* set (x,x) in cells*/
	for (i=0; i<32; i++) {
		for (j=0; j<32; j++) {
			matrix[i][j][0]=-1;
			matrix[i][j][1]=-1;
		}
	}
/*	showMatrix();*/

		
	printf("Enter the kakuro:\n");	

	/************************/
	/* Leemos toda una fila */
	/* Comprobamos que todo esta correcto */
	scanf("%c", letra); 
	if (correctInput(letra, &Ncolumn, &Nrow, &NcActual)) {
		return 1;
	} else { /* char corrects */
		/*printf("Character correct\n");*/
	}
	Ncolumn++;
	/* fin de estudio de entrada ok */
	/************************/
	
	/* input grid exceeds 32 */
	if (( Ncolumn >= 32 ) || (Nrow >= 32)) {
		return invalidInput();
	}

	showMatrix(&Nrow, &Ncolumn);

	/* tengo todo en la mtrix */
	
	posibilities(&Nrow, &Ncolumn);	


	printf("No solution.\n");
	return 0;
}

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
int mSolution[32][32];
/*
 * Return error message and error value
 */
int invalidInput () {
	printf("Invalid input.\n");
	return 1;
}

/*
 * freeAll
 * free all cells
 */
int freeAll (int* matriz[32][32], int* row, int* column) {
	int i, j;
	for (i = 0; i < *row; i++) {
		for (j = 0; j < *column; j++) {
			free(matriz[i][j]);
		}
	}
	return 0;
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
					if ((matrix[i][j][0]) != -1) {
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
 * matixFormatted correctly
 * matrix
 */
int matrixFormatted(int *row, int* column) {
	int i, j;
	int empty = 0, previous=0;

	for (i=0; i < *row; i++) {
		for (j=0; j < *column; j++) {
			if ((matrix[i][j][0] > 0) && ((i==(*row-1) || (matrix[i+1][j][0] != 0)))) {
				return 1;
			} else if ((matrix[i][j][0]==0)) {
				if (previous==0) {
					previous=1;
				} 
				empty++;
				if (empty==10)
					return 1;
			} else {
				previous=0;
				empty=0;
			}
			if ((matrix[i][j][1] > 0) && ((j==(*column-1) || (matrix[i][j+1][1] != 0)))) {
				return 1;
			}
		}
	}
	/* all is ok */
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
/*			printf("Has leido esto:%c\n", *letra);*/
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
				matrix[*Nrow][*NcActual][0] = 0; 
				matrix[*Nrow][*NcActual][1] = 0; 
			} else if ((*letra=='0') || (*letra=='1') || (*letra=='2') || (*letra=='3') || (*letra=='4') ||
					(*letra=='5') || (*letra=='6') || (*letra=='7') || (*letra=='8') || (*letra=='9') ) {
				/*ok*/
				numero = numero*(pow(10.0, potencia)) + atoi(letra);
				matrix[*Nrow][*NcActual][slash] = numero;
				potencia++;
				/*slash=0;*/
				x=0;
			} else {
				/* one character */
				return invalidInput();
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
			slash=0;
		} 
		/* i have to clean my stdin */
		fflush( stdin );
	}
	/* input grid exceeds 32 */
	if (( *Ncolumn >= 32 ) || (*Nrow >= 32)) {
		return invalidInput();
	}
	*Ncolumn = *Ncolumn+1;
	return 0;
}


/*
 * Searchit
 */
int searchit (int row, int column, int kind) {
	int fila=row, columna = column;
/*	printf("fila %d, y colum %d \n", row, column);*/
	if (kind==0) { /* row */
		while ((matrix[fila][column][0])==0) {
			fila--;
		}
		return fila;
	} else {
		while ((matrix[row][columna][1])==0) {
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
	int tmp, tmpmax;

	/* i have to read all cells*/
	for (i=0; i<*row; i++) {		/* row		*/
		for (j=0; j<*column; j++) {	/* column	*/
			pos = 0;
			if ((max=matrix[i][j][0]) > 0) {
				tmp=i+1;
				tmpmax=1;
				while (matrix[tmp][j][0] == 0) {
					mkakuro[tmp][j] =  (int *) malloc((sizeof(int) * max));
					while (tmpmax < max) {
						mkakuro[tmp][j][pos]=tmpmax;
						tmpmax++;
						pos++;
					}
					tmp++;
					pos = 0; 
					tmpmax=1;
				}
			}
			pos = 0;
			if ((max=matrix[i][j][1]) > 0) {
				tmp = j+1;
				tmpmax = 1;
				while (matrix[i][tmp][0] == 0) {
					if (mkakuro[i][tmp] != 0) {
						/* Contiene algo*/
						if (matrix[searchit(i,tmp ,0)][tmp][0] > (matrix[i][j][1])) {
							free((void*)mkakuro[i][tmp]);
							mkakuro[i][tmp] =  (int *) malloc((sizeof(int) * max));
							while (tmpmax < max) {
								mkakuro[i][tmp][pos]=tmpmax;
								tmpmax++;
								pos++;
							}
						}
					} else {	/* NO Contiene nadie */
						mkakuro[i][tmp] =  (int *) malloc(sizeof(int)*(max-1));
						while (tmpmax < max) {
							mkakuro[i][tmp][pos]=tmpmax;
							tmpmax++;
							pos++;
						}
					}
					tmp++;
					pos = 0; 
					tmpmax=1;
				}
			}
		}
	}
	return 0;
}

/*
 * minorArray
 * what is my minor array of my matrix
 */
int minorArray (int* Nrow, int* Ncolumn, int * R, int * C) {
	int i, j;
	int minor = 100;
	for (i = 0; i < *Nrow; i++) {
		for (j = 0; j < *Ncolumn; j++) {
			if ((matrix[i][j][0] > 0) && (minor > matrix[i][j][0])) {
				minor = matrix[i][j][0];
				*R = i+1; *C = j;
			}
			if ((matrix[i][j][1] > 0) && (minor > matrix[i][j][1])) {
				minor = matrix[i][j][1];
				*R = i; *C = j+1;
			}
		}
	}
/*	printf("elem: %d %d %d %d\n", mkakuro[*R][*C][0], mkakuro[*R][*C][1], mkakuro[*R][*C][2], mkakuro[*R][*C][3]);*/
	return 0;
}

/*
 * anyPossibility
 * 0 means that i have not possiblity = No solution
 * e.o.c. i have a posibily
 */
int anyPossibility(int* Nrow, int* Ncolumn) {
	int R=0, C=0;
	int i, j;
	/*int array[44];*/
	int numero;

	minorArray(Nrow, Ncolumn, &R, &C);
	numero = (matrix[searchit(R, C, 0)][C][0] < matrix[R][searchit(R, C, 1)][1])?matrix[searchit(R, C, 0)][C][0]:matrix[R][searchit(R, C, 1)][1];

	
	mSolution[R][C] = mkakuro[R][C][0];
	
	for (i = 0; i < *Nrow; i++) {
		for (j = 0; j < *Ncolumn; j++) {
		/*mSolution*/
			if (matrix[i][j][0]==0) {
				mSolution[i][j] = i+1;
			}
		}
	}
	
/*	printf("el menor array en la posicion %d %d\n", R, C);*/
	return (numero-1);
}

int main () {
	char letra[2];
	int Ncolumn=0, Nrow=0, NcActual=0;
	int i, j;
	int solution;
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
	if (correctInput(letra, &Ncolumn, &Nrow, &NcActual))
		return 1;
	/* fin de estudio de entrada ok */
	/************************/
	

	/*
	 * My matrix is formatted well?
	*/
	if (matrixFormatted(&Nrow, &Ncolumn)) {
		return invalidInput();
	}


/*	showMatrix(&Nrow, &Ncolumn); */

	/* tengo todo en la matrix[][] */
	posibilities(&Nrow, &Ncolumn);
	/* and now i have my minimilazed matrix */
	solution = anyPossibility(&Nrow, &Ncolumn);

	/* use valgrind for cheking */
	freeAll(mkakuro, &Nrow, &Ncolumn);

	if (solution == 1) {
		printf("No solution.\n");
		return 1;
	} else {
		printf("Total solutions: %d\n", solution);
	}
	return 0;
}

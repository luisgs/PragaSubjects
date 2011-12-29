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
int showMatrix () {
	int i, j;
	for (i=0; i<10; i++) {
		for (j=0; j<10; j++) {
			printf("(%d," , matrix[i][j][0]);
			printf("%d) ", matrix[i][j][1]);
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
	int numero = 0, potencia=0;

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
				numero = numero*(10^potencia) + atoi(letra);
/*				printf("esto es un numero:%d\n", numero);*/
				matrix[*Nrow][*NcActual][slash] = numero;
				potencia++;
				slash=0;
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
 * Array with all posibilities
*/
int posibilities () {
	int i, j, max, pos=0;
	int *fragment;

	/* i have to read all cells*/
	for (i=0; i<32; i++) {		/* row		*/
		for (j=0; j<32; j++) {	/* column	*/
			if ((max=matrix[i][j][0]) > 0) {
				fragment = (int *) malloc(sizeof(int)*(max-1));
				/*mkakuro[i][j] =  (int *) malloc(sizeof(int)*(max-1));*/
				while (max > 1) {
					max--;
					fragment[pos] = max;
/*					printf("valor de fragment: %d\n", fragment[pos]);*/
					pos++;
				}
			}
		}
	}
	return 0;
}


int main () {
	char letra[2];
	int *Ncolumn, *Nrow, *NcActual;
	int i, j;
	/* set (x,x) in cells*/
	for (i=0; i<32; i++) {
		for (j=0; j<32; j++) {
			matrix[i][j][0]=-1;
			matrix[i][j][1]=-1;
		}
	}
/*	showMatrix();*/

	Ncolumn = malloc(sizeof(int));    /* Allocate an int pointee, */
	Nrow = malloc(sizeof(int));
	NcActual = malloc(sizeof(int));
	*Ncolumn = 0;
	*Nrow = 0;
	*NcActual = 0;
		
	printf("Enter the kakuro:\n");	

	/************************/
	/* Leemos toda una fila */
	/* Comprobamos que todo esta correcto */
	scanf("%c", letra); 
	if (correctInput(letra, Ncolumn, Nrow, NcActual)) {
		return 1;
	} else { /* char corrects */
		/*printf("Character correct\n");*/
	}
	/* fin de estudio de entrada ok */
	/************************/
	
	/* input grid exceeds 32 */
	if (( *Ncolumn >= 32 ) || (*Nrow >= 32)) {
		return invalidInput();
	}

/*	showMatrix();*/

	/* tengo todo en la mtrix */
	
posibilities();	


	printf("No solution.\n");
	return 0;
}

/*
 * =====================================================================================
 *
 *       Filename:  h7_5.c
 *
 *    Description:  Homework 7, 5 points
 *
 *        Version:  1.0
 *        Created:  02/12/11 16:27:27
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Luis Ildefonso Gómez Solana (), luis.ild@gmail.com
 *        Company:  CVUT
 *
 * =====================================================================================
 */
#ifndef __PROGTEST__
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#endif /* __PROGTEST__ */


/*
 * how much money you can earn.
 */
int ganancia (int price, int * budget, int * earn) {
/*	printf("valor de price %d, budget %d, y earn %d\n", price, *budget, *earn);	*/
	return (( (*budget) / price ) * (*earn));
}

/* your supplementary functions (if any) */
int sumBribes ( int budget, int * table, int tableLen ) {
	/* implementation */
	int max = 0, i;
	int resto = budget;

	float divisor=0;
	float divtmp;

	int tmpMax=0, tmpResto=0;
	float opcion2=0;

	int valor1, valor2;

	printf("*******Empezamos sumBribes**\n");
	printf("budget %d\n", budget);	

	/* if i have 0 dollars */
	if (budget==0) {
		return 0;
	}

	for (i = 1; i < tableLen; i++) {	/* when i=0 has no sense */
		if ((table[i] != 0) && (i<=budget) && ((divtmp=((float) table[i]/ (float) i)) > divisor) ) {
			opcion2 = divisor;	/* antigup max */
			tmpResto = resto;

			max = ganancia( i, &budget, &table[i]);
			resto = budget - ( i * ( budget / i) );
			divisor = divtmp;
			printf("Has ganado un max de %d con un budget de %d, resto %d\n", max, budget, resto);	
		} 
/*		printf("valor de tmp %d, max %d, resto %d\n", tmp, max, resto);	*/
		printf("valor de divisor %f, y opcion2 %f y divisor %f\n", divtmp, opcion2, divisor);
	}
printf("opcion 1 %f y opcion %f\n", divtmp, opcion2 );

	if (resto == budget) {
		/* no podemos hacer nada! */
		printf("Sumaste %d, resto %d\n", max, resto);
		return max;
	} else {
/*		printf("Sumaste %d, resto %d\n", max, resto);*/
		valor1 = sumBribes(resto, table, tableLen);
		valor2 = sumBribes(tmpResto, table, tableLen);
		max += (valor1>valor2)?valor1:valor2;
	}

/*	printf("*******Terminamos sumBribes**\n");*/
/*	printf("valor de tmp %d, max %d, resto %d\n", tmp, max, resto);*/

	
	return max;
}
  
#ifndef __PROGTEST__
int main ( int argc, char * argv [] ) {
	/* your tests */
	int r;
	int test1[] = {0, 100, 0, 0, 350, 0, 750 }; 
	int test2[] = { 0, 1, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 53 };
	int test3[] = { 0, 0, 0, 0, 0, 0, 0, 1 };

/*	r = sumBribes( 1, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 2, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 3, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 4, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 5, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 6, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 7, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 8, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 9, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 10, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 11, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 12, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 13, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 14, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 15, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 16, test1, 7); 
	printf("%d\n", r);
	r = sumBribes( 17, test1, 7); 
	printf("%d\n", r);
*/
/*	r = sumBribes( 1, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 2, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 3, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 4, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 5, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 6, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 7, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 8, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 9, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 10, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 11, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 12, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 13, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 14, test2, 16); 
	printf("%d\n", r);
	r = sumBribes( 15, test2, 16); 
	printf("%d\n", r);
*/
	r = sumBribes( 16, test2, 16); 
	printf("%d\n", r);
/*	r = sumBribes( 17, test2, 16); 
	printf("%d\n", r);
*/
/*	r = sumBribes( 1, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 2, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 3, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 4, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 5, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 6, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 7, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 8, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 9, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 10, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 11, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 12, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 13, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 14, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 15, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 16, test3, 8); 
	printf("%d\n", r);
	r = sumBribes( 17, test3, 8); 
	printf("%d\n", r);
*/
/*	printf("===============================\n");
	printf("Solucion de sumBribes: %d\n", r);
	printf("===============================\n");
*/
	return 0;
}
#endif /* __PROGTEST__ */

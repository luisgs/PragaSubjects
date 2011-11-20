/*
 * =====================================================================================
 *
 *       Filename:  h53.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  18/11/11 17:21:19
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  YOUR NAME (), 
 *        Company:  
 *
 * =====================================================================================
 */
#include <stdio.h>

int NameCities[1024];	/* for save all cities */

/* Return OK if there are not any rute like this. A->B = B->A  */
int existAnotherRute (int array[1024][3], int A, int B, int ArraySize) {
	int i;
	for (i=0; i<ArraySize; i++) {
		if (((array[i][0]==A) && (array[i][1])==B) || ((array[i][0]==B) && (array[i][1])==A)) { 
			return 1;
		}
	}
	return 0;
}

int NcitiesWrong (int A, int B, int Ncities) {
	int i, ok=0, elem=-1;
	printf("jurjur\n");
	for (i=0; i<Ncities; i++) {
		if (NameCities[i]==A) {
			ok++;
			elem=1;	
		}
		if (NameCities[i]== B) {
			ok++;
			elem=2;
		}
		if (ok==2) {
			return 0;
		}
		if ((NameCities[i]==-1) && (ok==0) && (i<=Ncities-2)) {
			NameCities[i]=A;
			NameCities[i+1]=B;
			return 0;	
		} else if ((NameCities[i]==-1) && elem==1) {
			NameCities[i]=B;
			return 0;
		} else if ((NameCities[i]==-1) && elem==2) {
			NameCities[i]=A;
			return 0;
		}
	}
	return 1;
}


int main () {
	int cities, i;
	int ncity=1024;
	int array[ncity][3];	/* array for save all distance */
	int tmp1, tmp2, tmp3;

	for (i=0; i<ncity; i++) {
		NameCities[i]=-1;
	}

	/* input data! */
	printf("Enter number of cities:\n");
	if ((scanf("%d", &cities) != 1) || (cities <=0)) {
		printf("Invalid input.\n");
		return 0;
	}
	/* N_Cities ok!*/
	printf("Enter distances:\n");
	i=0;
	while (scanf("%d %d %d", &tmp1, &tmp2, &tmp3)!=EOF) {
		if ((tmp1<0) || tmp2<0 || tmp3<=0) {
			printf("Invalid Input.\n");
			return 0;
		} else if (existAnotherRute(array, tmp1, tmp2, i)) {
			printf("Invalid Input.\n");
			return 0;
		} else if ((NcitiesWrong(tmp1, tmp2, cities))) {
			printf("Invalid Input.\n");
			return 0;
		} else {
			array[i][0]=tmp1;	/* A */
			array[i][1]=tmp2;	/* B */
			array[i][2]=tmp3;	/* Distance */
			i++;	/* ArraySize */
		}
	}

	return 0; /* ending my program */
}

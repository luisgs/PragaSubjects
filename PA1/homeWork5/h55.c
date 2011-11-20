/*
 * =====================================================================================
 *
 *       Filename:  h55.c
 *
 *    Description:  Exercise for PA1
 *
 *        Version:  1.0
 *        Created:  19/11/11 17:08:29
 *       Revision:  
 *       Compiler:  gcc
 *
 *         Author:  Luis Ildefonso Gómez Solana, luis.ild@gmail.com
 *        Company:  CVUT
 *
 * =====================================================================================
 */
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define MAX 8192 

int longWire (int point[2], int coordinates[MAX][2], int ncustomers) {
	int count=0, i;
	for (i=0; i<ncustomers; i++) {
		count += abs(point[0] - coordinates[i][0]) + abs(point[1] - coordinates[i][1]);
	}
	return count;
}

int main () {
	int customers[MAX][2];	/* X-Y coordinates! */
	int NcustomerS = 0, tmp, j;
	int minimal = 0, current, point;
	int hub[2], hub1[2], hub2[2];

	printf("Enter customer coordinates:\n");
	
	while ((tmp=scanf("%d %d", &customers[NcustomerS][0], &customers[NcustomerS][1]))==2) {
		/* EOF! break! */
		NcustomerS++;
	}
	
	/* i have data for calculate my solution */
	if (getchar()==EOF) {
		/* inicialize */
		minimal=longWire(customers[0], customers, NcustomerS);
		point=0;
		for (j=1; j<NcustomerS; j++) {
			current = longWire(customers[j], customers, NcustomerS);
			point = (current<minimal)?j:point;
			minimal = (current<minimal)?current:minimal;
		}

		hub[0] = customers[point][0];
		hub[1] = customers[point][1];
			hub2[0] = hub[0];
			hub2[1] = hub[1];
		/* I know what point better to begin at calculate */
		while (1) {
			hub1[0] = hub[0];
			hub1[1] = hub[1]+1;
			if ((current=longWire(hub1, customers, NcustomerS)) < minimal) {
				hub2[0] = hub1[0];
				hub2[1] = hub1[1];
				minimal = current;
			}

			hub1[0] = hub[0];
			hub1[1] = hub[1]-1;
			if ((current=longWire(hub1, customers, NcustomerS)) < minimal) {
				hub2[0] = hub1[0];
				hub2[1] = hub1[1];
				minimal = current;
			}

			hub1[0] = hub[0]+1;
			hub1[1] = hub[1];
			if ((current=longWire(hub1, customers, NcustomerS)) < minimal) {
				hub2[0] = hub1[0];
				hub2[1] = hub1[1];
				minimal = current;
			}

			hub1[0] = hub[0]-1;
			hub1[1] = hub[1];
			if ((current=longWire(hub1, customers, NcustomerS)) < minimal) {
				hub2[0] = hub1[0];
				hub2[1] = hub1[1];
				minimal = current;
			}

			if ((hub2[0]==hub[0]) && (hub2[1]==hub[1])) {
				printf("Total length: %d\n", minimal);
				return 0;
			}
			hub[0] = hub2[0];
			hub[1] = hub2[1];
 		}

		return 0;
	}


	/* if we didnt read 2 times, -> invalid input */
	if (tmp != 2) {
		printf("Invalid input.\n");
		return 0;
	}

	/* you will never stay here! */
	/* Final */
	return 0;
}

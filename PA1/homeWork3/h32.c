#include <stdio.h>
#include <stdlib.h>

/*
 * Return sum of all divisor of this number
*/
int divisorsSum (int number) {
	int count=0, divisor=1;
	/* divisor will be less of number */
	int max=number/2;
	while (divisor<=max) {
		count += ((number%divisor==0)?divisor:0);
		divisor++;
	}
	return count;
}


int main (int argc, char **argv) {
	int i;
	int max;	/* maximum number */
	int *array;

	/*
	 * Correct arguments?
	*/
	printf("Enter limit:\n");
	if (!((scanf("%d",&max)==1) && (max>0))) {
		printf("Invalid input.\n");
		return 0;
	}
	/*
	 * I need increase my array. Use malloc for dynamic memory
	*/
	array = (int*)malloc(sizeof (int[max]));
	
	for (i=1; i<max; i++) {
		if (array[i] != -1) {
			array[i]=divisorsSum(i);
			if ((array[i]>i) && (array[i]<=max)) {
				array[array[i]] = divisorsSum(array[i]);
				if (array[array[i]]==i) {
					printf("%d, %d\n",i ,array[i]);
					array[i]=-1;
				}
			}
		}
	}

	return 0;
}

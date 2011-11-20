#include <stdio.h>
#include <math.h>

int main ( int argc, char * argv [] )
{

	double w1,l1,w2,l2,h,volume;
	printf("Enter a and b:\n");
	
	if(scanf("%lf %lf",&w1,&l1) <= 0)
	{
		printf("Invalid input.\n");
		return 0;
	}
	// Check input	
	if((w1 <= 0) || (l1 <= 0))
	{
		printf("Invalid input.\n");
		return 0;
	}

	printf("Enter a' and b':\n");

	if(scanf("%lf %lf",&w2,&l2) <= 0)
	{	
		printf("Invalid input.\n");
		return 0;
	}
	// Check input
	if((w2 <= 0) || (l2 <= 0))
	{
		printf("Invalid input.\n");
		return 0;
	}

	printf("Enter h:\n");

	// Check input

	if ((w1 <= w2) || (l1 <= l2))
	{	
		printf("Invalid input.\n");
		return 0;
	}

	if(scanf("%lf",&h) <= 0)
	{
		printf("Invalid input.\n");
		return 0;
	}
	// Check input	
	if(h <= 0)
	{
		printf("Invalid input.\n");
		return 0;
	}
		

	// formule for calculate volume of golden bar
	volume = ((h/6)*((2*l1*w1) + (2*l2*w2) + (l1*w2) + (l2*w1)));
	// Display golden bar volume
	printf("Volume: %0.6lf\n", volume);

	#ifndef __PROGTEST__ 
  	system ( "pause" ); /* this is ignored by Progtest */
	#endif /* __PROGTEST__ */
  return 0;
} 

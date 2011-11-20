/*#===============================================================================
#
#          FILE:  
# 
#         USAGE:  ./
# 
#   DESCRIPTION:  
# 
#       OPTIONS:  ---
#  REQUIREMENTS:  ---
#          BUGS:  ---
#         NOTES:  ---
#        AUTHOR: LuisGS (), luis.ild@gmail.com
#       COMPANY: CVUT
#       CREATED: 20/10/11 12:40:43 CEST
#      REVISION:  ---
#===============================================================================
*/

#include <stdio.h>
#include <math.h>



int triangule_exits (double s1, double s2, double s3) {
	return 	((s1<(s2+s3)) && 
			(s2<(s1+s3)) && 
				(s3<(s1+s2)));
}

double angule ( double s1, double s2, double s3) {
	return ((acos(((pow(s2,2)+pow(s3,2))-pow(s1,2))/((2*s2)*s3))*180.0000)/M_PI);
}


int main ( int argc, char * argv [] ) {
  	double side1=0,side2=0,side3=0;
	double ang1=0, min1=0, sec1=0;
	double ang2=0, min2=0, sec2=0;
	double ang3=0, min3=0, sec3=0;

 	/* 3 sides for my triangule! */
  	printf("Enter lengths a, b, and c:\n");
	if (!(scanf("%lf",&side1)) || 
		!(scanf("%lf",&side2)) || 
			!(scanf("%lf",&side3)) ||
				(side1<=0) || (side2<=0) || (side3<=0))	{
		printf("Invalid input.\n");
		return 0;
	}


	if (triangule_exits (side1, side2, side3)) {
		/* Type of triangle (isosceles, scalene, equilateral) */
		if ((side1==side2) && (side2==side3) && (side1==side3))	{
			printf("The triangle is equilateral.\n");
		} else if ((side1==side2) || (side1==side3) || (side2==side3))	{
			printf("The triangle is isosceles.\n");
		} else {
			printf("The triangle is scalene.\n");
		}

		/* angles. */
		ang1=angule(side1, side2, side3);
		ang2=angule(side2, side1, side3);
		ang3=angule(side3, side1, side2);
		
		/* I know angules, i can know what type is my triangule */
		if((ang1==90) || (ang2==90) || (ang3==90)) {
			printf("The triangle is a right triangle.\n");
		} else if ((ang1<90) && (ang2<90) && (ang3<90)) {
			printf("The triangle is acute.\n");
		} else {
			printf("The triangle is obtuse.\n");
		}

		/* Convert the angle in minutes and seconds! */
		min1=ang1-(int)ang1;
    		ang1-=min1;
    		min1*=60.0000;
   		sec1=min1-(int)min1;
    		min1-=sec1;
   		sec1*=60.0000;
		
		min2=ang2-(int)ang2;
    		ang2-=min2;
    		min2*=60.0000;
   		sec2=min2-(int)min2;
    		min2-=sec2;
   		sec2*=60.0000;

		min3=ang3-(int)ang3;
    		ang3-=min3;
    		min3*=60.0000;
   		sec3=min3-(int)min3;
    		min3-=sec3;
   		sec3*=60.0000;

                printf("Angle alpha: %0.4f rad = %0.0f deg %02.0f'%05.2f''\n", (((acos(((pow(side2,2)+pow(side3,2))-pow(side1,2))/((2*side2)*side3))*180.0000)/(M_PI*M_PI))/180.0000), ang1, min1, sec1);
                printf("Angle beta: %0.4f rad = %0.0f deg %02.0f'%05.2f''\n", (((acos(((pow(side1,2)+pow(side3,2))-pow(side2,2))/((2*side1)*side3))*180.0000)/(M_PI*M_PI))/180.0000), ang2, min2, sec2);
                printf("Angle gamma: %0.4f rad = %0.0f deg %02.0f'%05.2f''\n",(((acos(((pow(side1,2)+pow(side2,2))-pow(side3,2))/((2*side1)*side2))*180.0000)/(M_PI*M_PI))/180.0000), ang3, min3, sec3);
                printf("Perimeter: %0.4f\n",(side1 + side2 + side3));
                printf("Area: %0.4f\n",((side1*(sqrt(((side1+side2+side3)*(side2+side3-side1))*((side1+side3-side2)*(side1+side2-side3)))/(2*side1)) )/2) );
                printf("Inradius: %0.4f\n",( 2 * (side1*(sqrt(((side1+side2+side3)*(side2+side3-side1))*((side1+side3-side2)*(side1+side2-side3)))/(2*side1))  /2) / (side1 + side2 + side3)));	/* area /perimetro */
                printf("Circumradius: %0.4f\n",(((side1*side2) * side3) / (4 * ((side1* (sqrt(((side1+side2+side3)*(side2+side3-side1))*((side1+side3-side2)*(side1+side2-side3)))/(2*side1)) )/2) )) );
                printf("Altitude a: %0.4f\n",(sqrt(((side1+side2+side3)*(side2+side3-side1))*((side1+side3-side2)*(side1+side2-side3)))/(2*side1)));
                printf("Altitude b: %0.4f\n",(sqrt(((side1+side2+side3)*(side2+side3-side1))*((side1+side3-side2)*(side1+side2-side3)))/(2*side2)));
                printf("Altitude c: %0.4f\n",(sqrt(((side1+side2+side3)*(side2+side3-side1))*((side1+side3-side2)*(side1+side2-side3)))/(2*side3)));
	} else {
		printf("The triangle does not exist.\n");
	}

  #ifndef __PROGTEST__
  	system ( "pause" ); /* this is ignored by Progtest */
  #endif /* __PROGTEST__ */

  return 0;
}

/*#===============================================================================
#
#          FILE:  
# 
#         USAGE:  ./
# 
#   DESCRIPTION:  i
# 
#       OPTIONS:  ---
#  REQUIREMENTS:  ---
#          BUGS:  ---
#         NOTES:  ---
#        AUTHOR: LuisGS (), luis.ild@gmail.com
#       COMPANY: CVUT
#       CREATED: 18/10/11 13:41:16 CEST
#      REVISION:  ---
#===============================================================================
*/
#include <stdio.h>
#include <stdlib.h>


char ManagerNames[] = {'A','B','C'};
int intervals [3][2] = {{0,0},{0,0},{0,0}};
int man1Start = 0, man1End=0;
int man2Start = 0, man2End=0;
int man3Start = 0, man3End=0;


const char* meetnotpos = "Meeting is not possible.";
const char* invalid = "Invalid input.";

/*
 * Return Ok if intervals exists
*/
int interval_correct(int t1, int t2) {
	if (t1 <= t2) {
		return EXIT_FAILURE;
	}
	return EXIT_SUCCESS;
}

/*
 * Return Ok if hour-min time is ok!.
*/
int time_correct (int hour, int min) {
	if ((hour <= 23) && (hour >= 0) &&
		(min >= 0) && (min <= 59) ) {
		return 1;
	} else { /* input error */
		return 0;
	}
}

int main (void) {
	int hour1=0, min1=0;	/* Start interval 	*/
	int hour2=0, min2=0;	/* End interval		*/
	/* AC intervals  */
	int AB_1 = -1, AB_2 = -1;
	/* A-C intervals */
        int AC_1 = -1;  /* Intervals AC */
        int AC_2 = -1;
        int ABC_1 = -1; /* Intervals ABC */
        int ABC_2 = -1;
	/* B-C intervals */
        int BC_1=-1, BC_2=-1;
	
	int i;

	for (i=0; i < (int)sizeof(ManagerNames); i++) {
		printf("Manager %c:\n",ManagerNames[i]);
		if ((scanf("%i:%i - %i:%i", &hour1, &min1, &hour2, &min2)==4) &&
				interval_correct((hour1*60)+min1, (hour2*60)+min2) && 
					time_correct(hour1,min1) && time_correct(hour2, min2)) {
			/* Write into array */
			intervals[i][0] = (hour1*60)+min1;	/* Start interval	*/
			intervals[i][1] = (hour2*60)+min2;	/* End interval		*/
		} else { /* Input Error */
			printf("%s\n", invalid);	/* error output	*/
			return EXIT_SUCCESS;	/* MUST BE EXIT_FAILURE  */
		}
	}

	/* Conditions! */
	/* Exists an option! */
	if ( (intervals[0][1] < intervals[1][0]) &&	/* A.end < B.start	*/
		(intervals[1][1] < intervals[2][0])) {	/* B.end < C.start	*/
		/* No coinciden ninguno! */
		printf("%s\n", meetnotpos);
		return EXIT_SUCCESS;
	}
	
	/* A-B intervals */
	if ((intervals[0][1] > intervals[1][0])	&&	/* A.end > B.start. Meeting A-B!	*/
		(intervals[0][0] < intervals[1][1])) {	/* A.start < B.end. Meeting A-B!	*/
		AB_1=(intervals[0][0] > intervals[1][0])?intervals[0][0]:intervals[1][0];
		AB_2=(intervals[0][1] < intervals[1][1])?intervals[0][1]:intervals[1][1];
	} 

	/* A-C intervals */
	if ((intervals[0][1] > intervals[2][0])	&&	/* A.end > C.start. Meeting A-C!	*/
		(intervals[0][0] < intervals[2][1])) {	/* A.start < C.end. Meeting A-C!	*/
		AC_1=(intervals[0][0] > intervals[2][0])?intervals[0][0]:intervals[2][0];
		AC_2=(intervals[0][1] < intervals[2][1])?intervals[0][1]:intervals[2][1];
		if ((AB_2 > AC_1) && (AB_1 < AC_2) && (AB_1 != -1)) {	/* All of them can meet	*/
			ABC_1=(AB_1 > AC_1)?AB_1:AC_1;
			ABC_2=(AB_2 < AC_2)?AB_2:AC_2;
			printf("All three managers: %d:%02d - %d:%02d\n", ABC_1/60,ABC_1%60, ABC_2/60,ABC_2%60 );
			return EXIT_SUCCESS;
		} else if (AB_1!=-1) {
			if (AB_1 == AC_2) {		/* intervals Concatention  */
				printf("Two managers: %d:%02d - %d:%02d\n", AC_1/60,AC_1%60, AB_2/60,AB_2%60);
			} else if (AC_1 == AB_2) {	/* Intervals concatenation  */
				printf("Two managers: %d:%02d - %d:%02d\n", AB_1/60,AB_1%60, AC_2/60,AC_2%60);
			} else {			/* Normal case */
				printf("Two managers: %d:%02d - %d:%02d, %d:%02d - %d:%02d\n", AB_1/60,AB_1%60, AB_2/60,AB_2%60, AC_1/60,AC_1%60, AC_2/60,AC_2%60);
			}
			return EXIT_SUCCESS;
		}
	}

	/* B-C intervals */
	if ((intervals[1][1] > intervals[2][0])	&&	/* B.end > C.start. Meeting B-C!	*/
		(intervals[1][0] < intervals[2][1])) {	/* B.start < C.end. Meeting B-C!	*/
		BC_1=(intervals[1][0] > intervals[2][0])?intervals[1][0]:intervals[2][0];
		BC_2=(intervals[1][1] < intervals[2][1])?intervals[1][1]:intervals[2][1];
                if ((AB_2 > BC_1) && (AB_1 < BC_2) && (AB_1 != -1)) {   /* All of them can meet	*/
                        ABC_1=(AB_1 > BC_1)?AB_1:BC_1;
                        ABC_2=(AB_2 < BC_2)?AB_2:BC_2;
                        printf("All three managers: %d:%02d - %d:%02d\n", ABC_1/60,ABC_1%60, ABC_2/60,ABC_2%60 );
                        return EXIT_SUCCESS;
                } else if (AB_1!=-1) {
			if (AB_1 == BC_2) {
                        	printf("Two managers: %d:%02d - %d:%02d\n", BC_1/60, BC_1%60, AB_2/60,AB_2%60);
			} else if (BC_1 == AB_2) {
                        	printf("Two managers: %d:%02d - %d:%02d\n", AB_1/60, AB_1%60, BC_2/60,BC_2%60);
			} else {
                        	printf("Two managers: %d:%02d - %d:%02d, %d:%02d - %d:%02d\n", AB_1/60,AB_1%60, AB_2/60,AB_2%60, BC_1/60,BC_1%60, BC_2/60,BC_2%60);
			}
			return EXIT_SUCCESS;
                } else if (AC_1 != -1) {
			if (AC_1 == BC_2) {
                        	printf("Two managers: %d:%02d - %d:%02d\n", BC_1/60, BC_1%60, AC_2/60,AC_2%60);
			} else if (BC_1 == AC_2) {
                        	printf("Two managers: %d:%02d - %d:%02d\n", AC_1/60, AC_1%60, BC_2/60,BC_2%60);
			} else {
                        	printf("Two managers: %d:%02d - %d:%02d, %d:%02d - %d:%02d\n", AC_1/60, AC_1%60, AC_2/60, AC_2%60, BC_1/60,BC_1%60, BC_2/60,BC_2%60);
			}
			return EXIT_SUCCESS;
		}
	}

	/* Two manager  */
	if ( (AB_1!=-1) || (AC_1!=-1) || (BC_1!=-1)) {
		printf("Two managers: ");
		if ((AB_1!=-1)) {
			printf("%d:%02d - %d:%02d", AB_1/60,AB_1%60, AB_2/60, AB_2%60);
		}

		if ((AC_1!=-1) && (AB_1 != -1)) {
			printf(", %d:%02d - %d:%02d", AC_1/60,AC_1%60, AC_2/60, AC_2%60);
		} else if (AC_1!=-1) {
			printf("%d:%02d - %d:%02d", AC_1/60,AC_1%60, AC_2/60, AC_2%60);
		}
			
		if ((BC_1 != -1) && ((AB_1!=-1) || (AC_1!=-1))) {
			printf(", %d:%02d - %d:%02d", BC_1/60,BC_1%60, BC_2/60, BC_2%60);
		} else if (BC_1!=-1) {
			printf("%d:%02d - %d:%02d", BC_1/60,BC_1%60, BC_2/60, BC_2%60);
		}
		printf("\n");
	} else {
		printf("%s\n", meetnotpos);
	}
	return EXIT_SUCCESS;
}

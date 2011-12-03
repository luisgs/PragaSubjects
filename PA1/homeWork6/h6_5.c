/*
 * =====================================================================================
 *
 *       Filename:  h6_5.c
 *
 *    Description:  homework6 5point
 *
 *        Version:  1.0
 *        Created:  25/11/11 20:43:11
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


char result[300];

char * numberUnitDigit (char *text) {
	switch (text[0]) {
		case '0':
			return "zero";
		case '1':
			return "one";
		case '2':
			return "two";
		case '3':
			return "three";
		case '4':
			return "four";
		case '5':
			return "five";
		case '6':
			return "six";
		case '7':
			return "seven";
		case '8':
			return "eight";
		case '9':
			return "nine";
		default:
			return "ERROR!";
	}
}

char * numberTenDigit (char *text) {
	switch (text[0]) {
		case '1':
			return "ten";
		case '2':
			return "twenty";
		case '3':
			return "thirty";
		case '4':
			return "forty";
		case '5':
			return "fifty";
		case '6':
			return "sixty";
		case '7':
			return "seventy";
		case '8':
			return "eighty";
		case '9':
			return "ninety";
		default:
			return "ERROR!";
	}
}

/* your supplementary functions (if any) */
char* convert (char *textnumber, int size) {
/*
billion 1.000.000.000
million	 1.000.000
thousand 1.000
hundred  100
*/
	int pos = 0;
	char *text = malloc(size);
	printf("Dentro de conversion de numeros\n");
	/* million */
	if (((size % 3) == 0 ) && (size >= 3)) {	/* >= 1000*/
		/* tiene centenas!*/
		if (textnumber[pos] != 0) {
			text = numberUnitDigit (&textnumber[pos]);
			printf("numerito convertido: %s hundred\n", text);
			pos++;
			if (textnumber[pos] != 0) {
				text = 	numberTenDigit(&textnumber[pos]);
			}
		}
	}	
printf("Esto es lo que guardaste en *text:%c\n", *text);
return "";
	
}

/*
 * isNumber return 0 if *str is not a number.
 * in other case it returns how many digits it has.
*/
int isNumber (char *str) {
	int count = 0;	/* how many digits i read*/
	int zero = 0;
	int i = 0;	/* position into the string */
	if (str[i] == '-') {
		i++;
	}
	while ((str[i]>=48) && (str[i]<=57)) {	/* ascii code */
		printf("[%c]\n", str[i]);
		i++;
		if (((i!=0) && (str[i]!=48)) || ((i!=0 && str[i]!=48 && count==0))) {
			count++;
		} 
		if ((i==0) && (str[i]==0)) {
			count++;
		}
		if ((zero!=0) && str[i]==0) {
			
		}
	}
	if ((str[i] == ' ') || (str[i] == '\n')) { /* more options? */ 
		return count;
	} else {
		return 0;
	}
}

char * replaceNumbers ( char * str ) {
/* implementation */
	int nDigits=0;
	int longitud = (int) strlen(str); /* size? */
	char *cambiado = (char *) malloc(longitud*sizeof(*cambiado));
	int i = 0;
	/* Podemos empezar a enviar! */
	printf("Replace Numbers!\n");
	cambiado[i+nDigits] = str[i];
	for (i=0; i < longitud; i++) {
		printf("Pasada para buscar otro numero\n");
		if ((nDigits = isNumber(str+i)) != 0) {
			printf("its a number\n");
			if (str[i]=='-'){
				strcat(result, "minus ");
			}
			strncat(result, &str[i], nDigits);
			convert(str+i, nDigits);
		} else { /* Basura */
			printf("its not a numnber\n");
		}
		i+=nDigits;
	}

	printf("Restultado impreso: %s\n", result);
	return str;
}


#ifndef __PROGTEST__
int main ( int argc, char * argv [] )
{
	/* your tests */
	char * r;
	r = replaceNumbers("123456 caracola9876 333esto444 007");			
	printf("Solution: %s\n",r);

	free(r);
	return 0;
}
#endif /* __PROGTEST__ */

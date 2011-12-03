/*
 * =====================================================================================
 *
 *       Filename:  h6_3.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  27/11/11 19:02:24
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
/* your supplementary functions (if any) */

char * wordWrap ( char * str, int width ) {
/* implementation */
	int i=0, pos=0;
	int ancho = 0;
	int longitud = (int) strlen(str); /* size? */
        char *cambiado = (char *) malloc(longitud*sizeof(*cambiado));
	char *tmp = (char *) malloc(width*sizeof(*tmp));
	int tmppos=0;

	int espacio=0;


	printf("Esta dentro de wordWrap, te envian:\n");
	printf("%s", str);

	/* if it is empty string, i will retrn the same*/
	if (str == NULL) {
		return NULL;
	} else if (strlen(str) == 0) {
		cambiado = (char *) malloc(sizeof(char));
		cambiado[0]='\0';
		return cambiado;
	}

	/* there are not spaces to the left line */
	while ((str[i] == ' ') || str[i] == '\n') {
		i++;
	}

	while ( str[i] != 0 )	{
		ancho++;
		if (ancho > width) {
		/*	printf("Sales porque ancho, %d, es mayor que width %d!\n", ancho, width);*/
			strcat(cambiado, "\n");
			while (str[i]!='\n') {
				i++;
				pos++;
			}
			if ((str[i]) == '0') {
				return cambiado;
			}
			ancho=0;
		} else {
			if (str[i]=='\n') {
				ancho=0;
			} else if ((str[i] != ' ') && (str[i-1]!=' ')) {
				tmp[tmppos] = str[i];
				printf("%c. copiando.", tmp[tmppos]);
				tmppos++;
	
				pos++;
			} else if ((str[i] == ' ') && (str[i-1]==' ')) {
				if (((pos+tmppos) <= width) && (tmppos!=0)) {
						strcat(cambiado,tmp);
						printf("jujur: %s ", cambiado);
						printf("jujur: %s", tmp);
					pos+=tmppos;
					espacio = 0;
					tmp[0]='\0';
/*					memset(&tmp[0], 0, sizeof(tmp));*/
				}
				espacio=1;
				ancho=0;
				tmppos=0;
			} else if ((str[i] == ' ') && (str[i-1] != ' ')) {
				strcat(cambiado, tmp);
				printf("relleno: %s.\n", cambiado);
				pos+=tmppos;
				tmppos=0;
				espacio = 1;
				tmp[0]='\0';
			} else if ((str[i] != ' ') && (str[i-1]==' ')) {
				tmppos=0;
				if (pos!=0)
					strcat(cambiado," ");
				tmp[tmppos] = str[i];
				tmppos++;
	
				pos++;
				espacio=0;	
			}
			i++;
		}
	}
	return cambiado;
}

#ifndef __PROGTEST__
int main ( int argc, char * argv [] ) {
/* your tests */
	char * str;
	str = wordWrap("    Lorem          ipsum dolor sit amet, consectetuer adipiscing elit. Integer metus\npede, pretium vitae, rhoncus et, auctor sit amet, ligula. Integer volutpat\n", 40);

	printf("Esto es lo que has cambiado:\n");
	printf("%s", str);

	free(str);
	
	return 0;
}
#endif /* __PROGTEST__ */


/*
 * =====================================================================================
 *
 *       Filename:  h8_3.c
 *
 *    Description:   8th exercise, 3 point.
 *
 *        Version:  1.0
 *        Created:  11/12/11 16:16:10
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

typedef struct TSoldier {
   struct TSoldier   * m_Next;
   int                 m_PersonalID;
   char                m_SecretRecord[64];
} TSOLDIER;

#endif /* __PROGTEST__ */

TSOLDIER * mergePlatoons ( TSOLDIER * p1, TSOLDIER * p2 ) {
	struct TSoldier *mPlatoon = NULL;
	struct TSoldier *a=p1, *b=p2, *tmp, *tmp2;

	/* inserting firtable p1 soldier. */	
	if (( a != NULL ) && (b == NULL)) {
		mPlatoon = p1;
		return mPlatoon;
	} else if ((a == NULL) && (b != NULL)) {
		mPlatoon = p2;
		return mPlatoon;
	} else if ((a == NULL) && (b == NULL)) {
		return NULL;
	} else {
		mPlatoon = p1;
		while ( 1 ) {
			if ((a != NULL) && (b != NULL)) {
				tmp = a->m_Next;
				a->m_Next = b;
			} else if (a!=NULL && b==NULL) {
				return mPlatoon;
			} else if ( a==NULL && b!=NULL) {
				return mPlatoon;
			} else {
				return mPlatoon;
			}

			tmp2 = b->m_Next;
			if (tmp != NULL) {
				b->m_Next = tmp;
			}
			a = tmp;
			b = tmp2;
		}	
		return mPlatoon;
	}
}

void splitPlatoon (TSOLDIER * src,
			TSOLDIER ** p1,
				TSOLDIER ** p2 ) {
	struct TSoldier *srcAux=src, *lastone;
	int count=0,liberado=0, pos=0;
	/* counting how many soldiers have got*/
	while (srcAux != NULL) {
		count++;
		lastone = srcAux;
		srcAux = srcAux->m_Next;
	}
	if (count%2==1) { /* odd -> free */
		free(lastone);
		liberado=1;
		count--;
		pos=count/2;
	}
	if (count == 0) {
		*p1 = NULL;
		*p2 = NULL;
		return;
	} else { /* even number */
		*p1 = src;
		count = count / 2;
		lastone = *p1;
		while (count > 1 ) {
			lastone = lastone->m_Next;
			count--;
		}
		*p2 = lastone->m_Next;
		lastone->m_Next = NULL;
		if (liberado==1) {
			lastone=*p2;
			while (pos > 1) {
				lastone = lastone->m_Next;
				pos--;
			}
			lastone->m_Next = NULL;	
		}
	}
	return;
}


void destroyPlatoon (TSOLDIER * src ) {
	struct TSoldier *tmp=src, *tmp1;
	if (src == NULL) {
		return;
	} else {
		tmp1=tmp->m_Next;
	}
	
	free(tmp);
	while (tmp1 != NULL) {
		tmp = tmp1->m_Next;
		free(tmp1);
		tmp1 = tmp;
	}
	return;
}

#ifndef __PROGTEST__
void pintalista (TSOLDIER * a) {
	TSOLDIER *aux=a;
	int pos=0;
	while (aux !=NULL) {
		printf("valor del ID: %d, position %d\n", aux->m_PersonalID, pos);
		aux=aux->m_Next;
		pos++;
	}
	
	return;
}

int main ( int argc, char * argv [] ){
   /* tests */
/* list c: 0 -> 10 -> 1 -> 11 -> 2 -> 12 -> 3 -> 13 -> 4 -> 14 */
/*splitPlatoon ( c, &a, &b );
*/
 int caca = 0;
int i;
TSOLDIER * lista, * lista2, *listaux;
TSOLDIER * list, *lisaux;

TSOLDIER * split1, *split2;

lista = (TSOLDIER *) malloc(sizeof(TSOLDIER));
listaux = lista;

list = (TSOLDIER *) malloc(sizeof(TSOLDIER));
lisaux = list;

for (i=1; i<1; i++)
       {
       listaux-> m_PersonalID = caca + i;
       listaux-> m_Next = (TSOLDIER *) malloc(sizeof(TSOLDIER));
       listaux = listaux->m_Next;
       }
listaux->m_Next = NULL;
listaux-> m_PersonalID = caca + i;

caca=10;
for (i=1; i<10; i++)
       {
       lisaux-> m_PersonalID = caca * i;
       lisaux-> m_Next = (TSOLDIER *) malloc(sizeof(TSOLDIER));
       lisaux = lisaux->m_Next;
       }
lisaux->m_Next = NULL;
lisaux-> m_PersonalID = caca * i;
/*
lista2 = mergePlatoons(lista, list);
pintalista(lista);
printf("===================\n");
pintalista(list);
printf("===================\n");
pintalista(lista2);
printf("===================\n");
*/
pintalista(lista);
printf("===================\n");
splitPlatoon(lista, &split1, &split2);
pintalista(split1);
printf("===================\n");
pintalista(split2);
printf("===================\n");

destroyPlatoon(split1);
return 0;
}


#endif /* __PROGTEST__ */

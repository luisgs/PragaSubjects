/*
 * =====================================================================================
 *
 *       Filename:  h8_5.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  06/12/11 18:12:07
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
#define MAX_BRANCHES 3
#define DECORATION_NONE 0
#define DECORATION_CANDLE 1
#define DECORATION_SPARKLER 2

typedef struct TNode {
	struct TNode
		* m_Parent;
	struct TNode
		* m_Branches[MAX_BRANCHES];
	int m_Decoration;
} TNODE;
#endif /* __PROGTEST__ */

/* your auxiliary functions */

/* */
int isPathCorrect (char* path) {
	int n = sizeof(path)-1;
	while (n >=0) {
		if ((path[n] != '0') && (path[n]!='1') && (path[n]!='2') && (path[n]!='')) {
			return 0;
		}
		n--;
	}
	return 1; /* exit succes*/
}

/* what number is my *char */
int Nposition (char* path, int n) {
	/* it returns 0, 1, 2 or -1 eoc */
	return (n >= sizeof(path))? -1 : (((int) path[n] == 48)? 0 : (((int)path[n] == 49)? 1 : (((int)path[n] == 49)? 2 : -1)));
}

/* create a node */
int createNode (TNODE * parentNode, int decor, int position) {
	struct TNode *nuevo;
	int position;

	nuevo = (struct TNode *) malloc (sizeof(struct TNode));
	if (nuevo==NULL) {
		printf( "No  hay memoria\n");
		return 0;
	} else {
		parentNode[position] = nuevo	/* link */
		nuevo->m_Parent = parentNode;	/* link	*/
		nuevo->m_Decoration = decor;	/* decoration */		

		return nuevo;
	}
}

int setDecoration ( TNODE ** root, char * path, int decor ) {
	if (isPathCorrect(path)) {	/* only contain '0''1''2' chars */	
		if (root == NULL) {
			createNode(root, DECORATION_NONE, 0);
		}
		return 1;
	} else {
		return 0;	/* char path is not correct */
	}

	
} 

int cutBranch ( TNODE ** root, char * path ) { 
} 

int easyToCatchFire ( TNODE * root ) {
}

void freeNode (TNODE * node) {
	/* recursive Free  mode */
	if (node->m_Branches[0] != NULL)
		freeNode(node->m_Branches[0]);
	if (node->m_Branches[1] != NULL)
		freeNode(node->m_Branches[1]);
	if (node->m_Branches[2] != NULL)
		freeNode(node->m_Branches[2]);

	free(node);
}

void destroyTree ( TNODE * root ) {
	freeNode(root->m_Branches[0]);
	free(root);
}


#ifndef __PROGTEST__
int main ( int argc, char * argv [] )
{
TNODE * n;
int     x;

n = NULL;
/*x = setDecoration ( &n, (char*) "000", DECORATION_SPARKLER );
x = setDecoration ( &n, (char*) "001", DECORATION_SPARKLER );  
x = setDecoration ( &n, (char*) "002", DECORATION_SPARKLER );  
x = setDecoration ( &n, (char*) "1", DECORATION_CANDLE ); 
x = setDecoration ( &n, (char*) "01", DECORATION_NONE );
x = setDecoration ( &n, (char*) "", DECORATION_CANDLE );
x = easyToCatchFire ( n ); /* x = 0 */
printf("has leido un %d en la pos %d\n", Nposition("0123456789", 1), 1);
/*destroyTree ( n ); */

}
#endif /* __PROGTEST__ */


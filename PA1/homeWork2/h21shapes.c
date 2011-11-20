#include <stdio.h>
#include <math.h>

char shape1, shape2;
int xpos1, ypos1, xpos2, ypos2, cir[2], radius;

struct punto {
	int x;
	int y;
};

struct punto P1, P2, C1;
struct punto Point1, Point2, Center1;


int distanciaP1P2 (int P1[2], int P2[2]) {
	return sqrt(((P1[0]-P2[0])^2) +  ((P1[1]-P2[1])^2));
}


int main (void) {
	/* punto extremos del circulo*/
	int CI[2], CD[2], CA[2], CB[2];
	int AI[2], AD[2], BI[2], BD[2];
	
	/* temporal  */
	int nShapes=1;

	while (nShapes<=2) {
		printf("Shape %d (R=rectangle, C=circle):\n", nShapes);
		(nShapes==1)?scanf("%c", &shape1):scanf("%c",&shape2);
		if (shape=='R') {
			printf("First vertex\n");
			 {}
			scanf("%d %d",&P1.x, &P1.y);	// 1º punto
			printf("Second vertex\n");
			scanf("%d %d",&P2.x, &P2.y);	// 2º punto
		} else if (shape1=='C') {
			printf("Center point\n");
			scanf("%d %d",&center.x, &center.y);
			printf("Radius\n");
			scanf("%d", &radius);
		} else if () {
	
		}
	}
	

	printf("First vertex\n");
	scanf("%d %d",&xpos1, &ypos1);	// 1º punto
	printf("Second vertex\n");
	scanf("%d %d",&xpos2, &ypos2);	// 2º punto

	/* Tenemos que ordenar el rectangulo  */
	if ((xpos1<xpos2) && (ypos1<ypos2)){
		AD[0] = xpos2;
		AD[1] = ypos2;
		AI[0] =	xpos1;
		AI[1] = ypos2;
		BD[0] = xpos2;
		BD[1] = ypos1;
		BI[0] = xpos1;
		BI[1] = ypos1;
	} else if ((xpos1<xpos2) && (ypos1>ypos2)){
		AD[0] = xpos2;
		AD[1] = ypos1;
		AI[0] =	xpos1;
		AI[1] = ypos1;
		BD[0] = xpos2;
		BD[1] = ypos2;
		BI[0] = xpos1;
		BI[1] = ypos2;
	} else if ((xpos1>xpos2) && (ypos1>ypos2)){
		AD[0] = xpos1;
		AD[1] = ypos1;
		AI[0] =	xpos2;
		AI[1] = ypos1;
		BD[0] = xpos1;
		BD[1] = ypos2;
		BI[0] = xpos2;
		BI[1] = ypos2;
	} else if ((xpos1>xpos2) && (ypos1<ypos2)){
		AD[0] = xpos1;
		AD[1] = ypos2;
		AI[0] =	xpos2;
		AI[1] = ypos2;
		BD[0] = xpos1;
		BD[1] = ypos1;
		BI[0] = xpos2;
		BI[1] = ypos1;
	}


	/* Circulito!, extremos del circulo*/
	CI[0] = cir[0] - radius;
	C1[1] = cir[1];
	CD[0] = cir[0] + radius;
	CD[1] = cir[1];
	CA[0] = cir[0];
	CA[1] = cir[1] + radius;
	CB[0] = cir[0];
	CB[1] = cir[1] - radius;

	/* Mierda de ifs eternos!!  */
	/*   */
	if ((AI[0]>CD[0] || AD[0]<CI[0] || BI[1]>CA[1] || AI[1]<CB[1]) ||	// esta fuera
		(AI[0]<CI[0] && AD[0]>CD[0] && AI[1] > CA[1] && BI[1]<CB[1])) {	// circulo contenido en rectangulo
	
		printf("Estas fuera\n!");
		return 0;
	} else if (AI[0]>CI[0] && AI[1]<CA[1] && AD[0]<CA[0] && BD[1]>CB[1]) {	// rectangulo dentro
		if ((distanciaP1P2(AI,cir) > radius) && (distanciaP1P2(AD,cir) > radius) &&
			(distanciaP1P2(BD,cir) > radius) && (distanciaP1P2(BI,cir) > radius) ) {
			printf("Estas fuera del circulo\n");
			return 1;
		} else if ((distanciaP1P2(AI,cir) > radius) || (distanciaP1P2(AD,cir) > radius) ||
				(distanciaP1P2(BD,cir) > radius) || (distanciaP1P2(BI,cir) > radius) ) {
			printf("Circulo y rectangulo estan cortandose\n");
		} else {
			printf("Todo el rectangulo dentro del circulo\n");
		}
	} else if () {

	} else {

	}
	
}

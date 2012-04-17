public class Triangulo {
	/* Se incluyen las operaciones que se pueden aplicar y deshacer a un triangulo
	 * ,Asi como los elementos que constituyen a un triangulo que concretamente son tres puntos.
	 * Cada punto tiene tres coordenadas(x,y,z) que son numeros reales.
	 
	 */
	protected float x1, y1, z1;
	protected float x2, y2, z2;
	protected float x3, y3, z3;

	// PRE : Cierto
	// POST: Devuelve cierto si los puntos son correctos.Se entiende por correcto que que
	//       los tres puntos del triangulo sean diferentes.
	protected boolean puntosIncorrectos() {
		return ((x1 == x2 && y1 == y2 && z1 == z2)
				|| (x1 == x3 && y1 == y3 && z1 == z3) || (x2 == x3 && y2 == y3 && z2 == z3));

	}

	//PRE :Cierto
	//POST: Aplica la Accion de Traslacion que se encuentra en 'Accion' al triangulo.
	protected void AplicarTraslacionTriangulo(Acciones Accion) {

		x1 = x1 + Accion.dx;
		y1 = y1 + Accion.dy;
		z1 = z1 + Accion.dz;

		x2 = x2 + Accion.dx;
		y2 = y2 + Accion.dy;
		z2 = z2 + Accion.dz;

		x3 = x3 + Accion.dx;
		y3 = y3 + Accion.dy;
		z3 = z3 + Accion.dz;

	}
	//PRE :Cierto
	//POST: Deshace la Accion de Traslacion que se encuentra en 'Accion' al triangulo.
	protected void DeshacerTraslacionTriangulo(Acciones Accion) {

		x1 = x1 - Accion.dx;
		y1 = y1 - Accion.dy;
		z1 = z1 - Accion.dz;

		x2 = x2 - Accion.dx;
		y2 = y2 - Accion.dy;
		z2 = z2 - Accion.dz;

		x3 = x3 - Accion.dx;
		y3 = y3 - Accion.dy;
		z3 = z3 - Accion.dz;

	}
	//PRE :Cierto
	//POST: Aplica la Accion de Escala que se encuentra en 'Accion' al triangulo.
	protected void AplicarEscalaTriangulo(Acciones Accion) {

		x1 = x1 * Accion.Escala;
		y1 = y1 * Accion.Escala;
		z1 = z1 * Accion.Escala;

		x2 = x2 * Accion.Escala;
		y2 = y2 * Accion.Escala;
		z2 = z2 * Accion.Escala;

		x3 = x3 * Accion.Escala;
		y3 = y3 * Accion.Escala;
		z3 = z3 * Accion.Escala;

	}

	//PRE :Cierto
	//POST: Deshace la Accion de Escala que se encuentra en 'Accion' al triangulo.
	protected void DeshacerEscalaTriangulo(Acciones Accion) {

		x1 = x1 / Accion.Escala;
		y1 = y1 / Accion.Escala;
		z1 = z1 / Accion.Escala;

		x2 = x2 / Accion.Escala;
		y2 = y2 / Accion.Escala;
		z2 = z2 / Accion.Escala;

		x3 = x3 / Accion.Escala;
		y3 = y3 / Accion.Escala;
		z3 = z3 / Accion.Escala;

	}

	//PRE :Cierto
	//POST: Aplica la Accion de Giro sobre eje X que se encuentra en 'Accion' al triangulo.
	protected void AplicarGiroX(Acciones Accion) {
		double Angulo = (double) Accion.Angulo;
		double Radianes = Math.toRadians(Angulo);
		double y11, z11, y22, z22, y33, z33, y111, z111, y222, z222, y333, z333;
		y11 = (double) y1;
		z11 = (double) z1;
		y22 = (double) y2;
		z22 = (double) z2;
		y33 = (double) y3;
		z33 = (double) z3;
		y111 = (double) y1;
		z111 = (double) z1;
		y222 = (double) y2;
		z222 = (double) z2;
		y333 = (double) y3;
		z333 = (double) z3;

		y111 = (y11 * Math.cos(Radianes)) - (z11 * Math.sin(Radianes));
		z111 = (y11 * Math.sin(Radianes)) + (z11 * Math.cos(Radianes));

		y222 = (y22 * Math.cos(Radianes)) - (z22 * Math.sin(Radianes));
		z222 = (y22 * Math.sin(Radianes)) + (z22 * Math.cos(Radianes));

		y333 = (y33 * Math.cos(Radianes)) - (z33 * Math.sin(Radianes));
		z333 = (y33 * Math.sin(Radianes)) + (z33 * Math.cos(Radianes));

		y1 = (float) y111;
		z1 = (float) z111;
		y2 = (float) y222;
		z2 = (float) z222;
		y3 = (float) y333;
		z3 = (float) z333;

	}
	//PRE :Cierto
	//POST: Deshace la Accion de Giro sobre eje X que se encuentra en 'Accion' al triangulo.
	protected void DeshacerGiroX(Acciones Accion) {
		int NuevoAngulo = 360 - Accion.Angulo;
		double Angulo = (double) NuevoAngulo;
		double Radianes = Math.toRadians(Angulo);
		double y11, z11, y22, z22, y33, z33, y111, z111, y222, z222, y333, z333;
		y11 = (double) y1;
		z11 = (double) z1;
		y22 = (double) y2;
		z22 = (double) z2;
		y33 = (double) y3;
		z33 = (double) z3;
		y111 = (double) y1;
		z111 = (double) z1;
		y222 = (double) y2;
		z222 = (double) z2;
		y333 = (double) y3;
		z333 = (double) z3;

		y111 = (y11 * Math.cos(Radianes)) - (z11 * Math.sin(Radianes));
		z111 = (y11 * Math.sin(Radianes)) + (z11 * Math.cos(Radianes));

		y222 = (y22 * Math.cos(Radianes)) - (z22 * Math.sin(Radianes));
		z222 = (y22 * Math.sin(Radianes)) + (z22 * Math.cos(Radianes));

		y333 = (y33 * Math.cos(Radianes)) - (z33 * Math.sin(Radianes));
		z333 = (y33 * Math.sin(Radianes)) + (z33 * Math.cos(Radianes));

		y1 = (float) y111;
		z1 = (float) z111;
		y2 = (float) y222;
		z2 = (float) z222;
		y3 = (float) y333;
		z3 = (float) z333;

	}
	//PRE :Cierto
	//POST: Aplica la Accion de Giro sobre eje Y que se encuentra en 'Accion' al triangulo.
	protected void AplicarGiroY(Acciones Accion) {
		double Angulo = (double) Accion.Angulo;
		double Radianes = Math.toRadians(Angulo);
		double x11, z11, x22, z22, x33, z33, x111, z111, x222, z222, x333, z333;
		x11 = (double) x1;
		z11 = (double) z1;
		x22 = (double) x2;
		z22 = (double) z2;
		x33 = (double) x3;
		z33 = (double) z3;
		x111 = (double) x1;
		z111 = (double) z1;
		x222 = (double) x2;
		z222 = (double) z2;
		x333 = (double) x3;
		z333 = (double) z3;

		x111 = (x11 * Math.cos(Radianes)) + (z11 * Math.sin(Radianes));
		z111 = (z11 * Math.cos(Radianes)) - (x11 * Math.sin(Radianes));

		x222 = (x22 * Math.cos(Radianes)) + (z22 * Math.sin(Radianes));
		z222 = (z22 * Math.cos(Radianes)) - (x22 * Math.sin(Radianes));

		x333 = (x33 * Math.cos(Radianes)) + (z33 * Math.sin(Radianes));
		z333 = (z33 * Math.cos(Radianes)) - (x33 * Math.sin(Radianes));

		x1 = (float) x111;
		z1 = (float) z111;
		x2 = (float) x222;
		z2 = (float) z222;
		x3 = (float) x333;
		z3 = (float) z333;

	}
	//PRE :Cierto
	//POST: Deshace la Accion de Giro sobre eje Y que se encuentra en 'Accion' al triangulo.
	protected void DeshacerGiroY(Acciones Accion) {
		int NuevoAngulo = 360 - Accion.Angulo;
		double Angulo = (double) NuevoAngulo;
		double Radianes = Math.toRadians(Angulo);
		double x11, z11, x22, z22, x33, z33, x111, z111, x222, z222, x333, z333;
		x11 = (double) x1;
		z11 = (double) z1;
		x22 = (double) x2;
		z22 = (double) z2;
		x33 = (double) x3;
		z33 = (double) z3;
		x111 = (double) x1;
		z111 = (double) z1;
		x222 = (double) x2;
		z222 = (double) z2;
		x333 = (double) x3;
		z333 = (double) z3;

		x111 = (x11 * Math.cos(Radianes)) + (z11 * Math.sin(Radianes));
		z111 = (z11 * Math.cos(Radianes)) - (x11 * Math.sin(Radianes));

		x222 = (x22 * Math.cos(Radianes)) + (z22 * Math.sin(Radianes));
		z222 = (z22 * Math.cos(Radianes)) - (x22 * Math.sin(Radianes));

		x333 = (x33 * Math.cos(Radianes)) + (z33 * Math.sin(Radianes));
		z333 = (z33 * Math.cos(Radianes)) - (x33 * Math.sin(Radianes));

		x1 = (float) x111;
		z1 = (float) z111;
		x2 = (float) x222;
		z2 = (float) z222;
		x3 = (float) x333;
		z3 = (float) z333;
	}
	//PRE :Cierto
	//POST: Aplica la Accion de Giro sobre eje Z que se encuentra en 'Accion' al triangulo.
	protected void AplicarGiroZ(Acciones Accion) {
		double Angulo = (double) Accion.Angulo;
		double Radianes = Math.toRadians(Angulo);
		double x11, y11, x22, y22, x33, y33, x111, y111, x222, y222, x333, y333;
		x11 = (double) x1;
		y11 = (double) y1;
		x22 = (double) x2;
		y22 = (double) y2;
		x33 = (double) x3;
		y33 = (double) y3;
		x111 = (double) x1;
		y111 = (double) y1;
		x222 = (double) x2;
		y222 = (double) y2;
		x333 = (double) x3;
		y333 = (double) y3;

	
		x111 = (x11 * Math.cos(Radianes)) - (y11 * Math.sin(Radianes));
		y111 = (x11 * Math.sin(Radianes)) + (y11 * Math.cos(Radianes));

		x222 = (x22 * Math.cos(Radianes)) - (y22 * Math.sin(Radianes));
		y222 = (x22 * Math.sin(Radianes)) + (y22 * Math.cos(Radianes));

		x333 = (x33 * Math.cos(Radianes)) - (y33 * Math.sin(Radianes));
		y333 = (x33 * Math.sin(Radianes)) + (y33 * Math.cos(Radianes));

		x1 = (float) x111;
		y1 = (float) y111;
		x2 = (float) x222;
		y2 = (float) y222;
		x3 = (float) x333;
		y3 = (float) y333;

	}
	//PRE :Cierto
	//POST: Deshace la Accion de Giro sobre eje Z que se encuentra en 'Accion' al triangulo.
	protected void DeshacerGiroZ(Acciones Accion) {
		int NuevoAngulo = 360 - Accion.Angulo;
		double Angulo = (double) NuevoAngulo;
		double Radianes = Math.toRadians(Angulo);
		double x11, y11, x22, y22, x33, y33, x111, y111, x222, y222, x333, y333;
		x11 = (double) x1;
		y11 = (double) y1;
		x22 = (double) x2;
		y22 = (double) y2;
		x33 = (double) x3;
		y33 = (double) y3;
		x111 = (double) x1;
		y111 = (double) y1;
		x222 = (double) x2;
		y222 = (double) y2;
		x333 = (double) x3;
		y333 = (double) y3;

		x111 = (x11 * Math.cos(Radianes)) - (y11 * Math.sin(Radianes));
		y111 = (x11 * Math.sin(Radianes)) + (y11 * Math.cos(Radianes));

		x222 = (x22 * Math.cos(Radianes)) - (y22 * Math.sin(Radianes));
		y222 = (x22 * Math.sin(Radianes)) + (y22 * Math.cos(Radianes));

		x333 = (x33 * Math.cos(Radianes)) - (y33 * Math.sin(Radianes));
		y333 = (x33 * Math.sin(Radianes)) + (y33 * Math.cos(Radianes));

		x1 = (float) x111;
		y1 = (float) y111;
		x2 = (float) x222;
		y2 = (float) y222;
		x3 = (float) x333;
		y3 = (float) y333;
	}
	//PRE :Cierto
	//POST: Devuelve la superfice de un triangulo
	protected float CalcularSuperficeTriangulo(){
		float a1,a2,a3,b1,b2,b3;
		a1=x2-x1;
		a2=y2-y1;
		a3=z2-z1;
		b1=x3-x1;
		b2=y3-y1;
		b3=z3-z1;
		return Math.abs((a2*b3+a3*b1+a1*b2-a3*b2-a1*b3-a2*b1)/2);	
	}
}

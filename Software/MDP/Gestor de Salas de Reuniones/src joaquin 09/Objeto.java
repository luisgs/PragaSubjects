import java.util.*;

public class Objeto {
/*
 *En esta clase se encuentran todos los elementos que definen a un objeto:
 *Nombre,color,Numero de Triangulos,Numero de Acciones Aplicadas
 *Lista Con sus triangulos,Pila Con Acciones Aplicadas y Lista Con Acciones Aplicadas
 *Tambien se encuentras las operaciones relativas a un objeto. 
 */
		
	protected String nombre;
	protected int color;
	protected int Numero_Triangulos = 000;
	protected int Numero_Acciones = 000;
	protected List<Triangulo> ListaTriangulo = new ArrayList<Triangulo>();
	private LinkedList<Acciones> PilaAcciones = new LinkedList<Acciones>();
	protected List<Acciones> ListaAccionesAplicadas = new ArrayList<Acciones>();
	protected static int identificadorAux = 0;

	// /////////////////////////AUXILIARES//////////////////////////////////////////////////

	//PRE:Cierto
	//POST: Aplica la Accion de Traslacion que se encuentra en 'Accion' a todos los 
	//      triangulos del objeto.
	private void AplicarTraslacion(Acciones Accion) {
		ListIterator<Triangulo> I = ListaTriangulo.listIterator();
		Triangulo Tria = new Triangulo();

		while (I.hasNext()) {
			Tria = I.next();
			Tria.AplicarTraslacionTriangulo(Accion);
		}

	}

	//PRE :Cierto
	//POST: Deshace la Accion de Traslacion que se encuentra en 'Accion' en todos los 
	//      triangulos del objeto.
	private void DeshacerTraslacion(Acciones Accion) {
		ListIterator<Triangulo> I = ListaTriangulo.listIterator();
		Triangulo Tria = new Triangulo();
		while (I.hasNext()) {
			Tria = I.next();
			Tria.DeshacerTraslacionTriangulo(Accion);
		}

	}

	//PRE:Cierto
	//POST: Aplica la Accion de Escala que se encuentra en 'Accion' a todos los 
	//      triangulos del objeto.
	private void AplicarEscala(Acciones Accion) {
		ListIterator<Triangulo> I = ListaTriangulo.listIterator();
		Triangulo Tria = new Triangulo();
		while (I.hasNext()) {
			Tria = I.next();
			Tria.AplicarEscalaTriangulo(Accion);
		}
	}
	//PRE :Cierto
	//POST: Deshace la Accion de Giro que se encuentra en 'Accion' en todos los 
	// triangulos del objeto.
	private void DeshacerGiro(Acciones Accion) {
		ListIterator<Triangulo> I = ListaTriangulo.listIterator();
		Triangulo Tria = new Triangulo();
		if (Accion.EjeDeGiro == 'x') {
			while (I.hasNext()) {
				Tria = I.next();
				Tria.DeshacerGiroX(Accion);
			}
		}
		if (Accion.EjeDeGiro == 'y') {
	
			while (I.hasNext()) {
				Tria = I.next();
				Tria.DeshacerGiroY(Accion);
			}
		}
		if (Accion.EjeDeGiro == 'z') {
			while (I.hasNext()) {
				Tria = I.next();
				Tria.DeshacerGiroZ(Accion);
			}
		}
	}

	//PRE :Cierto
	//POST: Deshace la Accion de Escala que se encuentra en 'Accion' en todos los 
	//      triangulos del objeto.
	private void DeshacerEscala(Acciones Accion) {
		ListIterator<Triangulo> I = ListaTriangulo.listIterator();
		Triangulo Tria = new Triangulo();
		while (I.hasNext()) {
			Tria = I.next();
			Tria.DeshacerEscalaTriangulo(Accion);
		}
	}
	//PRE :Cierto
	//POST: Aplica la Accion de giro que se encuentra en 'Accion' a todos los 
	//      triangulos del objeto.
	private void AplicarGiro(Acciones Accion) {
		ListIterator<Triangulo> I = ListaTriangulo.listIterator();
		Triangulo Tria = new Triangulo();
		if (Accion.EjeDeGiro == 'x') {
			while (I.hasNext()) {
				Tria = I.next();
				Tria.AplicarGiroX(Accion);
			}
		}
		if (Accion.EjeDeGiro == 'y') {
			while (I.hasNext()) {
				Tria = I.next();
				Tria.AplicarGiroY(Accion);
			}
		}
		if (Accion.EjeDeGiro == 'z') {

			while (I.hasNext()) {
				Tria = I.next();
				Tria.AplicarGiroZ(Accion);
			}
		}
	}

	// PRE :Cierto
	// POST: Devuelve cierto si el triangulo que se le pasa se encuentra en el objeto.
	protected boolean estaTria(float x1, float y1, float z1, float x2, float y2,
			float z2, float x3, float y3, float z3) {

		boolean esta = false;
		Triangulo Tria = new Triangulo();
		ListIterator<Triangulo> I = ListaTriangulo.listIterator();
		while (I.hasNext() && !esta) {

			Tria = I.next();
			if (Tria.x1 == x1 && Tria.y1 == y1 && Tria.z1 == z1
					&& Tria.x2 == x2 && Tria.y2 == y2 && Tria.z2 == z2
					&& Tria.x3 == x3 && Tria.y3 == y3 && Tria.z3 == z3) {
				esta = true;
			}

		}
		return esta;

	}
	// PRE:Cierto
	// POST: Devuelve cierto si la accion definida por 'Identificador' esta aplicada en el objeto.
	protected boolean estaAccionAplicada(int Identificador) {
		boolean esta = false;
		Acciones Accion = new Acciones();
		ListIterator<Acciones> I = ListaAccionesAplicadas.listIterator();
		while (I.hasNext() && !esta) {
			Accion = I.next();
			if (Accion.Identificador == Identificador) {
				esta = true;
			}
		}
		return esta;
	}

	//PRE:cierto
	//POST: Devuelve un valor entero:
	//      Si valor==0(Correcto) , el triangulo ha sido agregado al objeto, y se incrementa la variable del objeto Numero_Triangulos
	//      Si valor==1(Error) , el triangulo ya habia sido agregado previamente
	//      Si valor==2(Error) , los puntos del triangulo son incorrectos.
	protected int agregarTria(float x1, float y1, float z1, float x2, float y2,
			float z2, float x3, float y3, float z3) {
		int valor = 0;
		Triangulo tria = new Triangulo();
		int indice;
		tria.x1 = x1;
		tria.y1 = y1;
		tria.z1 = z1;
		tria.x2 = x2;
		tria.y2 = y2;
		tria.z2 = z2;
		tria.x3 = x3;
		tria.y3 = y3;
		tria.z3 = z3;
		ListIterator<Triangulo> Iterador = ListaTriangulo.listIterator();
		if (!estaTria(x1, y1, z1, x2, y2, z2, x3, y3, z3)
				&& !tria.puntosIncorrectos()) {
			while (Iterador.hasNext()) {
				Iterador.next();
			}
			indice = Iterador.nextIndex();
			ListaTriangulo.add(indice, tria);
			Numero_Triangulos = Numero_Triangulos + 1;

		} else {
			if (estaTria(x1, y1, z1, x2, y2, z2, x3, y3, z3)) {

				valor = 1;
			}
			if (tria.puntosIncorrectos()) {

				valor = 2;
			}

		}
		return valor;
	}

	//PRE:cierto
	//POST: devuelve un valor entero:
	//      Si valor==0(Correcto) , la accion ha sido aplicada al objeto, y se incrementa la variable del objeto Numero_Acciones
	//      Si valor==-2(Error) , No hay acciones que aplicar.
	
	protected int AplicarAccion(Acciones Accion) {
		int valor = 0;
		int posicion;
		ListIterator<Acciones> I = ListaAccionesAplicadas.listIterator();
		ListIterator<Triangulo> T = ListaTriangulo.listIterator();
		if (T.hasNext() == false) {
			valor = -2;
		} else {

			Numero_Acciones = Numero_Acciones + 1;

			if (Accion.TipoAccion.equals("t")) {
				AplicarTraslacion(Accion);
				PilaAcciones.addFirst(Accion);
				while (I.hasNext()) {
					I.next();
				}
				posicion = I.nextIndex();
				ListaAccionesAplicadas.add(posicion, Accion);

			}
			if (Accion.TipoAccion.equals("e")) {
				AplicarEscala(Accion);
				PilaAcciones.addFirst(Accion);
				while (I.hasNext()) {
					I.next();
				}
				posicion = I.nextIndex();
				ListaAccionesAplicadas.add(posicion, Accion);

			}
			if (Accion.TipoAccion.equals("g")) {
				AplicarGiro(Accion);
				PilaAcciones.addFirst(Accion);
				while (I.hasNext()) {
					I.next();
				}
				posicion = I.nextIndex();
				ListaAccionesAplicadas.add(posicion, Accion);

			}
		}
		return valor;
	}

	//PRE:Cierto
	//POST: Devuelve un valor entero:
	//      Si valor==0(Correcto) , la accion ha sido deshecha en el objeto, y se decrementa la variable del objeto Numero_Acciones
	//      Si valor==-1(Error) , No hay acciones que deshacer.
	
	protected int DeshacerAccion() {
		int valor = 0;
		int posicion = 0;
		ListIterator<Acciones> I = ListaAccionesAplicadas.listIterator();
		Acciones Accion = new Acciones();
		if (Numero_Acciones == 0) {
			valor = -1;
		} else {

			Numero_Acciones = Numero_Acciones - 1;
			Accion = PilaAcciones.getFirst();
			PilaAcciones.removeFirst();
			identificadorAux = Accion.Identificador;
			while (I.hasNext()) {
				I.next();
			}
			if (I.hasPrevious() == false) {

				ListaAccionesAplicadas.remove(0);

			} else {

				posicion = I.previousIndex();
				ListaAccionesAplicadas.remove(posicion);
			}

			if (Accion.TipoAccion.equals("t")) {
				DeshacerTraslacion(Accion);

			}
			if (Accion.TipoAccion.equals("e")) {
				DeshacerEscala(Accion);
			}
			if (Accion.TipoAccion.equals("g")) {
				DeshacerGiro(Accion);
			}
		}
		return valor;
	}

	//PRE :cierto
	//POST: devuelve un valor real:
	//      Si valor==-1(Error), no hay triangulos para calcular superfice
	//      e.o.c Devuelve el la superficie del objeto
	protected float CalcularSuperfice() {
		float resultado = 0;
		float Acumulador = 0;
		ListIterator<Triangulo> I = ListaTriangulo.listIterator();
		Triangulo Tria = new Triangulo();
		if (I.hasNext() == false) {
			Acumulador = -1;
		} else {
			while (I.hasNext()) {
				Tria = I.next();
				resultado = Tria.CalcularSuperficeTriangulo();
				Acumulador = Acumulador + resultado;
			}
		}
		return Acumulador;
	}

}

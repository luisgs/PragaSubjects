import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Objetos {
	
	/*
	 * En esta clase se encuentras todos los objetos y acciones agregados en el sistema
	 * en forma de lista.Asi como un tipo(TipoDevuelto) que especifica los
	 * resultados que pueden dar las operaciones definidas en esta clase.
	 */
	
	private List<Objeto> ListaObjetos = new ArrayList<Objeto>();
	private List<Acciones> ListaAcciones = new ArrayList<Acciones>();

	protected enum TipoDevuelto {
		Correcto, ObjetoYaexiste, ObjetoNoExiste, TrianguloExisteOtroObjeto, TrianguloExisteEsteObjeto, PuntosTriangulosIncorrectos, IdentificadorYaExiste, IdentificadorNoExiste, NoExisteAccionParaAplicar, NoTrianguloAplicarAccion, NoSepuedeBorrarAccion, CambioEscalaPositivo, NoAccionDeshacer, NoTriangulosCalcularSuperfice
	};

	// ////////////////////////////////AUXILIARES ////////////////////////////

	//PRE :Cierto
	//POST: Devuelve cierto si la accion definida por 'Identificador' se encuentra
	//      aplicada en algun objeto.
	private boolean EstaAccionAplicadaEnAlgunObjeto(int Identificador) {
		ListIterator<Objeto> I = ListaObjetos.listIterator();
		Objeto objt = new Objeto();
		boolean esta = false;
		while (I.hasNext() && !esta) {
			objt = I.next();
			if (objt.estaAccionAplicada(Identificador)) {
				esta = true;
			}
		}
		return esta;
	}
   //PRE : Cierto
   //POST: Devuelve cierto si el triangulo que se le pasa esta agregado en otro objeto.
	private boolean EstaTrianguloEnOtro(float x1, float y1, float z1, float x2,
			float y2, float z2, float x3, float y3, float z3,Objeto objeto) {
		Objeto objt = new Objeto();
		ListIterator<Objeto> I = ListaObjetos.listIterator();
		boolean esta = false;
		while (I.hasNext() && !esta) {
			objt = I.next();
			if (!objt.nombre.equals(objeto.nombre)) {
				if (objt.estaTria(x1, y1, z1, x2, y2, z2, x3, y3, z3)) {
					esta = true;
				}
			}
		}
		return esta;

	}
    // PRE :Cierto
	// POST: Devuelve un valor entero:
	//       valor=-1, si el identificador no se encuentra en el sistema
	//       e.o.c la posicion que ocupa en ListaAcciones
	private int EstaIdentificador(int Ident) {
		int posicion = -1;
		boolean esta = false;
		ListIterator<Acciones> I = ListaAcciones.listIterator();
		Acciones Accion = new Acciones();
		while (I.hasNext() && !esta) {
			posicion = I.nextIndex();
			Accion = I.next();
			if (Accion.Identificador == Ident) {
				esta = true;
			}
		}
		if (!esta) {
			posicion = -1;
		}
		return posicion;
	}

	// PRE:  Cierto
	// POST: Devuelve un valor entero:
	//       valor=-1, si el Objeto no se encuentra en el sistema
	//       e.o.c la posicion que ocupa en ListaObjetos
	private int EstaObjeto(String Nombre) {
		boolean esta = false;
		int posicion = -1;
		Objeto obj = new Objeto();
		ListIterator<Objeto> I = ListaObjetos.listIterator();
		while (I.hasNext() && !esta) {
			posicion = I.nextIndex();
			obj = I.next();
			if (obj.nombre.equals(Nombre))
				esta = true;
		}

		if (!esta) {
			posicion = -1;

		}

		return posicion;
	}

	// //////////////////////////////////////////////////////////////////////////////

	// PRE:cierto
	// POST: Agrega el objeto que se le pasa,definido por su nombre y su color, y devuelve correcto.
	//       e.o.c el error que se haya producido
	protected TipoDevuelto AgregarObjeto(String Nombre, int Color) {
		TipoDevuelto Devolver = TipoDevuelto.Correcto;
		int indice;
		Objeto obj = new Objeto();
		obj.nombre = Nombre;
		obj.color = Color;
		int valor;
		ListIterator<Objeto> Iterador = ListaObjetos.listIterator();
		valor = EstaObjeto(Nombre);
		if (valor == -1) {
			while (Iterador.hasNext()) {
				Iterador.next();
			}
			indice = Iterador.nextIndex();
			ListaObjetos.add(indice, obj);
		} else {
			Devolver = TipoDevuelto.ObjetoYaexiste;
		}
		return Devolver;
	}

	// PRE: Cierto
	// POST: Elimina el objeto que indica 'Nombre'y devuelve correcto.
	//       e.o.c el error que se haya producido
	protected TipoDevuelto EliminarObjeto(String Nombre) {
		TipoDevuelto Devolver = TipoDevuelto.Correcto;
		int valor;
		valor = EstaObjeto(Nombre);
		if (valor != -1) {
			ListaObjetos.remove(valor);

		} else
			Devolver = TipoDevuelto.ObjetoNoExiste;
		return Devolver;
	}

	// PRE :Cierto
	// POST:Agrega el Triangulo que  se le pasa al objeto que indica 'Nombre'y devuelve correcto.
	//      e.o.c el error que se haya producido
	protected TipoDevuelto AgregarTriangulo(float x1, float y1, float z1,
			float x2, float y2, float z2, float x3, float y3, float z3,
			String Nombre) {
		TipoDevuelto Devolver = TipoDevuelto.Correcto;
		int valor;
		int errores;
		Objeto obj = new Objeto();
		ListIterator<Objeto> Iterador = ListaObjetos.listIterator();
		valor = EstaObjeto(Nombre);
		if (valor != -1) {

			while (valor >= 0) {
				obj = Iterador.next();
				valor = valor - 1;
			}

			if (EstaTrianguloEnOtro(x1, y1, z1, x2, y2, z2, x3, y3, z3, obj)) {
				Devolver = TipoDevuelto.TrianguloExisteOtroObjeto;
			} else {
				while (valor >= 0) {
					obj = Iterador.next();
					valor = valor - 1;
				}

				errores = obj.agregarTria(x1, y1, z1, x2, y2, z2, x3, y3, z3);
				if (errores == 1) {
					Devolver = TipoDevuelto.TrianguloExisteEsteObjeto;
				}
				if (errores == 2) {
					Devolver = TipoDevuelto.PuntosTriangulosIncorrectos;
				}
			}
		} else {
			Devolver = TipoDevuelto.ObjetoNoExiste;
		}
		return Devolver;
	}
       //Pre  :cierto
	   // Post:Devuelve CORRECTO si el objeto definido por 'Nombre' que se quiere mostrar se encuentra agregado
	 //        e.o.c el error que se haya producido
	protected TipoDevuelto MostrarDatosObjetos(String Nombre) {
		TipoDevuelto Devolver = TipoDevuelto.Correcto;
		int valor;
		valor = EstaObjeto(Nombre);
		if (valor == -1) {
			Devolver = TipoDevuelto.ObjetoNoExiste;
		}
		return Devolver;
	}
    
	//PRE:  Cierto
	//POST: Muestra por fichero los datos del objeto definido por 'Nombre', en el formato pedido en la especificacion de la practica
	protected void MostrarDatos(String Nombre, RandomAccessFile Salida)
			throws IOException {
		ListIterator<Objeto> Iterador = ListaObjetos.listIterator();
		Objeto obj = new Objeto();

		Triangulo Tria = new Triangulo();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		DecimalFormat FIa = new DecimalFormat("000", dfs);
		DecimalFormat FEnt = new DecimalFormat("+000000.00;-000000.00", dfs);
		String Trasformado = "";
		String PuntoTrasformado = "";
		int valor = EstaObjeto(Nombre);

		while (valor >= 0) {
			obj = Iterador.next();
			valor = valor - 1;
		}
		ListIterator<Triangulo> I = obj.ListaTriangulo.listIterator();

		Salida.writeBytes("OBJETO");
		Salida.writeBytes("\r\n");
		Salida.writeBytes("Objeto: ");
		Salida.writeBytes(obj.nombre);
		Salida.writeBytes(" ");
		String Color = java.lang.Integer.toHexString(obj.color);
		Salida.writeBytes(Color.toUpperCase());
		Salida.writeBytes("\r\n");
		Salida.writeBytes("Acciones: ");
		Trasformado = FIa.format(obj.Numero_Acciones);
		Salida.writeBytes(Trasformado);
		Salida.writeBytes("\r\n");
		Salida.writeBytes("Triangulos: ");
		Trasformado = FIa.format(obj.Numero_Triangulos);
		Salida.writeBytes(Trasformado);
		Salida.writeBytes("\r\n");
		while (I.hasNext() || valor == 0) {

			Tria = I.next();
			PuntoTrasformado = FEnt.format(Tria.x1);
			Salida.writeBytes(PuntoTrasformado);
			Salida.writeBytes(" ");
			PuntoTrasformado = FEnt.format(Tria.y1);
			Salida.writeBytes(PuntoTrasformado);
			Salida.writeBytes(" ");
			PuntoTrasformado = FEnt.format(Tria.z1);
			Salida.writeBytes(PuntoTrasformado);
			Salida.writeBytes(" ");
			PuntoTrasformado = FEnt.format(Tria.x2);
			Salida.writeBytes(PuntoTrasformado);
			Salida.writeBytes(" ");
			PuntoTrasformado = FEnt.format(Tria.y2);
			Salida.writeBytes(PuntoTrasformado);
			Salida.writeBytes(" ");
			PuntoTrasformado = FEnt.format(Tria.z2);
			Salida.writeBytes(PuntoTrasformado);
			Salida.writeBytes(" ");
			PuntoTrasformado = FEnt.format(Tria.x3);
			Salida.writeBytes(PuntoTrasformado);
			Salida.writeBytes(" ");
			PuntoTrasformado = FEnt.format(Tria.y3);
			Salida.writeBytes(PuntoTrasformado);
			Salida.writeBytes(" ");
			PuntoTrasformado = FEnt.format(Tria.z3);
			Salida.writeBytes(PuntoTrasformado);
			Salida.writeBytes("\r\n");
		}

		Salida.writeBytes("FIN OBJETO");
		Salida.writeBytes("\r\n");
		Salida.seek(Salida.length());
	}
    //PRE:Cierto
	//POST:Agrega la accion de traslacion y devuelve correcto
	//     e.o.c el error producido.
	protected TipoDevuelto AgregarAccionTraslacion(int Identificador,
			String Tipo, float dx, float dy, float dz) {
		TipoDevuelto Devolver = TipoDevuelto.Correcto;
		int Posicion;
		Acciones Accion = new Acciones();
		ListIterator<Acciones> I = ListaAcciones.listIterator();
		if (EstaIdentificador(Identificador) == -1) {
			Accion.Identificador = Identificador;
			Accion.TipoAccion = Tipo;
			Accion.dx = dx;
			Accion.dy = dy;
			Accion.dz = dz;
			while (I.hasNext()) {
				I.next();
			}
			Posicion = I.nextIndex();
			ListaAcciones.add(Posicion, Accion);
		} else {
			Devolver = TipoDevuelto.IdentificadorYaExiste;
		}
		return Devolver;
	}

	//PRE:Cierto
	//POST:Agrega la accion de giro y devuelve correcto
	//     e.o.c el error producido.
	protected TipoDevuelto AgregarAccionGiro(int Identificador, String Tipo,
			char Eje, int Angulo) {
		TipoDevuelto Devolver = TipoDevuelto.Correcto;
		int Posicion;
		Acciones Accion = new Acciones();
		ListIterator<Acciones> I = ListaAcciones.listIterator();
		if (EstaIdentificador(Identificador) == -1) {
			Accion.Identificador = Identificador;
			Accion.TipoAccion = Tipo;
			Accion.EjeDeGiro = Eje;
			Accion.Angulo = Angulo;
			while (I.hasNext()) {
				I.next();
			}
			Posicion = I.nextIndex();
			ListaAcciones.add(Posicion, Accion);
		} else {
			Devolver = TipoDevuelto.IdentificadorYaExiste;
		}
		return Devolver;
	}
	//PRE:Cierto
	//POST:Agrega la accion de cambio de escala y devuelve correcto
	//     e.o.c el error producido.
	
	protected TipoDevuelto AgregarAccionEscala(int Identificador, String Tipo,
			float Escala) {
		TipoDevuelto Devolver = TipoDevuelto.Correcto;
		int Posicion;
		Acciones Accion = new Acciones();
		ListIterator<Acciones> I = ListaAcciones.listIterator();
		if (Escala <= 0) {
			Devolver = TipoDevuelto.CambioEscalaPositivo;
		} else {
			if (EstaIdentificador(Identificador) == -1) {
				Accion.Identificador = Identificador;
				Accion.TipoAccion = Tipo;
				Accion.Escala = Escala;
				while (I.hasNext()) {
					I.next();
				}
				Posicion = I.nextIndex();
				ListaAcciones.add(Posicion, Accion);
			} else {
				Devolver = TipoDevuelto.IdentificadorYaExiste;
			}
		}
		return Devolver;
	}

	//PRE:Cierto
	//POST:Aplica la accion definida por 'Indetificador' al objeto definido por 'Nombre' y devuelve correcto
	//     e.o.c el error producido.
	protected TipoDevuelto AplicarAccion(String Nombre, int Identificador) {
		TipoDevuelto Devolver = TipoDevuelto.Correcto;
		int valorObjeto;
		int valorAccion;
		int devuelto = 0;
		ListIterator<Objeto> IteradorO = ListaObjetos.listIterator();
		ListIterator<Acciones> IteradorA = ListaAcciones.listIterator();

		valorObjeto = EstaObjeto(Nombre);
		Objeto O = new Objeto();
		Acciones A = new Acciones();
		if (valorObjeto == -1) {
			Devolver = TipoDevuelto.ObjetoNoExiste;
		}
		valorAccion = EstaIdentificador(Identificador);
		if (valorAccion == -1) {
			Devolver = TipoDevuelto.NoExisteAccionParaAplicar;
		} else {
			while (valorObjeto >= 0) {
				O = IteradorO.next();
				valorObjeto = valorObjeto - 1;
			}
			while (valorAccion >= 0) {
				A = IteradorA.next();
				valorAccion = valorAccion - 1;
			}
			devuelto = O.AplicarAccion(A);

			if (devuelto == -2) {
				Devolver = TipoDevuelto.NoTrianguloAplicarAccion;
			}
		}
		return Devolver;
	}

	//PRE:Cierto
	//POST:Deshace la ultima accion aplicada al objeto definido por 'Nombre' y devuelve correcto
	//     e.o.c el error producido.
	protected TipoDevuelto DeshacerAccion(String Nombre) {
		TipoDevuelto Devolver = TipoDevuelto.Correcto;
		int valorObjeto;
		ListIterator<Objeto> IteradorO = ListaObjetos.listIterator();
		valorObjeto = EstaObjeto(Nombre);
		Objeto O = new Objeto();
		if (valorObjeto == -1) {
			Devolver = TipoDevuelto.ObjetoNoExiste;
		} else {
			while (valorObjeto >= 0) {
				O = IteradorO.next();
				valorObjeto = valorObjeto - 1;
			}
			if ((O.DeshacerAccion()) == -1) {
				Devolver = TipoDevuelto.NoAccionDeshacer;
			}
		}
		return Devolver;
	}

	//PRE:Cierto
	//POST:Elimina la accion definida por 'Indetificador' y devuelve correcto
	//     e.o.c el error producido.
	
	protected TipoDevuelto EliminarAccion(int Identificador) {
		TipoDevuelto Devolver = TipoDevuelto.Correcto;
		int valor;
		valor = EstaIdentificador(Identificador);
		if (!EstaAccionAplicadaEnAlgunObjeto(Identificador)) {

			if (valor != -1) {
				ListaAcciones.remove(valor);
			} else {

				Devolver = TipoDevuelto.IdentificadorNoExiste;
			}
		} else {
			Devolver = TipoDevuelto.NoSepuedeBorrarAccion;
		}
		return Devolver;
	}

	//PRE:Cierto
	//POST:devuelve CORRECTO si se puede calcular la superfice del objeto definido por 'Nombre'
	//     e.o.c el error producido.
	protected TipoDevuelto CalcularSuperficieObjeto(String Nombre)
			throws IOException {
		TipoDevuelto Devolver = TipoDevuelto.Correcto;
		float Superficie;
		int valorObjeto;
		valorObjeto = EstaObjeto(Nombre);
		if (valorObjeto == -1) {
			Devolver = TipoDevuelto.ObjetoNoExiste;
		}
		Superficie = MostrarSuperficie(Nombre);
		if (Superficie == -1) {
			Devolver = TipoDevuelto.NoTriangulosCalcularSuperfice;
		}
		return Devolver;
	}

	//PRE:Cierto
	//POST:Devuelve la superfice del objeto definido por 'Nombre'.
	protected float MostrarSuperficie(String Nombre) throws IOException {
		float Superficie;
		int valorObjeto;
		ListIterator<Objeto> IteradorO = ListaObjetos.listIterator();
		Objeto O = new Objeto();
		valorObjeto = EstaObjeto(Nombre);
		while (valorObjeto >= 0) {
			O = IteradorO.next();
			valorObjeto = valorObjeto - 1;

		}
		Superficie = O.CalcularSuperfice();

		return Superficie;
	}
	//PRE:  Cierto
	//POST: Muestra por fichero las acciones aplicadas del objeto definido por 'Nombre', en el formato pedido en la especificacion de la practica
	protected void MostrarAcciones(String Nombre, RandomAccessFile Salida)
			throws IOException {
		ListIterator<Objeto> Iterador = ListaObjetos.listIterator();
		Objeto obj = new Objeto();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		DecimalFormat FpuntosSal = new DecimalFormat("+000000.00;-000000.00",
				dfs);
		DecimalFormat Angulo = new DecimalFormat("000");
		DecimalFormat Escala = new DecimalFormat("00.00", dfs);
		DecimalFormat id = new DecimalFormat("0000");

		int valor = EstaObjeto(Nombre);

		while (valor >= 0) {
			obj = Iterador.next();
			valor = valor - 1;
		}
		ListIterator<Acciones> I = obj.ListaAccionesAplicadas.listIterator();
		Acciones A = new Acciones();

		Salida.writeBytes("ACCIONES DE OBJETO\r\n");
		Salida.writeBytes("Objeto: ");
		Salida.writeBytes(Nombre);
		Salida.writeBytes("\r\n");
		Salida.seek(Salida.length());

		while (I.hasNext()) {
			A = I.next();
			if (A.TipoAccion.equals("t")) {
				Salida.writeBytes(id.format(A.Identificador));
				Salida.writeByte(' ');
				Salida.writeBytes("TRAS");
				Salida.writeByte(' ');
				Salida.writeBytes(FpuntosSal.format(A.dx));
				Salida.writeByte(' ');
				Salida.writeBytes(FpuntosSal.format(A.dy));
				Salida.writeByte(' ');
				Salida.writeBytes(FpuntosSal.format(A.dz));
				Salida.writeBytes("\r\n");
				Salida.seek(Salida.length());
			}
			if (A.TipoAccion.equals("g")) {
				Salida.writeBytes(id.format(A.Identificador));
				Salida.writeByte(' ');
				Salida.writeBytes("GIRO");
				Salida.writeByte(' ');
				Salida.writeBytes(String.valueOf(A.EjeDeGiro));
				Salida.writeByte(' ');
				Salida.writeBytes(Angulo.format(A.Angulo));
				Salida.writeBytes("\r\n");
				Salida.seek(Salida.length());
			}
			if (A.TipoAccion.equals("e")) {
				Salida.writeBytes(id.format(A.Identificador));
				Salida.writeByte(' ');
				Salida.writeBytes("ESCA");
				Salida.writeByte(' ');
				Salida.writeBytes(Escala.format(A.Escala));
				Salida.writeBytes("\r\n");
				Salida.seek(Salida.length());
			}
		}
		Salida.writeBytes("FIN ACCIONES DE OBJETO\r\n");
		Salida.seek(Salida.length());
	}

	protected TipoDevuelto MostrarAccionesObjetos(String Nombre) {
		int valorObjeto = 0;
		Objeto O = new Objeto();
		ListIterator<Objeto> Iterador = ListaObjetos.listIterator();
		TipoDevuelto Devolver = TipoDevuelto.Correcto;
		valorObjeto = EstaObjeto(Nombre);

		if (valorObjeto == -1) {
			Devolver = TipoDevuelto.ObjetoNoExiste;
		} else {
			while (valorObjeto >= 0) {
				O = Iterador.next();
				valorObjeto = valorObjeto - 1;
			}
			if (O.ListaAccionesAplicadas.isEmpty()) {
				Devolver = TipoDevuelto.NoExisteAccionParaAplicar;
			}
		}
		return Devolver;
	}
}
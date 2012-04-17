import java.util.*;

public class Lista_Salas {
//Salas primero = new Salas();
private boolean resultado;

//Nos declaramos la lista de la clase elementos(nombre,capacidad,precio).

private LinkedList <Salas> ListaSalas = new LinkedList <Salas> ();
public LinkedList <Salas> getListaSalas (){
	return ListaSalas;
}
public void SetListaSalas (LinkedList <Salas> ListaSalas){
	this.ListaSalas = ListaSalas;
}
	//Agrega una Sala de reuniones.
	protected boolean Agregar_Sala (String Nombre, int Capacidad, int Precio){
		Salas primero = new Salas();
        primero.setNombre(Nombre);
        primero.setCapacidad(Capacidad);
		primero.setPrecio(Precio);
        //int n=0;
        if (this.getListaSalas().size() == 0)
        {
            this.getListaSalas().add(primero);
        }
        else if (Comparar(Nombre)== false)
        {
            boolean sala_agregada = false;
            int n=0;
            while (!(sala_agregada))
            {
                Salas actual = this.getListaSalas().get(n);
                if (actual.getCapacidad() < Capacidad && n == ListaSalas.size()-1)//insertamos ultimo
                {
                    ListaSalas.add(primero);
                    sala_agregada = true;
                }
                else if (actual.getCapacidad() > Capacidad)//insertamos
                {

                        ListaSalas.add(n, primero);
                    sala_agregada = true;
                }
                else if (actual.getCapacidad() == Capacidad
                        && actual.getNombre().compareTo(Nombre) > 0)//insertamos
                {

                        ListaSalas.add(n, primero);

                    sala_agregada = true;
                }
                else if (actual.getCapacidad() == Capacidad
                        && actual.getNombre().compareTo(Nombre) < 0
                        && n == ListaSalas.size()-1)//insertamos
                {

                        ListaSalas.add(primero);

                    sala_agregada = true;
                }
                else
                {
                    sala_agregada = false;
                }
                n++;
            }
			resultado = true;
        }
		else
        {
			resultado = false;	
		}
        //System.out.println(n);
		return resultado;
	}
	
	//Compara el nombre con todos los nombres de la sala ya existentes.
	private boolean Comparar (String Nombre){
		Salas primero = new Salas();
		boolean resultado1 =false;
 		for (int i = 0; i <  this.getListaSalas().size() && !resultado1; i++){
 			primero = this.getListaSalas().get(i);
 			if (Nombre.compareToIgnoreCase(primero.getNombre())==0) {
 				resultado1 = true;
 			}
 			else{
 				resultado1 = false;
 			}
 		}
 		return resultado1;
 	}
	
	//Eliminar una Sala de reuniones
	protected boolean Eliminar_Sala (String Nombre){
		Salas primero = new Salas();
		for (int i = 0; i <  this.getListaSalas().size(); i++){
			primero = this.getListaSalas().get(i);
			if (primero.getNombre().compareToIgnoreCase(Nombre)==0){
				this.getListaSalas().remove(i);
				resultado = true;
			}
			else{
				resultado = false;
			}
		}
		return resultado;
	}
	
	//Dado el nombre de una sala te devuelve la capacidad de la misma
	protected int Capacidad (String Nombre){
		int Capacidad_Aux = 0;
		Salas primero = new Salas();
		for (int i = 0; (i <  this.getListaSalas().size()) && (Capacidad_Aux == 0); i++){
			primero = this.getListaSalas().get(i);
			if (primero.getNombre().compareToIgnoreCase(Nombre) == 0){
				Capacidad_Aux = primero.getCapacidad();
			}
			else{
				Capacidad_Aux = 0;
			}
	    }
	    return Capacidad_Aux;
	    
	}
	
	//Dado el nombre de una sala te devuelve el precio/hora de la misma
	protected int PrecioHora (String Nombre){
		int PrecioHora_Aux = 0;
		Salas primero = new Salas();
		for (int i = 0; i <  this.getListaSalas().size() && (PrecioHora_Aux == 0); i++){
			primero = this.getListaSalas().get(i);
			if (primero.getNombre().compareToIgnoreCase(Nombre) == 0){
				PrecioHora_Aux = primero.getPrecio();
			}
			else{
				PrecioHora_Aux = 0;
			}
		}
		return PrecioHora_Aux;
	}
	
	protected String Sala_Optima (int n, int Numero){ //n es el numero de veces que la sala ha falado en las comprobaciones postertiores
		Salas primero = new Salas();
		Salas Sala_Res = new Salas();
		Sala_Res.setCapacidad(99999);
        if (n >= this.getListaSalas().size())
        {
            return null;
        }
        else
        {
            for (int i = 0; i <  this.getListaSalas().size()-n; i++){
                primero = this.getListaSalas().get(i);
                if (Numero <= primero.getCapacidad()){
                    if (Sala_Res.getCapacidad() > primero.getCapacidad()) {
                        //Sala_Res = this.getListaSalas().get(i+n);
                        return this.getListaSalas().get(i+n).getNombre();
                    }
                }
            }
		}
		//return Sala_Res.getNombre();
        return null;//si no encuentra ninguno


//        if (Numero >10)
//        {
//            return null;
//        }
//        else
//        {
//        Salas primero = new Salas();
//        primero = this.getListaSalas().get(1);
//        return primero.getNombre();
//        }
	}
}	


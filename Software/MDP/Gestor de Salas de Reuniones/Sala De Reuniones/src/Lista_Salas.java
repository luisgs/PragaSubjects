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
		boolean resultado2 = false;
		int n = 0;
			if (Comparar(Nombre)== false){
				primero.setNombre(Nombre);
				primero.setCapacidad(Capacidad);
				primero.setPrecio(Precio);
				while (resultado2 == false){
					if (this.getListaSalas().size() == 0){
						this.getListaSalas().add(primero);
						resultado2 = true;
					}
					else{
						if (n == this.getListaSalas().size()){
							this.getListaSalas().add(n,primero);
							resultado2 = true;	
						}
						else{
							if(primero.getCapacidad() <= this.getListaSalas().get(n).getCapacidad()){
							this.getListaSalas().add(n,primero);
							resultado2 = true;	
							}
							else{
								resultado2 = false;
								n = n + 1;
							}
							
						}
					}
				}
			}
			else{
			resultado2 = false;	
			}
		
		return resultado2;
	}
	
	//Compara el nombre con todos los nombres de la sala ya existentes.
	private boolean Comparar (String Nombre){
		Salas primero = new Salas();
		boolean resultado1 =false;
 		for (int i = 0; i <  this.getListaSalas().size() && !resultado1; i++){
 			primero = this.getListaSalas().get(i);
 			if ((primero.getNombre() == Nombre)){
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
			if (primero.getNombre() == Nombre){
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
			if (primero.getNombre() == Nombre){
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
			if (primero.getNombre() == Nombre){
				PrecioHora_Aux = primero.getPrecio();
			}
			else{
				PrecioHora_Aux = 0;
			}
		}
		return PrecioHora_Aux;
	}
	
	protected String Sala_Optima (int Numero){
		Salas primero = new Salas();
		Salas Sala_Res = new Salas();
		Sala_Res.setCapacidad(99999);
		for (int i = 0; i <  this.getListaSalas().size(); i++){
			primero = this.getListaSalas().get(i);
			if (Numero <= primero.getCapacidad()){
			   if (Sala_Res.getCapacidad() > primero.getCapacidad()) {
				 Sala_Res = primero;  
			   }
			}		
		}
		return Sala_Res.getNombre();
	}
}	


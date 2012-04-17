import java.util.*;

public class Lista_Clientes {

	private boolean BienMal;
	
	//Nos declaramos la lista de la clase elementos(nombre,capacidad,precio).
	private LinkedList <Clientes> ListaClientes = new LinkedList <Clientes> ();
	
	public LinkedList <Clientes> getListaClientes (){
		return ListaClientes;
	}
	public void SetListaClientes (LinkedList <Clientes> ListaClientes){
		this.ListaClientes = ListaClientes;
	}
	

	//Agrega un Cliente a la Lista de Clientes.
	protected boolean Agregar_Clientes (String NIF, String Nombre, String Direccion){
		Clientes primero = new Clientes();
			if (Comparar(NIF)==false){
				primero.setNIF(NIF);
				primero.setNombre(Nombre);
				primero.setDireccion(Direccion);
				this.getListaClientes().add(primero);
				BienMal = true;
				
			}
			else{
				
				BienMal = false;
			}
		
		return BienMal;
	}

	//Compara el NIF con todos los demas NIF de la Lista de CLientes ya existentes.
	public boolean Comparar (String NIF){
		Clientes primero = new Clientes();
		boolean BienMal1 = false;
 		for (int i = 0; i <  this.getListaClientes().size() && !BienMal1; i++){
 			primero = this.getListaClientes().get(i);
 			if (primero.getNIF().compareTo(NIF)==0) {
 				BienMal1 = true;
 			}
 			else{
 				BienMal1 = false;
 			}
 		}
 		return BienMal1;
 	}
	
	//Elimina un Cliente de la Lista de Clientes.
	protected boolean Eliminar_Cliente (String NIF){
		Clientes primero = new Clientes();
		for (int i = 0; i <  this.getListaClientes().size(); i++){
			primero = this.getListaClientes().get(i);
			if (NIF.compareTo(primero.getNIF()) == 0){
				this.getListaClientes().remove(i);
				BienMal = true;
                break;
			}
			else{
				BienMal = false;
			}
		}
		return BienMal;
	}
	
	//Dado el NIF de un cliente te devuelve el Nombre correspondiente a ese NIF.
	protected String Nombre (String NIF){
		String Nombre_Aux = "";
		Clientes primero = new Clientes();
		for (int i = 0; i <  this.getListaClientes().size(); i++){
			primero = this.getListaClientes().get(i);
                        //System.out.println("NIF xa comparar1--------------"+NIF);
                        //System.out.println("NIF xa comparar2--------------"+primero.getNIF() );
			if (NIF.compareTo(primero.getNIF())==0){
                            //System.out.println("comparacion_way");
				Nombre_Aux = primero.getNombre();
                                return Nombre_Aux;
			}
			else
				Nombre_Aux = "";
	    }
	    return Nombre_Aux;
	}
	
	//Dado un NIF de un cliente te devuelve la direccion de facturacion de ese NIF.
	protected String Direccion (String NIF){
		Clientes primero = new Clientes();
		String Direccion_Aux = null;
        boolean encontrado = false;
        int i = 0;
		while (i <  this.getListaClientes().size() && !encontrado){
			primero = this.getListaClientes().get(i);
			if (NIF.compareTo(primero.getNIF()) == 0){
				Direccion_Aux = primero.getDireccion();
                encontrado = true;
			}
			else
            {
				Direccion_Aux = null;
                encontrado = false;
            }
            i++;
	    }
        
	    return Direccion_Aux;
	}
	
}

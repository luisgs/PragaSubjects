
import java.util.Calendar;
import java.util.LinkedList;



public class ListaReuniones {
	
private boolean resultado;
	
private LinkedList <Reuniones> Lista_Reuniones = new LinkedList <Reuniones> ();
public LinkedList <Reuniones> getLista_Reuniones (){
	return Lista_Reuniones;
}
public void SetLista_Reuniones (LinkedList <Reuniones> Lista_Reuniones){
	this.Lista_Reuniones = Lista_Reuniones;
}


	
	protected boolean agregarReunion (String Nombre, int numerodeasistentes, Calendar Fechafin, Calendar Fechainicio){
		Reuniones primero = new Reuniones();
		if (Comparar(Nombre) == false){
			primero.setNombre(Nombre);
			primero.setnumerodeasistentes(numerodeasistentes);
			primero.setFechafin(Fechafin);
			primero.setFechainicio(Fechainicio);
			this.getLista_Reuniones().add(primero);
			return true;
		}
		else{
			return false;
		}
		}
	
		private boolean Comparar (String Nombre){
			Reuniones primero = new Reuniones();
	 		for (int i = 0; i <  Lista_Reuniones.size(); i++){
	 			primero = Lista_Reuniones.get(i);
	 			if (primero.getNombre().compareTo(Nombre)==0) {
	 				resultado = true;
	 			}
	 			else{
	 				resultado = false;
	 			}
	 		}
	 		return resultado;
		}		
	
	
		protected boolean Eliminar_Reunion (String Nombre){
			Reuniones primero = new Reuniones();
			for (int i = 0; i <  Lista_Reuniones.size(); i++){
				primero = this.getLista_Reuniones().get(i);
				if (primero.getNombre() == Nombre){
					this.getLista_Reuniones().remove(i);
					resultado = true;
				}
				else{
					resultado = false;
				}
			}
			return resultado;
		}
		
		protected Calendar Fechainicio (String Nombre){
			Calendar Fechainicio_aux = null ;
			Reuniones primero = new Reuniones();
			for (int i = 0; (i <  this.getLista_Reuniones().size()) && (Fechainicio_aux == null); i++){
				primero = this.getLista_Reuniones().get(i);
				if (primero.getNombre() == Nombre){
				Fechainicio_aux = primero.getFechainicio();
				}
				else{
					Fechainicio_aux = null ;
				}
		    }
			
		    return Fechainicio_aux;
		    
		}
		
		
		
		
		protected int numerodeasistentes (String Nombre){
			int numerodeasistentes_Aux = 0;
			Reuniones primero = new Reuniones();
			for (int i = 0; (i <  this.getLista_Reuniones().size()) && (numerodeasistentes_Aux == 0); i++){
				primero = this.getLista_Reuniones().get(i);
				if (primero.getNombre() == Nombre){
					numerodeasistentes_Aux = primero.getnumerodeasistentes();
				}
				else{
					numerodeasistentes_Aux = 0;
				}
		    }
		    return numerodeasistentes_Aux;
		    
		}
		
		
		
		
		protected Calendar Fechafin (String Nombre){
			Calendar Fechafinal_aux =  null;
			Reuniones primero = new Reuniones();
			for (int i = 0; (i <  this.getLista_Reuniones().size()) && (Fechafinal_aux == null); i++){
				primero = this.getLista_Reuniones().get(i);
				if (primero.getNombre() == Nombre){
					Fechafinal_aux = primero.getFechafin();
				}
				else{
					Fechafinal_aux = null ;
				}
		    }
			
		    return Fechafinal_aux;
		    
		}
	
}
	

	

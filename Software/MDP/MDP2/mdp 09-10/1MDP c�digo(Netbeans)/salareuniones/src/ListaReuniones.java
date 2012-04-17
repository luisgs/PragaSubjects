

import java.util.*;



public class ListaReuniones {
	
private boolean resultado;
	
private LinkedList <Reuniones> Lista_Reuniones = new LinkedList <Reuniones> ();
public LinkedList <Reuniones> getLista_Reuniones (){
	return Lista_Reuniones;
}
public void SetLista_Reuniones (LinkedList <Reuniones> Lista_Reuniones){
	this.Lista_Reuniones = Lista_Reuniones;
}


	
	protected boolean agregarReunion (String Nombre, int numerodeasistentes, GregorianCalendar Fechainicio,GregorianCalendar Fechafin){
		Reuniones primero = new Reuniones();
		if (Comparar(Nombre) == false){
			primero.setNombre(Nombre);
			primero.setnumerodeasistentes(numerodeasistentes);
			primero.setFechainicio(Fechainicio);
			primero.setFechafin(Fechafin);
			this.getLista_Reuniones().add(primero);
			 resultado = true;
		}
		else{
			 resultado = false;
		}
		return resultado;
	
		}
	
		private boolean Comparar (String Nombre){
			Reuniones primero = new Reuniones();
			boolean resultado1 =false;
            int i = 0;
	 		while (i <  Lista_Reuniones.size() && !resultado1){
	 			primero = Lista_Reuniones.get(i);
	 			if (primero.getNombre().compareTo(Nombre)==0) {
	 				resultado1 = true;
	 			}
	 			else{
	 				resultado1 = false;
	 			}
                i++;
	 		}
	 		return resultado1;
		}		
	
	
		protected boolean Eliminar_Reunion (String Nombre){
			Reuniones primero = new Reuniones();
			for (int i = 0; i <  Lista_Reuniones.size(); i++){
				primero = this.getLista_Reuniones().get(i);
				if (primero.getNombre().compareTo(Nombre) == 0){
					this.getLista_Reuniones().remove(i);
					resultado = true;
				}
				else{
					resultado = false;
				}
			}
			return resultado;
		}
		
				
		protected int numerodeasistentes (String Nombre){
			int numerodeasistentes_Aux = 0;
			Reuniones primero = new Reuniones();
			
			for (int i = 0; (i <  this.getLista_Reuniones().size()) && (numerodeasistentes_Aux == 0); i++){
				primero = this.getLista_Reuniones().get(i);
				if (primero.getNombre().compareTo(Nombre) == 0){
					numerodeasistentes_Aux = primero.getnumerodeasistentes();
				}
				else{
					numerodeasistentes_Aux = 0;
				}
		    }
		    return numerodeasistentes_Aux;
		  
		}
		
		protected GregorianCalendar Fechainicio (String Nombre)
        {
			GregorianCalendar Fechainicio_aux = null;
			Reuniones primero = new Reuniones();
            boolean encontrado = false;
            int i = 0;
			while (i <  this.getLista_Reuniones().size() && !(encontrado))
            {
				primero = this.getLista_Reuniones().get(i);
                //System.out.println("fecha inicio comp---------------------------------------------------");
                //System.out.println(Nombre);
                //System.out.println(primero.getNombre());
                //System.out.println(primero.getNombre().compareTo(Nombre) == 0);
				if (primero.getNombre().compareTo(Nombre) == 0)
                {
					Fechainicio_aux = primero.getFechainicio();
                    encontrado = true;
				}
				else
                {
					Fechainicio_aux = null ;
                    encontrado = false;
				}
                i++;
		    }
            //System.out.println(Fechainicio_aux);
		    return Fechainicio_aux;
		    
		}
		
		
		
		protected GregorianCalendar Fechafin (String Nombre){
			GregorianCalendar Fechafin_aux = null;
            boolean encontrado = false;
			Reuniones primero = new Reuniones();
            int i = 0;
			while (i <  this.getLista_Reuniones().size() && !(encontrado)){
				primero = this.getLista_Reuniones().get(i);
				if (primero.getNombre().compareTo(Nombre) == 0){
					Fechafin_aux = primero.getFechafin();
                    encontrado = true;
				}
				else{
					Fechafin_aux = null ;
                    encontrado = false;
				}
                i++;
		    }
            //System.out.println(Fechafin_aux);
		    return Fechafin_aux;
		    
		}
	
}
	

	

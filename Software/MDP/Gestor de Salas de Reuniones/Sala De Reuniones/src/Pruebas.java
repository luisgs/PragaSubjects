//import java.util.LinkedList;


public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	////////////
	////SALAS///
	////////////
		
	System.out.println("///////////////////");
	System.out.println("///////SALAS///////");
	System.out.println("///////////////////");
	Salas Sala1 = new Salas();
	Salas Sala2 = new Salas();
	Salas Sala3 = new Salas();
	Sala1.setNombre("Doraimon");
	Sala1.setCapacidad(32);
	Sala1.setPrecio(12);
	Sala2.setNombre("Spiderman");
	Sala2.setCapacidad(23);
	Sala2.setPrecio(15);
	Sala3.setNombre("PipperAnn");
	Sala3.setCapacidad(11);
	Sala3.setPrecio(13);
	Lista_Salas L1 = new Lista_Salas();
	System.out.println("Agrego a Doraimon");
	L1.Agregar_Sala(Sala1.getNombre(), Sala1.getCapacidad(), Sala1.getPrecio());
	System.out.println("Agrego a Spiderman");
	L1.Agregar_Sala(Sala2.getNombre(), Sala2.getCapacidad(), Sala2.getPrecio());
	System.out.println("Agrego a PipperAnn");
	L1.Agregar_Sala(Sala3.getNombre(), Sala3.getCapacidad(), Sala3.getPrecio());
	System.out.println("Agrego a Doraimon");
	L1.Agregar_Sala(Sala1.getNombre(), Sala1.getCapacidad(), Sala1.getPrecio());
	System.out.println("El tamaño de la lista es = ");
	System.out.println(L1.getListaSalas().size());
	
	System.out.println("La lista es: ");
	
	for(int i = 0; i < L1.getListaSalas().size(); i++){
		System.out.println(L1.getListaSalas().get(i).getNombre());
		System.out.println(L1.getListaSalas().get(i).getCapacidad());
		System.out.println(L1.getListaSalas().get(i).getPrecio());
	}
	
	System.out.println("La capacidad de Spiderman es = ");
	L1.Capacidad(Sala2.getNombre());
	System.out.println("La capacidad de Doarimon es = ");
	L1.Capacidad(Sala1.getNombre());
	System.out.println("La capacidad de PipperAnn es = ");
	L1.Capacidad(Sala3.getNombre());
	System.out.println("El tama�o de la lista antes de eliminar es: ");
	System.out.println(L1.getListaSalas().size());
	System.out.println("Elimino de la lista la sala Spiderman");
	L1.Eliminar_Sala(Sala2.getNombre());
	System.out.println("El tama�o de la lista es: ");
	System.out.println(L1.getListaSalas().size());
	System.out.println("La lista queda despues del eliminado : ");
	
	for(int i = 0; i < L1.getListaSalas().size(); i++){
		System.out.println(L1.getListaSalas().get(i).getNombre());
		System.out.println(L1.getListaSalas().get(i).getCapacidad());
		System.out.println(L1.getListaSalas().get(i).getPrecio());
	}
	System.out.println("El precio de las salas que quedan es : ");
	L1.PrecioHora(Sala1.getNombre());
	L1.PrecioHora(Sala3.getNombre());
	System.out.println("La sala optima para 13 personas es : ");
	L1.Sala_Optima(13);
	System.out.println("La sala optima para 60 personas es : ");
	L1.Sala_Optima(60);
	
	////////////
	//CLIENTES//
	////////////
	
	
	System.out.println("////////////////////");
	System.out.println("//////CLIENTES//////");
	System.out.println("////////////////////");
	
	Clientes Cliente1 = new Clientes();
	Clientes Cliente2 = new Clientes();
	Clientes Cliente3 = new Clientes();
	Cliente1.setNIF("3245123s");
	Cliente1.setNombre("Salim");
	Cliente1.setDireccion("Pinto");
	Cliente2.setNIF("4658492c");
	Cliente2.setNombre("Gonzalo");
	Cliente2.setDireccion("Mirasierra");
	Cliente3.setNIF("87659283f");
	Cliente3.setNombre("Busta");
	Cliente3.setDireccion("Boadilla");
	Lista_Clientes L2 = new Lista_Clientes();
	System.out.println("Agrego a Salim");
	L2.Agregar_Clientes(Cliente1.getNIF(), Cliente1.getNombre(),Cliente1.getDireccion());
	System.out.println("Agrego a Gonzalo");
	L2.Agregar_Clientes(Cliente2.getNIF(), Cliente2.getNombre(),Cliente2.getDireccion());
	System.out.println("Agrego a Busta");
	L2.Agregar_Clientes(Cliente3.getNIF(), Cliente3.getNombre(),Cliente3.getDireccion());
	System.out.println("Agrego a Salim");
	L2.Agregar_Clientes(Cliente1.getNIF(), Cliente1.getNombre(),Cliente1.getDireccion());
	System.out.println("El tamaño de la lista es = ");
	System.out.println(L2.getListaClientes().size());
	System.out.println("La lista es: ");
	for(int i = 0; i < L2.getListaClientes().size(); i++){
		System.out.println(L2.getListaClientes().get(i).getNIF());
		System.out.println(L2.getListaClientes().get(i).getNombre());
		System.out.println(L2.getListaClientes().get(i).getDireccion());
	}
	System.out.println("Elimino a Gonzalo(NIF:4658492c)");
	L2.Eliminar_Cliente("4658492c");
	System.out.println("El tamaño de la lista es = ");
	System.out.println(L2.getListaClientes().size());
	System.out.println("La lista queda despues del eliminado : ");
	
	for(int i = 0; i < L2.getListaClientes().size(); i++){
		System.out.println(L2.getListaClientes().get(i).getNIF());
		System.out.println(L2.getListaClientes().get(i).getNombre());
		System.out.println(L2.getListaClientes().get(i).getDireccion());
	}
	System.out.println("El nombre de la persona con NIF-87659283f es: ");
	L2.Nombre("87659283f");
	System.out.println("La direccion de facturacion de la persona con NIF-87659283f es: ");
	L2.Direccion("87659283f");
	System.out.println("La direccion de facturacion de la persona con NIF-85928f es: ");
	L2.Direccion("85928f");
	}
}



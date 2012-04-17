
public class Clientes {
	
	protected String NIF;
	protected String Nombre;
	protected String Direccion;
	
	//public Clientes (){
	//NIF = "000000";
	//Nombre = "Vacio";
	//Direccion = "Ninguna";
	//}
	public void setNIF(String nIF) {
		NIF = nIF;
	}
	public String getNIF() {
		return NIF;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getDireccion() {
		return Direccion;
	}
	public static int length() {
		// TODO Auto-generated method stub
		return 0;
	}

}

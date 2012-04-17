



import java.util.GregorianCalendar;


public class Reuniones {
	
	GregorianCalendar Fechainicio = new GregorianCalendar();
	GregorianCalendar Fechafin = new GregorianCalendar();	
	protected String Nombre;
	protected int numerodeasistentes;
	//protected SimpleDateFormat getFechainicio1;
	
	
	public Reuniones (){
		Nombre = "";
		numerodeasistentes = 0;
		
	}
	
	
	
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setFechainicio(GregorianCalendar fechainicio) {
		this.Fechainicio = fechainicio;
	}
	
	public void setFechafin(GregorianCalendar fechafin) {
		this.Fechafin = fechafin;
	}
	
	
	
	public void setnumerodeasistentes(int Numerodeasistentes) {
		this.numerodeasistentes = Numerodeasistentes;
	}
	public int getnumerodeasistentes() {
		return numerodeasistentes;
	}
	

	public GregorianCalendar getFechafin() {
		return Fechafin;
		
	}

	public GregorianCalendar getFechainicio() {
		return Fechainicio;
		
	}
	
	
	
}

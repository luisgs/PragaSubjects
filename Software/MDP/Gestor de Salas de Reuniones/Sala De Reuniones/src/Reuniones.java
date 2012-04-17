


import java.util.Calendar;
import java.util.GregorianCalendar;


public class Reuniones {
	Calendar Fechainicio = new GregorianCalendar();
	Calendar Fechafin = new GregorianCalendar();	
	protected String Nombre;
	protected int numerodeasistentes;
	//protected SimpleDateFormat getFechainicio1;
	
	public Reuniones (){
		Nombre = "";
		numerodeasistentes = 0;
		Fechafin = null;
		Fechainicio = null;
	}
	
	
	
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setFechainicio(Calendar fechainicio) {
		this.Fechainicio = fechainicio;
	}
	public Calendar getFechainicio() {
		return Fechainicio;
	}
	public void setFechafin(Calendar fechafin) {
		this.Fechafin = fechafin;
	}
	public Calendar getFechafin() {
		return Fechafin;
	}
	public void setnumerodeasistentes(int Numerodeasistentes) {
		this.numerodeasistentes = Numerodeasistentes;
	}
	public int getnumerodeasistentes() {
		return numerodeasistentes;
	}
	public static int length() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}

import java.util.*;

//------------------------> Xa mostrar Reuniones
//En la lista almacenamos de 0 a size()-1
public class ReunionesDia
{
    ArrayList<ReunionDia> ReusDia;

    private class ReunionDia
    {
        private String NomReunion;
        private String NomCliente;
        private String NomSala;
        private GregorianCalendar HoraI;
        private GregorianCalendar HoraF;
        private int Precio;

        public ReunionDia () {};
        public ReunionDia (String NomR, String NomC, String NomS, GregorianCalendar FI,
                           GregorianCalendar FF)
        {
            NomReunion = NomR;
            NomCliente = NomC;
            NomSala = NomS;
            HoraI = FI;
            HoraF = FF;
        }
    }

    public ReunionesDia ()
    {
        ReusDia = new ArrayList<ReunionDia>();
    }

    public String Dar_NomReunion (int n)
    {
        return ReusDia.get(n).NomReunion;
    }

    public String Dar_NomCliente (int n)
    {
        return ReusDia.get(n).NomCliente;
    }

    public String Dar_NomSala (int n)
    {
        return ReusDia.get(n).NomSala;
    }

    public GregorianCalendar Dar_HoraInicio (int n)
    {
        return ReusDia.get(n).HoraI;
    }

    public GregorianCalendar Dar_HoraFin (int n)
    {
        return ReusDia.get(n).HoraF;
    }

    public int Dar_Long ()//para que se use como limite de la lista
    {
        return ReusDia.size();
    }

    public void AñadirReunionDia (String R, String C, String S,
                                  GregorianCalendar HI,
                                  GregorianCalendar HF)
    {
        ReunionDia ReuDia = new ReunionDia(R,C,S,HI,HF);
        ReusDia.add(ReuDia);
    }

    public void AñadirReunionDiaOrdenado (String R, String C, String S,
                                  GregorianCalendar HI,
                                  GregorianCalendar HF)
    {
        boolean agregado = false;
        if (ReusDia.isEmpty())
        {
            ReunionDia ReuDia = new ReunionDia(R,C,S,HI,HF);
            ReusDia.add(ReuDia);
        }
        else
        {
            //System.out.println("lista reu dia");
            int n = 0;//-----------a lo mejor es = 0
            ReunionDia ReuDia = new ReunionDia(R,C,S,HI,HF);
            //Mientras el nombre del cliente sea menor alfabeticamente y no
            //hallamos alcanzado el final de la lista ReusDia incrementamos n
            while (n < ReusDia.size() && !agregado)
            {
                System.out.println("while reusdia");
                //si hemos alcanzado el final de la lista pero el ultimo elemento
                //sigue siendo menor, añadimos al final
                if (n == ReusDia.size()-1 && (R.compareTo(ReusDia.get(n).NomReunion) > 0))
                {
                    ReusDia.add(ReuDia);
                    agregado = true;
                }
                //El cliente coincide o es mayor y, o la reunion es mayor o igual,
                //o los clientes no coinciden añadimos en la posición actual.
                //La reunion dato coincide o es mayor que la lista
                //
                else if (R.compareTo(ReusDia.get(n).NomReunion) <= 0)
                {
                    ReusDia.add(n,ReuDia);
                    agregado = true;
                }
                //Si no, seguimos recorriendo la lista
                else
                {
                    agregado = false;
                }
                n++;
            }
        }
    }
}

import java.io.*;
import java.util.*;
import java.text.*;

public class Fichero {
    /*Clase principal
     * Se encuentra todo lo relativo al tratamiento de fichero
     * 
     */
	private static Objetos objetos = new Objetos();
    // PRE:Cierto
	// POST:Comprueba que una linea que contenga agregar un cambio de escala este formato correcto
	private static int lineaEscala(String Linea) {
		int Resultado = 0;
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		DecimalFormat FEnt = new DecimalFormat("00.00", dfs);
		String Nombre = "";
		StringTokenizer stTexto = new StringTokenizer(Linea);

		if (stTexto.hasMoreTokens()) {

			Nombre = stTexto.nextToken();
			if (isNumberFloat(Nombre)) {
				
				float num = Float.valueOf(Nombre);
				String Trasformado = FEnt.format(num);

				if (!Trasformado.equals(Nombre)) {

					Resultado = 1;
				}
			}
			else {
				Resultado=1;
			}
		} else {
			Resultado = 1;
		}
		return Resultado;
	}
    // PRE:Cierto
	// POST:Comprueba que una linea que contenga agregar un giro este formato correcto
	private static int lineaGiro(String Linea) {

		int Resultado = 0;
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		DecimalFormat FEnt = new DecimalFormat("000", dfs);
		String Nombre = "";
		StringTokenizer stTexto = new StringTokenizer(Linea);
		LinkedList<String> PilaTok = new LinkedList<String>();
		int cnt = 0;
		while (stTexto.hasMoreTokens()) {
			cnt = cnt + 1;
			Nombre = stTexto.nextToken();

			PilaTok.addFirst(Nombre);
		}
		if (cnt != 2) {
			Resultado = 1;
		} else {

			String Trasformado = "";
			int num = 0;
			String Angulo = PilaTok.getFirst();
			PilaTok.removeFirst();
			String EjeGiro = PilaTok.getFirst();
			if (isNumberInteger(Angulo)) {

				num = Integer.valueOf(Angulo);
				Trasformado = FEnt.format(num);

				if (!Trasformado.equals(Angulo)) {
					Resultado = 1;
				} else {
					if (num < 0 || num > 359) {
						Resultado = 1;
					} else {
						if (!(EjeGiro.equals("x") || EjeGiro.equals("y") || EjeGiro
								.equals("z"))) {
							Resultado = 1;
						}
					}
				}
			} else {
				Resultado = 1;
			}
		}
		return Resultado;
	}
	// PRE:Cierto
	// PSOT: Comprueba que una linea que contenga agregar una traslacion este formato correcto
	private static int lineaTraslacion(String Linea) {
		int Resultado = 0;
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		DecimalFormat FEnt = new DecimalFormat("+000.00;-000.00", dfs);
		String Nombre = "";
		StringTokenizer stTexto = new StringTokenizer(Linea);
		LinkedList<String> PilaTok = new LinkedList<String>();
		int cnt = 0;
		while (stTexto.hasMoreTokens()) {
			cnt = cnt + 1;
			Nombre = stTexto.nextToken();

			PilaTok.addFirst(Nombre);
		}
		if (cnt != 3) {
			Resultado = 1;
		} else {
			boolean Correcto = true;
			String Trasformado = "";
			Float num;
			while (Correcto && cnt != 0) {
				String Dato = PilaTok.getFirst();
				cnt = cnt - 1;
				PilaTok.removeFirst();
				if (!isNumberFloat(Dato)) {
					Correcto = false;
				} else {
					num = Float.valueOf(Dato);
					Trasformado = FEnt.format(num);
					if (!Trasformado.equals(Dato)) {
						Correcto = false;
					}
				}
			}
			if (Correcto == false) {
				Resultado = 1;
			}
		}
		return Resultado;
	}

	// PRE:cierto
	// POSt:Devuelve cierto si 'num' es un numero real
	private static boolean isNumberFloat(String num) {
		try {
			Float.parseFloat(num);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	// PRE:cierto
	// POSt:Devuelve cierto si 'num' es un numero entero.
	private static boolean isNumberInteger(String num) {
		try {
			Integer.parseInt(num);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	//PRE:Cierto
	//POST: Comprueba que 'Color' sea un numero Hexadecimal
	private static int ComprobarNumeroHex(String Color) {
		int Resultado = 1;
		if (Color.length() < 2) {
			Resultado = 1;
		} else {
			String Color1 = Color.substring(0, 1);
			String Color2 = Color.substring(1, 2);
			if ((Color1.equals("0") || Color1.equals("1") || Color1.equals("2")
					|| Color1.equals("3") || Color1.equals("4")
					|| Color1.equals("5") || Color1.equals("6")
					|| Color1.equals("7") || Color1.equals("8")
					|| Color1.equals("9") || Color1.equals("A")
					|| Color1.equals("B") || Color1.equals("C")
					|| Color1.equals("D") || Color1.equals("E") || Color1
					.equals("F"))) {
				if ((Color2.equals("0") || Color2.equals("1")
						|| Color2.equals("2") || Color2.equals("3")
						|| Color2.equals("4") || Color2.equals("5")
						|| Color2.equals("6") || Color2.equals("7")
						|| Color2.equals("8") || Color2.equals("9")
						|| Color2.equals("A") || Color2.equals("B")
						|| Color2.equals("C") || Color2.equals("D")
						|| Color2.equals("E") || Color2.equals("F"))) {
					Resultado = 0;
				}
			}
		}
		return Resultado;
	}
    // PRE:Cierto
	// POST:Comprueba que una linea con el comando AOB este en formato correcto,
	private static int ComprobarLinea_AOB(String Linea) {
		int Resultado = 0;
		if (Linea.length() <= 4) {
			Resultado = 1;
		} else {
			String Blanco = Linea.substring(3, 4);
			if (Blanco.equals(" ")) {
				String SinComando2 = Linea.substring(4);
				String Nombre = "";
				StringTokenizer stTexto = new StringTokenizer(SinComando2);
				LinkedList<String> PilaTok = new LinkedList<String>();
				int cnt = 0;
				while (stTexto.hasMoreTokens()) {
					cnt = cnt + 1;
					Nombre = stTexto.nextToken();
					PilaTok.addFirst(Nombre);
				}
				if (cnt != 2) {
					Resultado = 1;
				} else {
					String Color = PilaTok.getFirst();
					PilaTok.removeFirst();
					if (Color.length() != 6) {
						Resultado = 1;
					} else {
						String Color1 = Color.substring(0, 2);
						String Color2 = Color.substring(2, 4);
						String Color3 = Color.substring(4, 6);
						int C1 = 0;
						int C2 = 0;
						int C3 = 0;
						if (ComprobarNumeroHex(Color1) == 1
								|| ComprobarNumeroHex(Color2) == 1
								|| ComprobarNumeroHex(Color3) == 1) {
							Resultado = 1;
						} else {
							C1 = Integer.parseInt(Color1, 16);
							C2 = Integer.parseInt(Color2, 16);
							C3 = Integer.parseInt(Color3, 16);
							if (C1 < 0 || C1 > 255 || C2 < 0 || C2 > 255
									|| C3 < 0 || C3 > 255) {
								Resultado = 1;
							} else {
								String Nombre2 = PilaTok.getFirst();
								int ComillasIniciales = (int) Nombre2.charAt(0);
								int ComillasFinales = (int) Nombre2
										.charAt(Nombre2.length() - 1);
								if (ComillasIniciales != 34
										|| ComillasFinales != 34
										|| Nombre2.length() >= 23
										|| Nombre2.length() <= 2) {
									Resultado = 1;
								} else {
									String Espacios = SinComando2
											.substring(Nombre2.length() + 7);
									if (Espacios.length() != 0) {
										Resultado = 1;
									}
								}
							}
						}
					}
				}
			} else {
				Resultado = 1;
			}
		}
		return Resultado;
	}

	private static int ComprobarLinea_Varios(String Linea) {
		int Resultado = 0;
		if (Linea.length() <= 4) {
			Resultado = 1;
		} else {
			String Blanco = Linea.substring(3, 4);
			if (Blanco.equals(" ")) {
				String SinComando2 = Linea.substring(4);
				String Nombre = "";
				StringTokenizer stTexto = new StringTokenizer(SinComando2);
				if (stTexto.hasMoreTokens()) {
					Nombre = stTexto.nextToken();
					int ComillasIniciales = (int) Nombre.charAt(0);
					int ComillasFinales = (int) Nombre
							.charAt(Nombre.length() - 1);
					if (ComillasIniciales != 34 || ComillasFinales != 34
							|| Nombre.length() >= 23 || Nombre.length() <= 2) {
						Resultado = 1;
					} else {
						String Espacios = SinComando2
								.substring(Nombre.length());
						if (Espacios.length() != 0) {
							Resultado = 1;
						}
					}
				} else {
					Resultado = 1;
				}
			} else {
				Resultado = 1;
			}
		}
		return Resultado;
	}

	 // PRE:Cierto
	// POST:Comprueba que una linea con el comando ATR este en formato correcto,
	private static int ComprobarLinea_ATR(String Linea) {
		int Resultado = 0;
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		DecimalFormat FEnt = new DecimalFormat("+000.00;-000.00", dfs);
		if (Linea.length() <= 4) {
			Resultado = 1;
		} else {
			String Blanco = Linea.substring(3, 4);
			if (Blanco.equals(" ")) {
				String SinComando2 = Linea.substring(4);
				String Nombre = "";
				StringTokenizer stTexto = new StringTokenizer(SinComando2);
				LinkedList<String> PilaTok = new LinkedList<String>();
				int cnt = 0;
				while (stTexto.hasMoreTokens()) {
					cnt = cnt + 1;
					Nombre = stTexto.nextToken();
					PilaTok.addFirst(Nombre);
				}
				if (cnt != 10) {
					Resultado = 1;
				} else {
					String N = PilaTok.getFirst();
					PilaTok.removeFirst();
					cnt = cnt - 1;
					int ComillasIniciales = (int) N.charAt(0);
					int ComillasFinales = (int) N.charAt(N.length() - 1);
					if (ComillasIniciales != 34 || ComillasFinales != 34
							|| Nombre.length() >= 23 || Nombre.length() <= 2) {
						Resultado = 1;
					} else {
						int lonNombre = N.length();
						boolean Correcto = true;
						String Trasformado = "";
						Float num;
						while (Correcto && cnt != 0) {
							String Dato = PilaTok.getFirst();
							cnt = cnt - 1;
							PilaTok.removeFirst();
							if (!isNumberFloat(Dato)) {
								Correcto = false;
							} else {
								num = Float.valueOf(Dato);
								Trasformado = FEnt.format(num);
								if (!Trasformado.equals(Dato)) {
									Correcto = false;
								}
							}
						}
						if (Correcto == false) {
							Resultado = 1;
						} else {
							String Espacios = SinComando2
									.substring(73 + lonNombre - 1);
							if (Espacios.length() != 0) {
								Resultado = 1;
							}
						}
					}
				}
			} else {
				Resultado = 1;
			}
		}

		return Resultado;
	}
	 // PRE:Cierto
	// POST:Comprueba que una linea con el comando ACC este en formato correcto,
	private static int ComprobarLinea_AAC(String Linea) {
		int Resultado = 0;
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		DecimalFormat FIa = new DecimalFormat("0000", dfs);
		if (Linea.length() <= 4) {
			Resultado = 1;
		} else {
			String Blanco = Linea.substring(3, 4);
			if (Blanco.equals(" ")) {
				String SinComando2 = Linea.substring(4);
				String Nombre = "";
				StringTokenizer stTexto = new StringTokenizer(SinComando2);
				LinkedList<String> PilaTok = new LinkedList<String>();
				int cnt = 0;
				while (stTexto.hasMoreTokens()) {
					cnt = cnt + 1;
					Nombre = stTexto.nextToken();
					PilaTok.addLast(Nombre);
				}
				if (cnt < 3) {
					Resultado = 1;
				} else {
					String Dato = PilaTok.getFirst();
					PilaTok.removeFirst();
					cnt = cnt - 1;
					int num = 0;
					String Trasformado = "";
					if (isNumberInteger(Dato)) {
						num = Integer.valueOf(Dato);
						Trasformado = FIa.format(num);
						if (!Dato.equals(Trasformado)) {
							Resultado = 1;
						} else {
							int x1 = Trasformado.length();
							int x2 = Trasformado.length() + 1;
							String Blanco2 = SinComando2.substring(x1, x2);
							String Blanco3 = SinComando2.substring(x1 + 2,
									x2 + 2);

							if (Blanco2.equals(" ")) {
								String TipoTras = PilaTok.getFirst();
								PilaTok.removeFirst();
								cnt = cnt - 1;
								String LineaAux = SinComando2.substring(x2 + 2);

								if (!(TipoTras.equals("t")
										|| TipoTras.equals("g") || TipoTras
										.equals("e"))) {
									Resultado = 1;
								} else {

									if (TipoTras.equals("t")) {
										if (Blanco3.equals(" ")) {
											if (lineaTraslacion(LineaAux) == 1) {
												Resultado = 2;
											} else {
												String Espacios = SinComando2
														.substring(30);
												if (Espacios.length() != 0) {
													Resultado = 1;
												}
											}
										} else {
											Resultado = 2;
										}
									}
									if (TipoTras.equals("g")) {
										if (Blanco3.equals(" ")) {
											if (lineaGiro(LineaAux) == 1) {
												Resultado = 3;
											} else {
												String Espacios = SinComando2
														.substring(12);
												if (Espacios.length() != 0) {
													Resultado = 1;
												}
											}
										} else {
											Resultado = 3;
										}
									}
									if (TipoTras.equals("e")) {
										if (Blanco3.equals(" ")) {
											if (lineaEscala(LineaAux) == 1) {
												Resultado = 4;
											} else {
												String Espacios = SinComando2
														.substring(12);
												if (Espacios.length() != 0) {
													Resultado = 1;
												}
											}
										} else {
											Resultado = 4;
										}
									}
								}
							} else {
								Resultado = 1;
							}
						}
					} else {
						Resultado = 1;
					}
				}
			} else {
				Resultado = 1;
			}
		}
		return Resultado;

	}
	 // PRE:Cierto
	// POST:Comprueba que una linea con el comando APA este en formato correcto,
	private static int ComprobarLinea_APA(String Linea) {
		int Resultado = 0;
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		DecimalFormat FIa = new DecimalFormat("0000", dfs);
		if (Linea.length() <= 4) {
			Resultado = 1;
		} else {
			String Blanco = Linea.substring(3, 4);
			if (Blanco.equals(" ")) {
				String SinComando2 = Linea.substring(4);
				String Nombre = "";
				StringTokenizer stTexto = new StringTokenizer(SinComando2);
				LinkedList<String> PilaTok = new LinkedList<String>();
				int cnt = 0;
				while (stTexto.hasMoreTokens()) {
					cnt = cnt + 1;
					Nombre = stTexto.nextToken();
					PilaTok.addFirst(Nombre);
				}
				if (cnt != 2) {
					Resultado = 1;
				} else {
					String Identificador = PilaTok.getFirst();
					PilaTok.removeFirst();
					String Trasformado = "";
					int num = 0;
					if (isNumberInteger(Identificador)) {
						num = Integer.valueOf(Identificador);
						Trasformado = FIa.format(num);
						if (!Identificador.equals(Trasformado)) {
							Resultado = 1;
						} else {
							String Nombre2 = PilaTok.getFirst();
							int ComillasIniciales = (int) Nombre2.charAt(0);
							int ComillasFinales = (int) Nombre2.charAt(Nombre2
									.length() - 1);
							if (ComillasIniciales != 34
									|| ComillasFinales != 34
									|| Nombre2.length() >= 23
									|| Nombre2.length() <= 2) {
								Resultado = 1;
							} else {
								String Espacios = SinComando2.substring(Nombre2
										.length() + 5);
								if (Espacios.length() != 0) {
									Resultado = 1;
								}
							}
						}
					} else {
						Resultado = 1;
					}
				}
			} else {
				Resultado = 1;
			}
		}
		return Resultado;
	}
	 // PRE:Cierto
	// POST:Comprueba que una linea con el comando EAC este en formato correcto,
	private static int ComprobarLinea_EAC(String Linea) {
		int Resultado = 0;
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		DecimalFormat FIa = new DecimalFormat("0000", dfs);
		if (Linea.length() <= 4) {
			Resultado = 1;
		} else {
			String Blanco = Linea.substring(3, 4);
			if (Blanco.equals(" ")) {
				String SinComando2 = Linea.substring(4);
				String Identificador = "";
				int num = 0;
				String Trasformado = "";
				StringTokenizer stTexto = new StringTokenizer(SinComando2);
				if (stTexto.hasMoreTokens()) {
					Identificador = stTexto.nextToken();
					if (isNumberInteger(Identificador)) {
						num = Integer.valueOf(Identificador);
						Trasformado = FIa.format(num);
						if (!Trasformado.equals(Identificador)) {
							Resultado = 1;
						} else {
							String Espacios = SinComando2
									.substring(Identificador.length());
							if (Espacios.length() != 0) {
								Resultado = 1;
							}
						}
					} else {
						Resultado = 1;
					}
				} else {
					Resultado = 1;
				}
			} else {
				Resultado = 1;
			}
		}
		return Resultado;
	}
	 // PRE:Cierto
	// POST:Comprueba que una linea esta en formato correcto, comprobando tambien que
	//      el comando sea valido
	private static String ComprobarLinea(String Linea) {
		String Resultado = "CORRECTO";

		int Valor = 0;

		if (Linea.length() < 3) {
			Resultado = "ERROR: FORMATO DE LINEA;FORMATO=COMANDO <Argumentos>;COMANDO=AOB,EOB,ATR,ACC,APA,DAC,EAC,MDO,MAO,CSU";
		} else {
			String Comando = Linea.substring(0, 3);

			if (!(Comando.equals("AOB") || Comando.equals("EOB")
					|| Comando.equals("ATR") || Comando.equals("AAC")
					|| Comando.equals("APA") || Comando.equals("DAC")
					|| Comando.equals("EAC") || Comando.equals("MDO")
					|| Comando.equals("MAO") || Comando.equals("CSU"))) {
				Resultado = "ERROR: FORMATO DE LINEA;FORMATO=COMANDO <Argumentos>;COMANDO=AOB,EOB,ATR,ACC,APA,DAC,EAC,MDO,MAO,CSU";
			} else {

				if (Comando.equals("AOB")) {
					Valor = ComprobarLinea_AOB(Linea);
					if (Valor == 1) {
						Resultado = "ERROR: FORMATO DE LINEA;FORMATO=AOB <no> <co>;<no>=nombre objeto(entre comillas dobles,1<=longitud<=20),<co>=6 digitos Hexadecimales(RRGGBB)";
					}
				}
				if (Comando.equals("EOB")) {
					Valor = ComprobarLinea_Varios(Linea);
					if (Valor == 1) {
						Resultado = "ERROR: FORMATO DE LINEA;FORMATO=EOB <no>;<no>=nombre objeto(entre comillas dobles,1<=longitud<=20)";
					}
				}
				if (Comando.equals("ATR")) {
					Valor = ComprobarLinea_ATR(Linea);
					if (Valor == 1) {
						Resultado = "ERROR: FORMATO DE LINEA;FORMATO=ATR <x1> <y1> <z1> <x2> <y2> <z2> <x3> <y3> <z3> <no>;<no>=nombre objeto(entre comillas dobles,1<=longitud<=20),<x> <y> <z>=Coordenada Tridimensional(sddd.dd); s=Signo,d=Digito";
					}
				}
				if (Comando.equals("AAC")) {
					Valor = ComprobarLinea_AAC(Linea);
					if (Valor == 1) {
						Resultado = "ERROR: FORMATO DE LINEA;(t=TRASLACION,g=GIRO,e=CAMBIO ESCALA);FORMATO=ACC <ia> t <dx> <dy> <dz> | ACC <ia> g <eg> <u> |ACC <ia> e <ve> ;<ia>=Identificador Accion(dddd), <dx> <dy> <dz>=Desplazamiento eje(sddd.dd),<eg>=Eje de giro(x,y,z),<u>=Angulo Giro(0<u<365),<ve>=Valor Escala(dd.dd);s=Signo,d=Digito";
					}
					if (Valor == 2) {
						Resultado = "ERROR: FORMATO DE LINEA;FORMATO=ACC <ia> t <dx> <dy> <dz>;<ia>=Identificador Accion(dddd), <dx> <dy> <dz>=Desplazamiento eje(sddd.dd);s=Signo,d=Digito";
					}
					if (Valor == 3) {
						Resultado = "ERROR: FORMATO DE LINEA;FORMATO=ACC <ia> g <eg> <u>;<ia>=Identificador Accion(dddd),<eg>=Eje de giro(x,y,z),<u>=Angulo Giro(0<u<365);d=Digito";
					}
					if (Valor == 4) {
						Resultado = "ERROR: FORMATO DE LINEA;FORMATO=ACC <ia> e <ve>;<ve>=Valor Escala(dd.dd);d=Digito";
					}
				}
				if (Comando.equals("APA")) {
					Valor = ComprobarLinea_APA(Linea);
					if (Valor == 1) {
						Resultado = "ERROR: FORMATO DE LINEA;FORMATO=APA <no> <ia>;<no>=nombre objeto(entre comillas dobles,1<=longitud<=20),<ia>=Identificador Accion(dddd);d=Digito";
					}
				}
				if (Comando.equals("DAC")) {
					Valor = ComprobarLinea_Varios(Linea);
					if (Valor == 1) {
						Resultado = "ERROR: FORMATO DE LINEA;FORMATO=DAC <no>;<no>=nombre objeto(entre comillas dobles,1<=longitud<=20)";
					}
				}
				if (Comando.equals("EAC")) {
					Valor = ComprobarLinea_EAC(Linea);
					if (Valor == 1) {
						Resultado = "ERROR: FORMATO DE LINEA;FORMATO=EAC <ia>;<ia>=Identificador Accion(dddd);d=Digito";
					}
				}
				if (Comando.equals("MDO")) {
					Valor = ComprobarLinea_Varios(Linea);
					if (Valor == 1) {
						Resultado = "ERROR: FORMATO DE LINEA;FORMATO=MDO <no>,<no>=nombre objeto(entre comillas dobles,1<=longitud<=20)";
					}
				}
				if (Comando.equals("MAO")) {
					Valor = ComprobarLinea_Varios(Linea);
					if (Valor == 1) {
						Resultado = "ERROR: FORMATO DE LINEA;FORMATO=MAO <no>,<no>=nombre objeto(entre comillas dobles,1<=longitud<=20)";
					}
				}
				if (Comando.equals("CSU")) {
					Valor = ComprobarLinea_Varios(Linea);
					if (Valor == 1) {
						Resultado = "ERROR: FORMATO DE LINEA;FORMATO=CSU <no>,<no>=nombre objeto(entre comillas dobles,1<=longitud<=20)";
					}
				}
			}
		}
		return Resultado;
	}
    //PRE:Cierto
	//POST: Realiza el tratamiento completo de una linea
	private static void AnalizadorLinea(RandomAccessFile Entrada,
			RandomAccessFile Salida, String Linea) throws IOException {

		if (Linea.length() == 0) {
			Salida.writeBytes("ERROR: LINEA VACIA");
			Salida.writeBytes("\r\n");
			Salida.seek(Salida.length());
		} else {
			String Comprobar = ComprobarLinea(Linea);
			if (!Comprobar.equals("CORRECTO")) {
				Salida.writeBytes(Comprobar);
				Salida.writeBytes("\r\n");
				Salida.seek(Salida.length());
			} else {
				Enum<Objetos.TipoDevuelto> Resultado;
				String Comando = Linea.substring(0, 3);
				String SinComando = Linea.substring(4);
				StringTokenizer stTexto = new StringTokenizer(SinComando);

				if (Comando.equals("AOB")) {
					String Nombre = stTexto.nextToken();
					String ColorAux = stTexto.nextToken();
					int Color = Integer.parseInt(ColorAux, 16);
					Resultado = objetos.AgregarObjeto(Nombre, Color);
					if (Resultado == Objetos.TipoDevuelto.Correcto) {
						Salida.writeBytes("Creado objeto: ");
						Salida.writeBytes(Nombre);
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					} else {
						Salida.writeBytes("ERROR: OBJETO(");
						Salida.writeBytes(Nombre);
						Salida.writeBytes(") YA EXISTE");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
				}

				if (Comando.equals("EOB")) {
					String Nombre = stTexto.nextToken();
					Resultado = objetos.EliminarObjeto(Nombre);
					if (Resultado == Objetos.TipoDevuelto.Correcto) {
						Salida.writeBytes("Eliminado objeto: ");
						Salida.writeBytes(Nombre);
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					} else {
						Salida.writeBytes("ERROR: OBJETO(");
						Salida.writeBytes(Nombre);
						Salida.writeBytes(") NO EXISTE");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
				}
				if (Comando.equals("ATR")) {
					String x1 = stTexto.nextToken();
					float x11 = Float.parseFloat(x1);
					String y1 = stTexto.nextToken();
					float y11 = Float.parseFloat(y1);
					String z1 = stTexto.nextToken();
					float z11 = Float.parseFloat(z1);
					String x2 = stTexto.nextToken();
					float x22 = Float.parseFloat(x2);
					String y2 = stTexto.nextToken();
					float y22 = Float.parseFloat(y2);
					String z2 = stTexto.nextToken();
					float z22 = Float.parseFloat(z2);
					String x3 = stTexto.nextToken();
					float x33 = Float.parseFloat(x3);
					String y3 = stTexto.nextToken();
					float y33 = Float.parseFloat(y3);
					String z3 = stTexto.nextToken();
					float z33 = Float.parseFloat(z3);
					String Nombre = stTexto.nextToken();
					Resultado = objetos.AgregarTriangulo(x11, y11, z11, x22,
							y22, z22, x33, y33, z33, Nombre);
					if (Resultado == Objetos.TipoDevuelto.Correcto) {
						Salida.writeBytes("Agregado triangulo: ");
						Salida.writeBytes(Nombre);
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
					if (Resultado == Objetos.TipoDevuelto.TrianguloExisteOtroObjeto) {
						Salida
								.writeBytes("ERROR: TRIANGULO YA EXISTE EN OTRO OBJETO ");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
					if (Resultado == Objetos.TipoDevuelto.TrianguloExisteEsteObjeto) {
						Salida
								.writeBytes("ERROR: TRIANGULO YA EXISTE EN ESTE OBJETO ");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
				
				    if (Resultado ==Objetos.TipoDevuelto.PuntosTriangulosIncorrectos) {
				    	Salida
						.writeBytes("ERROR: PUNTOS DEL TRIANGULO INCORRECTOS.LOS 3 PUNTOS DEBEN DE SER DIFERENTES");
				Salida.writeBytes("\r\n");
				Salida.seek(Salida.length());
				    }
				    if (Resultado ==Objetos.TipoDevuelto.ObjetoNoExiste){
				    	Salida.writeBytes("ERROR: NO SE PUEDE AGREGAR TRIANGULO.OBJETO(");
						Salida.writeBytes(Nombre);
						Salida.writeBytes(") NO EXISTE");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
				    }
				
				}
				if (Comando.equals("AAC")) {
					String I = stTexto.nextToken();
					String Tipo = stTexto.nextToken();

					DecimalFormatSymbols dfs = new DecimalFormatSymbols();
					dfs.setDecimalSeparator('.');
					DecimalFormat FIa = new DecimalFormat("0000", dfs);

					int Identificador = Integer.valueOf(I);
					String Trasformado = FIa.format(Identificador);

					if (Tipo.equals("t")) {
						String x = stTexto.nextToken();
						String y = stTexto.nextToken();
						String z = stTexto.nextToken();
						float dx = Float.parseFloat(x);
						float dy = Float.parseFloat(y);
						float dz = Float.parseFloat(z);
						Resultado = objetos.AgregarAccionTraslacion(
								Identificador, Tipo, dx, dy, dz);
						if (Resultado == Objetos.TipoDevuelto.Correcto) {
							Salida.writeBytes("Agregada accion: ");
							Salida.writeBytes(Trasformado);
							Salida.writeBytes("\r\n");
							Salida.seek(Salida.length());
						}
						if (Resultado == Objetos.TipoDevuelto.IdentificadorYaExiste) {
							Salida.writeBytes("ERROR: EL IDENTIFICADOR(");
							Salida.writeBytes(Trasformado);
							Salida
									.writeBytes(") YA HA SIDO ASIGNADO A OTRA ACCION");
							Salida.writeBytes("\r\n");
							Salida.seek(Salida.length());
						}
					}
					if (Tipo.equals("g")) {
						String e = stTexto.nextToken();
						String u = stTexto.nextToken();
						char Eg = e.charAt(0);
						int Angulo = Integer.parseInt(u);
						Resultado = objetos.AgregarAccionGiro(Identificador,
								Tipo, Eg, Angulo);
						if (Resultado == Objetos.TipoDevuelto.Correcto) {
							Salida.writeBytes("Agregada accion: ");
							Salida.writeBytes(Trasformado);
							Salida.writeBytes("\r\n");
							Salida.seek(Salida.length());
						}
						if (Resultado == Objetos.TipoDevuelto.IdentificadorYaExiste) {
							Salida.writeBytes("ERROR: EL IDENTIFICADOR(");
							Salida.writeBytes(Trasformado);
							Salida
									.writeBytes(") YA HA SIDO ASIGNADO A OTRA ACCION");
							Salida.writeBytes("\r\n");
							Salida.seek(Salida.length());
						}
					}
					if (Tipo.equals("e")) {
						String esc = stTexto.nextToken();
						float Escala = Float.parseFloat(esc);
						Resultado = objetos.AgregarAccionEscala(Identificador,
								Tipo, Escala);
						if (Resultado == Objetos.TipoDevuelto.Correcto) {
							Salida.writeBytes("Agregada accion: ");
							Salida.writeBytes(Trasformado);
							Salida.writeBytes("\r\n");
							Salida.seek(Salida.length());
						}
						if (Resultado == Objetos.TipoDevuelto.IdentificadorYaExiste) {
							Salida.writeBytes("ERROR: EL IDENTIFICADOR(");
							Salida.writeBytes(Trasformado);
							Salida
									.writeBytes(") YA HA SIDO ASIGNADO A OTRA ACCION");
							Salida.writeBytes("\r\n");
							Salida.seek(Salida.length());
						}
						if (Resultado == Objetos.TipoDevuelto.CambioEscalaPositivo) {
							Salida
									.writeBytes("ERROR: NO SE ADMITEN ACCIONES DE ESCALA CON VALOR MENOR O IGUAL A CERO");
							Salida.writeBytes("\r\n");
							Salida.seek(Salida.length());
						}
					}
				}
				if (Comando.equals("APA")) {
					String Nombre = stTexto.nextToken();
					String I = stTexto.nextToken();
					DecimalFormatSymbols dfs = new DecimalFormatSymbols();
					dfs.setDecimalSeparator('.');
					DecimalFormat FIa = new DecimalFormat("0000", dfs);
					int Identificador = Integer.valueOf(I);
					String Trasformado = FIa.format(Identificador);

					Resultado = objetos.AplicarAccion(Nombre, Identificador);

					if (Resultado == Objetos.TipoDevuelto.Correcto) {
						Salida.writeBytes("Aplicada accion: ");
						Salida.writeBytes(Nombre);
						Salida.writeBytes(" ");
						Salida.writeBytes(Trasformado);
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
					if (Resultado == Objetos.TipoDevuelto.NoExisteAccionParaAplicar) {
						Salida
								.writeBytes("ERROR: LA ACCION NO SE PUEDE APLICAR.EL IDENTIFICADOR(");
						Salida.writeBytes(Trasformado);
						Salida.writeBytes(")NO HA SIDO AGREGADO PREVIAMENTE");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
					if (Resultado == Objetos.TipoDevuelto.NoTrianguloAplicarAccion) {
						Salida
								.writeBytes("ERROR: LA ACCION NO SE PUEDE APLICAR.EL OBJETO(");
						Salida.writeBytes(Nombre);
						Salida
								.writeBytes(")NO TIENE TRIANGULOS PARA APLICAR LA ACCION");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
				}
				if (Comando.equals("DAC")) {
					String Nombre = stTexto.nextToken();
					Resultado = objetos.DeshacerAccion(Nombre);
					DecimalFormatSymbols dfs = new DecimalFormatSymbols();
					dfs.setDecimalSeparator('.');
					DecimalFormat FIa = new DecimalFormat("0000", dfs);
					String Trasformado=FIa.format(Objeto.identificadorAux);
					if (Resultado == Objetos.TipoDevuelto.Correcto) {
						Salida.writeBytes("Deshecha accion: ");
						Salida.writeBytes(Nombre);
						Salida.writeBytes(" ");
                        Salida.writeBytes(Trasformado);
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
					if (Resultado == Objetos.TipoDevuelto.ObjetoNoExiste) {
						Salida.writeBytes("ERROR: OBJETO(");
						Salida.writeBytes(Nombre);
						Salida.writeBytes(") NO EXISTE");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
					if (Resultado == Objetos.TipoDevuelto.NoAccionDeshacer) {
						Salida
								.writeBytes("ERROR: NO SE PUEDE DESHACER ACCION.EL OBJETO(");
						Salida.writeBytes(Nombre);
						Salida.writeBytes(")NO TIENE ACCIONES APLICADAS");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
				}
				if (Comando.equals("EAC")) {
					String I = stTexto.nextToken();
					DecimalFormatSymbols dfs = new DecimalFormatSymbols();
					dfs.setDecimalSeparator('.');
					DecimalFormat FIa = new DecimalFormat("0000", dfs);
					int Identificador = Integer.valueOf(I);
					String Trasformado = FIa.format(Identificador);

					Resultado = objetos.EliminarAccion(Identificador);
					if (Resultado == Objetos.TipoDevuelto.Correcto) {
						Salida.writeBytes("Eliminada accion: ");
						Salida.writeBytes(Trasformado);
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
					if (Resultado == Objetos.TipoDevuelto.IdentificadorNoExiste) {
						Salida
								.writeBytes("ERROR: AL ELIMINAR ACCION.EL IDENTIFICADOR(");
						Salida.writeBytes(Trasformado);
						Salida.writeBytes(")NO EXISTE");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());

					}
					if (Resultado == Objetos.TipoDevuelto.NoSepuedeBorrarAccion) {
						Salida
								.writeBytes("ERROR: AL ELIMINAR ACCION.NO SE PUEDE BORRAR UNA ACCION QUE ESTE APLICADA EN ALGUN OBJETO");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
				}
				if (Comando.equals("MDO")){
					String Nombre = stTexto.nextToken();
				    Resultado=objetos.MostrarDatosObjetos(Nombre);
				    if (Resultado==Objetos.TipoDevuelto.ObjetoNoExiste){
				    	Salida.writeBytes("ERROR: OBJETO(");
						Salida.writeBytes(Nombre);
						Salida.writeBytes(") NO EXISTE");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
				    }
				    if (Resultado==Objetos.TipoDevuelto.Correcto){
					  objetos.MostrarDatos(Nombre, Salida) ; 
				    }
				}
				if (Comando.equals("MAO")){
					String Nombre = stTexto.nextToken();					
					Resultado = objetos.MostrarAccionesObjetos(Nombre);
					

					if (Resultado == Objetos.TipoDevuelto.Correcto){
						objetos.MostrarAcciones(Nombre, Salida);
					}
					if (Resultado == Objetos.TipoDevuelto.ObjetoNoExiste) {
						Salida.writeBytes("ERROR: OBJETO NO EXISTE");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length()); 
					} 
					if (Resultado == Objetos.TipoDevuelto.NoExisteAccionParaAplicar) {							Salida.seek(Salida.length()); 
						Salida.writeBytes("ERROR: NO HAY ACCIONES EN EL OBJETO PARA MOSTRAR");
						Salida.writeBytes("\r\n"); 
						Salida.seek(Salida.length());
					} 
				
				}
				
				if (Comando.equals("CSU")){
					float Sup;
					String Nombre = stTexto.nextToken();
					DecimalFormatSymbols dfs = new DecimalFormatSymbols();
					dfs.setDecimalSeparator('.');
					DecimalFormat FSup = new DecimalFormat("0000000.00", dfs);
					Resultado = objetos.CalcularSuperficieObjeto(Nombre);
					if (Resultado == Objetos.TipoDevuelto.Correcto) {
						Sup = objetos.MostrarSuperficie(Nombre);
						Salida.writeBytes("Superficie: ");
						Salida.writeBytes(Nombre);
						Salida.writeBytes(" ");
						Salida.writeBytes(FSup.format(Sup));
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
					if (Resultado == Objetos.TipoDevuelto.NoTriangulosCalcularSuperfice) {
						Salida.writeBytes("ERROR: NO HAY TRIANGULOS EN EL OBJETO PARA CALCULAR LA SUPERFICIE");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
					if (Resultado == Objetos.TipoDevuelto.ObjetoNoExiste) {
						Salida.writeBytes("ERROR: OBJETO NO EXISTE");
						Salida.writeBytes("\r\n");
						Salida.seek(Salida.length());
					}
				}
				
			}
		}
	}
	 //PRE:Cierto
	//POST: Realiza el tratamiento completo de un fichero
	private static void AnalizadorFichero(File Ent, File Sal) throws IOException {
		try {
			RandomAccessFile Entrada = new RandomAccessFile(Ent, "r");
			RandomAccessFile Salida = new RandomAccessFile(Sal, "rw");

			String Linea = Entrada.readLine();
			while (Linea != null) {
				AnalizadorLinea(Entrada, Salida, Linea);

				Linea = Entrada.readLine();	
			}
		} catch (IOException e) {
			System.out.println ("ERROR: NO EXISTE O MAL ACCESO A FICHERO DE ENTRADA/SALIDA");
		}
		}

	public static void main(String[] args) throws IOException {
 
	if (args.length != 2) {
		System.out.println("ERROR: FORMATO = EJECUTABLE <ENTRADA> <SALIDA>");
	} else {			
		
		String ruta = args[0];
		ruta = ruta.replace('/', File.separatorChar);
		File Entrada = new File(ruta);

		String ruta2 = args[1];
		ruta2 = ruta2.replace('/', File.separatorChar);
		File Salida = new File(ruta2);
				
		Salida.delete();
		AnalizadorFichero(Entrada, Salida);
	
	}
	}
}
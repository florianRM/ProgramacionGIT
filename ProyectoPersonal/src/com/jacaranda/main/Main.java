package com.jacaranda.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

import com.jacaranda.usuario.Estandar;
import com.jacaranda.usuario.Premium;
import com.jacaranda.usuario.Usuario;
import com.jacaranda.usuario.UsuarioException;

public class Main {
	
	public static Scanner teclado = new Scanner(System.in);
	private static Connection conexion;
	private static Statement instruccion = null;
	private static ResultSet resultado;
	private static ResultSetMetaData info;
	
	public static void main(String[] args) throws UsuarioException, SQLException {
		int opc;
		char cuenta;
		
		try {
			conexion = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLCDB.localdomain", "dummy", "dummy");
			instruccion = conexion.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		do {
			menu();
			opc = pedirEntero();
			if(opc == 1) {
				String login;
				String contrasenna;
				double saldo;
				boolean terminar = false;
				
				do {
					System.out.println("Escoja un plan de cuenta(P-Premium o E-Estandar):\n"
							+ "Si escoge el plan premium se le quitara 15 euros del saldo.");
					cuenta = pedirCaracter();
					if(cuenta == 'p' || cuenta == 'P') {
						System.out.println("Introduzca el nombre de usuario:");
						login = pedirString();
						System.out.println("Introduzca la contraseña:");
						contrasenna = pedirString();
						System.out.println("Introduzca el saldo:");
						saldo = pedirDouble();
						resultado = instruccion.executeQuery("SELECT COUNT(LOGIN) AS NUMCUENTAS FROM USUARIO WHERE LOGIN = '"+login+"'");
						resultado.next();
						
						if(resultado.getInt("NUMCUENTAS") == 1) {
							System.out.println("El usuario ya existe.");
						} else {
							resultado = instruccion.executeQuery("INSERT INTO USUARIO VALUES('" + login + "', '" + contrasenna + "', "+ saldo +", 1)");
							System.out.println("Se ha registrado con exito.");
						}
							
					} else if(cuenta == 'e' || cuenta == 'E') {
						System.out.println("Introduzca el nombre de usuario:");
						login = pedirString();
						System.out.println("Introduzca la contraseña:");
						contrasenna = pedirString();
						resultado = instruccion.executeQuery("SELECT COUNT(LOGIN) AS NUMCUENTAS FROM USUARIO WHERE LOGIN = '"+login+"'");
						resultado.next();
						if(resultado.getInt("NUMCUENTAS") == 1) {
							System.out.println("El usuario ya existe.");
						} else {
							resultado = instruccion.executeQuery("INSERT INTO USUARIO(LOGIN, CONTRASENNA, PREMIUM) VALUES('" + login + "', '" + contrasenna + "', 0)");
							System.out.println("Se ha registrado con exito.");
						}
						
					} else {
						System.out.println("El plan escogido no existe");
					}
				} while ((cuenta != 'P' && cuenta != 'p') && (cuenta != 'E' && cuenta != 'e'));
			} else if(opc == 2) {
				String login;
				String contrasenna;
				int sesion = 1;
				do {
					System.out.println("Introduzca el usuario:");
					login = pedirString();
					System.out.println("Introduzca la contraseña:");
					contrasenna = pedirString();
					ResultSet resultadoLogin = instruccion.executeQuery("SELECT * FROM USUARIO WHERE LOGIN = '" + login + "'");
					resultadoLogin.next();
					if(resultadoLogin.getString("LOGIN").equals(login) && resultadoLogin.getString("CONTRASENNA").equals(contrasenna)) {
						int opcCuenta;
						Premium usuarioP = null;
						Estandar usuarioE = null;
						boolean premium = false;
						String dni;
						LocalDateTime fecha;
						
						if(resultadoLogin.getInt("PREMIUM") == 1) {
							usuarioP = new Premium(resultadoLogin.getString("LOGIN"), resultadoLogin.getString("CONTRASENNA"), resultadoLogin.getDouble("SALDO"));
							premium = true;
						} else {
							usuarioE = new Estandar(resultadoLogin.getString("LOGIN"), resultadoLogin.getString("CONTRASENNA"));
						}
						
						do {
							menuCuenta();
							opcCuenta = pedirEntero();
							switch (opcCuenta) {
							case 1:
								if(premium) {
									comprarBillete(usuarioP);
								} else {
									comprarBillete(usuarioE);
								}
								break;
							case 2:
								if(premium) {
									comprarBilleteIdaVuelta(usuarioP);
								} else {
									comprarBilleteIdaVuelta(usuarioE);
								}
								break;
							case 3:
								if(premium) {
									borrarBillete(usuarioP);
								} else {
									borrarBillete(usuarioE);
								}
								break;
							case 4:
								if(premium) {
									borrarCarrito(usuarioP);
								} else {
									borrarCarrito(usuarioE);
								}
								break;
							case 5:
								if(premium) {
									modificarSaldo(usuarioP);
								} else {
									modificarSaldo(usuarioE);
								}
								break;
							case 6:
								if(premium) {
									System.out.println(usuarioP.listadoBilletes());
								} else {
									System.out.println(usuarioE.listadoBilletes());
								}
								break;
							case 7:
								System.out.println("¿Quiere finalizar la compra?(S/N):");
								char seguir = pedirCaracter();
								if(seguir == 'S' || seguir == 's') {
									if(premium) {
										System.out.println(usuarioP.calcularPrecio() + " y su saldo es de " + usuarioP.getSaldo());
										usuarioP.guardarBilletes();
									} else {
										System.out.println(usuarioE.calcularPrecio() + " y su saldo es de " + usuarioE.getSaldo());
										usuarioE.guardarBilletes();
									}
								} else if(seguir == 'N' || seguir == 'n') {
									System.out.println("Gracias por usar nuestros servicios.");
								}
								sesion = 0;
								break;
							default:
								System.out.println("La opción escogida es incorrecta.");
								break;
							}
						} while (opcCuenta != 7);
					}
				} while (sesion != 0);
				
			}
			
		} while (opc != 3);
		
	}
	

	private static void modificarSaldo(Usuario user) throws SQLException {
		System.out.println("Introduzca cuánto dinero quiere introducir:");
		double dinero = pedirDouble();
		instruccion.executeQuery("UPDATE USUARIO SET SALDO = SALDO + "+dinero+"  WHERE LOGIN = '"+user.getLogin()+"'");
	}
	
	private static void borrarCarrito(Usuario user) throws UsuarioException {
		System.out.println("Introduzca el dni:");
		String dni = pedirString();
		System.out.println("Introduzca la fecha del billete que quiere cancelar:");
		LocalDateTime fecha = pedirFecha();
		
		user.borrarDelCarrito(dni, fecha);
	}
	
	private static void borrarBillete(Usuario user) throws UsuarioException, SQLException {
		System.out.println("Introduzca el dni:");
		String dni = pedirString();
		System.out.println("Introduzca la fecha del billete que quiere cancelar:");
		LocalDateTime fecha = pedirFecha();
		Date fechaSalida = Date.valueOf(fecha.getYear() + "-" + fecha.getMonthValue() + "-" + fecha.getDayOfMonth());
		Time horaSalida = Time.valueOf(fecha.getHour() + ":" + fecha.getMinute() + ":" + fecha.getSecond());
		String fechaSalidaString = fechaSalida + " " + horaSalida;
		
		user.cancelarBillete(dni, fecha);
		if(comprobarExistencia() == 1) {
			instruccion.executeQuery("DELETE FROM BILLETES WHERE DNI = '"+dni+"' AND FECHA_SALIDA = TO_DATE('"+fechaSalidaString+"', 'yyyy-MM-dd hh:mi:ss'))");
		} else {
			System.out.println("El billete no existe.");
		}
	}
	
	private static int comprobarExistencia() throws SQLException {
		resultado = instruccion.executeQuery("SELECT COUNT(DNI) FROM BILLETES");
		return resultado.getInt(0);
	}
	
	private static void comprarBillete(Usuario user) throws UsuarioException {
		String dni;
		String tipoBillete;
		LocalDateTime fechaIda;
		String nombreAerolinea;
		
		System.out.println("Introduzca el dni:");
		dni = pedirString();
		System.out.println("Introduzca el tipo de billete(Economy, Businnes, First):");
		tipoBillete = pedirString();
		System.out.println("Introduzca la fecha de ida:");
		fechaIda = pedirFecha();
		System.out.println("Introduzca el nombre de la aerolínea:");
		nombreAerolinea = pedirString();
		
		user.comprarBillete(dni, tipoBillete, fechaIda, nombreAerolinea);
	}
	
	private static void comprarBilleteIdaVuelta(Usuario user) throws UsuarioException {
		String dni;
		String tipoBillete;
		LocalDateTime fechaIda;
		LocalDateTime fechaVuelta;
		String nombreAerolinea;
		
		System.out.println("Introduzca el dni:");
		dni = pedirString();
		System.out.println("Introduzca el tipo de billete(Economy, Businnes, First):");
		tipoBillete = pedirString();
		System.out.println("Introduzca la fecha de ida:");
		fechaIda = pedirFecha();
		System.out.println("Introduzca la fecha de vuelta:");
		fechaVuelta = pedirFecha();
		System.out.println("Introduzca el nombre de la aerolínea:");
		nombreAerolinea = pedirString();
		
		user.comprarBillete(dni, tipoBillete, fechaIda, fechaVuelta, nombreAerolinea);
	}
	
	private static LocalDateTime pedirFecha() {
		int dia;
		int mes;
		int annio;
		int hora;
		int min;
		System.out.println("Introduzca el día:");
		dia = pedirEntero();
		System.out.println("Introduzca el mes:");
		mes = pedirEntero();
		System.out.println("Introduzca el año:");
		annio = pedirEntero();
		System.out.println("Introduzca la hora:");
		hora = pedirEntero();
		System.out.println("Introduzca los minutos:");
		min = pedirEntero();
		
		return LocalDateTime.of(annio, mes, dia, hora, min);
	}
	
	private static void menu() {
		System.out.println("1.Registrarse.\n"
				+ "2.Iniciar Sesión.\n"
				+ "3.Salir.\n"
				+ "Introduzca una opción:");
	}
	
	private static void menuCuenta() {
		System.out.println("1.Comprar billete ida.\n"
				+ "2.Comprar billete ida y vuelta.\n"
				+ "3.Cancelar billete.\n"
				+ "4.Borrar billete del carrito.\n"
				+ "5.Aumentar saldo.\n"
				+ "6.Listar carrito de la compra.\n"
				+ "7.Finalizar.\n"
				+ "Introduzca una opción:");
	}
	
	private static int pedirEntero() {
		return Integer.parseInt(teclado.nextLine());
	}
	
	private static char pedirCaracter() {
		return teclado.nextLine().charAt(0);
	}
	
	private static String pedirString() {
		return teclado.nextLine();
	}
	
	private static double pedirDouble() {
		return Double.parseDouble(teclado.nextLine());
	}
	

}

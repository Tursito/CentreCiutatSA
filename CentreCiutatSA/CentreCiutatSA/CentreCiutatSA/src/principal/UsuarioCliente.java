package principal;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import clases.*;;

/**
 * Esta es la clase UsuarioCliente que tiene el metodo main
 * @author Adrian tur, Adrian marzo, Miriam fernandez, Sergio Bibiloni, Daniel yela 
 *
 */
public class UsuarioCliente {

	public static void main(String[] args) {

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/centreciutat", "root", "");	

			Scanner sc = new Scanner(System.in);
			login(con, sc);

		} catch (SQLException e) {
			System.err.println("Error connexi?n BBDD");
			e.printStackTrace();
			printSQLException(e);
		}	
	}

	/**
	 * Est? es el m?todo LOGIN de la clase UsuarioCliente, este m?todo solicita al usuario un inicio de sesion con usuario y contrase?a,
	 * pide un nombre y contrase?a validos almacenados en la BBDD si los valores son incorrectos mostrar? un error.
	 */
	public static void login(Connection con, Scanner sc) throws SQLException {
		
		Cliente c1 = new Cliente();
		Administrador a1 = new Administrador();
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("======================================");
		System.out.println("========| Centre Ciutat S.A. |========");
		System.out.println("======================================");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("==        Cual es su nombre?        ==");
		System.out.println(" ");
		System.out.print("Introduce tu nombre: ");
		String nombreIntroducido = sc.nextLine();
		System.out.println(" ");
		System.out.println("==            Contrase?a             ==");
		System.out.println(" ");
		System.out.print("Introduce tu contrase?a: ");
		String contrasenaIntroducida = sc.nextLine();

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		Statement stmt = null;

		// String query = "SELECT nombre, apellidos, admin, contrasena from usuarios
		// where nombre = " + "'" + nombreIntroducido + "'";
		String query = "SELECT nombre, apellidos,admin, contrasena FROM `usuarios` WHERE nombre = '" + nombreIntroducido
				+ "'";

		try {

			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			String nombreUsuario = "";
			String apellidosUsuario = "";
			String esAdmin = "";
			String contrasena = "";

			if (rs.next() == false) {
				System.err.println("No se encuentra en la base de datos  ");
				login(con, sc);

			} else {

				nombreUsuario = rs.getString("nombre");
				esAdmin = rs.getString("admin");
				apellidosUsuario = rs.getString("apellidos");
				contrasena = rs.getString("contrasena");

				if (nombreUsuario.equals(nombreIntroducido) && contrasena.equals(contrasenaIntroducida)
						&& esAdmin.equals("0")) {
					menuCliente(con, c1, sc, nombreIntroducido, nombreUsuario, apellidosUsuario);

				} else if (nombreUsuario.equals(nombreIntroducido) && contrasena.equals(contrasenaIntroducida) && esAdmin.equals("1")) {

					menuAdmin(con, a1, sc);
				} else {
					System.err.println("No se encuentra en la base de datos");
					login(con, sc);
				}

				
			}

		} catch (SQLException e) {
			System.err.println("ERROR! No se encuentra en la base de datos");
			e.getMessage();
			printSQLException(e);
			
		} finally {
			stmt.close();
		}
	}

	/**
	 * Est? es el m?todo menuAdmin de la clase UsuarioCliente, este m?todo nos da la bienvenida al men? del usuario administrador,
	 * pide al usuario administrador realizar una de las opciones v?lidas en el despegable.
	 */
	public static void menuAdmin(Connection con, Administrador a1, Scanner sc) throws SQLException {
		String opcion;
		
		System.out.println("========== Bienvenid@  ==========");
		System.out.println(" ");
		System.out.println("(Seleccione la opci?n que desee con ");
		System.out.println("las teclas * 1 - 2 - 3 - 4 o 5* )");
		System.out.println(" ");
		System.out.println(" 1) Listar alquileres");
		System.out.println(" 2) Editar alquileres");
		System.out.println(" 3) Eliminar alquileres ");
		System.out.println(" 4) Crear nuevo usuario ");
		System.out.println(" 5) Cerrar sesi?n ");
		System.out.println(" ");
		System.out.println("======================================");
		System.out.println(" ");
		System.out.print("Opci?n: ");
		opcion = sc.nextLine();
		
		if(opcion=="") {
			System.err.println("Solo n?meros entre 1 y 5");
			System.out.println(" ");
			
			menuAdmin(con, a1, sc);
		}else {
		
	
		if (opcion.charAt(0)=='1') {
		
			// CONTECTAR CON BASE DE DATOS Y MOSTRAR POR PANTALLA LISTADO DE ALQUILERES
			// RETORNA AL MENU ADMINISTRADOR
						a1.listarAlquiler(con);
						menuAdmin(con, a1, sc);
		}
			else if(opcion.charAt(0)=='2') {

				System.out.print("Introduce el Id del Alquiler para editar: ");
				try {
		 		int id = sc.nextInt();
		 		sc.nextLine(); // evitar errores
		 		System.out.println(" ");

		 	// CONTECTAR CON BASE DE DATOS ALMACENA EL ID SOLICITADO AL USUARIO
			//NOS MUESTRA EL MENU PARA EDITAR ALQUILERES 
		 	// RETORNA AL MENU ADMINISTRADOR
				a1.editarAlquiler(con, id);
				menuAdmin(con, a1, sc);
				
				
				}catch(InputMismatchException e){
		 			System.err.println("Debes introducir un ID v?lido!");
		 			System.out.println(" ");
		 			
		 		}
				sc.nextLine(); //Refresca el scanner para llamar al menuAdmin limpio
				menuAdmin(con, a1, sc);
			}
			
			else if(opcion.charAt(0)=='3') {
				
				// CONTECTAR CON BASE DE DATOS PARA ELIMINAR ALQUILERES
				//RETORNA AL MENU ADMINSTRADOR
				a1.eliminarAlquiler(con);
				menuAdmin(con, a1, sc);
				
			}
			else if(opcion.charAt(0)=='4') {
				
				// CONTECTAR CON BASE DE DATOS PARA CREAR USUARIO
				//RETORNA AL MENU ADMINSTRADOR
				a1.crearUsuario(con);
				menuAdmin(con, a1, sc);}
		
			else if(opcion.charAt(0)=='5') {

				//CIERRA SESION Y RETORNA AL METODO LOGIN
				System.out.println("CERRANDO SESI?N...");
				System.out.println("");
				login(con, sc);

			}
			else {
				//MENSAJE POR DEFAULT SI NO CUMPLE NINGUAN DE LAS CONDICIONES Y RETORNA AL MENU ADMINISTRADOR
				System.err.println("Solo n?meros entre 1 y 5");
				System.out.println(" ");
				menuAdmin(con, a1, sc);
			}
		}
	}

	/**
	 * Est? es el m?todo menuCliente de la clase UsuarioCliente, este m?todo nos da la bienvenida al men? del usuario Cliente,
	 * pide al usuario cliente realizar una de las opciones v?lidas en el despegable.
	 */
	public static void menuCliente(Connection con, Cliente c1, Scanner sc, String nombreIntroducido,
			String nombreUsuario, String apellidosUsuario) throws SQLException {
		String opcion ;
		// SQL que recoga los datos y poner el nombre del usuario

		System.out.println("Bienvenido " + nombreUsuario + " " + apellidosUsuario);
		System.out.println(" ");

		System.out.println("========== QUE DESEA HACER? ==========");
		System.out.println(" ");
		System.out.println("(Seleccione la opci?n que desee con ");
		System.out.println("las teclas * 1 - 2 o 3* )");
		System.out.println(" ");
		System.out.println(" 1) Mostrar informaci?n de su vehiculo");
		System.out.println(" 2) Mostrar su informaci?n personal");
		System.out.println(" 3) Cerrar sesi?n ");
		System.out.println(" ");
		System.out.println("======================================");
		
		System.out.println(" ");
		System.out.print("Opci?n: ");
		
		opcion = sc.nextLine();
		if(opcion=="") {
			System.err.println("Solo n?meros entre 1 y 3");
			System.out.println(" ");
			menuCliente(con, c1, sc, nombreIntroducido, nombreUsuario, apellidosUsuario);
		}else {
		
	
		if (opcion.charAt(0)=='1') {
			// CONTECTAR CON BASE DE DATOS Y MOSTRAR POR PANTALLA INFORMACI?N DEL VEH?CULO
			// RETORNA EL MEN? DEL USUARIO CLIENTE
			c1.buscartuVehiculo(con);
			menuCliente(con, c1, sc, nombreIntroducido, nombreUsuario, apellidosUsuario);
			
		}
			else if(opcion.charAt(0)=='2') {

			// CONTECTAR CON BASE DE DATOS Y MOSTRAR POR PANTALLA INFORMACI?N DEL CLIENTE
			//RETORNA EL MUN? DEL USUARIO CLIENTE
			c1.buscartuInformacion(con);
			menuCliente(con, c1, sc, nombreIntroducido, nombreUsuario, apellidosUsuario);
			}
			
			else if(opcion.charAt(0)=='3') {
			
				//CIERRA SESION Y RETORNA AL LOGIN 
			System.out.println("CERRANDO SESI?N...");
			System.out.println("");
			System.out.println("Gracias por tu colaboraci?n " + nombreIntroducido + ", que tengas un buen dia");
			System.out.println("");
			login(con, sc);
			}
			
			else {
			//MENSAJE POR DEFAULT SI NO CUMPLE NINGUAN DE LAS CONDICIONES Y RETORNA AL MENU CLIENTE
			System.err.println("Solo n?meros entre 1 y 3");
			System.out.println(" ");
			menuCliente(con, c1, sc, nombreIntroducido, nombreUsuario, apellidosUsuario);
			}
		}

		}

	/**
	 * Este es el m?todo printSQLException de la clase UsuarioCliente, este m?todo nos muestra sentencias de errores posibles,
	  * @param ex Objeto cuya informaci?n esta siendo mostrada.
	 *
	 */
	public static void printSQLException(SQLException ex) {

		ex.printStackTrace(System.err);

		System.err.println("SQLState: " + ex.getSQLState()); // getSQLState()
		System.err.println("Error Code: " + ex.getErrorCode()); // getErrorCode()
		System.err.println("Message: " + ex.getMessage()); // getMessage()

		Throwable t = ex.getCause(); // getCause() - Leemos la primera causa

		while (t != null) {
			System.out.println("Cause: " + t); // Imprimimos una causa
			t = t.getCause(); // Leemos otra causa
		}

	}
}


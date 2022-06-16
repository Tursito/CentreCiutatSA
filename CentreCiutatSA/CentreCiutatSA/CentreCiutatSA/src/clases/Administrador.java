package clases;

import java.sql.*;
import java.io.*;
import java.util.*;
/**
 * Esta es la clase Administrador que hereda los métodos, atributos y constructor de la clase abstracta Persona.
 * @author Adrian tur, Adrian marzo, Miriam fernandez, Sergio Bibiloni, Daniel yela 
 *
 */
public class Administrador extends Persona {

	// Atributos
	private String usuario, contrasena;
	/**
	 * 
	 * @param nombre Utiliza el atributo nombre para crear el objeto.
	 * @param apellidos Utiliza el atributo apellidos para crear el objeto.
	 * @param dni Utiliza el atributo dni para crear el objeto.
	 * @param direccion Utiliza el atributo direccion para crear el objeto.
	 * @param cuentaCorriente Utiliza el atributo cuentaCorriente para crear el objeto.
	 * @param usuario Utiliza el atributo usuario para crear el objeto.
	 * @param contraseña Utiliza el atributo contraseña para crear el objeto.
	 * 
	 */
	// Constructor
	public Administrador(String nombre, String apellidos, String dni, String direccion, int cuentaCorriente,
			String usuario, String contrasena) {
		super(nombre, apellidos, dni, direccion, cuentaCorriente);
		this.usuario = usuario;
		this.contrasena = contrasena;

	}

	public Administrador() {

	}
	/**
	 * Este método nos retorna el valor actual de usuario.
	 * @return usuario valor de usuario.
	 */
	// Getters & Setters
	public String getUsuario() {
		return usuario;
	}
	/**
	 * Este método sirve para introducir un valor a usuario.
	 * @param usuario valor para usuario.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * Este método nos retorna el valor actual de constraseña.
	 * @return contraseña valor de contraseña.
	 */
	public String getContrasena() {
		return contrasena;
	}
	/**
	 * Este método sirve para introducir un valor a contraseña.
	 * @param contraseña valor para contraseña.
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	/**
	 * Esté es el método crearUsuario de la clase Administrador, este método nos muestra un menú de creación de usuario cliente/administrador,
	 * para crear un usuario cliente/administrador solicitamos al usuario insertar cada uno de sus atributos,
	 * el campo DNI sera obligatorio en ambos casos y si ya exista dara un error,
	 * Se asegurará de que en la bbdd no hayan valores repetidos y creará el nuevo usuario con su vehiculo añadido a un nuevo estacionamiento.
	 *
	 */
	public boolean crearUsuario(Connection con) {
		Scanner teclado = new Scanner(System.in);

		String opcion = "";
		String nombre = "";
		String apellido = "";
		String dni = "";
		String psw = "";
		String direccion = "";
		String cuenta = "";
		String matricula = "";
		String idEstacionamiento = "";
		boolean valido = false;
		boolean correcto = false;

		System.out.println("========== CREAR USUARIO ==========");
		System.out.println(" ");
		System.out.println(" Selecciona el tipo de usuario que ");
		System.out.println(" desea crear");
		System.out.println(" ");
		System.out.println(" 1) Administrador");
		System.out.println(" 2) Cliente");
		System.out.println(" 3) Salir ");
		System.out.println(" ");
		System.out.println("======================================");
		System.out.println(" ");
		System.out.print("Opción: ");
		opcion = teclado.nextLine();
		if (opcion == "") {
			System.out.println("Solo números entre 1 y 3");
			System.out.println(" ");
			crearUsuario(con);
		} else {

			if (opcion.charAt(0) == '1') {

				System.out.println("");
				System.out.println("======= USUARIO ADMINISTRADOR =======");
				System.out.println(" ");
				System.out.println(" Introduce los datos que se le piden");
				System.out.println(" ");
				System.out.println(" ");

				System.out.print(" NOMBRE: ");
				nombre = teclado.nextLine();

				System.out.println(" ");
				System.out.print(" APELLIDOS:  ");
				apellido = teclado.nextLine();

				System.out.println(" ");
				System.out.print(" CONTRASEÑA: ");
				psw = teclado.nextLine();

				while(!valido) {
				System.out.println(" ");
				System.out.print(" DNI: ");
				dni = teclado.nextLine();
				if (dni == "") {
					System.out.println(" DNI Obligatorio");
					System.out.println(" ");
					valido = false;
				} else {

					valido = true;
					System.out.println(" ");
					System.out.print(" DIRECCÓN: ");
					direccion = teclado.nextLine();

					System.out.println(" ");
					System.out.print(" CUENTA CORRIENTE: ");
					cuenta = teclado.nextLine();

					Statement stmt = null;

					try {

						stmt = con.createStatement();
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

						ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");

						// Nos posicionamos con el cursor en la próxima fila vacía de la tabla
						rs.moveToInsertRow();
						// Actualizamos los campos con los datos pasados por parámetro en la llamada al
						// método
						rs.updateString("dni", dni);
						rs.updateString("nombre", nombre);
						rs.updateString("apellidos", apellido);
						rs.updateString("direccion", direccion);
						rs.updateString("cuentaCorriente", cuenta);
						rs.updateInt("admin", 1);
						rs.updateString("contrasena", psw);
						// Insertamos la nueva fila con los datos proporcionados
						rs.insertRow();
						// Nos posicionamos con el cursor antes de la primera fila
						rs.beforeFirst();

						System.out.println("");
						System.out.println("Se ha insertado correctamente el nuevo usuario " + nombre + "!");
						correcto = true;

					} catch (SQLException e) {
						printSQLException(e);
					} finally {
						try {
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							printSQLException(e);
						}
					}
				}
				}
			
			} else if (opcion.charAt(0) == '2') {

				System.out.println("");
				System.out.println("======= USUARIO CLIENTE =======");
				System.out.println(" ");
				System.out.println(" Introduce los datos que se le piden");
				System.out.println(" ");
				System.out.println(" ");

				System.out.print(" NOMBRE: ");
				nombre = teclado.nextLine();

				System.out.println(" ");
				System.out.print(" APELLIDOS: ");
				apellido = teclado.nextLine();

				System.out.println(" ");
				System.out.print(" CONTRASEÑA: ");
				psw = teclado.nextLine();

				while (!valido) {
					System.out.println(" ");
					System.out.print(" DNI: ");
					dni = teclado.nextLine();
					if (dni == "") {
						System.out.println(" DNI Obligatorio");
						System.out.println(" ");
						valido = false;
					} else {
						valido = true;
					}
				}
				Statement stmt2 = null;
				try {
					String query = "select * from usuarios where dni = '" + dni + "'";

					stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery(query);
					if (rs2.next()) {
						System.out.println("ERROR! Este DNI ya existe!");
					} else {

						System.out.println(" ");
						System.out.print(" DIRECCÓN: ");
						direccion = teclado.nextLine();

						System.out.println(" ");
						System.out.print(" CUENTA CORRIENTE: ");
						cuenta = teclado.nextLine();

						matricula = crearVehiculo(con, teclado);
						correcto = insertarUsuario(con, nombre, apellido, dni, psw, direccion, cuenta);
						System.out.println(" ");
						System.out.print(" ID DE ESTACIONAMIENTO: ");
						idEstacionamiento = teclado.nextLine();

						crearAlquiler(con, idEstacionamiento, dni, matricula);
						
					}

				} catch (SQLException e) {

					printSQLException(e);

				}

			}

			else if (opcion.charAt(0) == '3') {

				System.out.println("== SALIENDO DEL PROGRAMA ==");
				System.out.println(" ");
				System.out.println("Gracias por tu colaboración, que tengas un buen dia");
				correcto = true;

			}

			else {
				System.err.println("Solo números entre 1 y 3");
				System.out.println(" ");
				crearUsuario(con);
			}
		}
		return correcto;
	}

	/**
	 * Esté es el método insertarUsuario de la clase Administrador, este método realiza un select en la BBDD 
	 * con todos parametros que contiene la tabla usuarios,
	 * Hace un listado que recoge todos los datos del nuevo usuario para despues almacenarlo en la bd.
	 *
	 */
	public boolean insertarUsuario(Connection con, String nombre, String apellido, String dni, String psw,
			String direccion, String cuenta) throws SQLException {
		Statement stmt = null;
		boolean correcto = false; 

		try {
			stmt = con.createStatement();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");

			// Nos posicionamos con el cursor en la próxima fila vacía de la tabla
			rs.moveToInsertRow();
			// Actualizamos los campos con los datos pasados por parámetro en la llamada al
			// método
			rs.updateString("dni", dni);
			rs.updateString("nombre", nombre);
			rs.updateString("apellidos", apellido);
			rs.updateString("direccion", direccion);
			rs.updateString("cuentaCorriente", cuenta);
			rs.updateInt("admin", 0);
			rs.updateString("contrasena", psw);
			// Insertamos la nueva fila con los datos proporcionados
			rs.insertRow();
			// Nos posicionamos con el cursor antes de la primera fila
			rs.beforeFirst();

			System.out.println("");
			System.out.println(" Se ha insertado correctamente el nuevo usuario " + nombre + "!");
			correcto = true;
			System.out.println("");
		} catch (SQLException e) {

			printSQLException(e);

		}
		return correcto;

	}

	/**
	 * Esté es el método eliminarAlquiler de la clase Administrador, este método realiza un select en la BBDD,
	 * con todos parametros que contiene el id seleccionado,
	 * Hace un delete del alquiler que le pasemos y actualiza el estacionamiento a disponible.
	 *
	 */
	public boolean eliminarAlquiler(Connection con) throws SQLException {

		Statement stmt = null;
		boolean correcto = false;
		int idAlq = 0;
		String opcion = "";
		boolean valido = true;

		Scanner teclado = new Scanner(System.in);
		System.out.println("");
		System.out.print("Introduce el ID del Alquiler que deseas borrar: ");
		try {
			idAlq = teclado.nextInt();
			teclado.nextLine();

			String query = "select * from alquiler where idAlquiler = " + idAlq;

			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				String idEstacionamiento = rs.getString("idEstacionamiento");
				String matricula = rs.getString("matricula");
				String dni = rs.getString("dni");
				while (valido) {
					System.out.println("Estas seguro que lo quieres eliminar?");
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");
					System.out.println("1. Si");
					System.out.println("2. No");
					System.out.print("Opción:");
					opcion = teclado.nextLine();

					if (opcion == "") {
						System.err.println("Escoge una opción válida.");
						System.out.println(" ");

					} else {

						if (opcion.charAt(0) == '1') {

							stmt = con.createStatement();
							stmt.executeUpdate("Delete from  alquiler  " + " where idAlquiler = " + idAlq);
							stmt.executeUpdate("UPDATE estacionamientos set disponible = 1 where idEstacionamiento = '"
									+ idEstacionamiento + "'");
							stmt.executeUpdate("Delete from  vehiculos where matricula = '" + matricula+"'");
							stmt.executeUpdate("Delete from  usuarios where dni = '" + dni+"'");
							valido = false;
							correcto = true;
						} else if (opcion.charAt(0) == '2') {

							valido = false;
						} else {
							System.err.println("Elige una opción correcta");
							System.out.println(" ");

						}
					}

				}

			} else {
				System.err.println("El ID del alquiler no se encuentra en la base de datos.");
			}

		} catch (SQLException e) {
			printSQLException(e);
		} catch (InputMismatchException e) {
			System.err.println("Introduce un ID válido!");
			System.out.println(" ");
		}
		return correcto;

	}
	/**
	 * Esté es el método listarAlquiler de la clase Administrador, este método realiza un select en la BBDD 
	 * con todos parametros que contiene la columna alquiler,
	 * Hace un listado con todos los alquileres realizados y que se encuentran almacenados.
	 *
	 */
	public String listarAlquiler(Connection con) throws SQLException {
		
		String listado = "";
		Scanner teclado = new Scanner(System.in);
		Statement stmt = null;
		String query = "select idAlquiler, idEstacionamiento, precioMensual, dni, matricula from alquiler";

		try {

			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			//String listado = "";

			System.out.println("");
			System.out.println("**** LISTA DE ALQUILERES ****");

			// listado += "**** LISTA DE ALQUILERES ****\n";

			while (rs.next()) {

				System.out.println("");
				System.out.println("*************************************");

				String idAlquiler = rs.getString("idAlquiler");
				System.out.println(" ID: " + idAlquiler);

				String idEstacionamiento = rs.getString("idEstacionamiento");
				System.out.println(" ID Estacionamiento: " + idEstacionamiento);

				String precioMensual = rs.getString("precioMensual");
				System.out.println(" Precio Mensual: " + precioMensual);

				String dni = rs.getString("dni");
				System.out.println(" DNI: " + dni);

				String matricula = rs.getString("matricula");
				System.out.println(" Matricula: " + matricula);

				System.out.println("*************************************");
				listado +=

						" Id: " + idAlquiler + "\n" + " Precio Mensual: " + precioMensual + "\n" + " DNI: " + dni + "\n"
								+ " Matricula: " + matricula + "\n";
				
				
				listado= rs.getString("dni");
			}
			if (listado.equals("")) {
				System.out.println("\n\n");
				System.err.println("No hay alquileres");
			} else {
				boolean valido = true;
				String temp = "**** LISTA DE ALQUILERES ****\n";
				temp += listado;
				temp += "\n";
				while (valido) {
					System.out.println("");
					System.out.println("¿Quieres exportar el listado?");
					System.out.println("         1) Si               ");
					System.out.println("         2) No               ");

					System.out.print("Opción: ");
					String opcion = teclado.nextLine();

					if (opcion == "") {
						System.out.println("Escoge una opción válida.");
						System.out.println(" ");

					} else {

						if (opcion.charAt(0) == '1') {

							ficherotxt(temp);
							valido = false;
						} else if (opcion.charAt(0) == '2') {

							valido = false;
						} else {
							
							System.out.println("Solo números entre 1 y 2");
							System.out.println(" ");
						}
					}
				}
			}
		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}
		return listado;
	}

	/**
	 * Esté es el método ficherotxt de la clase Administrador;
	 *  Este método nos muestra el listado de alquileres que ha sido exportado,.
	 */
	public void ficherotxt(String listado) {
		FileOutputStream f = null;
		try {
			f = new FileOutputStream("listado.txt");
			for (int i = 0; i < listado.length(); i++) {
				char c = listado.charAt(i);
				f.write((byte) c);
			}
			System.out.println("Listado exportado correctamente.");
			System.out.println("");
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				f.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}
	
	/**
	 * Esté es el método editarAlquiler de la clase Administrador, este método nos muestra el menú de edicion para un alquiler,
	 * pide al usuario un id de alquiler el cual esta en la bd si dicho id no se encuentra nos dara un error.
	 *
	 */
	public boolean editarAlquiler(Connection con, int id) throws SQLException {

		Scanner teclado = new Scanner(System.in);

		Statement stmt = null;
		boolean correcto = false;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * from alquiler where idAlquiler = " + id);

		if (rs.next()) {
			System.out.println("");
			System.out.println("1) Estacionamiento");
			System.out.println("2) Precio Mensual");
			System.out.println("3) DNI");
			System.out.println("4) Matricula");
			System.out.println("5) Salir");
			System.out.println("");
			System.out.print("¿Qué deseas editar?: ");

			String opcion = teclado.nextLine();
			System.out.println("");
			correcto = compruebaOpcionEditar(con, teclado, id, opcion);
		}

		else {
			System.err.println("No se reconoce el ID");
			System.out.println("");

		}
		return correcto;
	}

	/**
	 * Esté es el método compruebaOpcionEditar de la clase Administrador, este método se encarga de hacer updates del campo que desea editar el usuario,
	 * y lo almacena en la base de datos,
	 * pide al usuario insertar un nuevo estacionamiento, precio mensual, matricula o DNI.
	 *
	 */
	public boolean compruebaOpcionEditar(Connection con, Scanner teclado, int id, String opcion) throws SQLException {
		Statement stmt;
		ResultSet rs;
		boolean correcto =false;
		if (opcion == "") {
			System.err.println("Solo números entre 1 y 5");
			System.out.println(" ");

		} else {

			if (opcion.charAt(0) == '1') {

				System.out.print("Escribe el nuevo estacionamiento: ");
				String estacionamiento = teclado.nextLine();
				System.out.println("");
				stmt = con.createStatement();
				rs = stmt.executeQuery(
						"SELECT * from estacionamientos where idEstacionamiento = '" + estacionamiento + "'");
				if (rs.next()) {

					stmt = con.createStatement();
					stmt.executeUpdate("UPDATE `alquiler` SET `idEstacionamiento` = '" + estacionamiento
							+ "' WHERE `alquiler`.`idAlquiler` = " + id);

					System.out.println("Valor editado correctamente.");
					System.out.println(" ");
					correcto =true;

				} else {
					System.err.println("Estacionamiento incorrecto!");
					System.out.println("");
					editarAlquiler(con, id);
				}
			} else if (opcion.charAt(0) == '2') {

				System.out.print(" Escribe el nuevo precio mensual: ");
				try {
					double precioMensual = teclado.nextDouble();
					System.out.println("");

					stmt = con.createStatement();
					stmt.executeUpdate("UPDATE `alquiler` SET `precioMensual` = '" + precioMensual
							+ "' WHERE `alquiler`.`idAlquiler` = " + id);
					System.out.println("Valor editado correctamente.");
					System.out.println(" ");
					correcto =true;
					editarAlquiler(con, id);
				} catch (InputMismatchException e) {
					System.err.println("Introduce un precio válido!");
					editarAlquiler(con, id);
				}

			}

			else if (opcion.charAt(0) == '3') {

				System.out.print(" Escribe el nuevo DNI: ");

				String dni = teclado.nextLine();
				System.out.println("");
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT * from usuarios where dni = '" + dni + "'");
				if (rs.next()) {

					stmt = con.createStatement();
					stmt.executeUpdate(
							"UPDATE `alquiler` SET `dni` = '" + dni + "' WHERE `alquiler`.`idAlquiler` = " + id);
					System.out.println("Valor editado correctamente.");
					System.out.println(" ");
					correcto =true;
					editarAlquiler(con, id);

				} else {
					System.err.println("DNI incorrecto!");
					System.out.println("");
					editarAlquiler(con, id);
				}
			} else if (opcion.charAt(0) == '4') {

				System.out.print(" Escribe la nueva matricula: ");

				String matricula = teclado.nextLine();
				System.out.println("");

				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT * from vehiculos where matricula = '" + matricula + "'");
				if (rs.next()) {

					stmt = con.createStatement();
					stmt.executeUpdate("UPDATE `alquiler` SET `matricula` = '" + matricula
							+ "' WHERE `alquiler`.`idAlquiler` = " + id);
					System.out.println("Valor editado correctamente.");
					System.out.println(" ");
					correcto =true;
					editarAlquiler(con, id);
				} else {
					System.err.println("Matrícula incorrecta!");
					System.out.println("");
					editarAlquiler(con, id);
				}
			} else if (opcion.charAt(0) == '5') {

				System.out.print("Saliendo...");
				System.out.println("");
				correcto =true;

			} else {
				System.err.println("Solo números entre 1 y 5");
				System.err.println("Vuelve a intentarlo!!!");
				System.out.println(" ");
				System.out.println(" ");
				editarAlquiler(con, id);
			}
		}
	return correcto;
	}

	/**
	 * Esté es el método printSQLException de la clase Administrador, este método nos muestra sentencias de errores posibles,
	  * @param ex Objeto cuya información esta siendo mostrada.
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
	/**
	 * Esté es el método crearVehiulo de la clase Administrador, este método nos crea un vehiculo y lo almacena en la base de datos,
	 * pide al usuario una matricula que debe ser obligatoria y cada una de las caracteristicas de dicho vehiculo.
	 *
	 */
	public String crearVehiculo(Connection con, Scanner teclado) {
		boolean valido = false;
		String matricula = "";
		System.out.println("");
		System.out.println("== DATOS DEL NUEVO VEHÍCULO ==");
		System.out.println(" ");
		while (!valido) {
			System.out.print(" MATRÍCULA: ");
			matricula = teclado.nextLine();

			if (matricula.equals("")) {
				System.out.println("");
				System.err.println(" DEBES INTRODUCIR UNA MATRÍCULA!");
				System.out.println("");
				valido = false;
			} else {

				System.out.println(" ");
				System.out.print(" MARCA: ");
				String marca = teclado.nextLine();

				System.out.println(" ");
				System.out.print(" MODELO: ");
				String modelo = teclado.nextLine();

				System.out.println(" ");
				System.out.print(" COLOR: ");
				String color = teclado.nextLine();

				System.out.println(" ");
				System.out.print(" MOTOR: ");
				String motor = teclado.nextLine();

				System.out.println(" ");
				System.out.print(" TIPO DE VECHIULO: ");
				String tipoVehiculo = teclado.nextLine();

				Statement stmt = null;
				try {

					stmt = con.createStatement();
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

					ResultSet rs = stmt.executeQuery("SELECT * FROM vehiculos");

					// Nos posicionamos con el cursor en la próxima fila vacía de la tabla
					rs.moveToInsertRow();
					// Actualizamos los campos con los datos pasados por parámetro en la llamada al
					// método
					rs.updateString("matricula", matricula);
					rs.updateString("marca", marca);
					rs.updateString("modelo", modelo);
					rs.updateString("color", color);
					rs.updateString("motor", motor);
					rs.updateString("tipoVehiculo", tipoVehiculo);
					// Insertamos la nueva fila con los datos proporcionados
					rs.insertRow();
					// Nos posicionamos con el cursor antes de la primera fila
					rs.beforeFirst();

					System.out.println("");
					System.out.println(" Se ha insertado correctamente el nuevo vehiculo " + matricula + "!");
					System.out.println("");

					valido = true;

				} catch (SQLException e) {

					printSQLException(e);

				}
			}
		}
		return matricula;

	}
	/**
	 * Esté es el método crearAlquiler de la clase Administrador, este método nos crea un alquiler y lo almacena en la base de datos,
	 * devuelve del usuario una matricula, dni y actualiza el estacionamiento disponible.
	 * los inserta junto con precio mensual en un nuevo alquiler 
	 */
	public boolean crearAlquiler(Connection con, String idEstacionamiento, String dni, String matricula) {

		Statement stmt = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		double precioMensual = 0;
		boolean valido = true;
		boolean correcto = false;
		int disponible = 8;
		String query = "select precioMensual, disponible from estacionamientos where idEstacionamiento = '"
				+ idEstacionamiento + "'";
		try {

			stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery(query);

			if (rs2.next()) {
				precioMensual = rs2.getDouble("precioMensual");
				disponible = rs2.getInt("disponible");

			} else {
				System.err.println(" El ID de Estacionamiento no existe!");
				valido = false;
				stmt2 = con.createStatement();
				stmt2.executeUpdate("Delete from  usuarios where dni = '" + dni + "'");
				System.err.println(" Se ha eliminado el usuario con dni: " + dni);
				stmt2.executeUpdate("Delete from  vehiculos where matricula = '" + matricula + "'");
				System.err.println(" Se ha eliminado el vehiculo con matricula: " + matricula);
			}

			if (valido) {

				if (disponible == 1) {
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery("SELECT * FROM alquiler");

					// Nos posicionamos con el cursor en la próxima fila vacía de la tabla
					rs.moveToInsertRow();
					// Actualizamos los campos con los datos pasados por parámetro en la llamada al
					// método
					rs.updateInt("idAlquiler", 0);
					rs.updateString("idEstacionamiento", idEstacionamiento.toUpperCase());
					rs.updateDouble("precioMensual", precioMensual);
					rs.updateString("dni", dni);
					rs.updateString("matricula", matricula);

					// Insertamos la nueva fila con los datos proporcionados
					rs.insertRow();
					// Nos posicionamos con el cursor antes de la primera fila
					rs.beforeFirst();

					System.out.println("");
					System.out.println(" Se ha insertado correctamente un nuevo alquiler!");
					System.out.println("");

					stmt3 = con.createStatement();
					stmt3.executeUpdate(
							//actualiza el valor disponible a 0 en la bbdd
							"UPDATE `estacionamientos` SET `disponible` = 0  WHERE `estacionamientos`.`idEstacionamiento` = '"
									+ idEstacionamiento + "'");
					correcto = true;
				} else {
					System.out.println("El ID de Estacionamiento no está disponible!");
					stmt2 = con.createStatement();
					stmt2.executeUpdate("Delete from  usuarios where dni = '" + dni + "'");
					System.err.println(" Se ha eliminado el usuario con dni: " + dni);
					stmt2.executeUpdate("Delete from  vehiculos where matricula = '" + matricula + "'");
					System.err.println(" Se ha eliminado el vehiculo con matricula: " + matricula);

				}
			}
		} catch (SQLException e) {

			printSQLException(e);

		}
		return correcto;
	}
}

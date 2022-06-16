package clases;

import java.sql.*;
import java.util.Scanner;
/**
 * Esta es la clase Cliente que hereda los métodos, atributos y constructor de la clase abstracta Persona.
 * @author Adrian tur, Adrian marzo, Miriam fernandez, Sergio Bibiloni, Daniel yela 
 *
 */
public class Cliente extends Persona {

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
	public Cliente(String nombre, String apellidos, String dni, String direccion, int cuentaCorriente, String usuario,
			String contrasena) {
		super(nombre, apellidos, dni, direccion, cuentaCorriente);
		this.usuario = usuario;
		this.contrasena = contrasena;
	}

	public Cliente() {

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
	 * Esté es el método buscartuInformacion de la clase Cliente, este método nos muestra la información del usuario,
	 * pide un DNI almacenado en la BBDD al usuario y lo muestra.
	 */
	// Métodos
	public String buscartuInformacion(Connection con) throws SQLException {

		String dni;
		Statement stmt = null;
		String nombre = "";
		Scanner teclado = new Scanner(System.in);
		
		System.out.println(" Introduce tu DNI: ");
		dni = teclado.nextLine();

		String query = "SELECT dni, nombre, apellidos, direccion, cuentaCorriente from usuarios where dni = " + "'"
				+ dni + "'";
		

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			

			while (rs.next()) {
				System.out.println(" ");
				System.out.println(" ");
				System.out.println("======================================");
				System.out.println("======================================");
				System.out.println("");
				System.out.println(" == USUARIO CLIENTE == ");
				System.out.println("\n Nombre: " + rs.getString("nombre"));
				System.out.println("\n Apellido: " + rs.getString("apellidos"));
				System.out.println("\n DNI: " + rs.getString("dni"));
				System.out.println("\n Dirección " + rs.getString("direccion"));
				System.out.println("\n Cuenta Corriente: " + rs.getString("cuentaCorriente"));
				System.out.println("");
				System.out.println("======================================");
				System.out.println("======================================");
				System.out.println("");
				System.out.println(" ");
				nombre= rs.getString("nombre");
				
			}
			if(nombre.equals("")) {
				System.out.println("\n\n");
				System.err.println("Este DNI no está en la base de datos.");
				System.out.println(" ");
				}
		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			if(stmt != null) {
			stmt.close();
			}
		}
		return nombre;
	}

	/**
	 * Esté es el método buscartuVehiculo de la clase Cliente, este método nos muestra la información del vehiculo,
	 * pide una matricula almacenada en la BBDD al usuario y lo muestra.
	 *
	 */
	public String buscartuVehiculo(Connection con) throws SQLException {

		String matricula;
		Statement stmt = null;
		Statement stmt2 = null;
		String marca = "";
		Scanner teclado = new Scanner(System.in);
		System.out.println(" Introduce la matricula: ");
		matricula = teclado.nextLine();

		String query = "SELECT matricula, marca, modelo, TipoVehiculo from vehiculos where matricula = " + "'"
				+ matricula + "'";

		String query2 = "SELECT idEstacionamiento from alquiler where matricula = " + "'" + matricula + "'";
		String idEstacionamiento ="";
		try {
			stmt = con.createStatement();
			stmt2 = con.createStatement();
			
			
			ResultSet rs = stmt.executeQuery(query);
			ResultSet rs2 = stmt2.executeQuery(query2);
			
			
			while (rs2.next()) {
				System.out.println(" ");
				System.out.println("======================================");
				System.out.println("======================================");
				System.out.println(" == VEHICULO DEL CLIENTE == ");
				System.out.println("\n Número de estacionamiento: " + rs2.getString("idEstacionamiento"));
				idEstacionamiento = rs2.getString("idEstacionamiento");
			

				while (rs.next()) {

					System.out.println("\n Marca: " + rs.getString("marca"));
					System.out.println("\n Modelo: " + rs.getString("modelo"));
					System.out.println("\n Tipo de vehículo: " + rs.getString("TipoVehiculo"));
					System.out.println("======================================");
					System.out.println("======================================");
					marca = rs.getString("marca");
				}
				
			}
			
			if(idEstacionamiento.equals("")) {
				System.out.println("\n\n");
				System.out.println("");
				System.err.println("Esta matricula no está en la base de datos.");
				System.out.println(" ");
				}	

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			if (stmt != null) {
			stmt.close();
			}
		}
		return marca;

	}

	/**
	 * Este es el método printSQLException de la clase Cliente, este método nos muestra sentencias de errores posibles,
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

}


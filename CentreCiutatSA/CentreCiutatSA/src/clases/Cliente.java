package clases;

import java.sql.*;
import java.util.Scanner;
/**
 * Esta es la clase Cliente que hereda los m�todos, atributos y constructor de la clase abstracta Persona.
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
	 * @param contrase�a Utiliza el atributo contrase�a para crear el objeto.
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
	 * Este m�todo nos retorna el valor actual de usuario.
	 * @return usuario valor de usuario.
	 */
	// Getters & Setters
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * Este m�todo sirve para introducir un valor a usuario.
	 * @param usuario valor para usuario.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Este m�todo nos retorna el valor actual de constrase�a.
	 * @return contrase�a valor de contrase�a.
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Este m�todo sirve para introducir un valor a contrase�a.
	 * @param contrase�a valor para contrase�a.
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Est� es el m�todo buscartuInformacion de la clase Cliente, este m�todo nos muestra la informaci�n del usuario,
	 * pide un DNI almacenado en la BBDD al usuario y lo muestra.
	 */
	// M�todos
	public void buscartuInformacion(Connection con) throws SQLException {

		String dni;
		Statement stmt = null;

		Scanner teclado = new Scanner(System.in);
		
		System.out.println(" Introduce tu DNI: ");
		dni = teclado.nextLine();

		String query = "SELECT dni, nombre, apellidos, direccion, cuentaCorriente from usuarios where dni = " + "'"
				+ dni + "'";
		

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String nombre = "";

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
				System.out.println("\n Direcci�n " + rs.getString("direccion"));
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
				System.err.println("Este DNI no est� en la base de datos.");
				System.out.println(" ");
				}
		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

	}

	/**
	 * Est� es el m�todo buscartuVehiculo de la clase Cliente, este m�todo nos muestra la informaci�n del vehiculo,
	 * pide una matricula almacenada en la BBDD al usuario y lo muestra.
	 *
	 */
	public void buscartuVehiculo(Connection con) throws SQLException {

		String matricula;
		Statement stmt = null;
		Statement stmt2 = null;

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
				System.out.println("\n N�mero de estacionamiento: " + rs2.getString("idEstacionamiento"));
				idEstacionamiento = rs2.getString("idEstacionamiento");
			

				while (rs.next()) {

					System.out.println("\n Marca: " + rs.getString("marca"));
					System.out.println("\n Modelo: " + rs.getString("modelo"));
					System.out.println("\n Tipo de veh�culo: " + rs.getString("TipoVehiculo"));
					System.out.println("======================================");
					System.out.println("======================================");
					
				}
				
			}
			
			if(idEstacionamiento.equals("")) {
				System.out.println("\n\n");
				System.err.println("Esta matricula no est� en la base de datos.");
				System.out.println(" ");
				}
			
				

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

	}

	/**
	 * Este es el m�todo printSQLException de la clase Cliente, este m�todo nos muestra sentencias de errores posibles,
	  * @param ex Objeto cuya informaci�n esta siendo mostrada.
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


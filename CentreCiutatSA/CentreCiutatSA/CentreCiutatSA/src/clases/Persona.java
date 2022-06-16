package clases;
/**
 *Esta clase Persona, es una clase abstracta que contiene el constructor y cada uno de los métodos necesarios que
 * heredarán las demás clases. 
 * 
 * @author Adrian tur, Adrian marzo, Miriam fernandez, Sergio Bibiloni, Daniel yela 
 *
 */
public abstract class Persona {

	//Atributos
	
	private String nombre, apellidos, dni, direccion;
	private int cuentaCorriente;
	
	/**
	 * Este es el constructor de la clase abstracta Persona que heredarán las demás clases.
	 * @param nomnbre Utiliza el atributo nombre para crear el objeto.
	 * @param apellidos Utiliza el atributo apellidos para crear el objeto.
	 * @param dni Utiliza el atributo dni para crear el objeto.
	 * @param direccion Utiliza el atributo direccion para crear el objeto.
	 * @param cuentaCorriente Utiliza el atributo cuentaCorriente para crear el objeto.
	 */
	
	//Constructor
	public Persona(String nombre, String apellidos, String dni, String direccion, int cuentaCorriente) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
		this.cuentaCorriente = cuentaCorriente;
	}
	
	public Persona() {
		
	}
	/**
	 * Este método nos devuelve el valor de nombre.
	 * @return nombre Retorna el valor actual de nombre.
	 */
	//Getters & Setters
	public String getNombre() {
		return nombre;
	}
	/**
	 * Este método sirve para introducir un valor a nombre.
	 * @param nombre Nuevo valor para nombre.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Este método nos devuelve el valor de apellidos.
	 * @return apellidos Retorna el valor actual de apellidos.
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * Este método sirve para introducir un valor a apellidos.
	 * @param apellidos Nuevo valor para apellidos.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * Este método nos devuelve el valor de dni.
	 * @return dni Retorna el valor actual de dni.
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * Este método sirve para introducir un valor a dni.
	 * @param dni Nuevo valor para dni.
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * Este método nos devuelve el valor de direccion.
	 * @return direccion Retorna el valor actual de direccion.
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * Este método sirve para introducir un valor a direccion.
	 * @param direccion Nuevo valor para direccion.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * Este método nos devuelve el valor de cuentaCorriente.
	 * @return cuentaCorriente Retorna el valor actual de cuentaCorriente.
	 */
	public int getCuentaCorriente() {
		return cuentaCorriente;
	}
	/**
	 * Este método sirve para introducir un valor a cuentaCorriente.
	 * @param cuentaCorriente nuevo valor para cuentaCorriente.
	 */
	public void setCuentaCorriente(int cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}	
	
}

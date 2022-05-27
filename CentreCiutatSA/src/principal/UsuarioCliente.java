package principal;

import java.sql.*;

import clases.Cliente;

public class UsuarioCliente {

	public static void main(String[] args) {

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/centreciutat", "root", "");
			System.out.println("Conexi�n establecida con la base de datos ciutat centre");
			Cliente c1 = new Cliente();
			c1.buscartuInformacion(con);
		} catch (SQLException e) {
			System.out.println("Error connexi�n BBDD");
			e.printStackTrace();
		}

	}
	
	Scanner NumScanner1 = new Scanner(System.in);
	Scanner TecladoScanner = new Scanner (System.in);   
	Scanner sc = new Scanner(System.in);
	Scanner sn = new Scanner(System.in);
	boolean salir = false;
	int opcion;
	 
	 
	System.out.println(" ");
	System.out.println("======================================");
	System.out.println("========| Centre Ciutat S.A. |========");
	System.out.println("======================================");
	System.out.println(" ");
	System.out.println(" ");
	System.out.println("==        Cual es su nombre?        ==");
	System.out.println(" ");
	String nombre = sc.nextLine();
	System.out.println(" ");
	System.out.println("==            Contrase�a             ==");
	System.out.println(" ");
	String contrase�a = sc.nextLine();
	System.out.println(" ");
	System.out.println("          Bienvenido  " + nombre);
	System.out.println(" ");
	 
	 
	while(!salir){
	System.out.println("========== QUE DESEA HACER? ==========");
	System.out.println(" ");
    System.out.println("(Seleccione la opci�n que desee con ");
	System.out.println("las teclas * 1 - 2 o 3* )");
	System.out.println(" ");
	System.out.println(" 1) Mostrar informaci�n de su vehiculo");
	System.out.println(" 2) Mostrar su informaci�n personal");
	System.out.println(" 3) Salir de la aplicaci�n ");
	System.out.println(" ");
	System.out.println("======================================");
	System.out.println(" ");
	opcion = sn.nextInt();

	switch(opcion){
    	case 1:
    	
    		//CONTECTAR CON BASE DE DATOS Y MOSTRAR POR PATNALLA INFORMACI�N DEL VAHICULO DEL CLIENTE
    		
    	break;
	case 2:
		
			//CONTECTAR CON BASE DE DATOS Y MOSTRAR POR PATNALLA INFORMACI�N DEL CLIENTE 
		
    	break;       	
 	case 3:                      	 
    	salir=true;
    	System.out.println("== SALIENDO DEL PROGRAMA ==");
    	System.out.println(" ");
    	System.out.println("Gracias por tu colaboraci�n " +nombre+ ", que tengas un buen dia");
    	break;
 	default:
    	System.out.println("Solo n�meros entre 1 y 3");
    	System.out.println(" ");  	 
  	 
  	}	 
	} 	    	 

}

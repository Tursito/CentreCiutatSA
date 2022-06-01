package principal;

import java.sql.*;
import java.util.Scanner;

import clases.Cliente;

public class UsuarioCliente {

	public static void main(String[] args) {

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/CentreCiutat", "tur", "tur");
			System.out.println("Conexión establecida con la base de datos CentreCiutat S.A.");
			
			
			Cliente c1 = new Cliente();
			Scanner sc = new Scanner(System.in);
			
			
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
		  	String nombreIntroducido = sc.nextLine();
		  	System.out.println(" ");
		  	System.out.println("==            Contraseña             ==");
		  	System.out.println(" ");
		  	String contraseñaIntroducida = sc.nextLine();
		  	
		  	System.out.println(" ");
		  	System.out.println(" ");
		  	System.out.println(" ");
		  	System.out.println(" ");
			
			Statement stmt = null;
			
			String query = "SELECT nombre, apellidos, admin, contrasena from usuarios where nombre = " + "'"
					+ nombreIntroducido + "'";
		  	try {
		  
		  	
		  	stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			String nombreUsuario = "";
			String apellidosUsuario = "";
			String esAdmin = "";
			String contrasena = "";
			
			
			while (rs.next()) {
				 nombreUsuario = rs.getString("nombre");
				 esAdmin = rs.getString("admin");
				 apellidosUsuario = rs.getString("apellidos");
				 contrasena = rs.getString("contrasena");
				 
				 
				 if (nombreUsuario.equals(nombreIntroducido)&& contrasena.equals(contraseñaIntroducida) && esAdmin.equals("0") ) {
					salir = menuCliente(con, c1, sc, salir, nombreIntroducido, nombreUsuario, apellidosUsuario); 
					

					}else if(nombreUsuario.equals(nombreIntroducido) && esAdmin.equals("1")){
					
						salir = menuAdmin(sc, salir); 	    	 
					}
				 
				 
				 
				 else {
						System.out.println("No se encuentra en la base de datos  ");
					 }
				  	
				
			}
		  	
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			stmt.close();
		}
			
			
			
			
			
			
		} catch (SQLException e) {
			System.out.println("Error connexión BBDD");
			e.printStackTrace();
		}

	
	
	
		    }





	public static boolean menuAdmin(Scanner sc, boolean salir) {
		int opcion;
		while(!salir){
			System.out.println("========== Bienvenid@  ==========");
			System.out.println(" ");
		    System.out.println("(Seleccione la opción que desee con ");
			System.out.println("las teclas * 1 - 2 - 3 - 4 o 5* )");
			System.out.println(" ");
			System.out.println(" 1) Listar alquileres");
			System.out.println(" 2) Editar alquileres");
			System.out.println(" 3) Eliminar alquileres ");
			System.out.println(" 4) Crear nuevo usuario ");
			System.out.println(" 5) Cerrar sesión ");
			System.out.println(" ");
			System.out.println("======================================");
			System.out.println(" ");
			opcion = sc.nextInt();

			switch(opcion){
		    	case 1:
		    	
		    		//CONTECTAR CON BASE DE DATOS Y MOSTRAR POR PATNALLA LISTADO DE ALQUILERES
		    		
		    	break;
			case 2:
				
					//CONTECTAR CON BASE DE DATOS PARA EDITAR ALQUILERES
				
		    	break;  
			case 3:
				
				//CONTECTAR CON BASE DE DATOS PARA ELIMINAR ALQUILERES
				
				break;
			case 4:
		    	
				//CONTECTAR CON BASE DE DATOS PARA CREAR USUARIO
				
				break;
		 	case 5:                      	 
		    	salir=true;
		    	System.out.println("== SALIENDO DEL PROGRAMA ==");
		    	System.out.println(" ");
		    	System.out.println("Gracias por tu colaboración, que tengas un buen dia");
		    	break;
		 	default:
		    	System.out.println("Solo números entre 1 y 5");
		    	System.out.println(" ");  	 
		  	 
		  	}	 
			}
		return salir;
	}
	
	
	
	 

	public static boolean menuCliente(Connection con, Cliente c1, Scanner sc, boolean salir, String nombreIntroducido,
			String nombreUsuario, String apellidosUsuario) throws SQLException {
		int opcion;
		//SQL que recoga los datos y poner el nombre del usuario 
		  	
		  	System.out.println("Bienvenido " + nombreUsuario + " " + apellidosUsuario);
		  	System.out.println(" ");
		  	
			
		  	 
			while(!salir){
			System.out.println("========== QUE DESEA HACER? ==========");
			System.out.println(" ");
		    System.out.println("(Seleccione la opción que desee con ");
			System.out.println("las teclas * 1 - 2 o 3* )");
			System.out.println(" ");
			System.out.println(" 1) Mostrar información de su vehiculo");
			System.out.println(" 2) Mostrar su información personal");
			System.out.println(" 3) Salir de la aplicación ");
			System.out.println(" ");
			System.out.println("======================================");
			System.out.println(" ");
			opcion = sc.nextInt();
			

			switch(opcion){
		    	case 1:
		    	c1.buscartuVehiculo(con);
		    		//CONTECTAR CON BASE DE DATOS Y MOSTRAR POR PANTALLA INFORMACIÓN DEL VEHÍCULO DEL CLIENTE
		    		
		    	break;
			case 2:
				
					//CONTECTAR CON BASE DE DATOS Y MOSTRAR POR PANTALLA INFORMACIÓN DEL CLIENTE 
				
				c1.buscartuInformacion(con);
				
		    	break;       	
		 	case 3:                      	 
		    	salir=true;
		    	System.out.println("== SALIENDO DEL PROGRAMA ==");
		    	System.out.println(" ");
		    	System.out.println("Gracias por tu colaboración " +nombreIntroducido+ ", que tengas un buen dia");
		    	System.exit(0);
		    	break;
		 	default:
		    	System.out.println("Solo números entre 1 y 3");
		    	System.out.println(" ");  	 
		  	 
		  	}	 
			}
		return salir;
	}
	}	 



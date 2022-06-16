package pruebas;

import java.sql.*;
import org.junit.*;
import clases.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertTrue;
public class ClienteTest {
	Cliente a = null;
	Connection con =null;
	@Before
	public void setUp() throws Exception {
		 a  = new Cliente();
		 try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/centreciutat", "root", "");	

			} catch (SQLException e) {
				System.err.println("Error connexión BBDD");
				e.printStackTrace();
				
			}
	}

	@Test
	public void testBuscartuInformacion() {
		//given
		System.out.println(" ");
		//when 
		try {
			Assert.assertEquals("Antonio", a.buscartuInformacion(con));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//then
		System.out.println("Test Buscar Información correcto!!");
	}


	@Test
	public void testBuscartuVehiculo() {
		//given
		System.out.println(" ");
		//when 
		try {
			Assert.assertEquals("Mazda", a.buscartuVehiculo(con));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//then
		System.out.println("Test Buscar vehiculo correcto!!");
	}

	
	
}

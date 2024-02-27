package MongoDB.MongoDB;

import com.mongodb.*;
import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import org.bson.Document;
/** USO DE MONGODB **/
public class App 
{
	//Conexion a la base de datos
    //String connectionString = "mongodb+srv://userMongoDB:BniotcMmRa2Wx4aV@cluster0.cobu4re.mongodb.net/?retryWrites=true&w=majority";
	private static String connectionString = "mongodb://localhost:27017/";
	private static MongoDatabase database;
	private static MongoCollection<Document> collection;
	private static MongoClient mongoClient;
	
    public static void main( String[] args ) {
    	crearConexion();
    }
    public static void crearConexion() {
        try { 
        	mongoClient = MongoClients.create(new ConnectionString(connectionString));
        	database = mongoClient.getDatabase("MongoDatabase");
        	collection = database.getCollection("MongoCollection");
    	}
    	catch (Exception e) { System.out.println(e.getMessage());}
    }
    //Metodo para insertar los datos a la coleccion
    public static void insertarDatos(String nombre,int edad,String ciudad) {
    	Document document = new Document("nombre",nombre).append("edad", edad).append("ciudad", ciudad);
    	collection.insertOne(document);
    }
    //Elimina el primer resultado
    public static void eliminarDatosNombre(String nombre) {
    	try {
    		collection.deleteOne(eq("nombre", nombre));
    	} catch (Exception e) { System.out.println(e.getMessage());}
    }
    //Elimina todos los resultados que cumplan las condiciones
    public static void eliminarDatosMultiplesNombre(String nombre) {
    	try {
    		collection.deleteMany(eq("nombre", nombre));
    	} catch (Exception e) { System.out.println(e.getMessage());}
    }    
    //Modifica los datos del cmapo con el nombre indicado
    public static void modificarDatosNombre(String nombre,String unombre) {
    	try {
    		collection.updateOne(eq("nombre", nombre),set("nombre",unombre));
    	} catch (Exception e) { System.out.println(e.getMessage());}
    }
    //Metodo para ver todos los datos de la coleccion
    public static void mostrarDatos() {
    	try {
    		MongoCursor<Document> cursor = collection.find().iterator();
    		while (cursor.hasNext()) {
    			Document document = cursor.next();
    			System.out.println("Nombre: " + document.getString("nombre"));
    			System.out.println("Edad: " + document.getInteger("edad"));
    			System.out.println("Ciudad: " + document.getString("ciudad"));
    		}
    		cursor.close();
    	} 
    	catch (Exception e) { System.out.println(e.getMessage());}
    }	
    //Muestra los datos de los campos con el nombre indicado
    public static void mostrarDatosNombre(String nombre) {
    	try {
    		//Document query = new Document("age", new Document("$gt", 30));
    		Document query = new Document("nombre", nombre);
    		MongoCursor<Document> cursor = collection.find(query).iterator();
    		while (cursor.hasNext()) {
    			Document document = cursor.next();
    			System.out.println("Nombre: " + document.getString("nombre"));
    			System.out.println("Edad: " + document.getInteger("edad"));
    			System.out.println("Ciudad: " + document.getString("ciudad"));
    		}
    		cursor.close();
    	} 
    	catch (Exception e) { System.out.println(e.getMessage());}
    }
    //Muestra los datos de los campos con la edad indicada
    public static void mostrarDatosEdad(int edad) {
    	try {
    		//Document query = new Document("age", new Document("$gt", 30));
    		Document query = new Document("edad", edad);
    		MongoCursor<Document> cursor = collection.find(query).iterator();
    		while (cursor.hasNext()) {
    			Document document = cursor.next();
    			System.out.println("Nombre: " + document.getString("nombre"));
    			System.out.println("Edad: " + document.getInteger("edad"));
    			System.out.println("Ciudad: " + document.getString("ciudad"));
    		}
    		cursor.close();
    	} 
    	catch (Exception e) { System.out.println(e.getMessage());}
    }
    //Muestra los datos de los campos con edad mayor a la indicada
    public static void mostrarDatosEdadMayor(int edad) {
    	try {
    		Document query = new Document("edad", new Document("$gt",edad));
    		MongoCursor<Document> cursor = collection.find(query).iterator();
    		while (cursor.hasNext()) {
    			Document document = cursor.next();
    			System.out.println("Nombre: " + document.getString("nombre"));
    			System.out.println("Edad: " + document.getInteger("edad"));
    			System.out.println("Ciudad: " + document.getString("ciudad"));
    		}
    		cursor.close();
    	} 
    	catch (Exception e) { System.out.println(e.getMessage());}
    }
    //Muestra los datos de los campos con edad menor a la indicada
    public static void mostrarDatosEdadMenor(int edad) {
    	try {
    		Document query = new Document("edad", new Document("$lt",edad));
    		MongoCursor<Document> cursor = collection.find(query).iterator();
    		while (cursor.hasNext()) {
    			Document document = cursor.next();
    			System.out.println("Nombre: " + document.getString("nombre"));
    			System.out.println("Edad: " + document.getInteger("edad"));
    			System.out.println("Ciudad: " + document.getString("ciudad"));
    		}
    		cursor.close();
    	} 
    	catch (Exception e) { System.out.println(e.getMessage());}
    }
}

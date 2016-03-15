import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeepClone {

	//creates a true copy of another object and all data structure classes in the object it copies must implement seriable
		public static Object deepClone(Object object)
		{
				
				//converts an object and breaks it down into array bytes
				try {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(baos);
					//writes the object into a memory or file
					oos.writeObject(object);
					// shell object reconstructs all the pieces together and you get new instance
					ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
					ObjectInputStream ois = new ObjectInputStream(bais);
					
					try {
						//read from a file or memory
						return ois.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null;
				
		}
}

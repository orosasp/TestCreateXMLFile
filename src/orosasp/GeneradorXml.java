package orosasp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.thoughtworks.xstream.XStream;

public class GeneradorXml {

	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir") + "\\test.xml";
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("Archivo XML creado correctamente");
			} catch (Exception e) {
				System.out.println("Ocurrio un error creando el archivo");
				System.out.println(e.getMessage());
			}
		}
		
		
		Emisor emisor = new Emisor("FAU210730D75", "FULLE AUTOMOTRIZ", "626");
		
		XStream xstream = new XStream();
		xstream.alias("cfdi:Emisor", Emisor.class);
		
		xstream.useAttributeFor(Emisor.class, "rfc");
		xstream.useAttributeFor(Emisor.class, "nombre");
		xstream.useAttributeFor(Emisor.class, "regimenFiscal");
		xstream.aliasField("Rfc", Emisor.class, "rfc");
		xstream.aliasField("Nombre", Emisor.class, "nombre");
		xstream.aliasField("RegimenFiscal", Emisor.class, "regimenFiscal");
		
		try (OutputStream outputStream = new FileOutputStream(filePath); Writer writer = new OutputStreamWriter(outputStream)) {
			xstream.toXML(emisor, writer);
		} catch (Exception e) {
			System.out.println("Ocurrio un error escribiendo el archivo XML");
		}
		
	}

}

package orosasp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GeneradorXml {
	
	private static String filePath = System.getProperty("user.dir") + "\\test.xml";;
	
	private static List<Emisor> listaEmisores;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("cfdi:Emisor", Emisor.class);
		xstream.useAttributeFor(Emisor.class, "rfc");
		xstream.useAttributeFor(Emisor.class, "nombre");
		xstream.useAttributeFor(Emisor.class, "regimenFiscal");
		xstream.alias("Emisores", List.class);
		xstream.aliasField("Rfc", Emisor.class, "rfc");
		xstream.aliasField("Nombre", Emisor.class, "nombre");
		xstream.aliasField("RegimenFiscal", Emisor.class, "regimenFiscal");
		
		File file = new File(filePath);
		if (file.exists()) {
			try (InputStream inputStream = new FileInputStream(file); Reader reader = new InputStreamReader(inputStream, Charset.forName("UTF8"))) {
				listaEmisores = (List<Emisor>) xstream.fromXML(reader);
				List<Emisor> nuevalistaEmisores = new ArrayList<Emisor>();
				if (!listaEmisores.isEmpty() && listaEmisores.size() >= 1) {
					nuevalistaEmisores.addAll(listaEmisores);
				}
				Emisor emisor = new Emisor("EEU210730D75", "EFULL AUTOMOTRIZ", "AE26");
				nuevalistaEmisores.add(emisor);
				
				try (OutputStream outputStream = new FileOutputStream(filePath); Writer writer = new OutputStreamWriter(outputStream)) {
					xstream.toXML(nuevalistaEmisores, writer);
					System.out.println("Dato agregado correctamente en el XML");
				} catch (Exception e) {
					System.out.println("Ocurrio un error escribiendo el archivo XML");
					System.out.println(e.getMessage());
				}
			}
			catch (Exception e) {
				System.out.println("Ocurrio un error al recurperar la informacion del XML");
				System.out.println(e.getMessage());
				return;
			}
		} else {
			try {
				file.createNewFile();
				System.out.println("Archivo XML creado correctamente");
				
				List<Emisor> listaEmisores = new ArrayList<Emisor>();
				Emisor emisor = new Emisor("FAU210730D75", "FULL AUTOMOTRIZ", "626");
				listaEmisores.add(emisor);
				try (OutputStream outputStream = new FileOutputStream(filePath); Writer writer = new OutputStreamWriter(outputStream)) {
					xstream.toXML(listaEmisores, writer);
				} catch (Exception e) {
					System.out.println("Ocurrio un error escribiendo el archivo XML");
					System.out.println(e.getMessage());
				}
			} catch (Exception e) {
				System.out.println("Ocurrio un error creando el archivo");
				System.out.println(e.getMessage());
			}
		}
	}
}

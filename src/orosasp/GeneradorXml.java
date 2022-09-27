package orosasp;

import com.thoughtworks.xstream.XStream;

public class GeneradorXml {

	public static void main(String[] args) {
		Emisor emisor = new Emisor("FAU210730D74", "FULLE AUTOMOTRIZ", "626");
		
		XStream xstream = new XStream();
		xstream.alias("cfdi:Emisor", Emisor.class);
		
		xstream.useAttributeFor(Emisor.class, "rfc");
		xstream.useAttributeFor(Emisor.class, "nombre");
		xstream.useAttributeFor(Emisor.class, "regimenFiscal");
		xstream.aliasField("Rfc", Emisor.class, "rfc");
		xstream.aliasField("Nombre", Emisor.class, "nombre");
		xstream.aliasField("RegimenFiscal", Emisor.class, "regimenFiscal");
		
		System.out.println(xstream.toXML(emisor));
	}

}

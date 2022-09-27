package orosasp;

public class Emisor {
	
	private String rfc;
	
	private String nombre;
	
	private String regimenFiscal;
	
	public Emisor(String rfc, String nombre, String regimenFiscal) {
		super();
		this.rfc = rfc;
		this.nombre = nombre;
		this.regimenFiscal = regimenFiscal;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}
	
}

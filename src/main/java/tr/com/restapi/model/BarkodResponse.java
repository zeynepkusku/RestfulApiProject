package tr.com.restapi.model;

public class BarkodResponse {
	private int id;
	private int kullaniciId;
	private String barkod;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKullaniciId() {
		return kullaniciId;
	}
	public void setKullaniciId(int kullaniciId) {
		this.kullaniciId = kullaniciId;
	}
	public String getBarkod() {
		return barkod;
	}
	public void setBarkod(String barkod) {
		this.barkod = barkod;
	}
	
	
}

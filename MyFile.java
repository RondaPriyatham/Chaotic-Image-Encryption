package capstone;

public class MyFile {
	private int id;
	private String name;
	private byte[] data;
	private String fileExtension;
	public MyFile(int id,String name,byte[] data,String f) {
		this.id=id;
		this.name=name;
		this.data=data;
		this.fileExtension=f;
	}
	public String getName() {
		return this.name;
	}
	public byte[] getdata() {
		return this.data;
	}
	public String getExtension() {
		return this.fileExtension;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	public void setname(String name) {
		this.name=name;
	}
	public void setdata(byte[] data) {
		this.data=data;
	}
	public void setId(String file) {
		this.fileExtension=file;
	}
	public int getId() {
		return id;
	}
}

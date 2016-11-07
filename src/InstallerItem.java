import java.io.File;

public class InstallerItem {
	private String itemName;
	private String filePath;
	private String ext;
	
	
	public InstallerItem(String name, String path, String ext) {
		this.itemName = name;
		this.filePath = path;
		this.ext = ext;
	}
	
	public void setName(String name) {
		itemName = name;
	}
	
	public void setPath(String path) {
		filePath = path;
	}
	
	public void setExt(String fileExt) {
		ext = fileExt;
	}
	
	public String getName() {
		return itemName;
	}
	
	public String getPath() {
		return filePath;
	}
	
	public String getExt() {
		return ext;
	}
}

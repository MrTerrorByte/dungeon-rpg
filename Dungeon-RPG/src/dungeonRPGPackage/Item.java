package dungeonRPGPackage;

/**
 * @author jordan
 */
public class Item {
	
	private String name;				//name of item
	private String description;			//description of item as displayed when cursor hovers over item
	
	public Item(String name, String description){
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

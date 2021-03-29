
public class TV {
	String name;
	int price;
	String explain;
	public TV(String name, int price, String explain) {
		this.name = name;
		this.price = price;
		this.explain = explain;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	@Override
	public String toString() {
		return "TV [name=" + name + ", price=" + price + ", explain=" + explain + "]";
	}
	
	
	
	
}

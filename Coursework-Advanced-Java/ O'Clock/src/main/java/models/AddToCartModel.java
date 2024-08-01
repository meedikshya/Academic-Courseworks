package models;

public class AddToCartModel {
	private int id;
	
	private int productId;
	
	private String username;
	
public AddToCartModel() {
	
}

public AddToCartModel(int id, int productId, String username) {
	super();
	this.id = id;
	this.productId = productId;
	this.username = username;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}

}


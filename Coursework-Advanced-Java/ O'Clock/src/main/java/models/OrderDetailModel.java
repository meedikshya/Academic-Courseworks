package models;

public class OrderDetailModel {
    private int orderdetails_id;
    private int orderId;
    private int productId;
    private int quantity;
    private double price;
    
    public OrderDetailModel() {}
    
    
    public int getOrderdetails_id() {
		return orderdetails_id;
	}



	public void setOrderdetails_id(int orderdetails_id) {
		this.orderdetails_id = orderdetails_id;
	}



	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public int getProductId() {
		return productId;
	}



	public void setProductId(int productId) {
		this.productId = productId;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}
package models;

public class OrderModel {
    private int order_id;
    private String username;
    private double grandTotal;
    private int totalItems;
    private String orderStatus;

    


	public OrderModel() {}

	// Constructor
    public OrderModel(String username, double grandTotal, int totalItems, String orderStatus) {
        this.username = username;
        this.grandTotal = grandTotal;
        this.totalItems = totalItems;
        this.orderStatus = orderStatus;  
    }
    
    
    public int getOrder_id() {
		return order_id;
	}



	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public double getGrandTotal() {
		return grandTotal;
	}



	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}



	public int getTotalItems() {
		return totalItems;
	}



	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}

    
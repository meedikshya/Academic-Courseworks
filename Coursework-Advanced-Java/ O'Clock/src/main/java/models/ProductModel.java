package models;

import java.io.File;

import javax.servlet.http.Part;

import utils.StringUtils;
import utils.StringUtilsProduct;

public class ProductModel {
	
	private int productId;
	private String name;
    private String category;
    private double price;
    private int quantity;
    private String imageUrlFromPart;
//    private Part image; // Change image type from String to Part

    // Constructor
    public ProductModel( String name, String category, double price, int quantity, Part imagePart) {
//    	this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.imageUrlFromPart = getImageUrl(imagePart);
//        this.image = imagePath;
    }
    
    public ProductModel() {
    
    }
    // Constructor
    public ProductModel(String name, String category, double price, int quantity, String imagePart) {
//    	this.id=id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.imageUrlFromPart = imagePart;
//        this.image = imagePath;
    }

	 //Getters
    public int getProductId() {
     	return productId;
    }
    
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}
    
    // Setters
   public void setProductId(int productId) {
    	this.productId = productId;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    public void setImageUrlFromPart(Part part) {
		this.imageUrlFromPart = getImageUrl(part);
	}
	
public void setImageUrlFromDB(String imageUrl) {
		this.imageUrlFromPart = imageUrl;
}
	
	 @Override
	 //to make the console output look better
	    public String toString() {
	        return "ProductModel{" +
	                "name='" + name + '\'' +
	                ", category='" + category + '\'' +
	                ", price=" + price +
	                ", quantity=" + quantity +
	                ", imageUrlFromPart='" + imageUrlFromPart + '\'' +
	                '}';
	    }
	
	private String getImageUrl(Part part) {
		String savePath = StringUtilsProduct.IMAGE_DIR_PRODUCT;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
				break;
			}
		}
		 // If no filename was extracted from the Content-Disposition header, set a default filename.
		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "download.jpg";
		}
		return imageUrlFromPart;
	}

}
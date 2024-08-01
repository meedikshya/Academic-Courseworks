package models;

import java.io.File;

import javax.servlet.http.Part;

import utils.StringUtilsUserProduct;

public class UserProductModel {
	private int id;
	private String name;
	private String category;
	private double price;
	private int quantity;
    private String imageUrlFromPart;
    
public UserProductModel() {
		
	}
public UserProductModel(int id, String name, String category,double price, int quantity, Part imagePart) {
	this.id = id;
	this.name =name;
	this.price = price;
	this.category = category;
	this.quantity = quantity;
    this.imageUrlFromPart = getImageUrl(imagePart);
	
}

public UserProductModel(int id, String name, String category, double price,int quantity, String imagePart) {
	this.id = id;
	this.name =name;
	this.category = category;
	this.price = price;
	this.quantity = quantity;
    this.imageUrlFromPart = imagePart;
	
}




public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getCategory() {
    return category;
}

public void setCategory(String category) {
    this.category = category;
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

public String getImageUrlFromPart() {
    return imageUrlFromPart;
}

public void setImageUrlFromPart(String imageUrlFromPart) {
    this.imageUrlFromPart = imageUrlFromPart;
}

public void setImageUrlFromPart(Part part) {
    this.imageUrlFromPart = getImageUrl(part);
}

public void setImageUrlFromDB(String imageUrl) {
    this.imageUrlFromPart = imageUrl;
}

@Override
public String toString() {
    return "ProductModel{" + "id='" + id + '\'' + "name='" + name + '\'' + ", description='" + category + '\''
            + ", price=" + price + ", imageUrlFromPart='" + imageUrlFromPart + '\'' + '}';
}

private String getImageUrl(Part part) {
String savePath = StringUtilsUserProduct.IMAGE_DIR_PRODUCT;
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

from read import openFile
from datetime import datetime

def writeTextFile(laptopDetailsDictionary):
    file = open("laptopDetails.txt", "w")
    for values in laptopDetailsDictionary.values():
        file.write(str(values[0]) + "," + str(values[1]) + "," + str(values[2]) +"," + str(values[3]) + "," + str(values[4]) + "," + str(values[5]))
        file.write("\n")
    file.close()


def invoiceBuy(validLaptopId, laptopQuantity, userName, customersDictionary):
    dateandtime = datetime.now()
    laptopDetailsDictionary = openFile()
    
    nameOfProduct = laptopDetailsDictionary[validLaptopId][0]
    totalQuantity = laptopQuantity
    unitPrice = (laptopDetailsDictionary[validLaptopId][2])
    price = laptopDetailsDictionary[validLaptopId][2].replace("$", '')
    totalPrice = int(price) * int(totalQuantity)
    dateandtime = datetime.now()
    datetime_ms = int(datetime.timestamp(datetime.now()) * 1000)
    brandName = laptopDetailsDictionary[validLaptopId][1]

    with open(str(userName) + str(datetime_ms) + ".txt", 'w') as buy:
        buy.write("\n")
        buy.write("-"*130)
        buy.write("\n")
        buy.write("Bit & Byte Store" + "\t\t\t\t\t\t\t\t\t\t" + " Date: " +dateandtime.strftime("%d" " " "%b"",""%Y"))
        buy.write("\n")
        buy.write("London,England" + "\t\t\t\t\t\t\t\t\t" "          " "Time:" +dateandtime.strftime("%I" ":" "%M" "%p"))
        buy.write("\n")
        buy.write("bitandbyte@gmail.com")
        buy.write("\n")
        buy.write("073599487")
        buy.write("\n")
        buy.write("\n")
        buy.write("\n")
        buy.write("\n")
        buy.write("Bill Number:" + str(datetime_ms))
        buy.write("\n")
        buy.write("\n")
        buy.write("-"*130)
        buy.write("\n")
        buy.write("S.N" + "\t\t" + "Product's Name" + "\t\t" + "Brand" +"\t\t  " + "Quantity" + "\t\t  " + " Unit Price" + "\t\t\t" + "Amount")
        buy.write("\n")
        buy.write("-"*130)
        buy.write("\n")
        buy.write("\n")
        counter = 1
        total = 0
        
        for key in customersDictionary:
            buy.write(str(counter) + "\t\t" + str(key[0]) + "\t\t   " + str(key[1]) +"\t\t\t" + str(key[2]) + "\t\t\t" + str(key[3]) + "\t\t\t\t" + "$" + str(key[5]))
            buy.write("\n")
            buy.write("\n")
            counter = counter+1
            total = total+int(key[5])

        buy.write("\n")
        buy.write("-"*130)
        buy.write("\n")

        buy.write("Net Amount:" + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + "$" + str(total))
        vatAmount = 0.13 * total
        buy.write("\n")
        buy.write("Vat Amount:" + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + "$" + str(vatAmount))
        buy.write("\n")
        buy.write("-"*130)
        buy.write("\n")
        buy.write("Gross Amount:" + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "$" + str(total + vatAmount))
        buy.write("\n")
        buy.write("-"*130)
        buy.write("\n")
        buy.write("\n")
        buy.write("Please note that 13% VAT is added on your net amount.")
        buy.write("\n")
        buy.write("\n")
        buy.write("Terms & Conditions" "\t\t\t\t\t\t\t\t\t\t\t" + "Bank Details")
        buy.write("\n")
        buy.write("Payment is due within 15 days." "\t\t\t\t\t\t\t" " " "Bank of United Kingdom")
        buy.write("\n")

        buy.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "Account Number:0530 1178 7908")
        buy.write("\n")
        buy.write("\n")
        buy.write("\t\t\t\t\t" + "Thank You for your order.")
        buy.write("\n")
        buy.write("\n")
        buy.write("\n")
        buy.write("\n")
        buy.write("-"*130)
        buy.write("\n")
        buy.write("\n")


def invoiceSell(validLaptopId, laptopQuantity, userName, userPhone, location, shippingCharge, customersDictionary):
    dateandtime = datetime.now()
    laptopDetailsDictionary = openFile()

    nameOfProduct = laptopDetailsDictionary[validLaptopId][0]
    totalQuantity = laptopQuantity
    unitPrice = laptopDetailsDictionary[validLaptopId][2]
    price = laptopDetailsDictionary[validLaptopId][2].replace("$", '')
    totalPrice = int(price) * int(totalQuantity)
    brandName = laptopDetailsDictionary[validLaptopId][1]
    dateandtime = datetime.now()
    datetime_ms = int(datetime.timestamp(datetime.now()) * 1000)

    with open(str(userName) + str(datetime_ms) + ".txt", 'w') as sell:
        sell.write("-"*130)
        sell.write("\n")
        sell.write("\n")
        sell.write("Bit & Byte Store" "\t\t\t\t" "   " + "Bill To"  "\t\t\t\t\t" "Date: " + str(dateandtime.strftime("%d" " " "%b"",""%Y")))
        sell.write("\n")
        sell.write("London,England" + "\t\t\t\t\t  " + "Name:" + str(userName) + "\t\t\t\t  " + "Time: " + str(dateandtime.strftime("%I" ":" "%M" "%p")))
        sell.write("\n")
        sell.write("bitandbyte@gmail.com" + "\t\t\t  " + "Location:" + str(location))
        sell.write("\n")
        sell.write("073599487" + "\t\t\t\t\t\t  " + "Contact:" + str(userPhone))
        sell.write("\n")
        sell.write("\n")
        sell.write("-"*130)
        sell.write("\n")
        sell.write("Bill Number: " + str(datetime_ms))
        sell.write("\n")
        sell.write("\n")
        sell.write("-"*130)
        sell.write("\n")
        sell.write("S.N"+"\t\t" + "Product's Name" + "\t\t" + "Brand"+"\t\t" + "Quantity" + "\t\t" + "Unit Price" + "\t\t" + "Amount")
        sell.write("\n")
        sell.write("\n")
        sell.write("\n")
        counter = 1
        total = 0
        
        for key in customersDictionary:
            sell.write(str(counter) + "\t\t" + str(key[0]) + "\t\t   " + str(key[1]) +"\t\t" + str(key[2]) + "\t\t" + str(key[3]) + "\t\t  " + "$" + str(key[5]))
            sell.write("\n")
            sell.write("\n")
            counter = counter+1
            total = total+int(key[5])
            
        sell.write("-"*130)
        sell.write("\n")
        sell.write("\n")
        sell.write("\n")
        sell.write("-"*130)
        sell.write("\n")
        sell.write("Net Amount:" + "\t\t\t\t\t\t\t\t\t\t\t\t\t  " + "$" + str(total))
        sell.write("\n")

        sell.write("Shipping Charge:" + "\t\t\t\t\t\t\t\t\t\t\t\t" + "$" + str(shippingCharge))
        sell.write("\n")
        sell.write("-"*130)
        sell.write("\n")
        sell.write("Gross Amount:" + "\t\t\t\t\t\t\t\t\t\t\t\t\t" + "$" + str(total + shippingCharge))
        sell.write("\n")
        sell.write("-"*130)
        sell.write("\n")
        sell.write("\n")
        sell.write("Terms & Conditions" "\t\t\t\t\t\t\t\t" + "Bank Details")
        sell.write("\n")
        sell.write("Payment is due within 15 days." "\t\t\t\t\t   " + "Bank of United Kingdom")
        sell.write("\n")
        sell.write("\t\t\t\t\t\t\t\t\t\t\t\t\t" + "Account Number:0530 1178 7908")
        sell.write("\n")
        sell.write("\n")
        sell.write("\t\t\t\t""  " + "Thank You for your order.")
        sell.write("\n")
        sell.write("\n")
        sell.write("-"*130)
        sell.write("\n")

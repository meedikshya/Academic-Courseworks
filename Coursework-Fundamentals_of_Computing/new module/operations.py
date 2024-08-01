from datetime import datetime
from write import writeTextFile
from read import readFromTextFile, openFile, valid_name, valid_phone, valid_id, quantity
laptopDetailsDictionary = {}

# function, welcome message
def display_welcome():
    print("\n")
    print("\t\t", " " "*"*50)
    print("\t \t \t \t \t \t    Welcome to Bit & Byte Store ")
    print("\n")
    print("\t\t", " " "*"*50)
    print("\t \t \t \t \t \t \t London, England")
    print("\n")
    print("\t\t", " " "*"*50)
    print("\n")

# when user wants to buy from manufacturer
def buy_from_manufacturer():
    customersDictionary = []
    loop = True
    laptopDetailsDictionary = openFile()
    print("-"*130)
    print("\t\t\t\t\t\t Let's shop.")
    print("-"*130)
    print("\n")
    print("\t\t\t\t We will need your name and contact number for the invoice.")
    print("\n")
    userName = valid_name()
    userPhone = valid_phone()
    print("\n")
    print("-"*130)
    print("S.N \t Name \t\t\t Brand \t\t\t Price \t   Quantity \t\t Processor \t\t Graphic Card")
    print("-"*130)
    readFromTextFile()
    print("\n")

    while loop == True:
        validLaptopId = valid_id()
        laptopQuantity = quantity()
        requiredQuantity = laptopDetailsDictionary[validLaptopId][3]

        while laptopQuantity <= 0:
            print("\t\t\t " + "That doesn't seem a valid quantity. Please provide valid quantity!")
            print("\n")
            laptopQuantity = int(input("Please provide the number of quantity of laptop: "))
            print("\n")
        print("\n")

       # Update the text file
        laptopDetailsDictionary[validLaptopId][3] = int(laptopDetailsDictionary[validLaptopId][3]) + int(laptopQuantity)
        writeTextFile(laptopDetailsDictionary)
        nameOfProduct = laptopDetailsDictionary[validLaptopId][0]
        totalQuantity = laptopQuantity
        unitPrice = (laptopDetailsDictionary[validLaptopId][2])
        price = laptopDetailsDictionary[validLaptopId][2].replace("$", '')
        totalPrice = int(price) * int(totalQuantity)
        dateandtime = datetime.now()
        datetime_ms = int(datetime.timestamp(datetime.now()) * 1000)
        brandName = laptopDetailsDictionary[validLaptopId][1]

        customersDictionary.append([nameOfProduct, brandName, totalQuantity, unitPrice, price, totalPrice])
        buy = input("Do you want to buy more? Enter y to buy more:").lower()
        print("\n")
        print("\n")
        if buy == "y":
            loop = True
        else:
            loop = False

    print("\n")
    print("-"*130)
    print("Bit & Byte Store", "\t\t\t\t\t\t\t\t\t\t" " Date: ",dateandtime.strftime("%d" " " "%b"",""%Y"))
    print("London,England", "\t\t\t\t\t\t\t\t\t\t\t" "   " "Time:",dateandtime.strftime("%I" ":" "%M" "%p"))
    print("bitandbyte@gmail.com")
    print("073599487")
    print("\n")
    print("-"*130)
    print("Bill Number:" + str(datetime_ms))
    print("-"*130)
    print("S.N" + "\t\t" + "Product's Name" + "\t\t" + "Brand" +"\t\t  " + "Quantity" + "\t\t   " + "Unit Price" + "\t\t\t" + "Amount")
    print("-"*130)
    counter = 1
    total = 0
    for key in customersDictionary:
        print(str(counter) + "\t\t" + str(key[0]) + "\t\t   " + str(key[1]) +"\t\t" + str(key[2]) + "\t\t\t" + str(key[3]) + "\t\t\t\t" + "$" + str(key[5]))
        counter = counter+1
        total = total+int(key[5])
    print("\n")
    print("\n")
    print("-"*130)
    print("Net Amount:" + "\t\t\t\t\t\t\t\t\t\t\t\t\t", "$" + str(total))
    vatAmount = 0.13 * total
    print("Vat Amount:" + "\t\t\t\t\t\t\t\t\t\t\t\t  ", "$" + str(vatAmount))
    print("-"*130)
    print("Gross Amount:" + "\t\t\t\t\t\t\t\t\t\t\t\t  ","$" + str(total + vatAmount))
    print("-"*130)
    print("\n")
    print("Please note that 13% VAT is added on your net amount.")
    print("\n")
    print("Terms & Conditions" "\t\t\t\t\t\t\t\t\t" + "Bank Details")
    print("Payment is due within 15 days." "\t\t\t\t\t\t\t" "    " "Bank of United Kingdom")
    print("\t\t\t\t\t\t\t\t\t\t" + "Account Number:0530 1178 7908")
    print("\n")
    print("\t\t\t\t\t""  " + "Thank You for your order.")
    print("-"*130)
    print("\n")
    return validLaptopId, laptopQuantity, userName, customersDictionary


# when user enters option 2
def sell_from_store():
    customersDictionary = []
    loop = True
    laptopDetailsDictionary = openFile()
    print("-"*130)
    print("\t \t  \t  \t \t \t Let's shop!")
    print("-"*130)
    print("\n")
    print("\t \t \t \t We will need your name and contact number for the invoice.")
    print("\n")
    userName = valid_name()
    userPhone = valid_phone()
    print("\n")
    print("-"*130)
    print("S.N \t Name \t\t\t Brand \t\t\t Price \t Quantity \t\t Processor \t\t Graphic Card")
    print("-"*130)
    readFromTextFile()
    print("\n")
    while loop == True:

        validLaptopId = valid_id()
        laptopQuantity = quantity()
        print("\n")
        requiredQuantity = laptopDetailsDictionary[validLaptopId][3]
        while laptopQuantity <= 0 or laptopQuantity > int(requiredQuantity):
            print("\t\t\t Dear" + " " + str(userName) + "  the quantity you are looking for is not on stock!!!")
            print("\n")
            laptopQuantity = int(input("Please provide the number of quantity of laptop: "))
        print("\n")

        # Update the text file
        laptopDetailsDictionary[validLaptopId][3] = int(laptopDetailsDictionary[validLaptopId][3]) - int(laptopQuantity)
        writeTextFile(laptopDetailsDictionary)

        # getting user purchased items

        nameOfProduct = laptopDetailsDictionary[validLaptopId][0]
        totalQuantity = laptopQuantity
        unitPrice = laptopDetailsDictionary[validLaptopId][2]
        price = laptopDetailsDictionary[validLaptopId][2].replace("$", '')
        totalPrice = int(price) * int(totalQuantity)
        brandName = laptopDetailsDictionary[validLaptopId][1]
        dateandtime = datetime.now()
        datetime_ms = int(datetime.timestamp(datetime.now()) * 1000)
        customersDictionary.append(
            [nameOfProduct, brandName, totalQuantity, unitPrice, price, totalPrice])
        buy = input("Do you want to buy more?Enter y to buy more:").lower()

        if buy == "y":
            loop = True
        else:
            loop = False
    print("\n")

    shippingCharge = 0
    shipping = (input("Do you want your laptop to be shipped?(y/n):"))
    
    if shipping == "y".lower():
        print("\n")
        print("\t\t\t Shipping charge varies inside and outside city.")
        print("\n")
        while True:
            try:
                shippingLocation = (input("Where do want your laptop to be delivered??(i/o):"))
                if shippingLocation == "i".lower():
                    shippingCharge = 20
                    break
                elif shippingLocation == "o".lower():
                    shippingCharge = 40
                    break
                else:
                    print("Invalid Input")
                    
            except ValueError:
                print("Invalid Input")
        print("\n")
        location = input("Please provide us your full address for the delivery:")

    else:
        print("Thank You for your order.")
        location = None

    print("\n")
    print("-"*130)
    print("Bit & Byte Store" "\t\t\t\t""   " + "Bill To"  "\t\t\t\t\t\t  " "Date: " +str(dateandtime.strftime("%d" " " "%b"",""%Y")))
    print("London,England" + "\t\t\t\t\t  " + "Name:" + str(userName) + "\t\t\t\t\t\t " + "Time: " +str(dateandtime.strftime("%I" ":" "%M" "%p")))
    print("bitandbyte@gmail.com" + "\t\t\t\t  " +"Location:" + str(location))
    print("073599487" + "\t\t\t\t\t   " + "Contact:" + str(userPhone))
    print("\n")
    print("-"*130)
    print("Bill Number: " + str(datetime_ms))
    print("-"*130)
    print("S.N" + "\t\t" + "Product's Name" + "\t\t" + "Brand" +"\t\t   " + "Quantity" + "\t\t" + "Unit Price" + "\t\t" + "Amount")
    print("-"*130)
    counter = 1
    total = 0
    for key in customersDictionary:
        print(str(counter) + "\t\t" + str(key[0]) + "\t\t   " + str(key[1]) +"\t\t" + str(key[2]) + "\t\t" + str(key[3]) + "\t\t" + "$" + str(key[5]))
        counter = counter+1
        total = total+int(key[5])
    print("\n")
    print("\n")
    print("-"*130)
    print("Net Amount:" + "\t\t\t\t\t\t\t\t\t\t\t\t" + "$" + str(total))
    print("Shipping Charge:" + "\t\t\t\t\t\t\t\t\t\t\t" +"$" + str(shippingCharge))
    print("-"*130)
    print("Gross Amount:" + "\t\t\t\t\t\t\t\t\t\t\t\t","$" + str(total + shippingCharge))
    print("-"*130)
    print("\n")
    print("Terms & Conditions" "\t\t\t\t\t\t\t\t\t" + "Bank Details")
    print("Payment is due within 15 days." "\t\t\t\t\t\t\t" "    " "Bank of United Kingdom")
    print("\t\t\t\t\t\t\t\t\t\t" + "Account Number:0530 1178 7908")
    print("\n")
    print("\t\t\t\t\t""  " + "Thank You for your order.")
    print("-"*130)
    print("\n")
    return validLaptopId, laptopQuantity, userName, userPhone, location, shippingCharge, customersDictionary


def exit_message():
    print("Thank You for using our system.Have a good day!.")
    print("\n")
    print("\n")

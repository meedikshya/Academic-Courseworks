
def openFile():
    laptopDetailsDictionary = {}
    file = open("laptopDetails.txt", "r")
    laptopId = 1
    for line in file:
        line = line.replace("\n", "")
        laptopDetailsDictionary[laptopId] = line.split(",")
        laptopId += 1
    return laptopDetailsDictionary


def readFromTextFile():
    a = 1
    file = open("laptopDetails.txt", "r")
    for line in file:
        print(a, "\t "+line.replace(",", "\t\t"))
        a += 1
    file.close()


# name_validation function
def valid_name():
    while True:
        userName = input("Please provide us your name:")
        try:
            int(userName)
        except ValueError:
            return userName
        print("\n")
        print("\t\t\t Please provide valid name.")
        print("\n")


# phone validation function
def valid_phone():
    while True:
        print("\n")
        userPhone = input("Your phone number here:")
        try:
            int(userPhone)
            return userPhone
        except ValueError:
            print("\n")
            print("\t\t\t Invalid Phone Number. Please provide valid phone numbers only.")
            print("\n")


# id validation function
def valid_id():
    while True:
        try:
            print("\n")
            validLaptopId = int(input("Please Provide the ID of the laptop you want to buy:"))
            if validLaptopId <= 0 or validLaptopId > len(openFile()):
                print("\n")
                print("\t\t\t Please provide a valid laptop id!!!")
                print("\n")
            else:
                return validLaptopId
            print("\n")
        except ValueError:
            print("\n")
            print("\t\t\t Uh-Oh! It doesn't seem a valid ID. Please check the details above and continue.")
            print("\n")



# quantity validation function
def quantity():
    while True:
        try:
            print("\n")
            laptopQuantity = int(input("Please provide the quantity of a laptop you want to buy:"))
            print("\n")
            return laptopQuantity
        except ValueError:
            print("\n")
            print("\t\t\t Please provide valid quantity.")
            print("\n")

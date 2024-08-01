from operations import display_welcome, buy_from_manufacturer, sell_from_store, exit_message
from write import invoiceBuy, invoiceSell

# displays welcome message
display_welcome()

# function defining user options
def userOptions():
    continueLoop = True
    while continueLoop == True:
        print("\t \t 1.  Press 1 to buy from manufacturer.")
        print("\t \t 2.  Press 2 to sell to customers.")
        print("\t \t 3.  Press 3 to exit from system.")
        print("\n")
        options = True
        while options == True:
            try:
                optionsInput = int(input("\t Enter from option:"))
                print("\n")
                options = False
            except:
                print("\n")
                print("\t \t Uh-Oh!The option you provided doesn't exist.Please try again.")
                print("\n")
        if optionsInput == 1:
            validLaptopId, laptopQuantity, userName, customersDictionary = buy_from_manufacturer()
            invoiceBuy(validLaptopId, laptopQuantity,userName, customersDictionary)

        elif optionsInput == 2:
            validLaptopId, laptopQuantity, userName, userPhone, location, shippingCharge, customersDictionary = sell_from_store()
            invoiceSell(validLaptopId, laptopQuantity,userName, userPhone, location, shippingCharge, customersDictionary)

        elif optionsInput == 3:
            continueLoop = False
            exit_message()

        else:
            print("\t \t To continue, Please select a proper option.")
            print("\n")


userOptions()

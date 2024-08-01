
/**
 * Write a description of class BankCard here.
 *
 * @author (22067520 Dikshya Sharma)
 * @version (1.0.0)
 */

public class DebitCard extends BankCard
{
    private int pinNumber;
    private int withdrawalAmount;
    private String dateOfWithdrawal;
    private boolean hasWithdrawn;

    public DebitCard(double balanceAmount, int cardId, String bankAccount, String issuerBank, String clientName, int pinNumber) {
        super(balanceAmount, cardId, bankAccount, issuerBank);
        super.setClientName(clientName);
        this.pinNumber = pinNumber;
        this.hasWithdrawn = false;
    }
    
    public int getPinNumber() {
        return this.pinNumber;
    }

    public int getWithdrawalAmount() {
        return this.withdrawalAmount;
    }

    public String getDateOfWithdrawal() {
        return this.dateOfWithdrawal;
    }

    public boolean getHasWithdrawn() {
        return this.hasWithdrawn;
    }
    
    public void setWithdrawalAmount(int withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }
    
    //method to deduct the money directly from the client's account
    public void Withdraw(int withdrawalAmount, String dateOfWithdrawal, int pinNumber) {
        if (pinNumber == this.pinNumber){
            //if the amount to be withdrawan is less than or equals to the client's Balance Amount
            if(withdrawalAmount <= super.getBalanceAmount())
            {
                //setting balance amount after withdrawal is done 
                super.setBalanceAmount(super.getBalanceAmount() - withdrawalAmount);
                this.withdrawalAmount = withdrawalAmount;
                this.dateOfWithdrawal = dateOfWithdrawal;
                this.hasWithdrawn = true;
                System.out.println("Successful transaction! Remaining amount : " + super.getBalanceAmount());
            } 
            else {
                System.out.println("Insufficient balance!");
            }
        }

        else {
            System.out.println("Invalid pin number!");
        }
    }
    
        public void display() {
        super.display();
        System.out.println("PIN number: " + pinNumber);
        
        if (hasWithdrawn == true) {
            System.out.println("Withdrawal amount: " + withdrawalAmount);
            System.out.println("Date of withdrawal: " + dateOfWithdrawal);
        }
        else{
            System.out.println("No withdrawal have taken place as of yet! Balance Amount: "+ super.getBalanceAmount());
        }
     }
    }

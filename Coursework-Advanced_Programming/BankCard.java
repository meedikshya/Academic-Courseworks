
/**
 * Write a description of class BankCard here.
 *
 * @author (22067520 Dikshya Sharma)
 * @version (1.0.0)
 */

public class BankCard
{
    
    private int cardId;
    private String clientName;
    private String issuerBank;
    private String bankAccount;
    private double balanceAmount;
    
    
    public BankCard(double balanceAmount, int cardId, String bankAccount,String issuerBank) 
    {
        this.balanceAmount = balanceAmount;
        this.cardId = cardId;
        this.bankAccount = bankAccount;
        this.issuerBank = issuerBank;
        this.clientName = "";
        
    }

    public int getCardId(){
        return this.cardId;
    }
    
    public String getClientName() {
        return this.clientName;
    }
    
    public String getIssuerBank() {
        return this.issuerBank;
    }
    
    public String getBankAccount() {
        return this.bankAccount;
    }
    
    public double getBalanceAmount() {
        return this.balanceAmount;
    }
    
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
    public void display() {
        System.out.println("Card ID: "+cardId);
        
        if(clientName.isEmpty()){
            System.out.println("Client name is not assigned");
        }
        else{
            System.out.println("Client Name: "+ clientName);
        }
        
        System.out.println("Issuer Bank: "+ issuerBank);
        
        System.out.println("Bank Account: "+ bankAccount);
        
        System.out.println("Balance Amount: "+ balanceAmount);
        
        
    }
}

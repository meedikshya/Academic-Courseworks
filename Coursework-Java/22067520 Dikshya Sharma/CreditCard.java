
/**
 * Write a description of class CreditCard here.
 *
 * @author (22067520 Dikshya Sharma)
 * @version (1.0.0)
 */

public class CreditCard extends BankCard
{
    private int cvcNumber;
    private double creditLimit;
    private double interestRate;
    private String expirationDate;
    private int gracePeriod;
    private boolean isGranted;

    public CreditCard(int cardID,String clientName, String issuerBank, String bankAccount, 
    double balanceAmount, int cvcNumber, double interestRate, String expirationDate)
    {
        super(balanceAmount,cardID, bankAccount,issuerBank);
        super.setClientName(clientName);
        this.cvcNumber = cvcNumber;
        this.interestRate = interestRate;
        this.expirationDate = expirationDate;
        this.isGranted = false;
    }

    public int getCvcNumber()
    {
        return this.cvcNumber;
    }

    public double getCreditLimit()
    {
        return this.creditLimit;
    }

    public double getInterestRate()
    {
        return this.interestRate;
    }

    public String getExpirationDate()
    {
        return this.expirationDate;
    }

    public int getGracePeriod()
    {
        return this.gracePeriod;
    }

    public boolean getIsGranted()
    {
        return this.isGranted;
    }

    public void setCreditLimit(double creditLimit, int gracePeriod)
    {
        this.creditLimit = creditLimit;
        this.gracePeriod = gracePeriod; 
        if(creditLimit <= 2.5 * (super.getBalanceAmount())){
            System.out.println("Credit is successfully granted!");
            this.isGranted = true;
        }else{
            System.out.println("Credit couldn't be issued due to a credit limit overuse");
        }
    } 

    public void cancelCreditCard()
    {
        this.cvcNumber = 0;
        this.creditLimit = 0;
        this.gracePeriod = 0;
        this.isGranted = false;
    }

    public void display()
    {
        super.display();
        if(isGranted = true){
            System.out.println("Credit limit is valid hence credit is granted");
            System.out.println("Credit Limit:" + this.creditLimit);
            System.out.println("Grace Period:" + this.gracePeriod);
        }
         else{
            System.out.println("Credit is not granted.");
        }
        System.out.println("CVC number:" + this.cvcNumber);
        System.out.println("Interest Rate:" + this.interestRate);
        System.out.println("Expiration Date:" + this.expirationDate);
    }
}


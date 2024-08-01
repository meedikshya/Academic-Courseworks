
/**
 * Write a description of class BankGUI here.
 *
 * @author (22067520 Dikshya Sharma)
 * @version (17.0.4)
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class BankGUI implements ActionListener
{
    private ArrayList<BankCard> bankGui;
    //declaring components
    //components for debit card
    //components for credi card
    private JFrame frame;
    //debit card components
    private JLabel welcomeLabel,welcomeTextLabel, textLabel, titleDLabel, nameLabel, cardIdLabel, bankLabel, accountLabel,
    amountLabel,pinNumberLabel ,cardIdWLabel,withdrawalAmountLabel ,
    dateOfWithdrawalLabel, pinLabel ;

    private JTextField cardIdField , bankField, nameField, accountField,
    amountField,withdrawalAmountField, pinNumberField, cardIdWField, pinField ;

    private JButton CreditButton, DebitButton, debitButton, withdrawButton, displayButton, clearButton, backButton;
    
    private JComboBox<String> dayBox ,monthBox, yearBox ;
    
    
    //credit card components
    private JLabel titleCLabel, nameCLabel,cardIdCLabel,bankCLabel,accountCLabel,amountCLabel,
    cvcLabel,pinNumberCLabel,interestRateLabel,expiryCLabel,cardIdLimitLabel,
    creditLimitLabel,gracePeriodLabel,cardIdCancelLabel;

    private JTextField nameCField, bankCField, cardIdCField,accountCField,amountCField,
    interestRateField,cvcField,pinNumberCField,cardIdLimitField,creditLimitField,
    gracePeriodField,cardIdCancelField;
    
    private JButton cancelButton , setLimitButton, creditButton,
    displayCButton, clearCButton, backCButton;

    private JComboBox<String> dayBoxC,monthBoxC, yearBoxC ;
    
    private JPanel mainPanel, newPanel, newPanelSecond; 

    public BankGUI()
    {
        //code to write gui
        bankGui = new ArrayList<BankCard>();
        frame = new JFrame("Bank Card");
        newPanel = new JPanel();
        newPanelSecond = new JPanel();
        
        mainPanel = new JPanel(null);
        newPanel.setLayout(null);
        newPanelSecond.setLayout(null);

        welcomeLabel = new JLabel("JAVA Bank");
        textLabel = new JLabel("The Bank you'll love.");
        String welcomeMessage = "Welcome to our online card service.";
        welcomeTextLabel = new JLabel(welcomeMessage);
        DebitButton = new JButton("Debit Card");
        CreditButton = new JButton("Credit Card");
        
        
        //debit cards
        titleDLabel = new JLabel("Debit Card");
        nameLabel = new JLabel("Client Name");
        cardIdLabel = new JLabel("Card ID");
        bankLabel = new JLabel("Issuer Bank");
        accountLabel = new JLabel("Bank Account");
        amountLabel = new JLabel("Balance Amount");
        nameField = new JTextField(); 
        cardIdField = new JTextField();
        bankField = new JTextField();
        accountField = new JTextField();
        amountField = new JTextField();
        pinNumberLabel = new JLabel("PIN Number");
        pinNumberField = new JTextField();
        cardIdWLabel = new JLabel("Card ID");
        cardIdWField = new JTextField();
        withdrawalAmountLabel = new JLabel("Withdrawal Amount");
        withdrawalAmountField = new JTextField();
        dateOfWithdrawalLabel = new JLabel("Date Of Withdrawal");

        String[] day = {"1", "2", "3", "4", "5", "6","7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21","22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        dayBox = new JComboBox<>(day);

        String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "August", "september", "October", "November", "December"};
        monthBox = new JComboBox<>(month);

        String[] year= {"2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028"};
        yearBox = new JComboBox<>(year);

        pinLabel = new JLabel("PIN Number");
        pinField = new JTextField();

        debitButton = new JButton("Add Debit Card");
        withdrawButton = new JButton("Withdraw");
        displayButton = new JButton("Display");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        //credit card
        titleCLabel = new JLabel("Credit Card");
        nameCLabel = new JLabel("Client Name");
        cardIdCLabel = new JLabel("Card ID");
        bankCLabel = new JLabel("Issuer Bank");
        accountCLabel = new JLabel("Bank Account");
        amountCLabel = new JLabel("Balance Amount");
        nameCField = new JTextField(); 
        cardIdCField = new JTextField();
        bankCField = new JTextField();
        accountCField = new JTextField();
        amountCField = new JTextField();
        cvcLabel = new JLabel("CVC Number");
        cvcField = new JTextField();
        interestRateLabel = new JLabel("Interest Rate");
        interestRateField = new JTextField();
        expiryCLabel = new JLabel("Expiration Date");
        
        String[] dayC = {"1", "2", "3", "4", "5", "6","7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21","22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        dayBoxC = new JComboBox<>(day);

        String[] monthC = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthBoxC = new JComboBox<>(month);

        String[] yearC= {"2018","2019","2020","2021","2022","2023", "2024", "2025", "2026", "2027", "2028"};
        yearBoxC = new JComboBox<>(year);

        cardIdLimitLabel = new JLabel("Card ID");
        creditLimitLabel = new JLabel("Credit Limit");
        gracePeriodLabel = new JLabel("Grace Period");
        cardIdCancelLabel = new JLabel("Card ID");
        cardIdLimitField = new JTextField();
        creditLimitField = new JTextField();
        gracePeriodField = new JTextField();
        cardIdCancelField = new JTextField();

        creditButton = new JButton("Add Credit Card");
        setLimitButton = new JButton("Set Credit Limit");
        cancelButton = new JButton("Cancel Credit Card");
        displayCButton = new JButton("Display");
        clearCButton = new JButton("Clear");
        backCButton = new JButton("Back");
        
        newPanel.setBounds(0, 0, 700, 600);
        newPanelSecond.setBounds(0, 0, 700, 600);
        
        
        welcomeLabel.setBounds(54,27,153,43);
        textLabel .setBounds(166,76,300,20);
        welcomeTextLabel.setBounds(127,126,403,107);
        
        DebitButton.setBounds(166,300,120,32);
        CreditButton.setBounds(365,300,120,32);
        
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        welcomeTextLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        textLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        
        //debit 
        titleDLabel.setBounds(300,36,100,30);
        nameLabel.setBounds(204,95,82,30);
        nameField.setBounds(187,130,113,26);
        cardIdLabel.setBounds(40,95,62,30);
        cardIdField.setBounds(19,130,113,26);
        bankLabel.setBounds(32,179,100,30);
        bankField.setBounds(19,209,113,26);
        accountLabel.setBounds(204,179,100,30);
        accountField.setBounds(191,209,113,26);
        amountLabel.setBounds(25, 259,108,30);
        amountField.setBounds(20,289,113,26);
        pinNumberLabel.setBounds(204,259,87,30);
        pinNumberField.setBounds(190,289,113,26);
        debitButton.setBounds(102,343,120,26);
        cardIdWLabel.setBounds(400,95,100,30);
        cardIdWField.setBounds(374,130,113,26);
        withdrawalAmountLabel.setBounds(544,95,130,30);
        withdrawalAmountField.setBounds(531,130,130,26);
        dateOfWithdrawalLabel.setBounds(466,179,122,30);
        yearBox.setBounds(382,209,76,26);
        monthBox.setBounds(472,209,90,26);
        dayBox.setBounds(576,209,73,26);
        pinLabel.setBounds(476,259,86,30);
        pinField.setBounds(460,287,113,26);
        withdrawButton.setBounds(476,343,104,26);
        displayButton.setBounds(214,411,104,26);
        clearButton.setBounds(354,411,91,26);
        backButton.setBounds(576,457,91,26);

        //credit card
        titleCLabel.setBounds(300,36,100,30);
        nameCLabel.setBounds(202,77,82,30);
        nameCField.setBounds(187,107,113,26);
        cardIdCLabel.setBounds(39,77,62,30);
        cardIdCField.setBounds(19,107,113,26);
        bankCLabel.setBounds(32,155,100,30);
        bankCField.setBounds(19,185,113,26);
        accountCLabel.setBounds(199,155,100,30);
        accountCField.setBounds(186,185,113,26);
        amountCLabel.setBounds(25,230,108,30);
        amountCField.setBounds(19,260,113,26);
        cvcLabel.setBounds(190,225,87,30);
        cvcField.setBounds(190,260,113,26);
        interestRateLabel.setBounds(118,298,230,30);
        interestRateField.setBounds(102,332,103,26);
        expiryCLabel.setBounds(113,362,109,30);
        yearBoxC.setBounds(26,397,76,26);
        monthBoxC.setBounds(113,397,90,26);
        dayBoxC.setBounds(214,396,73,26);
        creditButton.setBounds(113,444,144,26);
        cardIdLimitLabel.setBounds(400,77,100,30);
        cardIdLimitField.setBounds(374,107,113,26);
        creditLimitLabel.setBounds(554,77,83,30);
        creditLimitField.setBounds(527,107,113,26);
        gracePeriodLabel.setBounds(466,155,122,30);
        gracePeriodField.setBounds(453,185,113,26);
        setLimitButton.setBounds(444,230,144,26);
        cardIdCancelLabel.setBounds(494,275,50,30);
        cardIdCancelField.setBounds(466,305,113,26);
        cancelButton.setBounds(454,353,144,26);
        displayCButton.setBounds(277,468,104,26);
        clearCButton.setBounds(390,468,91,26);
        backCButton.setBounds(576,457,91,26);

    
        DebitButton.addActionListener(this);
        CreditButton.addActionListener(this);
        debitButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        backButton.addActionListener(this);
        backCButton.addActionListener(this);
        displayButton.addActionListener(this);
        clearButton.addActionListener(this);
        creditButton.addActionListener(this);
        setLimitButton.addActionListener(this);
        cancelButton.addActionListener(this);
        displayCButton.addActionListener(this);
        clearCButton.addActionListener(this);
        
        mainPanel.add(welcomeLabel);
        mainPanel.add(textLabel);
        mainPanel.add(welcomeTextLabel);
        mainPanel.add(DebitButton);
        mainPanel.add(CreditButton);

        //debit card
        newPanel.add(titleDLabel);
        newPanel.add(nameLabel);
        newPanel.add(nameField);
        newPanel.add(cardIdLabel);
        newPanel.add(cardIdField);
        newPanel.add(bankLabel);
        newPanel.add(bankField);
        newPanel.add(accountLabel);
        newPanel.add(accountField);
        newPanel.add(amountLabel);
        newPanel.add(amountField);
        newPanel.add(pinNumberLabel);
        newPanel.add(pinNumberField);
        newPanel.add(debitButton);
        newPanel.add(cardIdWLabel);
        newPanel.add(cardIdWField);
        newPanel.add(withdrawalAmountLabel);
        newPanel.add(withdrawalAmountField);
        newPanel.add(dateOfWithdrawalLabel);
        newPanel.add(yearBox);
        newPanel.add(monthBox);
        newPanel.add(dayBox);
        newPanel.add(pinLabel);
        newPanel.add(pinField);
        newPanel.add(withdrawButton);
        newPanel.add(displayButton);
        newPanel.add(clearButton);
        newPanel.add(backButton);

        //creditcard
        newPanelSecond.add(titleCLabel);
        newPanelSecond.add(nameCLabel);
        newPanelSecond.add(nameCField);
        newPanelSecond.add(cardIdCLabel);
        newPanelSecond.add(cardIdCField);
        newPanelSecond.add(bankCLabel);
        newPanelSecond.add(bankCField);
        newPanelSecond.add(accountCLabel);
        newPanelSecond.add(accountCField);
        newPanelSecond.add(amountCLabel);
        newPanelSecond.add(amountCField);
        newPanelSecond.add(cvcLabel);
        newPanelSecond.add(cvcField);
        newPanelSecond.add(interestRateLabel);
        newPanelSecond.add(interestRateField);
        newPanelSecond.add(expiryCLabel);
        newPanelSecond.add(yearBoxC);
        newPanelSecond.add(monthBoxC);
        newPanelSecond.add(dayBoxC);
        newPanelSecond.add(creditButton);
        newPanelSecond.add(cardIdLimitLabel);
        newPanelSecond.add(cardIdLimitField);
        newPanelSecond.add(creditLimitLabel);
        newPanelSecond.add(creditLimitField);
        newPanelSecond.add(gracePeriodLabel);
        newPanelSecond.add(gracePeriodField);
        newPanelSecond.add(setLimitButton);
        newPanelSecond.add(cardIdCancelLabel);
        newPanelSecond.add(cardIdCancelField);
        newPanelSecond.add(cancelButton);
        newPanelSecond.add(displayCButton);
        newPanelSecond.add(clearCButton);
        newPanelSecond.add(backCButton);
        
        frame.add(mainPanel);
        
        Color c1 = new Color(153,212,211);  
        Color c2 = new Color(249,230,93);
        Color c3 = new Color(52, 87, 114);
        Color c4 = new Color(0, 140, 186);
        Color c5 = new Color(76, 175, 80);
        Color c6 = new Color(237, 108, 99);
        Color c7 = new Color(238, 242, 133);
        Color c8 = new Color(209, 174, 229);
        Color c9 = new Color(198, 182, 194);
        
        mainPanel.setBackground(c1);
        newPanel.setBackground(c1);
        newPanelSecond.setBackground(c1);
       
        CreditButton.setBackground(c2);
        debitButton.setBackground(c4);
        creditButton.setBackground(c4);
        withdrawButton.setBackground(c5);
        setLimitButton.setBackground(c5);
        cancelButton.setBackground(c6);
        displayButton.setBackground(c7);
        displayCButton.setBackground(c7);
        clearButton.setBackground(c8);
        clearCButton.setBackground(c8);
        backButton.setBackground(c9);
        backCButton.setBackground(c9);
        
        welcomeTextLabel.setForeground(c3);
        welcomeLabel.setForeground(c3);
        textLabel.setForeground(c3);
        DebitButton.setBackground(c2);
    
        frame.setSize(700, 600);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        //logic here of button functionality

        //Panel Debit Button Functionality
        if(e.getSource() == DebitButton) {
            mainPanel.add(newPanel);
            DebitButton.setVisible(false);
            CreditButton.setVisible(false);
            welcomeLabel.setVisible(false);
            textLabel.setVisible(false);
            welcomeTextLabel.setVisible(false);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
        
        //Panel Credit Button Functionality
        if(e.getSource() == CreditButton) {
            mainPanel.add(newPanelSecond);
            DebitButton.setVisible(false);
            CreditButton.setVisible(false);
            welcomeLabel.setVisible(false);
            textLabel.setVisible(false);
            welcomeTextLabel.setVisible(false);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
        
        if(e.getSource() == backButton) {
            mainPanel.remove(newPanel);
            mainPanel.remove(newPanelSecond);
            DebitButton.setVisible(true);
            CreditButton.setVisible(true);
            welcomeLabel.setVisible(true);
            textLabel.setVisible(true);
            welcomeTextLabel.setVisible(true);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
        
        if(e.getSource() == backCButton){
            mainPanel.remove(newPanel);
            mainPanel.remove(newPanelSecond);
            DebitButton.setVisible(true);
            CreditButton.setVisible(true);
            welcomeLabel.setVisible(true);
            textLabel.setVisible(true);
            welcomeTextLabel.setVisible(true);
            mainPanel.revalidate();
            mainPanel.repaint();

        }

        if(e.getSource() ==  debitButton) 
        {

            if(cardIdField.getText().equals("")||nameField.getText().equals("")||bankField.getText().equals("")||accountField.getText().equals("")
            ||amountField.getText().equals("")||pinNumberField.getText().equals("")) 
            {
                JOptionPane.showMessageDialog(frame,"Please don't leave the fields empty.", "Error" , JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                try
                {
                    int debitCardId = Integer.parseInt(cardIdField.getText());
                    String debitClientName = nameField.getText();
                    String debitIssuerBank = bankField.getText();
                    String debitBankAccount = accountField.getText();
                    double debitBalanceAmount = Double.parseDouble(amountField.getText());
                    int debitPinNumber = Integer.parseInt(pinNumberField.getText());
                    //if arraylist is empty: isEmpty method bhayeo bhane add
                    boolean debitCardIdExists = false;
 
                    if(bankGui.isEmpty())
                    {
                        //adding object of debit card using constructor
                        DebitCard debitcard = new DebitCard(debitBalanceAmount,debitCardId,debitBankAccount,debitIssuerBank,debitClientName,debitPinNumber);
                        bankGui.add(debitcard);
                        JOptionPane.showMessageDialog(frame,"Successfully Added", "Card Added" , JOptionPane.INFORMATION_MESSAGE);
                    }else
                    {
                        //check to see if card id is present
                        for(BankCard bankcard : bankGui)
                        {
                            //check cardID is debitcard or not
                            if(bankcard instanceof DebitCard)
                            {
                                //downcast
                                DebitCard newDebitCard = (DebitCard) bankcard;

                                if(newDebitCard.getCardId() == debitCardId)
                                {
                                    debitCardIdExists = true ;
                                    JOptionPane.showMessageDialog(frame,"The card ID you entered already exists", "Already Exists." , JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                            } 
                            //if pin number matches  
                        }if(!debitCardIdExists)
                        {
                            //adding object to debit card
                            DebitCard debitcard = new DebitCard(debitBalanceAmount,debitCardId,debitBankAccount,debitIssuerBank,debitClientName,debitPinNumber);
                            bankGui.add(debitcard);
                            JOptionPane.showMessageDialog(frame,"Successfully Added" , "added", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }catch(NumberFormatException n)
                {
                    JOptionPane.showMessageDialog(null,"Please enter numeric values only");

                }
            }
            
        }   
        
        if (e.getSource() == withdrawButton)
        {
        String cardIdStr = cardIdWField.getText();
        String withdrawalAmountStr = withdrawalAmountField.getText();
        String year = yearBox.getSelectedItem().toString();
        String month = monthBox.getSelectedItem().toString();
        String day = dayBox.getSelectedItem().toString();
        String date = year + month + day;
        String pinNumberStr = pinField.getText();
        String debitBalanceAmountCheck = amountField.getText();
        boolean isWithdrawn = false;
    
        if (bankGui.isEmpty())
        {
            JOptionPane.showMessageDialog(frame, "Please provide your card details first.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (cardIdStr.isEmpty() || withdrawalAmountStr.isEmpty() || pinNumberStr.isEmpty())
        {
            JOptionPane.showMessageDialog(frame, "Please don't leave the fields empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            int cardIdW, withdrawalAmountW, pinNumberW, balanceAmountDW;
    
            try 
            {
                cardIdW = Integer.parseInt(cardIdStr);
                withdrawalAmountW = Integer.parseInt(withdrawalAmountStr);
                pinNumberW = Integer.parseInt(pinNumberStr);
                balanceAmountDW = Integer.parseInt(debitBalanceAmountCheck); 

                boolean debitCardPresent = false;
    
                for (BankCard bankcard : bankGui) 
                {
                    if (bankcard instanceof DebitCard) 
                    {
                        DebitCard debitCard = (DebitCard) bankcard;
    
                        if (debitCard.getCardId() == cardIdW) 
                        {
                            debitCardPresent = true;
    
                            if (debitCard.getPinNumber() == pinNumberW)
                            {
                                if (withdrawalAmountW < balanceAmountDW  ) 
                                {
                                    debitCard.Withdraw(withdrawalAmountW, date, pinNumberW);
                                    JOptionPane.showMessageDialog(frame, "Withdraw was Successful.", "Transaction Performed", JOptionPane.INFORMATION_MESSAGE);
                                    isWithdrawn = true;
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(frame, "You don't seem to have enough balance for the transaction.", "Not Enough Balance Amount", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            else 
                        {
                            JOptionPane.showMessageDialog(frame, "Pin Number doesn't match!", "Pin Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }
                }
            }

            if (!debitCardPresent) 
            {
                JOptionPane.showMessageDialog(frame, "Debit card with the given ID not found.", "Card not found", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(NumberFormatException n) 
                {
                    JOptionPane.showMessageDialog(frame, "Please enter numeric values only", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
            

        if (e.getSource() == displayButton) {
            if (bankGui.isEmpty())
            {
                JOptionPane.showMessageDialog(frame, "Nothing to display.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                String message = "Name: " + nameField.getText() + "\n" + "Card ID: " + cardIdField.getText() + "\n"
                    + "Issuer Bank :" + bankField.getText() + "\n" + " Account Number :" + accountField.getText() + "\n"
                    + "Balance Amount :" + amountField.getText() + "\n" +  "PIN Number : " + pinNumberField.getText() + "\n" +
                    "Withdraw Details " + "\n" + "\n" + "Withdrawal Amount :" + withdrawalAmountField.getText() +
                    "\n" + "Withdrawal Date : " + dayBox.getSelectedItem().toString()+ "/" +  monthBox.getSelectedItem().toString()+ "/" +  yearBox.getSelectedItem()+ "/" + 
                    "\n" + "PIN Number:" + pinField.getText() ;
                for (BankCard bankcard : bankGui)
                {
                    if (bankcard instanceof DebitCard) {
                        DebitCard debitCard = (DebitCard) bankcard;
                        debitCard.display();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Debit card not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                JOptionPane.showMessageDialog(frame, message, "Your Details" , JOptionPane.INFORMATION_MESSAGE);

            }
        }

        if(e.getSource() == clearButton){
            cardIdField.setText("");
            nameField.setText("");
            bankField.setText("");
            accountField.setText("");
            amountField.setText("");
            pinNumberField.setText("");
            cardIdWField.setText("");
            withdrawalAmountField.setText("");
            yearBox.setSelectedItem("-1");
            monthBox.setSelectedItem("-1");
            dayBox.setSelectedItem("-1");
            pinField.setText("");   
        }

        //Credit buttons Functionality
        if(e.getSource() == creditButton){
            if(cardIdCField.getText().equals("")||nameCField.getText().equals("")||bankCField.getText().equals("")||accountCField.getText().equals("")
            ||amountCField.getText().equals("")||cvcField.getText().equals("") || interestRateField.getText().equals("")) 
            {
                JOptionPane.showMessageDialog(frame,"Please don't leave the fields empty.", "Error" , JOptionPane.ERROR_MESSAGE);
            }
            else
            {

                try
                {
                    int creditCardId = Integer.parseInt(cardIdCField.getText());
                    String creditClientName = nameCField.getText();
                    String creditIssuerBank = bankCField.getText();
                    String creditBankAccount = accountCField.getText();

                    double creditBalanceAmount = Double.parseDouble(amountCField.getText());
                    int creditCVC = Integer.parseInt(cvcField.getText());
                    int interestRate = Integer.parseInt(interestRateField.getText());
                    String yearC = yearBoxC.getSelectedItem().toString();
                    String monthC = monthBoxC.getSelectedItem().toString();
                    String dayC = dayBoxC.getSelectedItem().toString();
                    String dateC = yearC + monthC + dayC;
                    //if arraylist is empty: isEmpty method bhayeo bhane add   
                    boolean creditCardIdExists = false;
                    if(bankGui.isEmpty())
                    {
                        //adding object of credit card using constructor
                        CreditCard creditcard = new CreditCard(creditCardId,creditClientName,creditIssuerBank,creditBankAccount, 
                                creditBalanceAmount,creditCVC,interestRate, dateC);
                        bankGui.add(creditcard);
                        JOptionPane.showMessageDialog(frame,"Successfully Added", "Card Added" , JOptionPane.INFORMATION_MESSAGE);

                    }else
                    {
                        //check to see if card id is present
                        for(BankCard bankcard : bankGui )
                        {
                            //check cardID is creditcard or not
                            if(bankcard instanceof CreditCard)
                            {
                                //downcast
                                CreditCard newCreditCard = (CreditCard) bankcard;

                                if(newCreditCard.getCardId() == creditCardId)
                                {
                                    creditCardIdExists = true ;
                                    JOptionPane.showMessageDialog(frame,"The card ID you entered already exists", "Already exists." , JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                            }
                            
                        }if(!creditCardIdExists)
                        {
                            //adding object to debit card
                            CreditCard creditcard = new CreditCard(creditCardId,creditClientName,creditIssuerBank,creditBankAccount,creditBalanceAmount,creditCVC,interestRate, dateC);
                            bankGui.add(creditcard);
                            JOptionPane.showMessageDialog(frame,"Successfully Added" , "added", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
                catch(NumberFormatException n)
                {
                    JOptionPane.showMessageDialog(null,"Please enter numeric values only");
                }

            }
        }

        if(e.getSource() == setLimitButton) {
            String cardIdString = cardIdLimitField.getText();
            String creditLimitString = creditLimitField.getText();
            String gracePeriodString = gracePeriodField.getText();

            if (cardIdString.isEmpty() || creditLimitString.isEmpty() || gracePeriodString.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please don't leave the fields empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try{
                    int cardIdLimit = Integer.parseInt(cardIdString);
                    int creditLimit = Integer.parseInt(creditLimitString);
                    int gracePeriod = Integer.parseInt(gracePeriodString);

                    boolean creditCardFound = false;

                    if(bankGui.isEmpty())
                    {
                        JOptionPane.showMessageDialog(frame, "Please provide the details first!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else{   
                        for (BankCard bankCard : bankGui) 
                        {

                            if (bankCard instanceof CreditCard) {
                                CreditCard creditCard = (CreditCard) bankCard;

                                    if (creditCard.getCardId() == cardIdLimit)
                                    {
                                        creditCardFound = true;

                                        creditCard.setCreditLimit(creditLimit, gracePeriod);
                                        JOptionPane.showMessageDialog(frame, "Credit Limit Set successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                            }
                        }
                    }

                    if (!creditCardFound) {
                        JOptionPane.showMessageDialog(frame, "Credit card with the given ID not found.", "Card not found", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }catch(NumberFormatException n) 
                {
                    JOptionPane.showMessageDialog(frame, "Please enter numeric values only", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if(e.getSource() == cancelButton) {
            String cardIdCancel = cardIdCancelField.getText();

            try{
                if (cardIdCancel.isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Please don't leave the field empty.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int cardIdcancel = Integer.parseInt(cardIdCancel);
                    boolean creditCardFound = false;
                    for(BankCard bankcard : bankGui) {
                        if(bankcard instanceof CreditCard) {
                            CreditCard creditcard = (CreditCard) bankcard;
                            if(creditcard.getCardId() == cardIdcancel) {
                                creditCardFound = true;
                                creditcard.cancelCreditCard();
                                creditLimitField.setText("");
                                gracePeriodField.setText("");
                                cvcField.setText("");
                                bankGui.remove(creditcard);
                                JOptionPane.showMessageDialog(frame, "Credit Card with" + "Id" + "  " + cardIdcancel + "  "  +"is cancelled", "Credit card Cancelled", JOptionPane.INFORMATION_MESSAGE);
                                break;

                            }

                        }
                    }
                    if (!creditCardFound) {
                        JOptionPane.showMessageDialog(frame, "Credit card with the given ID not found.", "Card not found", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }catch(NumberFormatException n) 
            {
                JOptionPane.showMessageDialog(frame, "Please enter numeric values only", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(e.getSource() == clearCButton){
            cardIdCField.setText("");
            nameCField.setText("");
            bankCField.setText("");
            accountCField.setText("");
            amountCField.setText("");
            cvcField.setText("");
            interestRateField.setText("");
            cardIdLimitField.setText("");
            creditLimitField.setText("");
            gracePeriodField.setText("");
            cardIdCancelField.setText("");
            yearBoxC.setSelectedItem("-1");
            monthBoxC.setSelectedItem("-1");
            dayBoxC.setSelectedItem("-1");

        }

        if(e.getSource() == displayCButton) {
            if (bankGui.isEmpty()) 
            {
                JOptionPane.showMessageDialog(frame, "Nothing to display.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                String message = "Name: " + nameCField.getText() + "\n" + "Card ID: " + cardIdCField.getText() + "\n"
                    + "Issuer Bank :" + bankCField.getText() + "\n" + " Account Number :" + accountCField.getText() + "\n"
                    + "Balance Amount :" + amountField.getText() + "CVC :" + cvcField.getText()  + "\n"
                    +  "interest Rate: "+ interestRateField.getText()  + "\n" + "\n" + "Expiration Date : " + dayBoxC.getSelectedItem().toString()+ "/" +  monthBoxC.getSelectedItem().toString()+ "/" +  yearBoxC.getSelectedItem()+ "/" + 
                    "\n" + " Credit Limit :"+ creditLimitField.getText() + "\n" + "Grace Period :" + gracePeriodField.getText() ;
                for (BankCard bankcard : bankGui)
                {
                    if (bankcard instanceof CreditCard) {
                        CreditCard creditCard = (CreditCard) bankcard;
                        creditCard.display();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Credit card not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                JOptionPane.showMessageDialog(frame, message, "Your Details" , JOptionPane.INFORMATION_MESSAGE);

            }
        }

}

    public static void main(String[] args)
    {
        //  constructor ko object
        BankGUI obj = new BankGUI();
    }

}
public class Bank {
    public static void main (String[] args) {
        BankAccount a1 = new BankAccount();
        BankAccount a2 = new Savings();
        Savings s1 = new Savings();
        Savings s2 = s1;
        Savings s3 = new PremireSavings();
        Object s4 = new Savings();
        PremireSavings p1 = new Savings();
        PremireSavings p2 = new BankAccount();
        s1.updateBalance(100.00);
        s2.updateBalance(50.00);

        a2.toString();
        s1.toString();
        s2.toString();
        s3.toString();
        s4.toString();
        s3.getCardNumber();
        s1.getBalance();
        s2.getBalance();
        s3.getNumber();
        s2.getNumber();
        s3.toString();
        s4.toString();
    }
}

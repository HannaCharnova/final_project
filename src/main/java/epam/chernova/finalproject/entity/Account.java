package epam.chernova.finalproject.entity;

import java.util.Objects;

public class Account {
    private int idAccount;
    private double credit;
    private String accountNumber;
    private int idClient;

    public Account() {
    }

    public Account(int idAccount, double credit, String accountNumber, int idClient) {
        this.idAccount = idAccount;
        this.credit = credit;
        this.accountNumber = accountNumber;
        this.idClient = idClient;
    }

    public Account(double credit, String accountNumber, int idClient) {
        this.credit = credit;
        this.accountNumber = accountNumber;
        this.idClient = idClient;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return idAccount == account.idAccount &&
                Double.compare(account.credit, credit) == 0 &&
                idClient == account.idClient &&
                Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAccount, credit, accountNumber, idClient);
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", credit=" + credit +
                ", accountNumber='" + accountNumber + '\'' +
                ", idClient=" + idClient +
                '}';
    }
}

package epam.chernova.finalproject.entity.ext;

import epam.chernova.finalproject.entity.User;

import java.io.Serializable;
import java.util.Objects;

public class Client extends User implements Serializable, Cloneable {
    private String name;
    private String surname;
    private String email;
    private double point;
    private boolean ban;
    private String address;

    public Client() {
        super();
    }

    public Client(int idClient, String login, String password, String name, String surname, String email, double point, boolean ban, boolean role, String address) {
        super(idClient, login, password, role);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.point = point;
        this.ban = ban;
        this.address = address;
    }

    public Client(String login, String password, String name, String surname, String email, double point, boolean ban, boolean role, String address) {
        super(login, password, role);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.point = point;
        this.ban = ban;
        this.address = address;
    }

    public Client(String login, String password, String name, String surname, String email, boolean role) {
        super(login, password, role);
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    @Override
    public boolean getRole() {
        return super.getRole();
    }

    @Override
    public int getIdUser() {
        return super.getIdUser();
    }

    @Override
    public String getLogin() {
        return super.getLogin();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Double.compare(client.point, point) == 0 &&
                ban == client.ban &&
                Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(email, client.email) &&
                Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name, surname, email, point, ban, address);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", point=" + point +
                ", ban=" + ban +
                ", address='" + address + '\'' +
                '}';
    }
}

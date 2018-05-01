package epam.chernova.finalproject.entity.ext;

import epam.chernova.finalproject.entity.User;

import java.io.Serializable;
import java.util.Objects;

public class Administrator extends User implements Serializable, Cloneable {
    private boolean isMain;

    public Administrator() {
        super();
    }

    public Administrator(boolean isMain) {
        super();
        this.isMain = isMain;
    }

    public Administrator(int idUser, String login, String password, boolean role, boolean isMain) {
        super(idUser, login, password, role);
        this.isMain = isMain;
    }

    public Administrator(String login, String password, boolean role, boolean isMain) {
        super(login, password, role);
        this.isMain = isMain;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Administrator that = (Administrator) o;
        return isMain == that.isMain;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), isMain);
    }

    @Override
    public String toString() {
        return super.toString()+"Administrator{" +
                "isMain=" + isMain +
                '}';
    }
}

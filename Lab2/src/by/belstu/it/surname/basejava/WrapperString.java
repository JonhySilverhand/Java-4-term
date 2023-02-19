package by.belstu.it.surname.basejava;

import java.util.Objects;
/**
 @author author
 @version 2.1
 */
public class WrapperString {
    private String stroka;
    public WrapperString(String stroka) {
        this.stroka = stroka;
    }
    public String getStroka() {
        return stroka;
    }
    public void setStroka(String stroka) {
        this.stroka = stroka;
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    @Override
    public int hashCode() {
        return Objects.hash(stroka);
    }
    @Override
    public String toString() {
        return "WrapperString{" +
                "stroka='" + stroka + '\'' +
                '}';
    }
    /**@param oldchar - старый символ
     * @param newchar - новый символ*/
    public void replace (char oldchar, char newchar){
        System.out.println("replace");
    }

}

package sample;

public class Teachers {
    private String name,phone;
    private int teacherid;
    private String eMail;
    private int isActive;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Teachers(int teacherid, String name, String phone, String eMail, int isActive) {
        this.name = name;
        this.phone = phone;
        this.teacherid = teacherid;
        this.eMail = eMail;
        this.isActive = isActive;
    }
}

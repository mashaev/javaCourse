package sample;

public class Students {
    private String name,phone,e_mail;
    private int isActive;
    private int studentid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getPhone() {
        return phone;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getStudentid() {
        return studentid;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public Students(int studentid, String name, String phone, String e_mail, int isActive ) {
        this.studentid = studentid;
        this.name = name;
        this.phone = phone;
        this.e_mail = e_mail;
        this.isActive = isActive;

    }
}

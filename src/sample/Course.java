package sample;

public class Course {
    Days dayAndTimes;
    private int courseid;
    private String name;
    private int price;
    private String day;
    private String time;

    public Course(int courseid, String name, int price,String day,String time) {
        this.courseid = courseid;
        this.name = name;
        this.price = price;
        this.day = day;
        this.time =time;

    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

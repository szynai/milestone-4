package oakwood;

public class Visitor {
    private String name;
    private String residentToVisit;
    private String purpose;
    private String date;
    private String timeIn;
    private String status;

    public Visitor(String name, String residentToVisit, String purpose,
                   String date, String timeIn, String status) {
        this.name = name;
        this.residentToVisit = residentToVisit;
        this.purpose = purpose;
        this.date = date;
        this.timeIn = timeIn;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getResidentToVisit() {
        return residentToVisit;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getDate() {
        return date;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


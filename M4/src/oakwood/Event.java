package oakwood;

public class Event {
    private String name;
    private String contact;
    private String eventName;
    private String eventDate;
    private String status;

    public Event(String name, String contact, String eventName,
                 String eventDate, String status) {
        this.name = name;
        this.contact = contact;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


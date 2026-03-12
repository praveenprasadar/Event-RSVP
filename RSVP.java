
import java.io.Serializable;

public class RSVP implements Serializable, Comparable<RSVP> {
    private int rsvpid;
    private String guestname;
    private String email;
    //private String phone;
    private String status;

    public RSVP(int rsvpid, String guestname, String email, String status) {
        this.email = email;
        this.guestname = guestname;
        this.rsvpid = rsvpid;
        //this.phone = phone;
        this.status = status;
    }

    public RSVP() {
    }

    public int getRsvpid() {
        return rsvpid;
    }

    public void setRsvpid(int rsvpid) {
        this.rsvpid = rsvpid;
    }

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RSVP{");
        sb.append("rsvpid=").append(rsvpid);
        sb.append(", guestname=").append(guestname);
        sb.append(", email=").append(email);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }


    
    @Override
    public int compareTo(RSVP other) {
        return Integer.compare(this.rsvpid, other.rsvpid);
    }

    
}

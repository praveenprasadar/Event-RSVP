import java.util.List;
public interface CRUD {
    public void addRSVP(RSVP rsvp);
    public List<RSVP> getAllRSVPs();
    public void updateRSVP(RSVP rsvp);
    public void deleteRSVP(int rsvpid);
}
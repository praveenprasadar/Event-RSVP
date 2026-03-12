import java.util.Comparator; // sorted by guest name

public class GuestNameComparator implements Comparator<RSVP> {
    @Override
    public int compare(RSVP r1, RSVP r2) {
        return r1.getGuestname().compareToIgnoreCase(r2.getGuestname());
    }
}
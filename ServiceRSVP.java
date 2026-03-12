import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceRSVP implements CRUD {
    private File data;
    private List<RSVP> rsvpList ;
    public ServiceRSVP() {
        this.data =new File("rsvpdata.doc");
        if(!data.exists()){
            try {
                this.data.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // Exception for invalid email
public static class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }
}



// Exception for missing RSVP record
public static class RSVPNotFoundException extends RuntimeException {
    public RSVPNotFoundException(String message) {
        super(message);
    }
}

    public void readfile(){
        try {
            FileInputStream fis = new FileInputStream(data);
            if(data.length()<=0){
                rsvpList = new ArrayList<>();
                return;
            }
            else{
                ObjectInputStream ois = new ObjectInputStream(fis);
                rsvpList = (List<RSVP>) ois.readObject();
                ois.close();
            }
            fis.close();
            
        }
        catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void writefile(){
        // Implementation for writing to file would go here
        try {
            FileOutputStream fos = new FileOutputStream(data);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(rsvpList);
            oos.close();
            fos.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static final String EMAIL_REGEX ="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final Pattern EMAIL_PATTERN =Pattern.compile(EMAIL_REGEX);
    private boolean isValidEmail(String email) {
        if (email == null) return false;
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    @Override
    public void addRSVP(RSVP rsvp) {
        readfile();
        if (!isValidEmail(rsvp.getEmail())) {
            throw new InvalidEmailException("Invalid email: " + rsvp.getEmail());
        }
        rsvpList.add(rsvp);
        writefile();
        System.out.println("RSVP added successfully.");
}

    public RSVP getRSVP(int rsvpid) {
        readfile();
        for (RSVP r : rsvpList) {
            if (r.getRsvpid() == rsvpid) {
                return r;
            }
        }
        throw new RSVPNotFoundException("RSVP with ID " + rsvpid + " not found.");
    }

    @Override
    //public List<RSVP> getAllRSVPs() {
    public List<RSVP> getAllRSVPs() {//compareable sort...
        readfile();
        Collections.sort(rsvpList);
        return rsvpList;
    }

    public List<RSVP> getAllRSVPsSortedByName() {//this is sort by name...
        readfile();
        Collections.sort(rsvpList, new GuestNameComparator());
        return rsvpList;
    }

    @Override
    public void updateRSVP(RSVP rsvp) {
        readfile();
        for(int index=0;index<rsvpList.size();index++){
            if(rsvpList.get(index).getRsvpid()==rsvp.getRsvpid()){
                rsvpList.set(index, rsvp);
                writefile();
                System.out.println("RSVP updated successfully.");
                return;
            }
        }
        System.out.println("RSVP with ID "+rsvp.getRsvpid()+" not found.");
    }

    @Override
    public void deleteRSVP(int rsvpid) {
        readfile();
        for(int index=0;index<rsvpList.size();index++){
            if(rsvpList.get(index).getRsvpid()==rsvpid){
                rsvpList.remove(index);
                writefile();
                System.out.println("RSVP deleted successfully.");
                return;
            }
        }
        System.out.println("RSVP with ID "+rsvpid+" not found.");
    }
}

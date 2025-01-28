
import java.util.ArrayList;
public  class Admin {
    private String adminid;
    private String adminpassword;
        // Transfer Money from one account to another
 public String getAdminId(){
    return adminid;
 }
 public String getAdminPassword(){
    return adminpassword;
 }

    public Admin() {
    }
 
     public Admin(String adminid, String adminpassword) {
                this.adminid = adminid;
                this.adminpassword = adminpassword;
            }

            public boolean AreYouAdmin(String id, String password, ArrayList<Admin> admins) {
                // Check if admin exists
                if (checkAdmins(id, admins)) {
                    // Admin found, now check the credentials
                    for (Admin a : admins) {
                        if (id.equals(a.getAdminId()) && password.equals(a.getAdminPassword())) {
                            return true; // Credentials match
                        }
                    }
                }
                return false; // Either admin not found or credentials don't match
            }
            
            public boolean checkAdmins(String id, ArrayList<Admin>admins) {
                for (Admin a : admins) {
                    if (id.equals(a.getAdminId())) {
                        return true; // Admin found
                    }
                }
                return false; // Admin not found
            }
            
    }


    

       

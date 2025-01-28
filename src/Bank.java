
import java.util.ArrayList;

    public class Bank {
        //constructor
        public Bank(){
        }
       
        // Remove an existing customer
        public void removeCustomer(String customerId, ArrayList<Customer>customers,ArrayList<Account>accounts) {
                 accounts.removeIf(account -> {
                    if (account.getId().equals(account.getId())) {
                        return true;
                    }else{
                            return false;
                        }
                 });
           boolean b = customers.removeIf(customer->{
            if (customer.getId().equals(customer.getId())) {
                return true;
            }else{
                    return false;
                }
            });
            if(b){
                System.out.println("Customer removed successfully!");
                System.out.println("Account removed successfully!");
            }else{
                System.out.println("Customer not found!");
            }
        }
        // View all customer
            public static void ViewAllCustomers(ArrayList<Customer> customers) {
                if (customers.isEmpty()) {
                    System.out.println("No customers found.");
                    return;
                }else{
                    System.out.println("All Customers:");
                for (Customer customer : customers) {
                    System.out.println(customer.getName()+" "+customer.getId());
                     }
                }
        }
        
        // Find an account by ID
        public Account findAccountById(String accountId, ArrayList<Account> accounts) {
            for (Account account : accounts) {
                if (account.getId().equals(accountId)) {
                    return account;
                }
            }
            return null; // Account not found
        }     
      }
    
    




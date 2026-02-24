import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

// -----------------------------
// Inheritance: Person is parent class
// -----------------------------
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

// -----------------------------
// Encapsulation: User class hides details with private fields
// -----------------------------
class User extends Person {
    private String email;
    private String phone;
    private String passwordHash;

    public User(String name, String email, String phone, String password) {
        super(name);
        this.email = email;
        this.phone = phone;
        this.passwordHash = hashPassword(password); // Precondition: password is not null
    }

    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    // Hashing ensures password is stored securely
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Postcondition: return true only if password matches stored hash
    public boolean checkPassword(String inputPassword) {
        return this.passwordHash.equals(hashPassword(inputPassword));
    }
}

// -----------------------------
// Profile class
// -----------------------------
class Profile {
    private String name;
    private String address;
    private String photo;

    public Profile(String name, String address, String photo) {
        this.name = name;
        this.address = address;
        this.photo = photo;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    @Override
    public String toString() {
        return "Profile{name='" + name + "', address='" + address + "', photo='" + photo + "'}";
    }
}

// -----------------------------
// ProfileUpdater
// -----------------------------
class ProfileUpdater {
    public void updateProfile(Profile profile, String newName, String newAddress, String newPhoto) {
        System.out.println("start");
        System.out.println(":User selects update profile;");

        if (newName != null && !newName.equals("")) {
            System.out.println(":Update name;");
            profile.setName(newName);
        } else {
            System.out.println(":Keep old name;");
        }

        if (newAddress != null && !newAddress.equals("")) {
            System.out.println(":Update address;");
            profile.setAddress(newAddress);
        } else {
            System.out.println(":Keep old address;");
        }

        if (newPhoto != null && !newPhoto.equals("")) {
            System.out.println(":Update photo;");
            profile.setPhoto(newPhoto);
        } else {
            System.out.println(":Keep old photo;");
        }

        System.out.println(":Profile updated successfully;");
        System.out.println("stop");
    }
}

// -----------------------------
// Emergency Contacts
// -----------------------------
class Contact {
    protected String phone;

    public Contact(String phone) { this.phone = phone; }
    public String getPhone() { return phone; }
    public void display() { System.out.println("Phone: " + phone); }
}

class EmergencyContact extends Contact {
    public EmergencyContact(String phone) { super(phone); }
    @Override
    public void display() { System.out.println("Emergency Contact: " + phone); }
}

class InvalidPhoneNumberException extends Exception {
    public InvalidPhoneNumberException(String msg) { super(msg); }
}

class ContactNotFoundException extends Exception {
    public ContactNotFoundException(String msg) { super(msg); }
}

class EmergencyContactManager {
    private List<Contact> emergencyContacts = new ArrayList<>();

    public void addContact(String phone) throws InvalidPhoneNumberException {
        if (!phone.matches("[0-9]{10,13}")) {
            throw new InvalidPhoneNumberException("Invalid contact number");
        }
        emergencyContacts.add(new EmergencyContact(phone));
        System.out.println("Contact added successfully");
    }

    public void deleteContact(String phone) throws ContactNotFoundException {
        boolean removed = emergencyContacts.removeIf(c -> c.getPhone().equals(phone));
        if (!removed) throw new ContactNotFoundException("Contact not found");
        System.out.println("Contact deleted successfully");
    }

    public void viewContacts() {
        if (emergencyContacts.size() == 0) {
            System.out.println("No contacts available");
            return;
        }
        System.out.println("Displaying all contacts:");
        for (Contact c : emergencyContacts) {
            c.display();
        }
    }
}

// -----------------------------
// Location Providers
// -----------------------------
abstract class LocationProvider {
    public abstract String getLocation();
}

class GPSProvider extends LocationProvider {
    @Override
    public String getLocation() { return "Lat: 24.8607, Lon: 67.0011 (from GPS)"; }
}

class WiFiProvider extends LocationProvider {
    @Override
    public String getLocation() { return "Lat: 24.8610, Lon: 67.0020 (from WiFi)"; }
}

class NetworkProvider extends LocationProvider {
    @Override
    public String getLocation() { return "Lat: 24.8620, Lon: 67.0030 (from Network)"; }
}

// -----------------------------
// SOS Flow
// -----------------------------
interface AlertSender {
    void sendAlert(String message, String location);
    String getType();
}

class InternetAlertSender implements AlertSender {
    public void sendAlert(String message, String location) {
        System.out.println("\n[InternetAlertSender] Sending alert over internet...");
        System.out.println("Payload: " + message + (location != null ? (" | " + location) : ""));
        System.out.println("[InternetAlertSender] Sent.");
    }
    public String getType() { return "Internet"; }
}

class SMSAlertSender implements AlertSender {
    public void sendAlert(String message, String location) {
        System.out.println("\n[SMSAlertSender] Sending alert via SMS...");
        System.out.println("Payload: " + message + (location != null ? (" | " + location) : ""));
        System.out.println("[SMSAlertSender] Sent.");
    }
    public String getType() { return "SMS"; }
}

// -----------------------------
// Main App
// -----------------------------
public class SafeCityApp {
    private static final String EMAIL_REGEX = ".+@.+\\..+";
    private static final String PHONE_REGEX = "\\d{10,13}";
    private static Map<String, User> emailUsers = new HashMap<>();
    private static Map<String, User> phoneUsers = new HashMap<>();

    public static boolean login(String email, String password) {
        User u = emailUsers.get(email);
        return u != null && u.checkPassword(password);
    }

    public static boolean loginByPhone(String phone, String password) {
        User u = phoneUsers.get(phone);
        return u != null && u.checkPassword(password);
    }

    public static void register(String name, String email, String phone, String password) throws Exception {
        if (!email.matches(EMAIL_REGEX)) throw new Exception("Invalid email format");
        if (!phone.matches(PHONE_REGEX)) throw new Exception("Invalid phone number");
        if (emailUsers.containsKey(email) || phoneUsers.containsKey(phone)) {
            throw new Exception("User already exists with this email or phone");
        }
        User newUser = new User(name, email, phone, password);
        emailUsers.put(email, newUser);
        phoneUsers.put(phone, newUser);
        System.out.println("Registration successful");
    }

    private static boolean askYesNo(Scanner scanner, String prompt) {
        System.out.print(prompt + " (y/n): ");
        while (true) {
            String line = scanner.nextLine().trim().toLowerCase();
            if (line.startsWith("y")) return true;
            if (line.startsWith("n")) return false;
            System.out.print("Please answer y or n: ");
        }
    }

    private static void showConfirmation(String via, String message, String location) {
        System.out.println("\n--- Confirmation to user ---");
        System.out.println("SOS sent via: " + via);
        System.out.println("Message: " + message);
        if (location != null) System.out.println("Location: " + location);
        else System.out.println("Location: (none)");
        System.out.println("A confirmation has been shown to the user.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("=== SafeCityApp Main Menu ===");
            System.out.println("Options: register | login | profile | contacts | location | sos | exit");

            while (true) {
                System.out.print("\nEnter option: ");
                String option = sc.nextLine().trim().toLowerCase();

                if (option.equals("register")) {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine().trim();
                    System.out.print("Enter phone: ");
                    String phone = sc.nextLine().trim();
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();
                    register(name, email, phone, password);

                } else if (option.equals("login")) {
                    System.out.println("Choose (email / phone):");
                    String choice = sc.nextLine().trim().toLowerCase();
                    if (choice.equals("email")) {
                        System.out.print("Enter email: ");
                        String email = sc.nextLine().trim();
                        System.out.print("Enter password: ");
                        String password = sc.nextLine();
                        if (login(email, password)) System.out.println("Access granted");
                        else System.out.println("Login failed");
                    } else if (choice.equals("phone")) {
                        System.out.print("Enter phone: ");
                        String phone = sc.nextLine().trim();
                        System.out.print("Enter password: ");
                        String password = sc.nextLine();
                        if (loginByPhone(phone, password)) System.out.println("Access granted");
                        else System.out.println("Login failed");
                    }
                } else if (option.equals("profile")) {
                    Profile profile = new Profile("Alice", "123 Old St", "alice.jpg");
                    ProfileUpdater updater = new ProfileUpdater();
                    System.out.println("Initial Profile: " + profile);
                    System.out.print("Enter new name (or blank): ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new address (or blank): ");
                    String newAddress = sc.nextLine();
                    System.out.print("Enter new photo (or blank): ");
                    String newPhoto = sc.nextLine();
                    updater.updateProfile(profile, newName, newAddress, newPhoto);
                    System.out.println("Updated Profile: " + profile);

                } else if (option.equals("contacts")) {
                    EmergencyContactManager manager = new EmergencyContactManager();
                    System.out.println("Options: add | delete | view | back");
                    while (true) {
                        System.out.print("\nContact option: ");
                        String cOpt = sc.nextLine().trim().toLowerCase();
                        try {
                            if (cOpt.equals("add")) {
                                System.out.print("Enter phone: ");
                                String phone = sc.nextLine();
                                manager.addContact(phone);
                            } else if (cOpt.equals("delete")) {
                                System.out.print("Enter phone to delete: ");
                                String phone = sc.nextLine();
                                manager.deleteContact(phone);
                            } else if (cOpt.equals("view")) {
                                manager.viewContacts();
                            } else if (cOpt.equals("back")) {
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }

                } else if (option.equals("location")) {
                    boolean gpsEnabled = askYesNo(sc, "Is GPS enabled?");
                    boolean wifiAvailable = askYesNo(sc, "Is WiFi available?");
                    boolean networkAvailable = askYesNo(sc, "Is Network available?");
                    LocationProvider provider = null;
                    if (gpsEnabled) provider = new GPSProvider();
                    else if (wifiAvailable) provider = new WiFiProvider();
                    else if (networkAvailable) provider = new NetworkProvider();
                    if (provider == null) System.out.println("Location unavailable");
                    else {
                        String location = provider.getLocation();
                        System.out.println("Current location: " + location);
                    }

                } else if (option.equals("sos")) {
                    System.out.print("Trigger SOS (voice/button): ");
                    String trigger = sc.nextLine().trim().toLowerCase();
                    boolean voiceRecognized = false;
                    if ("voice".equals(trigger)) {
                        voiceRecognized = askYesNo(sc, "Is the voice recognized?");
                        if (!voiceRecognized) {
                            System.out.println("Voice not recognized");
                            continue;
                        }
                        System.out.println("Voice recognized. Proceed with SOS.");
                    } else {
                        System.out.println("Button pressed.");
                    }

                    boolean internetAvailable = askYesNo(sc, "Is internet available?");
                    AlertSender sender;
                    if (internetAvailable) sender = new InternetAlertSender();
                    else {
                        boolean smsAvailable = askYesNo(sc, "Is SMS available?");
                        if (smsAvailable) sender = new SMSAlertSender();
                        else {
                            System.out.println("No method available");
                            continue;
                        }
                    }

                    boolean gpsEnabled = askYesNo(sc, "Is GPS enabled?");
                    System.out.print("Enter SOS message (or blank for default): ");
                    String message = sc.nextLine();
                    if (message.trim().isEmpty()) message = "SOS! I need help.";
                    String location = null;
                    if (gpsEnabled) {
                        System.out.print("Enter location (or blank for mock): ");
                        String locInput = sc.nextLine().trim();
                        if (locInput.isEmpty()) location = "Lat:24.8607, Lon:67.0011 (mock)";
                        else location = locInput;
                    }
                    sender.sendAlert(message, location);
                    showConfirmation(sender.getType(), message, location);

                } else if (option.equals("exit")) {
                    System.out.println("Exiting SafeCityApp. Goodbye!");
                    break;
                } else {
                    System.out.println("Unknown option");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}

package cs3220;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// Main Application Class
@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    // MessageComponent class
    @Component
    public static class MessageComponent {
        private List<MessageEntry> messages;

        public MessageComponent() {
            this.messages = new ArrayList<>();
        }

        public List<MessageEntry> getMessage() {
            return messages;
        }

        public void setMessage(List<MessageEntry> messages) {
            this.messages = messages;
        }

        public MessageEntry getMessageById(int id) {
            for (MessageEntry message : this.messages) {
                if (message.getId() == id) {
                    return message;
                }
            }
            return null;
        }

        public void removeMessage(MessageEntry message) {
            this.messages.remove(message);
        }
    }

    // UserComponent class
    @Component
    public static class UserComponent {
        private List<UserEntry> users;

        public UserComponent() {
            this.users = new ArrayList<>();
        }

        public Boolean checkUserExist(String email) {
            System.out.println("checkUserExist " + email);
            for (UserEntry user : this.users) {
                if (user.getEmail().equals(email)) {
                    return true;
                }
            }
            return false;
        }

        public UserEntry getUserByEmail(String email) {
            for (UserEntry user : this.users) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
            }
            return null;
        }

        public UserEntry getUserById(int id) {
            for (UserEntry user : this.users) {
                if (user.getId() == id) {
                    return user;
                }
            }
            return null;
        }

        public List<UserEntry> getUsers() {
            return users;
        }

        public void setUsers(List<UserEntry> users) {
            this.users = users;
        }
    }

    // Define MessageEntry and UserEntry as placeholders 
    public static class MessageEntry {
        private int id;
        private String content;

        public MessageEntry(int id, String content) {
            this.id = id;
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public String getContent() {
            return content;
        }
    }

    public static class UserEntry {
        private int id;
        private String email;

        public UserEntry(int id, String email) {
            this.id = id;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }
    }
}

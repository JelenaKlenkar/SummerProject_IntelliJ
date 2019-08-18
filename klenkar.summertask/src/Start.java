import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Start {
    private List<Applicant> applicants = new ArrayList<Applicant>();
    private Connection connection;

    public Start() {

        applicants = new ArrayList<>();
        main_loop:
        while (true) {
            menu();
            switch (Helper.enterInteger("Enter number from menu: ")) {
                case 1:
                    listApplicants();
                    break;
                case 2:
                    applicants.add(enterApplicant());
                    break;
                case 3:
                    editApplicant();
                    break;
                case 4:
                    deleteApplicant();
                    break;
                case 5:
                    openEra();
                    break;
                case 6:
                    openGitHubCode();
                    break;
                case 7:
                    break main_loop;
                default:
                    break;

            }
        }


    }

    private void openGitHubCode() {
        String url = "https://github.com/JelenaKlenkar/SummerProject_IntelliJ/tree/master/klenkar.summertask/src";
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void openEra() {
        String url = "https://github.com/JelenaKlenkar/SummerProject_IntelliJ/blob/master/klenkar.summertask/era/human_resources.png";
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void deleteApplicant() {
        connection = ConnectToDatabase.getConnection();
        listApplicants();
        try {
            PreparedStatement expression = connection.prepareStatement("delete from applicant where id =?");
            expression.setInt(1, ordinalNumberOfApplicant());
            expression.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editApplicant() {
        connection = ConnectToDatabase.getConnection();
        listApplicants();
        int id = ordinalNumberOfApplicant();
        Applicant a = new Applicant();

        try {
            PreparedStatement expression = connection.prepareStatement("Select * from applicant where id = '" + id + ";'");
            ResultSet rs = expression.executeQuery();
            while (rs.next()) {
                System.out.println("First name: " + rs.getString("firstName"));
                System.out.println("Last name: " + rs.getString("lastName"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("Phone number: " + rs.getInt("phoneNumber"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Personal identification number: " + rs.getInt("personalIdentificationNumber"));
                System.out.println("Applicant cv: " + rs.getString("applicantCV"));
                System.out.println("Motivational letter: " + rs.getString("motivationalLetter"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        a = setValues(a);
        try {
            PreparedStatement expressionUpdate = connection.prepareStatement("update applicant set firstName=?,lastName=?,address=?,phoneNumber=?,email=?,personalIdentificationNumber=?,applicantCV=?,motivationalLetter=? where id ='" + id + "';");
            expressionUpdate.setString(1, a.getFirstName());
            expressionUpdate.setString(2, a.getLastName());
            expressionUpdate.setString(3, a.getAddress());
            expressionUpdate.setInt(4, a.getPhoneNumber());
            expressionUpdate.setString(5, a.getEmail());
            expressionUpdate.setInt(6, a.getPersonalIdentificationNumber());
            expressionUpdate.setString(7, a.getApplicantCv());
            expressionUpdate.setString(8, a.getMotivationalLetter());
            expressionUpdate.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private int ordinalNumberOfApplicant() {
        int on;
        while (true) {
            on = Helper.enterInteger("Enter ordinal number");
            if (on < 0) {
                JOptionPane.showConfirmDialog(null, "Entering ordinal number required");
                continue;
            }
            return on;
        }
    }

    private Applicant enterApplicant() {
        connection = ConnectToDatabase.getConnection();
        Applicant a = new Applicant();
        a = setValues(a);
        String sql = "INSERT INTO applicant"
                + "(id,firstName,lastName, address,phoneNumber,email,personalIdentificationNumber,applicantCV,motivationalLetter)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement expression = connection.prepareStatement(sql);
            expression.setNull(1, java.sql.Types.INTEGER);
            expression.setString(2, a.getFirstName());
            expression.setString(3, a.getLastName());
            expression.setString(4, a.getAddress());
            expression.setInt(5, a.getPhoneNumber());
            expression.setString(6, a.getEmail());
            expression.setInt(7, a.getPersonalIdentificationNumber());
            expression.setString(8, a.getApplicantCv());
            expression.setString(9, a.getMotivationalLetter());
            expression.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;

    }

    private void listApplicants() {

        connection = ConnectToDatabase.getConnection();

        try {
            PreparedStatement expression = connection.prepareStatement("Select * from applicant");
            ResultSet rs = expression.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("firstName") + " " + rs.getString("lastName"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    private void menu() {
        System.out.println("===MENU SELECTION===");
        System.out.println("1.List all the applicants");
        System.out.println("2.Enter new applicant");
        System.out.println("3.Edit applicant");
        System.out.println("4.Delete applicant");
        System.out.println("5.Open ERA diagram");
        System.out.println("6.Open GITHUB code");
        System.out.println("7.Exit the program");
    }

    private Applicant setValues(Applicant a) {
        a.setFirstName(Helper.enterString("Enter first name: "));
        a.setLastName(Helper.enterString("Enter last name: "));
        a.setAddress(Helper.enterString("Enter address: "));
        a.setPhoneNumber(Helper.enterInteger("Enter phone number: "));
        a.setEmail(Helper.enterString("Enter email: "));
        a.setPersonalIdentificationNumber(Helper.enterInteger("Enter personal identification number: "));
        a.setApplicantCv(Helper.enterString("Enter applicant cv: "));
        a.setMotivationalLetter(Helper.enterString("Enter motivational letter: "));
        return a;
    }

    public static void main(String[] args) {
        new Start();
    }
}

public class Start {

    public Start() {

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

    private Applicant setValues(Applicant a){
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

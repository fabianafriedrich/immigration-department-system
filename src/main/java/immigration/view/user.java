package immigration.view;

import immigration.model.People;
import immigration.model.PriorityLevels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class user {

    String fName = "", lName = "", passport = "", input = "", inputDate = "", option = "";
    Date doa;
    PriorityLevels priorityLevels;

    public user(){

        menu();
    }

    public void menu(){
        System.out.println("\n");
        System.out.println("1.Add a new Person");
        System.out.println("2.See the Position of a Person ");
        System.out.println("3.Delete a Person");
        System.out.println("4.Remove Number of Peoples");
        System.out.println("5.Update Information");
        System.out.println("\n");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input2 = "";

        try {

            boolean validMenu = false;

            do {
                System.out.println("Please select a number: ");
                input2 = br.readLine();

                if (input2.matches("[0-9]+")) {
                    validMenu = true;
                } else {
                    validMenu = false;
                }


            } while (validMenu == false);

        } catch (Exception e) {
            System.out.println("Error");
        }
        //System.out.println(input);

        //Check the user number and send to the menu select
        if (input2.equals("1")) {
            addNewPerson();
        } else if (input2.equals("2")) {
            seePosition();
        } else if (input2.equals("3")) {
            deletePerson();
        } else if (input2.equals("4")) {
            removeNumberOfPeope();
        } else if (input2.equals("5")) {
            updateInfo();
        }
    }


    private void updateInfo() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long uniqueN ;
        String id ="";

        try {
            System.out.println("Please input the person id:");
            id = br.readLine();
            if (id.equals("")) {
                System.out.println("Person id can be null");
                updateInfo();
            }else if (id.matches("[0-9]+")){
                uniqueN = Long.valueOf(id);
                System.out.println("Update Information of the person.");
                System.out.println("Please insert first name");
                fName = br.readLine();
                String regex = "^[a-zA-Z]+$";
                if (fName.equals("") || !fName.matches(regex)) {
                    System.out.println("Input can't be empty or a number, try again ");
                    addNewPerson();
                }
                System.out.println("Please insert last name");
                lName = br.readLine();
                if (lName.equals("") || !lName.matches(regex)) {
                    System.out.println("Input can't be empty or a number, try again ");
                    addNewPerson();
                }

                System.out.println("Please insert date of arrival (dd/MM/yyyy)");
                inputDate = br.readLine();
                if (inputDate.equals("")) {
                    System.out.println("Input can't be empty  try again ");
                    addNewPerson();
                } else {
                    if (validDate(inputDate) == true) {
                        doa = new Date(inputDate);
                    } else {
                        System.out.println("Input is not valid as a Date ");
                        addNewPerson();
                    }
                }
                System.out.println("Please insert passport number");
                passport = br.readLine();
                if (passport.equals("")) {
                    System.out.println("Input can't be empty, try again ");
                    addNewPerson();
                }

                System.out.println("Would like to update this person information? 1.Yes or 2.No .Choose a number");
                option = br.readLine();
                if (option.equals("1")) {
                    System.out.println("This person has been updated, Thank you");
                    //SAVE IT ON THE QUEUE AND DATABASE
                    menu();
                } else {
                    menu();
                }

            }else {
                System.out.println("Please input has to be an id valid");
                updateInfo();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void removeNumberOfPeope() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input the number of the people you want to remove");
    }

    private void deletePerson() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input the id number of the person you want to delete");
        String id = "";
        try {
            id = br.readLine();
            if (id.equals("") || !id.matches("[0-9]+")){
                System.out.println("Please input a ID valid");
                deletePerson();
            }else {
                System.out.println("Are you sure you want to delete this person? Yes or No" );
                String delete = br.readLine();
                if (delete.equals("YES") || delete.equals("Yes") || delete.equals("yes")){
                    //remove person
                    System.out.println("This person were deleted");
                    menu();
                }else {
                    menu();
                }


            }


        }catch (Exception e){

        }
    }

    private void seePosition() {
    }

    public static boolean validDate(String strDate){
        /* * Set preferred date format,
         * For example dd.MM.yyyy etc.*/
        SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
        sdfrmt.setLenient(false);
        /* Create Date object
         * parse the string into date
         */
        try
        {
            Date javaDate = sdfrmt.parse(strDate);
        }
        /* Date format is invalid */
        catch (ParseException e)
        {
            System.out.println(strDate+" is Invalid Date format");
            return false;
        }
        /* Return true if date format is valid */
        return true;
    }
    private void addNewPerson() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {

            System.out.println("Please insert first name");
            fName = br.readLine();
            String regex = "^[a-zA-Z]+$";
            if (fName.equals("")|| !fName.matches(regex)) {
                System.out.println("Input can't be empty or a number, try again ");
                addNewPerson();
            }
            System.out.println("Please insert last name");
            lName = br.readLine();
            if (lName.equals("") || !lName.matches(regex)){
                System.out.println("Input can't be empty or a number, try again ");
                addNewPerson();
            }

            System.out.println("Please insert date of arrival (dd/MM/yyyy)");
            inputDate = br.readLine();
            if (inputDate.equals("")){
                System.out.println("Input can't be empty  try again ");
                addNewPerson();
            }else{
                if (validDate(inputDate) == true) {
                    doa = new Date(inputDate);
                }else {
                    System.out.println("Input is not valid as a Date ");
                    addNewPerson();
                }
            }
            System.out.println("Please insert passport number");
            passport = br.readLine();
            if (passport.equals("") ){
                System.out.println("Input can't be empty, try again ");
                addNewPerson();
            }
            System.out.println("Choose Priority Level = 1.HIGH PRIORITY, 2.MEDIUM PRIORITY, 3.LOW PRIORITY");
            input = br.readLine();
            if (input.equals("1")) {
                priorityLevels = PriorityLevels.HIGH;
            } else if (input.equals("2")) {
                priorityLevels = PriorityLevels.MEDIUM;
            } else if (input.equals("3")) {
                priorityLevels = PriorityLevels.LOW;
            }else {
                addNewPerson();
            }

            System.out.println("Would like to add this person to the queue? 1.Yes or 2.No .Choose a number");
            option = br.readLine();
            if (option.equals("1")) {
                System.out.println("This person has been added, Thank you");
                //SAVE IT ON THE QUEUE AND DATABASE
                menu();
            } else {
                menu();
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

        new user();

    }



}

package org.example;

import com.staffmanagement.system.managers.Manager;
import com.staffmanagement.system.managers.StaffManager;
import com.staffmanagement.system.services.Service;
import com.staffmanagement.system.services.StaffServiceJson;
import com.staffmanagement.system.utils.interfaces.Reader;
import com.staffmanagement.system.utils.interfaces.Writer;
import com.staffmanagement.system.utils.json.JsonFileCreator;
import com.staffmanagement.system.utils.json.JsonReader;
import com.staffmanagement.system.utils.json.JsonWriter;

import java.io.File;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        File jsonFile = JsonFileCreator.createFile("employees.json");
        //File csvFile = FileCreator.createFile("employees.csv");
        //Writer jsonWriter = new JsonWriter(jsonFile);
        //Reader jsonReader = new JsonReader(jsonFile);
        Reader reader = new JsonReader(jsonFile);
        Writer writer = new JsonWriter(jsonFile);
        Service service = new StaffServiceJson(writer, reader);
        Manager manager = new StaffManager(service);
        System.out.println("Welcome to Staff Management System");
        displayCommands();
        boolean isRunning = true;
        int command;
        try (Scanner scanner = new Scanner(System.in)) {
            while (isRunning) {
                System.out.println("Enter command: ");
                try {
                    command = scanner.nextInt();
                    if (command == 9) {
                        isRunning = false;
                        continue;
                    }
                    manager.executeCommand(command, scanner);
                }catch (Exception e){
                    System.out.println("Please enter a valid data next time.");
                    scanner.nextLine();
                }
            }
        }catch (Exception e){
            System.out.println("Error occurred!");
        }
    }

    private static void displayCommands() {
        System.out.println("Available Commands:");
        System.out.println("1. Add Employee");
        System.out.println("2. Display Employees");
        System.out.println("3. Fire Employee");
        System.out.println("4. Edit Employee");
        System.out.println("5. Search Employee by Id");
        System.out.println("6. Search Employee by Name");
        System.out.println("7. Search Employee by Department");
        System.out.println("8. Sort by name");
        System.out.println("9. Exit");
    }

}

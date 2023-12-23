# Final project 

# Brief description

Java Spring Boot application designed to process data from CSV files containing information about employees working on various projects. The primary goal of the application is to identify pairs of employees who have collaborated on common projects for the longest periods and provide insights into the duration of each collaboration.

# Key Features

* CSV File Processing: Reads data from CSV files, extracting details about employees, projects, and the time they spent working together.
  
* Database Integration: Stores processed data in a PostgreSQL database for persistent storage.
  
* Collaboration Analysis: Identifies pairs of employees who have collaborated on common projects for the longest periods.
  
* Thymeleaf UI: Provides a user-friendly interface for interacting with the application and viewing collaboration insights

# Main Algorithm Explanation

1. Data Retrieval:

  * Read data from the CSV file, representing employees working on projects within specified date ranges (EmployeeToProjectCsv: employeeId, projectId, dateFrom, dateTo).

2. Data Validation and Conversion:

  * For each line in the file:
    * Check if employees and projects with the given IDs exist in the database.
    * Check if the object already exist in the database.
    * Perform date format conversions and validations.
    * Skip lines with invalid data.
      
3. Data Storage in Database and Dynamic List:

  * Use a Mapper to convert CSV file objects (EmployeeToProjectCsv) to entities (EmployeeToProject), including assigning unique IDs.
  * Save valid objects to the database.
  * Maintain a dynamic list of valid objects for optimization purposes. Storing and processing the data dynamically is more cost-effective than saving it to the database and retrieving it for further processing.
  
4. Main Logic Execution:

  * Take the list of valid objects and convert it into a map where the key is the projectId, and the value is the object containing employee information and the time range worked on the project.

5. Identify Overlapping Periods:

  * Detect pairs of employees with overlapping periods by creating a list of EmployeePairs.
  * EmployeePair contains two EmployeeToProjectCsv objects and a map with projectIds and the corresponding periods worked on them.

6. Calculate Working Days:

  * Implement a method to calculate the sum of values in the map for each EmployeePair, representing the total working days on common projects.

7. Identify Most Collaborative Pair:

  * Choose the EmployeePair with the largest sum of working days in the map, indicating the pair that has worked together on common projects for the longest period.


## Technologies Used

- Spring Boot
- PostgreSQL
- Thymeleaf
- HTML

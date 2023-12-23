package com.example.FinalProject.utils;

import com.example.FinalProject.model.EmployeesPair;
import com.example.FinalProject.utils.fileOperations.fileModel.EmployeeToProjectCsv;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MainLogic {
    /**
     * Converts a list of EmployeeToProject objects into a map,
     * where the key is the project ID and the value is a list
     * of EmployeeToProjectCsv objects representing employees
     * working on the corresponding projects.
     *
     * @param list The list of EmployeeToProject objects to convert.
     * @return A Map<Long, List<EmployeeToProjectCsv>> representing the
     *         association between project IDs and employees.
     */
    public static Map<Long,List<EmployeeToProjectCsv>> convertListToMap(List<EmployeeToProjectCsv> list){
        Map<Long, List<EmployeeToProjectCsv>> projectsMap = new HashMap<>();
        for (EmployeeToProjectCsv item : list){
            // If the project ID is not already in the map, add a new ArrayList
            projectsMap.putIfAbsent(item.getProjectId(), new ArrayList<>());

            // Get the existing ArrayList for the project ID
            List<EmployeeToProjectCsv> employeeList = projectsMap.get(item.getProjectId());

            // Add the current item to the ArrayList
            employeeList.add(item);
        }
        return projectsMap;
    }

    /**
     * Generates a list of EmployeesPair objects based on a given map of projects
     * and their associated lists of EmployeeToProjectCsv objects.
     *
     * @param map A HashMap where project IDs are keys, and values are lists of
     *            EmployeeToProjectCsv objects associated with each project.
     * @return A list of EmployeesPair objects representing pairs of employees who
     *         worked on common projects, along with the durations of their collaboration
     *         on each project.
     */
    public static List<EmployeesPair> putEmployeesInPairs(Map<Long, List<EmployeeToProjectCsv>> map){
        List<EmployeesPair> listPairs = new ArrayList<>();

        for (var project : map.entrySet()){
            List<EmployeeToProjectCsv> employees = project.getValue();
            for (int i = 0; i < employees.size(); i++) {
                EmployeeToProjectCsv employeeOne = map.get(project.getKey()).get(i);
                for (int j = i + 1; j < employees.size(); j++) {
                    EmployeeToProjectCsv employeeTwo = map.get(project.getKey()).get(j);
                    // Check if the work periods of employeeOne and employeeTwo overlap
                    long overlappingDays = calculateOverlapDays(employeeOne.getDateFrom(), employeeOne.getDateTo(),
                            employeeTwo.getDateFrom(), employeeTwo.getDateTo());
                    if (overlappingDays > 0) {
                        processOverlap(listPairs, employeeOne, employeeTwo, project.getKey(), overlappingDays);
                    }
                }
            }
        }
        return listPairs;
    }

    /**
     * Processes the overlap between two employees and updates the list of pairs accordingly.
     *
     * @param listPairs    The list of existing pairs.
     * @param projectKey   The project ID associated with the overlapping pair.
     */
    private static void processOverlap(List<EmployeesPair> listPairs,
                EmployeeToProjectCsv employeeOne,
                EmployeeToProjectCsv employeeTwo,
                Long projectKey, long overlappingDays) {

        EmployeesPair pair;
        // Check if a pair with these employees already exists
        if ((pair = pairExisting(employeeOne, employeeTwo, listPairs)) != null) {
            // Pair exists, update the project and duration information
            pair.getProjectsAndDurations().put(projectKey, (int)overlappingDays);
        } else {
            // Pair does not exist, create a new pair with project and duration information
            Map<Long, Integer> projectsDurations = new HashMap<>();
            projectsDurations.put(projectKey, (int)overlappingDays);
            EmployeesPair employeesPair = new EmployeesPair(employeeOne, employeeTwo, projectsDurations);
            listPairs.add(employeesPair);
        }
    }

    /**
     * Checks if there is an overlap between the work periods of two employees.
     *
     * @param startDate1 Start date of the first employee.
     * @param endDate1   End date of the first employee.
     * @param startDate2 Start date of the second employee.
     * @param endDate2   End date of the second employee.
     * @return The number of overlapping days, if there is no overlap, then 0
     */
    private static long calculateOverlapDays(LocalDate startDate1, LocalDate endDate1,
                                            LocalDate startDate2, LocalDate endDate2) {
        // Check for no overlap
        if (endDate1.isBefore(startDate2) || endDate2.isBefore(startDate1)) {
            return 0;
        }

        // Calculate the overlap
        LocalDate overlapStartDate = startDate1.isAfter(startDate2) ? startDate1 : startDate2;
        LocalDate overlapEndDate = endDate1.isBefore(endDate2) ? endDate1 : endDate2;

        // Calculate the number of days in the overlap
        return ChronoUnit.DAYS.between(overlapStartDate, overlapEndDate) + 1;
    }


    /**
     * Checks if a pair with the given employees already exists in the list of pairs.
     *
     * @param employeeOne The first employee.
     * @param employeeTwo The second employee.
     * @param listPairs   The list of existing pairs.
     * @return The existing pair if found; null otherwise.
     */
    private static EmployeesPair pairExisting(EmployeeToProjectCsv employeeOne, EmployeeToProjectCsv employeeTwo, List<EmployeesPair> listPairs){
        for (EmployeesPair pair : listPairs) {
            if ((pair.getEmployeeOne().getEmployeeId().equals(employeeOne.getEmployeeId()) && pair.getEmployeeTwo().getEmployeeId().equals(employeeTwo.getEmployeeId())) ||
                    (pair.getEmployeeOne().getEmployeeId().equals(employeeTwo.getEmployeeId()) && pair.getEmployeeTwo().getEmployeeId().equals(employeeOne.getEmployeeId()))) {
                // Pair with the specified employees already exists
                return pair;
            }
        }
        return null;
    }

}

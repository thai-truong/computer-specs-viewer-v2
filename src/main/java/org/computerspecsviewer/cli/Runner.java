package org.computerspecsviewer.cli;

import org.computerspecsviewer.infoquery.controller.InfoQueryController;
import org.computerspecsviewer.infoquery.utils.PrintHelpers;
import org.computerspecsviewer.infoquery.utils.StringHelpers;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        InfoQueryController iqc = new InfoQueryController();

        Scanner inputReader = new Scanner(System.in);
        setUpShutdownHook(inputReader);

        while(true) {
            printStartingInstructions();
            String queryRequest = inputReader.next();

            if(foundQuitInput(queryRequest)) {
                break;
            }

            if(iqc.foundInfoType(queryRequest)) {
                printInfoQuery(iqc, queryRequest);
            } else if(queryRequest.equals("all")) {
                for(String infoType: InfoQueryController.allInfoTypes) {
                    printInfoQuery(iqc, infoType);
                }
            } else {
                printInfoTypeNotFound(queryRequest);
            }
        }
    }

    private static void cleanUpRunner(Scanner scanner) {
        printClosingStatements();
        scanner.close();
    }

    private static void setUpShutdownHook(Scanner scanner) {
        Thread closingHook = new Thread(() -> cleanUpRunner(scanner));
        Runtime.getRuntime().addShutdownHook(closingHook);
    }

    private static void printInfoQuery(InfoQueryController iqc, String queryRequest) {
        System.out.println();
        System.out.println(StringHelpers.transformFieldName(queryRequest) + " Info:");
        System.out.println(PrintHelpers.injectTabs(iqc.getInfoQuery(queryRequest).toString(), 1));
    }

    private static void printStartingInstructions() {
        List<String> allInfoTypes = Arrays.asList(InfoQueryController.allInfoTypes);
        String printedAllInfoTypes = PrintHelpers.injectTabs(PrintHelpers.injectNewlinesInFront(allInfoTypes).toString(), 1);

        System.out.println(String.format("\n- Type one of these choices to view their information: %s",
                printedAllInfoTypes));
        System.out.println("- To view all types of information, type \"all\"");
        System.out.println("- To quit, type \"q\" or \"quit\"!");
        System.out.println("\nEnter information listed above that you want to see: ");
    }

    private static void printClosingStatements() {
        System.out.println("\n- Thank you so much for using Computer Specs Viewer CLI!");
        System.out.println("- Hope you had a great experience.");
    }

    private static void printInfoTypeNotFound(String invalidInfoType) {
        System.out.println(String.format("\n- You have inputted an invalid info type of value %s. " +
                "Please only select the supported info types", invalidInfoType));
    }

    private static boolean foundQuitInput(String input) {
        if(input.equals("q") || input.equals("quit")) {
            return true;
        }

        return false;
    }
}

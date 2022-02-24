package org.computerspecsviewer.cli;

import org.computerspecsviewer.infoquery.controller.InfoQueryController;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        InfoQueryController iqc = new InfoQueryController();

        Scanner inputReader = new Scanner(System.in);
        setUpShutdownHook(inputReader);

        while(true) {
            PrintingHelpers.printStartingInstructions();
            String queryRequest = inputReader.next();

            if(foundQuitInput(queryRequest)) {
                break;
            }

            if(iqc.foundInfoType(queryRequest)) {
                PrintingHelpers.printInfoQuery(iqc, queryRequest);
            } else if(queryRequest.equals("all")) {
                for(String infoType: InfoQueryController.allInfoTypes) {
                    PrintingHelpers.printInfoQuery(iqc, infoType);
                }
            } else {
                PrintingHelpers.printInfoTypeNotFound(queryRequest);
            }
        }
    }

    private static void cleanUpRunner(Scanner scanner) {
        PrintingHelpers.printClosingStatements();
        scanner.close();
    }

    private static void setUpShutdownHook(Scanner scanner) {
        Thread closingHook = new Thread(() -> cleanUpRunner(scanner));
        Runtime.getRuntime().addShutdownHook(closingHook);
    }

    private static boolean foundQuitInput(String input) {
        if(input.equals("q") || input.equals("quit")) {
            return true;
        }

        return false;
    }
}

package org.computerspecsviewer.cli;

import org.computerspecsviewer.infoquery.controller.InfoQueryController;
import org.computerspecsviewer.infoquery.utils.PrintHelpers;
import org.computerspecsviewer.infoquery.utils.StringHelpers;

import java.util.Arrays;
import java.util.List;

public class PrintingHelpers {
    static void printInfoQuery(InfoQueryController iqc, String queryRequest) {
        System.out.println();
        System.out.println(StringHelpers.transformFieldName(queryRequest) + " Info:");
        System.out.println(PrintHelpers.injectTabs(iqc.get(queryRequest).toString(), 1));
    }

    static void printStartingInstructions(InfoQueryController iqc) {
        List<String> allInfoTypes = iqc.getInfoQueryTypes();
        String infoTypesWithNewlines = PrintHelpers.injectNewlinesInFront(allInfoTypes).toString();
        String printedAllInfoTypes = PrintHelpers.injectTabs(infoTypesWithNewlines, 1);

        System.out.println(String.format("\n- Type one of these choices to view their information: %s",
                printedAllInfoTypes));
        System.out.println("- To view all types of information, type \"all\"");
        System.out.println("- To quit, type \"q\" or \"quit\"!");
        System.out.println("\nEnter information listed above that you want to see: ");
    }

    static void printClosingStatements() {
        System.out.println("\n- Thank you so much for using Computer Specs Viewer CLI!");
        System.out.println("- Hope you had a great experience.");
    }

    static void printInfoTypeNotFound(String invalidInfoType) {
        System.out.println(String.format("\n- You have inputted an invalid info type of value %s. " +
                "Please only select the supported info types", invalidInfoType));
    }
}

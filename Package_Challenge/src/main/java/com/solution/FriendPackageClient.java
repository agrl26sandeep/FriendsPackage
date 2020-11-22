package com.solution;

import com.solution.action.ServiceAction;

import java.util.Scanner;

/**
 * This is a Friend package client which receives a package with different items.
 * @author Sandeep Agrawal
 */
public class FriendPackageClient {
    public static void main(String[] args) {
        // Getting Input from User
        Scanner in = new Scanner(System.in);
        System.out.println("Enter input file location: ");
        String filePath = in.nextLine();
        System.out.println("You entered file path: '" + filePath + "'");

        //Calling Package service
        System.out.println(ServiceAction.FRIENDPACKAGE.getInstance().execute(filePath).toString());
    }
}

package com.solution.service;

import com.solution.action.ProcessAction;
import com.solution.action.ReaderAction;
import com.solution.domain.PackageItem;
import com.solution.domain.Response;
import com.solution.exception.FileReaderException;
import com.solution.exception.NotSupportedException;
import com.solution.parser.FriendPackageRequestParser;
import com.solution.reader.FileReaderFactory;
import com.solution.utility.FileUtility;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *  Class is provide service to process and prepare the friends package item
 * @author Sandeep Agrawal
 */
public class FriendPackageService implements IService<Response<Map<String, List<PackageItem>>>> {
    /**
     *  Mehod is to execute the following:
     *      1. Read the given input file by passing the custom parser
     *      2. Process the parsed object to compute the friends package item.
     *      3. Return the response
     *
     * @param filePath
     * @return
     */
    @Override
    public Response execute(String filePath) {
        ReaderAction action = null;
        try {
            switch (FileUtility.getFileExtension(filePath).get().toUpperCase()){
                case "TXT":
                    action = ReaderAction.READ_TXT;
                    break;
                default:
                    throw new NotSupportedException(filePath);
            }

            FileReaderFactory readerFactory = FileReaderFactory.getInstance();
            readerFactory.setParser(new FriendPackageRequestParser());
            return new Response("Request successfully processed!",
                    ProcessAction.FRIENDPACKAGE.getInstance()
                            .process(readerFactory.readFile(action, filePath)));

        } catch (FileReaderException | NotSupportedException ex) {
            return new Response(ex.getMessage(), null);
        } catch (NoSuchElementException nex) {
            return new Response("Please provide the valid input file", null);
        }
    }
}

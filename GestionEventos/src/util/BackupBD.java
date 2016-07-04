package util;

import java.io.IOException;

/**
 * Created by JuanAJ on 03/07/2016.
 */
public class BackupBD {
    public static boolean backup(String mysqldumpPath, String backupPath) {
        boolean status = false;
        String username = "name";
        String password = "pword";
        String database = "database_name";


        String command = "/" + mysqldumpPath + "/mysqldump -u " + username + " -p" + password + " " + database + " -r " + backupPath;
        try {
            Process runtimeProcess = Runtime.getRuntime().exec(command);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("DatabaseManager.backup: Backup Successfull");
                status = true;
            } else {
                System.out.println("DatabaseManager.backup: Backup Failure!");
            }
        } catch (IOException ioe) {
            System.out.println("Exception IO");
            ioe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
        return status;
    }
}

package edu.virginia.cs.helloworld.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.sql.*;
import java.io.*;
import java.util.HashMap;

@Service
public class UserCheckService {

    HashMap<String, String> usersAndPasswords = new HashMap<>();

    public UserCheckService() {
        try {
            File passFile = ResourceUtils.getFile("classpath:user.txt");

            InputStream inputStream = new FileInputStream(passFile);

            if (inputStream != null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));

                String l = buffer.readLine();
                while (l != null) {
                    String[] userAndPass = l.split(",");
                    String validUser = userAndPass[0].trim();
                    String validPass = userAndPass[1].trim();
                    /*System.out.println(validUser);
                    System.out.println(validPass);*/
                    usersAndPasswords.put(validUser, validPass);
                    l = buffer.readLine();
                }
                buffer.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkPassword(String username, String password) {


        if ((password == null) || (username == null)) {
            return false;
        } else if (!usersAndPasswords.containsKey(username)) {
            return false;
        } else if (!usersAndPasswords.get(username).equals(password)) {
            return false;
        } else {
            return true;
        }
    }
}

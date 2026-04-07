package com.pedrojvdv.jdbc.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {

    public static String encode(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean match(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }

}

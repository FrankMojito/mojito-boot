package com.mojito.mojitoboot.web.URLCnstant;

/**
 * @Auther: Mojito
 * @Date: 2019/1/21 00:37
 * @Description:
 */
public interface URLConstant {

    // is static final
     String LINE = "-";
    /**
     * about user
     */
    class UserURL {
        public static final String USER = "user";
        public static final String USER_INFO =  USER + LINE + "info";
        public static final String USER_LOGIN = USER + LINE + "login";
        public static final String USER_REGISTER = USER + LINE + "register";
        public static final String GETOTP = USER + LINE + "getotp";
    }

    /**
     * about goods
     */
    class GoodsUrl{

    }
}

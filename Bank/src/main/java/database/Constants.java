package database;

import java.util.*;

import static database.Constants.Rights.*;
import static database.Constants.Roles.*;

public class Constants {

    public static class Schemas {
        public static final String TEST = "test_bank";
        public static final String PRODUCTION = "bank";

        public static final String[] SCHEMAS = new String[]{TEST, PRODUCTION};
    }

    public static class Tables {
        public static final String CLIENT = "client";
        public static final String ACCOUNT = "account";
        public static final String USER = "user";
        public static final String ROLE = "role";
        public static final String RIGHT = "right";
        public static final String ROLE_RIGHT = "role_right";
        public static final String USER_ROLE = "user_role";

        public static final String[] ORDERED_TABLES_FOR_CREATION = new String[]{USER, ROLE, RIGHT, ROLE_RIGHT, USER_ROLE, ACCOUNT, CLIENT};
    }

    public static class Roles {
        public static final String ADMINISTRATOR = "administrator";
        public static final String EMPLOYEE = "employee";
        public static final String CLIENT = "client";

        public static final String[] ROLES = new String[]{ADMINISTRATOR, CLIENT, EMPLOYEE};
    }

    public static class Rights {
        //public static final String CREATE_CLIENT = "create_client";
        //public static final String UPDATE_CLIENT = "update_client";
        //public static final String VIEW_CLIENT = "view_client";

        public static final String CREATE_ACCOUNT = "create_account";
        public static final String READ_ACCOUNT = "read_account";
        public static final String UPDATE_ACCOUNT = "update_account";
        public static final String DELETE_ACCOUNT = "delete_account";

        public static final String ADD_INFO = "add_info";
        public static final String UPDATE_INFO = "update_info";
        public static final String VIEW_INFO = "view_info";

        public static final String[] RIGHTS = new String[]{ADD_INFO, UPDATE_INFO, VIEW_INFO, CREATE_ACCOUNT, READ_ACCOUNT, UPDATE_ACCOUNT, DELETE_ACCOUNT};
    }

    public static Map<String, List<String>> getRolesRights() {
        Map<String, List<String>> ROLES_RIGHTS = new HashMap<>();
        for (String role : ROLES) {
            ROLES_RIGHTS.put(role, new ArrayList<>());
        }
        ROLES_RIGHTS.get(ADMINISTRATOR).addAll(Arrays.asList(RIGHTS));
        ROLES_RIGHTS.get(EMPLOYEE).addAll(Arrays.asList(CREATE_ACCOUNT, READ_ACCOUNT, UPDATE_ACCOUNT, DELETE_ACCOUNT, ADD_INFO, UPDATE_INFO, VIEW_INFO));

        return ROLES_RIGHTS;
    }

}
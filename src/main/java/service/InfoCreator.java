package service;

import model.EmailMessage;
import model.User;
import utils.TestDataReader;

public class InfoCreator {
    private static final String EMAIL = TestDataReader.getTestData("email");
    private static final String PASSWORD = TestDataReader.getTestData("password");
    private static final String RECIPIENT = TestDataReader.getTestData("recipient");
    private static final String SUBJECT = TestDataReader.getTestData("subject");
    private static final String BODY = TestDataReader.getTestData("body");

    public static User getInfoUser() {
        return new User(EMAIL, PASSWORD);
    }

    public static EmailMessage getInfoMail() {
        return new EmailMessage(RECIPIENT, SUBJECT, BODY);
    }

}

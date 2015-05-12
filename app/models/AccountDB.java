package models;

/**
 * Provides an in-memory repository for UserInfo.
 * Storing credentials in the clear is kind of bogus.
 * @author Philip Johnson
 * Created by Eric on 5/12/2015.
 */
public class AccountDB {

  /**
   * Adds the specified user to the UserInfoDB.
   * @param name Their name.
   * @param email Their email.
   * @param password Their password.
   */
  public static void addUserInfo(String name, String email, String password) {
    Account account = new Account(name, email, password);
    account.save();
  }

  /**
   * Returns true if the email represents a known user.
   * @param email The email.
   * @return True if known user.
   */
  public static boolean isUser(String email) {
    Account account = Account.find().byId(email);

    if (account == null) {
      throw new RuntimeException("Can't find account with the given id.");
    }
    return true;
  }

  /**
   * Returns the UserInfo associated with the email, or null if not found.
   * @param email The email.
   * @return The UserInfo.
   */
  public static Account getUser(String email) {
    Account account = Account.find().byId(email);

    if (account == null) {
      throw new RuntimeException("Can't find account with the given id.");
    }
    return account;
  }

  /**
   * Returns true if email and password are valid credentials.
   * @param email The email.
   * @param password The password.
   * @return True if email is a valid user email and password is valid for that email.
   */
  public static boolean isValid(String email, String password) {
    return ((email != null)
        &&
        (password != null)
        &&
        isUser(email)
        &&
        getUser(email).getPassword().equals(password));
  }
}

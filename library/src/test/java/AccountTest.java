import com.step.bank.Account;
import com.step.bank.InvalidAccountNumberException;
import com.step.bank.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

  private Account account;

  @Before
  public void setUp() throws MinimumBalanceException, InvalidAccountNumberException {
    account = new Account("1234-1234", 1000);
  }

  @Test
  public void checkBalance(){
    assertThat(account.getBalance(), is((float) 1000));
  }

  @Test(expected = MinimumBalanceException.class)
  public void checkMinimumBalance() throws MinimumBalanceException, InvalidAccountNumberException {
    new Account("1322-4234",200);
  }

  @Test(expected = InvalidAccountNumberException.class)
  public void checkAccountNumberValidity() throws MinimumBalanceException, InvalidAccountNumberException {
    new Account("1234",1000);
    new Account("12344323",1000);
    new Account("12-34",1000);
    new Account("1245-32344",1000);
  }

  @Test
  public void checkAccountNumber() {
    assertThat(account.getAccountNumber(), is("1234-1234"));
  }

  @Test
  public void withdraw() throws MinimumBalanceException, InvalidAccountNumberException {
    Account account = new Account("1234-1264", 2000);
    assertThat(account.withdraw(200), is((float) 1800));
    assertThat(account.getBalance(), is((float) 1800));
  }
}

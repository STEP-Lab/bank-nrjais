import com.step.bank.Account;
import com.step.bank.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

  private Account account;

  @Before
  public void setUp() throws MinimumBalanceException {
    account = new Account("1234", 1000);
    //implement--
    // accountNumber 1232-4221
    //balance to float
    //withdraw feature check balance also
  }

  @Test
  public void checkBalance(){
    assertThat(account.getBalance(), is((float) 1000));
  }

  @Test(expected = MinimumBalanceException.class)
  public void checkMinimumBalance() throws MinimumBalanceException {
    new Account("132",200);
  }

  @Test
  public void checkAccountNumber() {
    assertThat(account.getAccountNumber(), is("1234"));
  }
}

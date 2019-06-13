package pl.atd.restdemo.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.atd.restdemo.client.handler.DefaultApi;
import pl.atd.restdemo.client.model.Actual;
import pl.atd.restdemo.client.model.Agreement;
import pl.atd.restdemo.client.model.Agreements;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientApplicationTests {

	@Autowired
	private DefaultApi defaultApi;

	@Test
	public void shouldCallServiceForActual() {
		Actual actuals = defaultApi.getActual(1, 2019, 1);
		System.out.println(actuals);
		assertEquals(Integer.valueOf(1), actuals.getId());
		assertEquals(2019, actuals.getDate().getYear());
		assertEquals(1, actuals.getDate().getMonthOfYear());
	}

	@Test
	public void shouldCallServiceForAgreement() {
		Agreement agreement = defaultApi.getAgreement(1);
		assertEquals(Integer.valueOf(1), agreement.getId());
	}

	@Test
	public void shouldCallServiceForAgreements() {
		Agreements agreements = defaultApi.getAgreementList();
		assertEquals(3, agreements.getAgreementList().size());
	}

}

package net.dean.jraw;

import junit.framework.Assert;
import net.dean.jraw.models.Captcha;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CaptchaTest {
	private static RedditClient reddit;

	@BeforeClass
	public static void setUp() {
		reddit = TestUtils.client(CaptchaTest.class);
		String[] credentials = TestUtils.getCredentials();
		try {
			reddit.login(credentials[0], credentials[1]);
		} catch (NetworkException | ApiException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testNeedsCaptchaWorking() {
		try {
			reddit.needsCaptcha();
		} catch (NetworkException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNonNullCaptchaComponents() {
		try {
			Captcha c = reddit.getNewCaptcha();
			Assert.assertNotNull(c.getId());
			Assert.assertNotNull(c.getImageStream());
		} catch (NetworkException e) {
			Assert.fail(e.getMessage());
		}
	}
}

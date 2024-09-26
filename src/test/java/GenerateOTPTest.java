import com.semicolon.africa.security.authentication.AuthOServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@SpringBootTest
class GenerateOTPTest {
    @Test
    void testThatOTPCanBeGenerated(){
        AuthOServiceImpl authenticator = new AuthOServiceImpl();
        String otp = authenticator.generateOTP();
        assertThat(otp.length()).isEqualTo(6);
    }
}
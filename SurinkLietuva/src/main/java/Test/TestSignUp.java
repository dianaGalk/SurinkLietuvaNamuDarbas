package Test;

import com.example.surinklietuva.Controllers.SignUpView;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;


public class TestSignUp {
    @Test
    public void TestUserPassword() throws FileNotFoundException {
        SignUpView signUpView = new SignUpView();
        Assert.assertEquals(signUpView.checkPasswordLength("kk"),false);
        Assert.assertEquals(signUpView.checkPasswordLength("0123456789012345"),false);
        Assert.assertEquals(signUpView.checkPasswordLength("kkkk"),true);
        Assert.assertEquals(signUpView.checkPasswordLength("012345678901234"),true);
        Assert.assertEquals(signUpView.checkPasswordLength("01234567890"),true);
    }
    @Test
    public void TestUserEmail(){
        SignUpView signUpView = new SignUpView();
        //Assert.assertEquals(signUpView.checkEmailValidation("diana.gmail.com"),true);                 //1.1 - wrong test
        //Assert.assertEquals(signUpView.checkEmailValidation("diana"),true);                           //1.1 - wrong test
        Assert.assertFalse(signUpView.checkEmailValidation("diana.gmail.com"));                //1.2 - correct
        Assert.assertTrue(signUpView.checkEmailValidation("diana@gmail.com"));                 //1.2 - correct
        Assert.assertFalse(signUpView.checkEmailValidation("@gmail.com"));
    }
    @Test
    public void TestUserPasswordValidation(){
        SignUpView signUpView = new SignUpView();
        Assert.assertTrue(signUpView.checkPasswordValidation("AAAbbbccc@123"));
        Assert.assertTrue(signUpView.checkPasswordValidation("A!@#&()–a1"));
        Assert.assertFalse(signUpView.checkPasswordValidation("12345678"));
        Assert.assertFalse(signUpView.checkPasswordValidation("ABC123$$$"));
    }


}

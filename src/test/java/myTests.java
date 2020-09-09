
import com.mycompany.NotificationHub.Company;
import com.mycompany.NotificationHub.DTOs.MailDTO;
import com.mycompany.NotificationHub.Senders.EmailSender;
import com.mycompany.NotificationHub.Exceptions.BlackListException;
import com.mycompany.NotificationHub.Exceptions.NoServiceFoundException;
import com.mycompany.NotificationHub.Exceptions.TwoMonthsNotPaidException;
import com.mycompany.NotificationHub.Languages.English;
import com.mycompany.NotificationHub.Packets.FixedPacketForEmail;
import com.mycompany.NotificationHub.Packets.FlexiblePacketForSMS;
import com.mycompany.NotificationHub.PaymentAccount;
import com.mycompany.NotificationHub.Service;
import com.mycompany.NotificationHub.User;
import com.mycompany.NotificationHub.Exceptions.UserAlreadyExistsInPostGroupException;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;

public class myTests {

    @Test
    public void remaining_money_must_equal_to_expected_after_payment() throws Exception{
        // Given
        Service serv = new Service(); 
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(1200, 200));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FixedPacketForEmail());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com", "testing payment...");

        //When
        double moneyBeforePayment = firm.getAccount().getCurrentMoney();
        firm.sendEmail(testEmail);
        double bill = firm.getEmailPacket().calculateCurrentCost();
        firm.payEmailPacketBill();
        double moneyAfterPayment = firm.getAccount().getCurrentMoney();
        
        //Then
        assertEquals(moneyBeforePayment - bill, moneyAfterPayment, 0);
            

    }
    @Test
    public void it_should_return_expected_fixed_email_cost() throws Exception{
        // Given
        Service serv = new Service();
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(1200, 200));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FixedPacketForEmail());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com", "testing fixed email cost...");

        //When
        for (int i = 0; i < 2001; i++) {
            firm.sendEmail(testEmail);
        }
        
        //Then
        //Fixed email packet must cost 30 due to 10(2000 post) + 10(2000 post) + 10 (1 of new 2000 post) 
        assertEquals(30, firm.getEmailPacket().calculateCurrentCost(), 0);

    }

    @Test
    public void it_should_return_expected_flexible_sms_cost() throws Exception{
        // Given
        Service serv = new Service();
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(1200, 200));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FlexiblePacketForSMS());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com", "testing flexible sms cost...");

        //When
        for (int i = 0; i < 2005; i++) {
            firm.sendEmail(testEmail);
        }

        
        //Then
        //Flexible SMS Packet must cost 30.5 due to 30(2000 post) + 0.1*5(5 posts)
        assertEquals(30.5, firm.getEmailPacket().calculateCurrentCost(), 0);
        

    }

    @Test
    public void bills_should_remain_same() throws Exception {
        // Given
        Service serv = new Service();
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(5, 3));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FixedPacketForEmail());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com", "testing payment...");

        // When
        for(int i=0;i<4;i++)
            firm.sendEmail(testEmail);
        double billBeforePayment = firm.getEmailPacket().calculateCurrentCost();
        firm.payEmailPacketBill();
        double billAfterPayment = firm.getEmailPacket().calculateCurrentCost();
        assertEquals(billBeforePayment, billAfterPayment, 0);
        // Then
    }

    @Test(expected = TwoMonthsNotPaidException.class)
    public void it_should_throw_TwoMonthsNotPaidException() throws Exception {
        // Given
        Service serv = new Service();
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(5, 0));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FixedPacketForEmail());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com", "testing twoMonthsNotPaidException...");

        // When
        for(int i=0;i<3;i++)
            firm.sendEmail(testEmail);
        firm.getEmailPacket().setRegistrationDate(LocalDateTime.now().minusDays(70));
        firm.sendEmail(testEmail); //Packet bill not paid after 70 days, it should return exception
    }

    @Test(expected = UserAlreadyExistsInPostGroupException.class)
    public void it_should_throw_UserAlreadyExistsInPostGroupException() throws Exception {
        // Given
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        User userToAdd = new User("john","doe", "treny@hotmail.com", "+90000000000", new English());
        firm.addToPostGroup(userToAdd);
        
        // When
        firm.addToPostGroup(userToAdd);
    }
    
    @Test(expected = BlackListException.class)
    public void it_should_throw_BlackListException() throws Exception {

        // Given
        Service serv = new Service();
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(5, 0));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FixedPacketForEmail());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com", "testing BlackListException...");

        // When
        serv.addToBlackList(firm.getUserInformation().getUniqueID());
        firm.sendEmail(testEmail);
    }

    @Test(expected = NoServiceFoundException.class)
    public void it_should_throw_NoServiceFoundException() throws Exception {
        // Given
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(5, 0));
        firm.setEmailPacket(new FixedPacketForEmail());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com", "testing NoServiceFoundException...");

        // When
        firm.sendEmail(testEmail);
    }

}

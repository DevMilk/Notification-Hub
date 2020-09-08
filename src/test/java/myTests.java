
import com.mycompany.NotificationHub.Company;
import com.mycompany.NotificationHub.DTOs.MailDTO;
import com.mycompany.NotificationHub.DTOs.SMSDTO;
import com.mycompany.NotificationHub.EmailSender;
import com.mycompany.NotificationHub.Exceptions.BlackListException;
import com.mycompany.NotificationHub.Exceptions.NoServiceFoundException;
import com.mycompany.NotificationHub.Exceptions.NotEnoughMoneyException;
import com.mycompany.NotificationHub.Exceptions.TwoMonthsNotPaidException;
import com.mycompany.NotificationHub.Languages.English;
import com.mycompany.NotificationHub.Packets.FixedEmailPacket;
import com.mycompany.NotificationHub.Packets.FlexibleSMSPacket;
import com.mycompany.NotificationHub.PaymentAccount;
import com.mycompany.NotificationHub.Service;
import com.mycompany.NotificationHub.SmsSender;
import com.mycompany.NotificationHub.User;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;


public class myTests {
    
    @Test
    public void remaining_money_must_equal_to_expected_after_payment(){
        // Given
        Service serv = new Service();
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(1200,200));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FixedEmailPacket());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com","testing...");
        
        //When
        try{
            
            double moneyBeforePayment = firm.getAccount().getCurrentMoney();
            firm.sendEmail(testEmail);
            double bill = firm.getEmailPacket().calculateCurrentCost();
            firm.payEmailPacketBill();
            double moneyAfterPayment =firm.getAccount().getCurrentMoney();
            assertEquals(moneyBeforePayment-bill,moneyAfterPayment,0);
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
        @Test
    public void it_should_return_expected_fixed_email_cost(){
        // Given
        Service serv = new Service();
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(1200,200));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FixedEmailPacket());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com","testing...");
        
        //When
        try{
            for(int i=0;i<2001;i++)
                firm.sendEmail(testEmail);
            
            assertEquals(30, firm.getEmailPacket().calculateCurrentCost(),0);
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    @Test
    public void it_should_return_expected_flexible_sms_cost(){
        // Given
        Service serv = new Service();
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(1200,200));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FlexibleSMSPacket());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com","testing...");
        
        //When
        try{
            for(int i=0;i<2005;i++)
                firm.sendEmail(testEmail);
            
            assertEquals(30.5,firm.getEmailPacket().calculateCurrentCost(),0);
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
   
    @Test
    public void bills_should_remain_same() throws Exception {
        // Given
        Service serv = new Service();
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(5,3));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FixedEmailPacket());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com","testing...");
        
        // When
        
        firm.sendEmail(testEmail);
        firm.sendEmail(testEmail);
        firm.sendEmail(testEmail);
        firm.sendEmail(testEmail);
        double billBeforePayment = firm.getEmailPacket().calculateCurrentCost();
        firm.payEmailPacketBill();
        double billAfterPayment = firm.getEmailPacket().calculateCurrentCost();
        assertEquals(billBeforePayment,billAfterPayment,0);
        // Then
    }
    
    @Test(expected = TwoMonthsNotPaidException.class)
    public void it_should_throw_TwoMonthsNotPaidException() throws Exception {
        // Given
        Service serv = new Service();
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(5,0));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FixedEmailPacket());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com","testing...");
        
        // When
        firm.sendEmail(testEmail);
        firm.sendEmail(testEmail);
        firm.sendEmail(testEmail);
        firm.getEmailPacket().setRegistrationDate(LocalDateTime.now().minusDays(70));
        firm.sendEmail(testEmail);
    }
    
    @Test(expected = BlackListException.class)
    public void it_should_throw_BlackListException() throws Exception{
        
        // Given
        Service serv = new Service();
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(5,0));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FixedEmailPacket());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com","testing...");
        
        // When
        serv.addToBlackList(firm.getUniqueID());
        firm.sendEmail(testEmail);
    }
    
    @Test(expected = NoServiceFoundException.class)
    public void it_should_throw_NoServiceFoundException() throws Exception {
        // Given
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(5,0));
        firm.setEmailPacket(new FixedEmailPacket());
        firm.setEmailSenderApp(new EmailSender());
        MailDTO testEmail = new MailDTO("test@hotmail.com","testing...");
        
        // When
        firm.sendEmail(testEmail);
    }
    
}

/*

Language lang = new English();
        Service serv = new Service();
        ArrayList<User> samplePostGroup = new ArrayList();
        samplePostGroup.add(new User("Ugr","Kskn","test@hotmail.com","+9201930",new English()));
        samplePostGroup.add(new User("Ufk","Kskn","test2@hotmail.com","+9201932",new English()));
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(5,0));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FixedEmailPacket());
        firm.setEmailSenderApp(new EmailSender());
        firm.setPostGroup(samplePostGroup);
        MailDTO testEmail = new MailDTO("testing...");
        //Check
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(lang.BlackListExceptionMessage());

*/
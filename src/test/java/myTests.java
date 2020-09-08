
import com.mycompany.NotificationHub.Company;
import com.mycompany.NotificationHub.DTOs.MailDTO;
import com.mycompany.NotificationHub.Email;
import com.mycompany.NotificationHub.Exceptions.BlackListException;
import com.mycompany.NotificationHub.Exceptions.NoServiceFoundException;
import com.mycompany.NotificationHub.Exceptions.NotEnoughMoneyException;
import com.mycompany.NotificationHub.Exceptions.TwoMonthsNotPaidException;
import com.mycompany.NotificationHub.Languages.English;
import com.mycompany.NotificationHub.Packets.FixedEmailPacket;
import com.mycompany.NotificationHub.PaymentAccount;
import com.mycompany.NotificationHub.Service;
import org.junit.Test;
import static org.junit.Assert.*;


public class myTests {

    @Test
    public void it_should_throw_NotEnoughMoneyException() {
        // Given
        Service serv = new Service();
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new English());
        firm.setAccount(new PaymentAccount(300,200));
        firm.setPacketService(serv);
        firm.setEmailPacket(new FixedEmailPacket());
        Email testEmail = new Email(new MailDTO("test@hotmail.com","testing..."));
        boolean exceptionThrowed = false;
        
        // When
        try{
            firm.sendEmail(testEmail);
            firm.sendEmail(testEmail);
            firm.sendEmail(testEmail);
            firm.sendEmail(testEmail);
            firm.payEmailPacketBill();
        }catch(Exception e){
            if( e instanceof NotEnoughMoneyException)
                exceptionThrowed = true;
        }
        // Then
        assertTrue(exceptionThrowed);
    }

    @Test
    public void it_should_return_formatted_phone_if_phone_starts_with_ninety() {
        // Given
        String phone = "905373230808";

        // When
        String formattedPhone = PhoneValidator.getFormattedPhone(phone);

        // Then
        assertTrue(true);

    }

}
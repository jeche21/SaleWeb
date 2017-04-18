package es.sidelab.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.MailSender;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@RestController
public class RestControllerMail {

    final String fromEmail = "salewebdad@gmail.com";
    final RespuestaEmail emailSentSuccesfully = new RespuestaEmail(true,"Your email was succesfully sent!");
    final RespuestaEmail emailNotValid = new RespuestaEmail(false,"The email address you provided isn't valid");


    @Autowired
    private JavaMailSender mail;

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public ResponseEntity<RespuestaEmail> sendEmail(@RequestParam("email") String email,@RequestParam("subject") String subject, @RequestParam("body") String body) {
        if (!validAddress(email)) {
            return new ResponseEntity<>(this.emailNotValid, HttpStatus.BAD_REQUEST);
        }
        try {
            this.buildEmailAndSend(email,this.fromEmail,subject,body);
            return new ResponseEntity<>(this.emailSentSuccesfully, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            RespuestaEmail errorResponse = new RespuestaEmail(false,e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static boolean validAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public synchronized void buildEmailAndSend(String toAddress, String fromAddress, String subject, String msgBody) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(msgBody);
        mail.send(message);
    }


}

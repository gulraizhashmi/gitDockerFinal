package testscripts;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.zeroturnaround.zip.ZipUtil;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import static ReportsConfiguration.ExtentManager.reportFileLocation;
import static ReportsConfiguration.ExtentManager.reportFilepath;


public class firstTest {
    public static RemoteWebDriver driver;
    @Test
    public void testOne() throws MalformedURLException {
        SoftAssert softAssert = new SoftAssert();
        System.out.println(System.getenv("HUB_HOST"));
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        dc.setCapability("takesScreenshot", true);
        URL url= new URL("http://localhost:4444/wd/hub");
        driver = new RemoteWebDriver(url,dc);
        driver.get("http://www.google.com");
        System.out.println("Title of trehe page: "+driver.getTitle());
        softAssert.assertTrue(driver.getTitle().equals("Browserstack"), "First soft assert failed");
        softAssert.assertAll();
        driver.quit();
        //sendMail();
    }
    public static void sendMail() {

        // Create object of Property file
        Properties props = new Properties();

        // this will set host of server- you can change based on your requirement
        props.put("mail.smtp.host", "smtp.gmail.com");

        // set the port of socket factory
        props.put("mail.smtp.socketFactory.port", "465");

        // set socket factory
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

        // set the authentication to true
        props.put("mail.smtp.auth", "true");

        // set the port of SMTP server
        props.put("mail.smtp.port", "465");

        // This will handle the complete authentication
        Session session = Session.getDefaultInstance(props,

                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication("gulraiz.hashmi@venturedive.com", "wecoeyjseyilabfe");

                    }

                });

        try {

            // Create object of MimeMessage class
            Message message = new MimeMessage(session);

            // Set the from address
            message.setFrom(new InternetAddress("gulraiz.hashmi@venturedive.com"));

            // Set the recipient address
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("gulraiz.hashmi@venturedive.com"));

            // Add the subject link
            message.setSubject("Testing Subject");

            // Create object to add multimedia type content
            BodyPart messageBodyPart1 = new MimeBodyPart();

            // Set the body of email
            messageBodyPart1.setText("This is message body");

            // Create another object to add another content
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();

            // Mention the file which you want to send
            //ZipUtil.pack(new File(reportFilepath), new File(reportFilepath+"/hello.zip"));
            //String filename = reportFilepath+"/hello.zip";
            String filename = reportFileLocation;
            // Create data source and pass the filename
            DataSource source = new FileDataSource(filename);

            // set the handler
            messageBodyPart2.setDataHandler(new DataHandler(source));

            // set the file
            messageBodyPart2.setFileName(filename);

            // Create object of MimeMultipart class
            Multipart multipart = new MimeMultipart();

            // add body part 1
            multipart.addBodyPart(messageBodyPart2);

            // add body part 2
            multipart.addBodyPart(messageBodyPart1);

            // set the content
            message.setContent(multipart);

            // finally send the email
            Transport.send(message);

            System.out.println("=====Email Sent=====");

        } catch (MessagingException e) {

            throw new RuntimeException(e);

        }

    }

}

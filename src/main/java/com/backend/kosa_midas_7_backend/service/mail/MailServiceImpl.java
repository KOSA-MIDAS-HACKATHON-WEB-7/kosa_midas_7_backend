package com.backend.kosa_midas_7_backend.service.mail;

import com.backend.kosa_midas_7_backend.entity.EmailAuthCode;
import com.backend.kosa_midas_7_backend.entity.dto.EmailAuthDto;
import com.backend.kosa_midas_7_backend.repository.EmailAuthCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    private final EmailAuthCodeRepository authTokenRepository;

    @Override
    public MimeMessage createAuthMessage(String name, String email) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        String code = UUID.randomUUID().toString().substring(0, 7);
        EmailAuthCode authToken = createAuthToken(email, code);
        EmailAuthCode token = authTokenRepository.findByEmail(email);

        if (token == null) {
            authTokenRepository.save(authToken);
        } else {
            authToken.setId(token.getId());
            authTokenRepository.save(authToken);
        }

        message.setFrom("nicenicnic123@naver.com");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("마이다스 헤커톤 인증코드");
        message.setContent("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "  <title>HTML Email Template</title>\n" +
                "</head>\n" +
                "<body style=\" margin: 0; padding: 0; position: absolute; width: 100% !important; height: 100% !important; margin: 0; padding: 0; color: rgb(0, 0, 0);\">\n" +
                "<table id=\"bodyTable\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" style=\" width: 100% !important; height: 100% !important; margin: 0; padding: 0; padding: 20px 0 30px 0; background-color: #ffffff;\">\n" +
                "<tr>\n" +
                "   <td>\n" +
                "      <table id=\"subTable\" cellpaddinng=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\n" +
                "      <tr>\n" +
                "         <td>\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "            <tr>\n" +
                "               <td>\n" +
                "               </td>   \n" +
                "            </tr>\n" +
                "            </table>\n" +
                "         </td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "         <td>\n" +
                "            <table id=\"mainTable\" cellpadding=\"0\" cellspacing=\"0\" width=\"600px\" style=\" margin: 0 auto; height: 300px; border: 1px solid rgba(70, 70, 70, 0.16); border-radius: 15px;vertical-align: middle; \">\n" +
                "            <tr style=\"display: block; padding: 0 calc((600px - 452px) / 2);\">\n" +
                "               <td class=\"title\" style=\"padding: 45px 0px 25px; font-size: 30px;\">\n" +
                "                  안녕하세요, <b>" + name + "</b>님\n" +
                "               </td>\n" +
                "            </tr>\n" +
                "            <tr style=\"display: block; padding: 0 calc((600px - 452px) / 2);\">\n" +
                "               <td class=\"subTitle\" style=\"font-size: 20px;\">\n" +
                "                  <b></b>이메일 인증코드입니다.\n" +
                "               </td>\n" +
                "            </tr style>\n" +
                "            <tr class=\"codeTr\" style=\"display: block; padding: 0 calc((600px - 452px) / 2); padding-top: 40px !important; padding-bottom: 20px !important;\">\n" +
                "               <td class=\"code\" style=\"width: 450px; height: 90px; font-size: 40px; font-weight: 900; border: 1px solid rgba(0, 0, 0, 0.05); text-align: center; vertical-align: middle; border-left: #ABA6EA 5px solid;\">\n" +
                "                  OOOOOOOO\n" +
                "               </td>\n" +
                "            </tr>\n" +
                "            <tr class=\"exprationTr\" style=\"display: block; padding: 0 calc((600px - 452px) / 2); text-align: center; padding: 0 calc((600px - 432px) / 2) !important; padding-bottom: 40px !important;\">\n" +
                "               <td class=\"expration\" style=\"width: 600px; color: rgba(0, 0, 0, 0.7); text-align: center; font-size: 12px; font-weight: 700;\">\n" +
                "                   해당 코드는 발송 시간으로부터 3분 뒤에 만료됩니다.\n" +
                "               </td>\n" +
                "            </tr>\n" +
                "      </table>\n" +
                "   </td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>", "text/html; charset=UTF-8");

        return message;
    }

    @Override
    public ResponseEntity<Boolean> checkAuthCode(String email, String code) {
        try {
            EmailAuthCode authToken = authTokenRepository.findByEmailAndExpirationDateAfterAndExpired(email, LocalDateTime.now(), false);
            if (authToken.getCode().equals(code)) {
                authToken.setTokenToUsed();
                authTokenRepository.save(authToken);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.CONFLICT);
            }
        } catch (Exception exception) {
            log.info("error: {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> sendAuthMail(EmailAuthDto emailAuthDto) throws Exception {
        try {
            String name = emailAuthDto.getName();
            String email = emailAuthDto.getEmail();
            MimeMessage mailMessage = createAuthMessage(name, email);

            javaMailSender.send(mailMessage);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MailException mailException) {
            mailException.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public EmailAuthCode createAuthToken(String email, String code) {
        EmailAuthCode emailAuthCode = new EmailAuthCode();
        emailAuthCode.setEmail(email);
        emailAuthCode.setCode(code);
        emailAuthCode.setExpirationDate(LocalDateTime.now().plusMinutes(3L));
        emailAuthCode.setExpired(false);
        return emailAuthCode;
    }

}

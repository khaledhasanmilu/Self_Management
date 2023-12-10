package com.self.management.self_management.Model;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Email;
import org.json.JSONArray;
import org.json.JSONObject;
public class OtpModel {
    MailjetClient client;
    MailjetRequest request;
    MailjetResponse response;
    // client = new MailjetClient(System.getenv("MJ_APIKEY_PUBLIC"), System.getenv("MJ_APIKEY_PRIVATE"));
   public void SendOtp(String otp , String email,String subject) throws MailjetSocketTimeoutException, MailjetException {
       client = new MailjetClient("d412e814a2fb4d1da73bf977abc5a5e7","ebf1a632e74b70a32a5beca878d774eb");
       request = new MailjetRequest(Email.resource)
               .property(Email.FROMEMAIL, "nurulhuda5801@gmail.com")
               .property(Email.FROMNAME, "Segmentation Fault")
               .property(Email.SUBJECT, subject)
               .property(Email.TEXTPART, "This is Your One Time OTP !! Dont sent it With Others!!")
               .property(Email.HTMLPART, "<b>"+otp+"</b>")
               .property(Email.RECIPIENTS, new JSONArray()
                       .put(new JSONObject()
                               .put("Email", email)));
       response = client.post(request);
       System.out.println(response.getStatus());
       System.out.println(response.getData());
   }
}

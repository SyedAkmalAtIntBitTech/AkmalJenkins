package email.mandrill;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageResponse {

    private String email;
    private String status;
    private String reject_reason;
    private String id;

    public MessageResponse(JSONObject object) throws JSONException {
        setEmail(object.getString("email"));
        setStatus(object.getString("status"));
        setId(object.getString("_id"));
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the reject_reason
     */
    public String getReject_reason() {
        return reject_reason;
    }

    /**
     * @param reject_reason the reject_reason to set
     */
    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}

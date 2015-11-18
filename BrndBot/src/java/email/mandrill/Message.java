package email.mandrill;

import email.mandrill.Attachment;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class Message {

    private String key;
    private String html;
    private String text;
    private String subject;
    private String from_email;
    private String from_name;
    private ArrayList<Recipient> messageTo;
    private String reply_to;
    private boolean important;
    private boolean track_opens;
    private boolean track_clicks;
    private boolean auto_text;
    private boolean auto_html;
    private boolean inline_css;
    private boolean url_strip_qs;
    private boolean preserve_recipients;
    private boolean view_content_link;
    private String bcc_address;
    private String tracking_domain;
    private String signing_domain;
    private String return_path_domain;
    private boolean merge;
    private String merge_language;
    private ArrayList<String> tags;
    private String subaccount;
    private HashMap<String, String> metadata;
    private ArrayList<RecipientMetadata> recipient_metadata;
    private ArrayList<Attachment> attachments;
    private ArrayList<Attachment> images;
    private boolean async;
    private String ip_pool;
    private String send_at;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /* to track open rate for email sent */
    public Message() {
       track_opens = true;
       track_clicks = true;
    }

    /**
     * @return the html
     */
    public String getHtml() {
        return html;
    }

    /**
     * @param html the html to set
     */
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the from_email
     */
    public String getFrom_email() {
        return from_email;
    }

    /**
     * @param from_email the from_email to set
     */
    public void setFrom_email(String from_email) {
        this.from_email = from_email;
    }

    /**
     * @return the from_name
     */
    public String getFrom_name() {
        return from_name;
    }

    /**
     * @param from_name the from_name to set
     */
    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    /**
     * @return the messageTo
     */
    public ArrayList<Recipient> getMessageTo() {
        return messageTo;
    }

    /**
     * @param messageTo the messageTo to set
     */
    public void setMessageTo(ArrayList<Recipient> messageTo) {
        this.messageTo = messageTo;
    }

    /**
     * @return the important
     */
    public boolean isImportant() {
        return important;
    }

    /**
     * @param important the important to set
     */
    public void setImportant(boolean important) {
        this.important = important;
    }

    /**
     * @return the track_opens
     */
    public boolean getTrack_opens() {
        return track_opens;
    }

    /**
     * @param track_opens the track_opens to set
     */
    public void setTrack_opens(boolean track_opens) {
        this.track_opens = track_opens;
    }

    /**
     * @return the track_clicks
     */
    public boolean getTrack_clicks() {
        return track_clicks;
    }

    /**
     * @param track_clicks the track_clicks to set
     */
    public void setTrack_clicks(boolean track_clicks) {
        this.track_clicks = track_clicks;
    }

    /**
     * @return the auto_text
     */
    public boolean getAuto_text() {
        return auto_text;
    }

    /**
     * @param auto_text the auto_text to set
     */
    public void setAuto_text(boolean auto_text) {
        this.auto_text = auto_text;
    }

    /**
     * @return the auto_html
     */
    public boolean getAuto_html() {
        return auto_html;
    }

    /**
     * @param auto_html the auto_html to set
     */
    public void setAuto_html(boolean auto_html) {
        this.auto_html = auto_html;
    }

    /**
     * @return the inline_css
     */
    public boolean getInline_css() {
        return inline_css;
    }

    /**
     * @param inline_css the inline_css to set
     */
    public void setInline_css(boolean inline_css) {
        this.inline_css = inline_css;
    }

    /**
     * @return the url_strip_qs
     */
    public boolean getUrl_strip_qs() {
        return url_strip_qs;
    }

    /**
     * @param url_strip_qs the url_strip_qs to set
     */
    public void setUrl_strip_qs(boolean url_strip_qs) {
        this.url_strip_qs = url_strip_qs;
    }

    /**
     * @return the preserve_recipients
     */
    public boolean getPreserve_recipients() {
        return preserve_recipients;
    }

    /**
     * @param preserve_recipients the preserve_recipients to set
     */
    public void setPreserve_recipients(boolean preserve_recipients) {
        this.preserve_recipients = preserve_recipients;
    }

    /**
     * @return the view_content_link
     */
    public boolean getView_content_link() {
        return view_content_link;
    }

    /**
     * @param view_content_link the view_content_link to set
     */
    public void setView_content_link(boolean view_content_link) {
        this.view_content_link = view_content_link;
    }

    /**
     * @return the bcc_address
     */
    public String getBcc_address() {
        return bcc_address;
    }

    /**
     * @param bcc_address the bcc_address to set
     */
    public void setBcc_address(String bcc_address) {
        this.bcc_address = bcc_address;
    }

    /**
     * @return the tracking_domain
     */
    public String getTracking_domain() {
        return tracking_domain;
    }

    /**
     * @param tracking_domain the tracking_domain to set
     */
    public void setTracking_domain(String tracking_domain) {
        this.tracking_domain = tracking_domain;
    }

    /**
     * @return the signing_domain
     */
    public String getSigning_domain() {
        return signing_domain;
    }

    /**
     * @param signing_domain the signing_domain to set
     */
    public void setSigning_domain(String signing_domain) {
        this.signing_domain = signing_domain;
    }

    /**
     * @return the return_path_domain
     */
    public String getReturn_path_domain() {
        return return_path_domain;
    }

    /**
     * @param return_path_domain the return_path_domain to set
     */
    public void setReturn_path_domain(String return_path_domain) {
        this.return_path_domain = return_path_domain;
    }

    /**
     * @return the merge
     */
    public boolean isMerge() {
        return merge;
    }

    /**
     * @param merge the merge to set
     */
    public void setMerge(boolean merge) {
        this.merge = merge;
    }

    /**
     * @return the merge_language
     */
    public String getMerge_language() {
        return merge_language;
    }

    /**
     * @param merge_language the merge_language to set
     */
    public void setMerge_language(String merge_language) {
        this.merge_language = merge_language;
    }

    /**
     * @return the tags
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    /**
     * @return the subaccount
     */
    public String getSubaccount() {
        return subaccount;
    }

    /**
     * @param subaccount the subaccount to set
     */
    public void setSubaccount(String subaccount) {
        this.subaccount = subaccount;
    }

    /**
     * @return the metadata
     */
    public HashMap<String, String> getMetadata() {
        return metadata;
    }

    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(HashMap<String, String> metadata) {
        this.metadata = metadata;
    }

    /**
     * @return the recipient_metadata
     */
    public ArrayList<RecipientMetadata> getRecipient_metadata() {
        return recipient_metadata;
    }

    /**
     * @param recipient_metadata the recipient_metadata to set
     */
    public void setRecipient_metadata(
            ArrayList<RecipientMetadata> recipient_metadata) {
        this.recipient_metadata = recipient_metadata;
    }

    /**
     * @return the attachments
     */
    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    /**
     * @param attachments the attachments to set
     */
    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    /**
     * @return the images
     */
    public ArrayList<Attachment> getImages() {
        return images;
    }

    /**
     * @param images the images to set
     */
    public void setImages(ArrayList<Attachment> images) {
        this.images = images;
    }

    /**
     * @return the async
     */
    public boolean isAsync() {
        return async;
    }

    /**
     * @param async the async to set
     */
    public void setAsync(boolean async) {
        this.async = async;
    }

    /**
     * @return the ip_pool
     */
    public String getIp_pool() {
        return ip_pool;
    }

    /**
     * @param ip_pool the ip_pool to set
     */
    public void setIp_pool(String ip_pool) {
        this.ip_pool = ip_pool;
    }

    /**
     * @return the send_at
     */
    public String getSend_at() {
        return send_at;
    }

    /**
     * @param send_at the send_at to set
     */
    public void setSend_at(String send_at) {
        this.send_at = send_at;
    }

    /**
     * @return the reply_to
     */
    public String getReply_to() {
        return reply_to;
    }

    /**
     * @param reply_to the reply_to to set
     */
    public void setReply_to(String reply_to) {
        this.reply_to = reply_to;
    }

    public String toJSONString() throws Exception {

        JSONObject messageDetail = new JSONObject();
        messageDetail.put("html", getHtml());
        messageDetail.put("text", getText());
        messageDetail.put("subject", getSubject());
        messageDetail.put("from_email", getFrom_email());
        messageDetail.put("from_name", getFrom_name());
        messageDetail.put("to", getMessageTo());

        JSONObject replyToObject = new JSONObject();
        replyToObject.put("Reply-To", getReply_to());

        messageDetail.put("headers", replyToObject);
        messageDetail.put("important", isImportant());
        messageDetail.put("track_opens", getTrack_opens());
        messageDetail.put("track_clicks", getTrack_clicks());
        messageDetail.put("auto_text", getAuto_text());
        messageDetail.put("auto_html", getAuto_html());
        messageDetail.put("inline_css", getInline_css());
        messageDetail.put("url_strip_qs", getUrl_strip_qs());
        messageDetail.put("preserve_recipients", getPreserve_recipients());
        messageDetail.put("view_content_link", getView_content_link());
        messageDetail.put("bcc_address", getBcc_address());
        messageDetail.put("tracking_domain", getTracking_domain());
        messageDetail.put("return_path_domain", getReturn_path_domain());
        messageDetail.put("merge", isMerge());
        messageDetail.put("tags", getTags());
        messageDetail.put("subaccount", getSubaccount());

        messageDetail.put("metadata", getMetadata());
        messageDetail.put("recipient_metadata", getRecipient_metadata());
        messageDetail.put("attachments", getAttachments());
        messageDetail.put("images", getImages());

        messageDetail.put("async", isAsync());
        messageDetail.put("ip_pool", getIp_pool());
        messageDetail.put("send_at", getSend_at());

        JSONObject object = new JSONObject();
        object.put("key", key);
        object.put("message", messageDetail);

        return object.toString();
    }

}

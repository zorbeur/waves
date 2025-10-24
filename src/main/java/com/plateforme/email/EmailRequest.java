package com.plateforme.email;

import java.util.List;
import java.util.Map;

public class EmailRequest {
    private String to;
    private String subject;
    private String template;
    private Map<String, Object> variables;
    private List<EmailAttachment> attachments;

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getTemplate() { return template; }
    public void setTemplate(String template) { this.template = template; }
    public Map<String, Object> getVariables() { return variables; }
    public void setVariables(Map<String, Object> variables) { this.variables = variables; }
    public List<EmailAttachment> getAttachments() { return attachments; }
    public void setAttachments(List<EmailAttachment> attachments) { this.attachments = attachments; }
}

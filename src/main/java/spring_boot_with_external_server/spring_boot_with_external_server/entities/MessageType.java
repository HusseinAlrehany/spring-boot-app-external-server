package spring_boot_with_external_server.spring_boot_with_external_server.entities;

public class MessageType {

    private int id;
    private String content;

    public MessageType(){}

    public MessageType(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

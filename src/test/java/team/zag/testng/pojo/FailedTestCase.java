package team.zag.testng.pojo;

public class FailedTestCase extends ExecutedTestCase {
    private String messageThrown;

    public String getMessageThrown() {
        return messageThrown;
    }

    public void setMessageThrown(String messageThrown) {
        this.messageThrown = messageThrown;
    }

    @Override
    public String toString() {
        return "FailedTestCase{" +
                "messageThrown='" + messageThrown + '\'' +
                "} " + super.toString();
    }
}

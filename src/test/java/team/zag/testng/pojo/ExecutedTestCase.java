package team.zag.testng.pojo;

import java.util.Map;

public class ExecutedTestCase extends BaseTestCase {
    private String sessionId;
    private long tookInMs;
    private Map<String, String> parameters;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getTookInMs() {
        return tookInMs;
    }

    public void setTookInMs(long tookInMs) {
        this.tookInMs = tookInMs;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "ExecutedTestCase{" +
                "sessionId='" + sessionId + '\'' +
                ", tookInMs=" + tookInMs +
                ", parameters=" + parameters +
                "} " + super.toString();
    }
}

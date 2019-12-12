package team.zag.testng.pojo;

import java.util.Date;
import java.util.List;

public class TestCase {

    private String name;
    private long durationInMs;
    private Date startedAt;
    private Date finishedAt;
    private List<BaseTestCase> skipped;
    private List<ExecutedTestCase> passed;
    private List<FailedTestCase> failed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDurationInMs() {
        return durationInMs;
    }

    public void setDurationInMs(long durationInMs) {
        this.durationInMs = durationInMs;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }


    public List<BaseTestCase> getSkipped() {
        return skipped;
    }

    public void setSkipped(List<BaseTestCase> skipped) {
        this.skipped = skipped;
    }

    public List<ExecutedTestCase> getPassed() {
        return passed;
    }

    public void setPassed(List<ExecutedTestCase> passed) {
        this.passed = passed;
    }

    public List<FailedTestCase> getFailed() {
        return failed;
    }

    public void setFailed(List<FailedTestCase> failed) {
        this.failed = failed;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "name='" + name + '\'' +
                ", durationInMs=" + durationInMs +
                ", startedAt=" + startedAt +
                ", finishedAt=" + finishedAt +
                ", skipped=" + skipped +
                ", passed=" + passed +
                ", failed=" + failed +
                '}';
    }
}

package jp.tokyo.leon.mybatis.entity;

/**
 * @author leon
 * @date 2024/2/5 21:36
 */
public class Log {

    public Log() {
        System.out.println("22222");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Log(Integer id, String log, String time) {
        this.id = id;
        this.log = log;
        this.time = time;
    }

    private Integer id;

    private String log;

    private String time;

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", log='" + log + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

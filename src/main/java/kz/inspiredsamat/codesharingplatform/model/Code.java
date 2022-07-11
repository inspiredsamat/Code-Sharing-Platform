package kz.inspiredsamat.codesharingplatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "code")
public class Code {

    @JsonIgnore
    @Id
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "time")
    private long time;

    @Column(name = "view")
    private int views;

    private boolean isRestrictedByTime;

    private boolean isRestrictedByViews;

    @JsonIgnore
    private LocalTime expirationTime;

    public Code() {
    }

    public Code(String code, long time, int views) {
        this.id = UUID.randomUUID().toString();
        this.code = code;
        this.date = LocalDateTime.now();
        this.time = time <= 0 ? 0 : time;
        this.views = views <= 0 ? 0 : views;
        this.isRestrictedByTime = time > 0;
        this.isRestrictedByViews = views > 0;
        expirationTime = date.toLocalTime().plusSeconds(time);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "Code{" +
                "code='" + code + '\'' +
                ", time=" + time +
                ", view=" + views +
                '}';
    }

    public LocalTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    @JsonIgnore
    public boolean isRestrictedByTime() {
        return isRestrictedByTime;
    }

    public void setRestrictedByTime(boolean restrictedByTime) {
        isRestrictedByTime = restrictedByTime;
    }

    @JsonIgnore
    public boolean isRestrictedByViews() {
        return isRestrictedByViews;
    }

    public void setRestrictedByViews(boolean restrictedByViews) {
        isRestrictedByViews = restrictedByViews;
    }
}
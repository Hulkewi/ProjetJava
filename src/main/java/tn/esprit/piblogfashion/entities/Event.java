/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.piblogfashion.entities;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Event {
    private int event_id;
    private Date event_date;
    private String event_address;
    private String event_title;
    private String event_desc;
    private String event_pic;

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public String getEvent_address() {
        return event_address;
    }

    public void setEvent_address(String event_address) {
        this.event_address = event_address;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }

    public String getEvent_pic() {
        return event_pic;
    }

    public void setEvent_pic(String event_pic) {
        this.event_pic = event_pic;
    }

    public Event(Date event_date, String event_address, String event_title, String event_desc, String event_pic) {
        this.event_date = event_date;
        this.event_address = event_address;
        this.event_title = event_title;
        this.event_desc = event_desc;
        this.event_pic = event_pic;
    }

}

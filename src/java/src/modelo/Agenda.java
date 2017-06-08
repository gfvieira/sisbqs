/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.modelo;

public class Agenda {
    public int id;
    public String title;
    public String start;
    public String end;
    public String backgroundColor;
    private String user;
    private String ip;
    private String dataini;
    private String horaini;
    private String datater;
    private String horater;
    
    public String getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(String color) {
        this.backgroundColor = color;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the dataini
     */
    public String getDataini() {
        return dataini;
    }

    /**
     * @param dataini the dataini to set
     */
    public void setDataini(String dataini) {
        this.dataini = dataini;
    }

    /**
     * @return the horaini
     */
    public String getHoraini() {
        return horaini;
    }

    /**
     * @param horaini the horaini to set
     */
    public void setHoraini(String horaini) {
        this.horaini = horaini;
    }

    /**
     * @return the datater
     */
    public String getDatater() {
        return datater;
    }

    /**
     * @param datater the datater to set
     */
    public void setDatater(String datater) {
        this.datater = datater;
    }

    /**
     * @return the horater
     */
    public String getHorater() {
        return horater;
    }

    /**
     * @param horater the horater to set
     */
    public void setHorater(String horater) {
        this.horater = horater;
    }
 
}
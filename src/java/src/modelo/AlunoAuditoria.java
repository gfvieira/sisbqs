/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.modelo;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author root
 */
public class AlunoAuditoria {

    private int id;
    private String matricula;
    private String descAuditoria;
    private String useralt;
    private Date dataalt;
    private LocalTime horaalt;
    private String ipalt;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the descAuditoria
     */
    public String getDescAuditoria() {
        return descAuditoria;
    }

    /**
     * @param descAuditoria the descAuditoria to set
     */
    public void setDescAuditoria(String descAuditoria) {
        this.descAuditoria = descAuditoria;
    }

    /**
     * @return the useralt
     */
    public String getUseralt() {
        return useralt;
    }

    /**
     * @param useralt the useralt to set
     */
    public void setUseralt(String useralt) {
        this.useralt = useralt;
    }

    /**
     * @return the dataalt
     */
    public Date getDataalt() {
        return dataalt;
    }

    /**
     * @param dataalt the dataalt to set
     */
    public void setDataalt(Date dataalt) {
        this.dataalt = dataalt;
    }

    /**
     * @return the horaalt
     */
    public LocalTime getHoraalt() {
        return horaalt;
    }

    /**
     * @param horaalt the horaalt to set
     */
    public void setHoraalt(LocalTime horaalt) {
        this.horaalt = horaalt;
    }

    /**
     * @return the ipalt
     */
    public String getIpalt() {
        return ipalt;
    }

    /**
     * @param ipalt the ipalt to set
     */
    public void setIpalt(String ipalt) {
        this.ipalt = ipalt;
    }

}

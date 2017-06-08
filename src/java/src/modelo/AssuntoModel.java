/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.modelo;

/**
 *
 * @author root
 */
public class AssuntoModel {
    
    private int id;
    private String name;
    private String codigoSimulado;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the codigoSimulado
     */
    public String getCodigoSimulado() {
        return codigoSimulado;
    }

    /**
     * @param codigoSimulado the codigoSimulado to set
     */
    public void setCodigoSimulado(String codigoSimulado) {
        this.codigoSimulado = codigoSimulado;
    }
    
}

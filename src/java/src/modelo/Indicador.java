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
public class Indicador {
    
    private String disciplina;
    private int acerto;
    private int erro;
    private int duvida;

    /**
     * @return the disciplina
     */
    public String getDisciplina() {
        return disciplina;
    }

    /**
     * @param disciplina the disciplina to set
     */
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * @return the acerto
     */
    public int getAcerto() {
        return acerto;
    }

    /**
     * @param acerto the acerto to set
     */
    public void setAcerto(int acerto) {
        this.acerto = acerto;
    }

    /**
     * @return the erro
     */
    public int getErro() {
        return erro;
    }

    /**
     * @param erro the erro to set
     */
    public void setErro(int erro) {
        this.erro = erro;
    }

    /**
     * @return the duvida
     */
    public int getDuvida() {
        return duvida;
    }

    /**
     * @param duvida the duvida to set
     */
    public void setDuvida(int duvida) {
        this.duvida = duvida;
    }
    
}

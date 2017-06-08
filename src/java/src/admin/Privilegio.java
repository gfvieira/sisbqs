package src.admin;

import src.modelo.Usuario;

public class Privilegio {

    public Usuario getPri(Usuario usuario) {
        if (usuario.getTypeAccess().equals("ADMIN")) {
            usuario.setPri("ADMIN");
        } else if (usuario.getTypeAccess().equals("IDENTIFICADOR")) {
            usuario.setPri("IDENTIFICADOR");
        } else if (usuario.getTypeAccess().equals("TOTAL")
                || usuario.getTypeAccess().equals("TOTAL")
                || usuario.getTypeAccess().equals("BH05")) {
            usuario.setPri("TOTAL");
        } else if (usuario.getTypeAccess().equals("BH04")) {
            usuario.setPri("BH04");
        }  else if (usuario.getTypeAccess().equals("BH08")) {
            usuario.setPri("BH08");
        } else if (usuario.getTypeAccess().equals("CHEFEBH10")
                || usuario.getTypeAccess().equals("NACIONAL")
                || usuario.getTypeAccess().equals("ESTRANGEIRA")
                || usuario.getTypeAccess().equals("FOLHAN")) {
            usuario.setPri("BH10");
        } else if (usuario.getTypeAccess().equals("CHEFEBH20")
                || usuario.getTypeAccess().equals("MUNICIAMENTO")
                || usuario.getTypeAccess().equals("ESTRANGEIRA")
                || usuario.getTypeAccess().equals("FOLHAN")) {
            usuario.setPri("BH25");
        } else if (usuario.getTypeAccess().equals("CHEFEBH30")) {
            usuario.setPri("BH30");
        } else if (usuario.getTypeAccess().equals("CHAPA")) {
            usuario.setPri("CHAPA");
        }else if (usuario.getTypeAccess().equals("ENCARREGADOBH31")
                || usuario.getTypeAccess().equals("PESSOAL")) {
            usuario.setPri("BH31");
        } else if (usuario.getTypeAccess().equals("ENCARREGADOBH32")
                || usuario.getTypeAccess().equals("ENCARREGADOBH32")
                || usuario.getTypeAccess().equals("ENCARREGADOBH32")
                || usuario.getTypeAccess().equals("ENCARREGADOBH32")) {
            usuario.setPri("BH32");
        } else if (usuario.getTypeAccess().equals("ENCARREGADOBH33")
                || usuario.getTypeAccess().equals("ENCARREGADOBH33")
                || usuario.getTypeAccess().equals("ENCARREGADOBH33")
                || usuario.getTypeAccess().equals("ENCARREGADOBH33")) {
            usuario.setPri("BH33");
        } else if (usuario.getTypeAccess().equals("ENCARREGADOBH34")
                || usuario.getTypeAccess().equals("SUPERVISORBH34")
                || usuario.getTypeAccess().equals("HARDWARE")
                || usuario.getTypeAccess().equals("REDE")
                || usuario.getTypeAccess().equals("LOTUS")
                || usuario.getTypeAccess().equals("TELEFONIA")
                || usuario.getTypeAccess().equals("ENCARREGADOBH34")) {
            usuario.setPri("BH34");
        } else if (usuario.getTypeAccess().equals("CHEFEBH40")
                || usuario.getTypeAccess().equals("CHEFEBH40")
                || usuario.getTypeAccess().equals("CHEFEBH40")
                || usuario.getTypeAccess().equals("CHEFEBH40")) {
            usuario.setPri("BH40");
        } else if (usuario.getTypeAccess().equals("CHEFEBH50")
                || usuario.getTypeAccess().equals("ENCARREGADOBH52")
                || usuario.getTypeAccess().equals("ENCARREGADOBH53")
                || usuario.getTypeAccess().equals("ELETRICA")
                || usuario.getTypeAccess().equals("AGUADA")
                || usuario.getTypeAccess().equals("REFRIGERACAO")
                || usuario.getTypeAccess().equals("CARPINTARIA")
                || usuario.getTypeAccess().equals("METALURGICA")
                || usuario.getTypeAccess().equals("GARAGEM")
                || usuario.getTypeAccess().equals("PREFEITURA")) {
            usuario.setPri("BH50");
        }
        return usuario;
    }
}

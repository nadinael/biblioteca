package br.ueg.biblio.accreditation;

import br.ueg.biblio.files.FileManager;
import br.ueg.biblio.model.Usuario;
import br.ueg.biblio.model.enumerated.TUsuario;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.omnifaces.util.Faces;

public class ControlePagina {

    private static Properties propriedades;

    public static boolean validarAcesso(Usuario usuarioLogado, String paginalAtuaString) {
        if (usuarioLogado.getTipo() == TUsuario.A) {
            if (paginalAtuaString.contains("form-estado")) {
                return false;
            }
        }
        List<String> permitidas = (List<String>) Faces.getSessionAttribute("paginas_permitidas");
        //    System.out.println("ATT " + paginalAtuaString);
        //   System.out.println("Paginas que este usuario pode acessar.");
        if (permitidas != null) {
            for (String permitida : permitidas) {
                //         System.out.println("  --  " + permitida + "  --  ");
                if (paginalAtuaString.contains(permitida)) {
                    return true;
                }
            }

        }
        return false;
    }

    public static List<String> carregarPermissoes(Usuario user) {
        List<String> permissoes = new ArrayList<>();
        //  System.out.println("IMPRIMINDO ELEMENTROS CHAVE DE PROPRIEDADES");
        propriedades = FileManager.getProperties("pages-config");
        //-------------
        if (propriedades != null) {
            Enumeration<?> e = propriedades.propertyNames();

            switch (user.getTipo()) {
                case A:
                    //      System.out.println("Você ADMIN".toUpperCase());
                    while (e.hasMoreElements()) {
                        String key = (String) e.nextElement();
                        //         System.out.println("  --  " + key + "  --  ");

                        if (key.startsWith("acesso.admin")) {
                            String uri = propriedades.getProperty(key);
                            //          System.out.println("adicionando a permissao: " + uri);

                            permissoes.add(uri);
                        }

                    }

                    break;
                case B:
                    while (e.hasMoreElements()) {
                        String key = (String) e.nextElement();

                        if (key.startsWith("acesso.bibliotecario")) {
                            String uri = propriedades.getProperty(key);
                            permissoes.add(uri);
                        }

                    }
                    break;
                case L:
                    while (e.hasMoreElements()) {

                        String key = (String) e.nextElement();
                        //   System.out.println("  --  " + key + "  --  ");

                        if (key.startsWith("acesso.leitor")) {
                            String uri = propriedades.getProperty(key);
                            permissoes.add(uri);
                        }
                    }
                    break;
                case S:
                    while (e.hasMoreElements()) {

                        String key = (String) e.nextElement();
                        if (key.startsWith("acesso.sistema")) {

                            String uri = propriedades.getProperty(key);
                            permissoes.add(uri);
                        }
                    }
                    break;
                default:
                    permissoes.add("/view/exception/acesso.jsf");
                //A, B, L;//s
                }
        }

        /*
                String valor = propriedades.getProperty(key);
                System.out.println("CHAVE: " + key + "VALOR: " + valor);*/
 /*while (properties.hasMoreElements()) {
        String key = (String) properties.nextElement();
        if (key.startWith("toplink") //pra filtrar caso haja mais propriedades no mesmo arquivo
        persistenceProperties.put(key, boundle.getString(key));
        }*/
        //  System.out.println("Permissoes adquiridas");
        permissoes.stream().forEach((p) -> {
            //   System.out.println(p);
        });

        return permissoes;
    }

    public static boolean isPublicPage(String paginaAtual) {
        List<String> publicas = new ArrayList<>();
        propriedades = FileManager.getProperties("pages-config");

        if (propriedades != null) {
            Enumeration<?> e = propriedades.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                if (key.startsWith("acesso.publico")) {
                    String uri = propriedades.getProperty(key);
                    //   System.out.println("adicionando a permissao: " + uri);
                    publicas.add(uri);
                }
            }

            if (!publicas.isEmpty()) {
                for (String publica : publicas) {
                    //   System.out.println("  --  " + publica + "  --  ");
                    if (paginaAtual.contains(publica)) {
                        return true;
                    }
                }
            }

        }

        return false;
    }

    public static Usuario usuarioDaSessao() {
        return (Usuario) Faces.getSessionAttribute("usuario_logado");
    }

}

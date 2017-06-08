package src.bqs.questao;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import src.dao.DaoQuestao;
import src.modelo.Questao;

public class CadQuestao extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private final int maxFileSize = 10000 * 1024;
    private final int maxMemSize = 10000 * 1024;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            Questao questao = new Questao();
            isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet upload</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<p>No file uploaded</p>");
                out.println("</body>");
                out.println("</html>");
                return;
            }
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);
            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File("/home/desenvolvimento/Área de Trabalho/sisbqs/web/bqs/questao/questao/"));
            //factory.setRepository(new File("/home/gustavo/Área de Trabalho/SIG-BASE/sigbase/web/secom/docs/"));
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // maximum file size to be uploaded.
            upload.setSizeMax(maxFileSize);

            try {
                // Parse the request to get file items.
                List fileItems = upload.parseRequest(request);
                // Process the uploaded file items
                Iterator i = fileItems.iterator();
                File file;
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        String contentType = fi.getContentType();
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();
                        filePath = "/home/desenvolvimento/Área de Trabalho/sisbqs/web/bqs/questao/questao/";
                        String filePathRelativo = "questao/";
                        // Write the file
                        String filePathName;
                        String filePathNameRelativo;
                        if (fileName.lastIndexOf("\\") >= 0) {
                            filePathName = filePath + "" + fileName.substring(fileName.lastIndexOf("\\"));
                            filePathNameRelativo = filePathRelativo + "" + fileName.substring(fileName.lastIndexOf("\\"));
                        } else {
                            filePathName = filePath + "" + fileName.substring(fileName.lastIndexOf("\\") + 1);
                            filePathNameRelativo = filePathRelativo + "" + fileName.substring(fileName.lastIndexOf("\\") + 1);
                        }
                        file = new File(filePathName);
                        fi.write(file);
                        switch (fieldName) {
                            case "filePerg":
                                questao.setPerguntaImg(filePathNameRelativo);
                                break;
                            case "fileRespA":
                                questao.setAImg(filePathNameRelativo);
                                break;
                            case "fileRespB":
                                questao.setBImg(filePathNameRelativo);
                                break;
                            case "fileRespC":
                                questao.setCImg(filePathNameRelativo);
                                break;
                            case "fileRespD":
                                questao.setDImg(filePathNameRelativo);
                                break;
                            case "fileRespE":
                                questao.setEImg(filePathNameRelativo);
                                break;
                            default:
                                break;
                        }
                    } else {
                        switch (fi.getFieldName()) {
                            case "disciplina":
                                questao.setDisciplina(Integer.parseInt(fi.getString()));
                                break;
                            case "assunto":
                                questao.setAssunto(Integer.parseInt(fi.getString()));
                                break;
                            case "origem":
                                questao.setOrigem(fi.getString().toUpperCase());
                                break;
                            case "TxtQuestao":
                                questao.setPergunta(fi.getString());
                                break;
                            case "A":
                                questao.setA(fi.getString());
                                break;
                            case "B":
                                questao.setB(fi.getString());
                                break;
                            case "C":
                                questao.setC(fi.getString());
                                break;
                            case "D":
                                questao.setD(fi.getString());
                                break;
                            case "E":
                                questao.setE(fi.getString());
                                break;
                            case "resposta1":
                                questao.setResposta1(fi.getString());
                                break;
                            case "resposta2":
                                questao.setResposta2(fi.getString());
                                break;
                            case "user":
                                questao.setUser(fi.getString());
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (FileUploadException | IOException | NumberFormatException ex) {
                Logger.getLogger(CadQuestao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(CadQuestao.class.getName()).log(Level.SEVERE, null, ex);
            }
            DaoQuestao daoquestao = new DaoQuestao();
            questao.setIp(request.getRemoteAddr());
            Timestamp tm = new Timestamp(System.currentTimeMillis());
            String da = new SimpleDateFormat("yyyydd").format(tm);
            String t = (da + new SimpleDateFormat("HHmmss").format(tm));
            questao.setCodigo(t);
            boolean check;
            Questao questaoTemp = daoquestao.buscaQuestao(questao.getCodigo());
            if (questaoTemp == null) {
                check = daoquestao.insereQuestao(questao);
                if (check == false) {
                    request.setAttribute("mensagem", "NO");
                } else {
                    check = daoquestao.insereAuditoriaQuestao(questao, "CADASTRO CONCLUIDO COM SUCESSO");
                    if (check == false) {
                        request.setAttribute("mensagem", "NO");
                    } else {
                        request.setAttribute("mensagem", "OK");
                    }
                }
            } else {
                request.setAttribute("mensagem", "NO");
            }
            RequestDispatcher rd = request.getRequestDispatcher("cadastroQuestao.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

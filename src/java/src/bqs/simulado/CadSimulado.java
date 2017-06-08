package src.bqs.simulado;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoSimulado;
import src.modelo.Questao;

public class CadSimulado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Map mapRequest = request.getParameterMap();
            Map.Entry entryRequest;
            Iterator iteratorRequest = mapRequest.entrySet().iterator();
            String key;
            String user = "";
            ArrayList<String> listQuestao = new ArrayList<>();
            while (iteratorRequest.hasNext()) {
                entryRequest = (Map.Entry) iteratorRequest.next();
                key = (String) entryRequest.getKey();
                for (int i = 1; i <= 50; i++) {
                    if (key.equals("pergunta" + i)) {
                        listQuestao.add(request.getParameterValues(key)[0]);
                    }
                }
                if (key.equals("user")) {
                    user = request.getParameterValues(key)[0];
                }
            }
            Random gerador = new Random();
            String numeroSimulado = "" + gerador.nextInt(2016112209);
            String ip = request.getRemoteAddr();
            DaoSimulado daosimulado = new DaoSimulado();
            boolean check = false;
            int count = 0;
            for (String questao : listQuestao) {
                check = daosimulado.insereSimulado(numeroSimulado, ++count, questao, user, ip);
            }
            if (check == true) {
                StringBuffer sbFilename = null;
                StringBuffer sbContentDispValue = null;
                Timestamp tm = null;
                DocumentException ex = null;
                ByteArrayOutputStream baosPDF = null;
                int i = 0;
                try {
                    baosPDF = generatePDFDocument(this.getServletContext(), numeroSimulado, listQuestao);
                    sbFilename = new StringBuffer();
                    sbFilename.append("MARE_");
                    tm = new Timestamp(System.currentTimeMillis());
                    String d = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(tm);
                    sbFilename.append(d);
                    sbFilename.append(".pdf");
                    response.setHeader("Cache-Control", "max-age=30");
                    response.setContentType("application/pdf");
                    sbContentDispValue = new StringBuffer();
                    sbContentDispValue.append("inline");
                    sbContentDispValue.append("; filename=");
                    sbContentDispValue.append(sbFilename);
                    response.setContentType("application/pdf");
                    response.setHeader("Content-disposition", sbContentDispValue.toString());
                    response.setContentLength(baosPDF.size());
                    ServletOutputStream sos;
                    sos = response.getOutputStream();
                    //resp.getOutputStream().write(baosPDF);
                    baosPDF.writeTo(sos);
                    sos.flush();
                } finally {
                    if (baosPDF != null) {
                        baosPDF.reset();
                    }
                }
            }
        } catch (DocumentException dex) {
            System.out.println("erro");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println(this.getClass().getName() + " caught an exception: "
                    + dex.getClass().getName() + "<br>");
            writer.println("<pr>");
            dex.printStackTrace(writer);
            writer.println("</pr>");
        }
    }

    protected ByteArrayOutputStream generatePDFDocument(
            final ServletContext ctx, String numeroSimulado, ArrayList<String> listQuestao)
            throws DocumentException {
        Document doc = new Document();
        ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
        PdfWriter docWriter = null;
        try {

            docWriter = PdfWriter.getInstance(doc, baosPDF);
            doc.addAuthor("Sample");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("Sample");
            doc.addTitle("SIMULADO_" + numeroSimulado);//nome do pdf
            doc.setPageSize(PageSize.A4);
            doc.open();
            String strServerInfo = ctx.getServerInfo();
            Font f = new Font(Font.FontFamily.COURIER, 14);
            Font f2 = new Font(Font.FontFamily.COURIER, 12);
            Font f3 = new Font(Font.FontFamily.COURIER, 10);
            Font f4 = new Font(Font.FontFamily.COURIER, 8);
            Paragraph p2 = new Paragraph("SIMULADO NUMERO: " + numeroSimulado, f2);
            p2.setAlignment(Element.ALIGN_RIGHT);
            p2.setSpacingAfter(20);
            doc.add(p2);
            Paragraph p3 = new Paragraph("ALUNO: _____________________________________   DATA: ___/___/______", f2);
            p3.setAlignment(Element.ALIGN_LEFT);
            p3.setSpacingAfter(20);
            doc.add(p3);
            Paragraph p4 = null;
            DaoSimulado daosimulado = new DaoSimulado();
            int count = 0;
            for (String question : listQuestao) {
                Questao questao = daosimulado.buscaQuestao(question);
                p4 = new Paragraph(++count + "- (" + questao.getOrigem() + "/Cod.:" + questao.getCodigo() + ") " + questao.getPergunta(), f2);
                p4.setAlignment(Element.ALIGN_LEFT);
                p4.setSpacingAfter(10);
                doc.add(p4);
                p4 = new Paragraph("A - " + questao.getA(), f2);
                p4.setAlignment(Element.ALIGN_LEFT);
                p4.setSpacingAfter(10);
                doc.add(p4);
                p4 = new Paragraph("B - " + questao.getB(), f2);
                p4.setAlignment(Element.ALIGN_LEFT);
                p4.setSpacingAfter(10);
                doc.add(p4);
                p4 = new Paragraph("C - " + questao.getC(), f2);
                p4.setAlignment(Element.ALIGN_LEFT);
                p4.setSpacingAfter(10);
                doc.add(p4);
                p4 = new Paragraph("D - " + questao.getD(), f2);
                p4.setAlignment(Element.ALIGN_LEFT);
                p4.setSpacingAfter(10);
                doc.add(p4);
                p4 = new Paragraph("E - " + questao.getE(), f2);
                p4.setAlignment(Element.ALIGN_LEFT);
                p4.setSpacingAfter(25);
                doc.add(p4);
            }//certeza ou duvida???

//            DaoMare daomare = new DaoMare();
//            ArrayList<Mares> mareObservada = null;
//            ArrayList<Mares> marePrevista = null;
//            ArrayList<ModeloGraficoItem> l = new ArrayList();
//            //aqui estra o if else para busca do tempo
//            mareObservada = daomare.buscaMare(data, 1, estacao);//observado
//            marePrevista = daomare.buscaMare(data, 2, estacao);//previsto
//            for (int i = 0; i < 24; i++) {
//                ModeloGraficoItem a = new ModeloGraficoItem();
//                ModeloGraficoItem b = new ModeloGraficoItem();
//                a.setHora("" + i);
//                b.setHora("" + i);
//                a.setValor(marePrevista.get(i).getValor());
//                b.setValor(mareObservada.get(i).getValor());
//                a.setTipoMare("Prevista");
//                b.setTipoMare("Observada");
//                l.add(a);
//                l.add(b);
//            }// testar o grafico com mais de 1 dia
//            BufferedImage imagem = GeradorGrafico.gerarGraficoLinha3D("", "Periodo", "Altura em cm", l);
//            Image image = Image.getInstance(imagem, null);
//            image.setAlignment(Element.ALIGN_CENTER);
//            doc.add(image);
            //criar uma classe que retorna a tabela
//            PdfPTable teste = new PdfPTable(13);
//            teste.setWidthPercentage(100);
//            teste.setSpacingAfter(30);
//            teste.setSpacingBefore(30);
//            PdfPCell cell = new PdfPCell(new Phrase("Altura da maré X Horario do dia: " + data));
//            cell.setColspan(13);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
////            cell.setBackgroundColor(BaseColor.GRAY);
//            teste.addCell(cell);
//            teste.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//            int a = 12, b = 0;
//            for (int k = 0; k < 2; k++) {
//                teste.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
//                teste.addCell(" ");
//                for (int i = b; i < a; i++) {
//                    String hora = "";
//                    if (i < 10) {
//                        hora = "0" + i + ":00";
//                    } else {
//                        hora = i + ":00";
//                    }
//                    teste.addCell(hora);
//                }
//                teste.getDefaultCell().setBackgroundColor(BaseColor.RED);
//                teste.addCell("Prv");
//                for (int i = b; i < a; i++) {
//                    teste.addCell("" + marePrevista.get(i).getValor());
//                }
//                teste.getDefaultCell().setBackgroundColor(BaseColor.BLUE);
//                teste.addCell("Obs");
//                for (int i = b; i < a; i++) {
//                    teste.addCell("" + mareObservada.get(i).getValor());
//                }
//                b = a;
//                a = a * 2;
//            }
//            doc.add(teste);
            doc.close();
        } catch (DocumentException dex) {
            baosPDF.reset();
            throw dex;
        } catch (Exception ex) {
            Logger.getLogger(CadSimulado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (docWriter != null) {
                docWriter.close();
            }
        }

        if (baosPDF.size() < 1) {
            throw new DocumentException("document has " + baosPDF.size()
                    + " bytes");
        }
        return baosPDF;
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
